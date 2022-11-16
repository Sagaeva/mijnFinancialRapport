package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.ExpenseFilterDto;
import be.sagaeva.financial.manager.dto.IncomeDTO;
import be.sagaeva.financial.manager.dto.IncomeFilterDto;
import be.sagaeva.financial.manager.services.ExpenseService;
import be.sagaeva.financial.manager.services.IncomeService;
import be.sagaeva.financial.manager.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BudgeController {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @GetMapping("/budget")
    public String showExpenseTotalAndIncomesTotal(Model model) {
        List<ExpenseDto> listExpense = expenseService.getAllExpenses();
        List<IncomeDTO> listIncome = incomeService.getAllIncomes();
        model.addAttribute("expenses", listExpense);
        model.addAttribute("incomes", listIncome);
        model.addAttribute("filterExpenses", new ExpenseFilterDto(DateTimeUtil
                .getCurrentMonthStartDate(), DateTimeUtil.getCurrentMonthDate()));
        model.addAttribute("filterIncomes", new IncomeFilterDto(DateTimeUtil
                .getCurrentMonthStartDate(), DateTimeUtil.getCurrentMonthDate()));
        String totalExpenses = expenseService.totalExpenses(listExpense);
        String totalIncomes = incomeService.totalIncomes(listIncome);
     //   model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("totalIncomes", totalIncomes);
        model.addAttribute("totalExpenses", expenseService.getAllExpenses());
        return "budget-form";



    }



}
