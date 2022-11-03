package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
       model.addAttribute("expenses", expenseService.getAllExpenses());
        return "expenses-list";
    }

    @GetMapping("/createExpense")
    public String schowExpenseForm(Model model) {
        model.addAttribute("expense", new ExpenseDto());
        return "expense-form";
    }

    @PostMapping("/saveOrUpdateExpense")
    public String saveOrdateExpenseDetails(@ModelAttribute("expense") ExpenseDto expenseDto) throws ParseException {
        expenseService.saveExpenseDetails(expenseDto);
        return "redirect:/expenses";
    }



}
