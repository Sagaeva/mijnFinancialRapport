package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.ExpenseFilterDto;
import be.sagaeva.financial.manager.services.ExpenseService;
import be.sagaeva.financial.manager.util.DateTimeUtil;
import be.sagaeva.financial.manager.validator.ExpenseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;



    @GetMapping("/expenses")
    public String showExpenseList(Model model) {
       List<ExpenseDto> list = expenseService.getAllExpenses();
       model.addAttribute("expenses", list);
       model.addAttribute("filter", new ExpenseFilterDto(DateTimeUtil
               .getCurrentMonthStartDate(), DateTimeUtil.getCurrentMonthDate()));
       String totalExpenses = expenseService.totalExpenses(list);
       model.addAttribute("totalExpenses", totalExpenses);
        return "expenses-list";
    }

    @GetMapping("/createExpense")
    public String schowExpenseForm(Model model) {
        model.addAttribute("expense", new ExpenseDto());
        return "expense-form";
    }

    @PostMapping("/saveOrUpdateExpense")
    public String saveOrdateExpenseDetails(@Valid @ModelAttribute("expense") ExpenseDto expenseDto,
                                           BindingResult result) throws ParseException {
        if(result.hasErrors()) {
            new ExpenseValidator().validate(expenseDto, result);
            return "expense-form";
        }
        expenseService.saveExpenseDetails(expenseDto);
        return "redirect:/expenses";
    }

    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam String id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

    @GetMapping("/updateExpense")
    public String updateExpense(@RequestParam String id, Model model) {
        ExpenseDto expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expense-form";
    }



}
