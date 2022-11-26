package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.data.Expense;
import be.sagaeva.financial.manager.data.User;
import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.ExpenseFilterDto;

import be.sagaeva.financial.manager.dto.IncomeDTO;
import be.sagaeva.financial.manager.services.ExpenseService;

import be.sagaeva.financial.manager.services.ExportPdfService;

import be.sagaeva.financial.manager.services.IncomeService;
import be.sagaeva.financial.manager.services.UserService;
import be.sagaeva.financial.manager.util.DateTimeUtil;
import be.sagaeva.financial.manager.validator.ExpenseValidator;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ExpenseController   {

    private final ExpenseService expenseService;
    private final ExportPdfService exportPdfService;
    private final UserService userService;
    private final IncomeService incomeService;


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
        if (result.hasErrors()) {
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



    @GetMapping("/createPdf")
    public void createPDF(HttpServletResponse response) throws IOException {
        Map<String, Object> data = createdData();
        ByteArrayInputStream exportedData = exportPdfService.exportPdf("expense", data);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=expense.pdf");
        IOUtils.copy(exportedData, response.getOutputStream());

    }

    private Map<String, Object> createdData() {
        Map<String, Object> data = new HashMap<>();
        List<ExpenseDto> expense = expenseService.getAllExpenses();
        List<IncomeDTO> income = incomeService.getAllIncomes();
        User user =  userService.getLoggedInUser();
        data.put("expenses", expense);
        data.put("user", user);
        data.put("incomes", income);
        return data;
    }



}
