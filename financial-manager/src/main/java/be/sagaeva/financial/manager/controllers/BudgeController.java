package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.data.Budget;
import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.ExpenseFilterDto;
import be.sagaeva.financial.manager.dto.IncomeDTO;
import be.sagaeva.financial.manager.dto.IncomeFilterDto;
import be.sagaeva.financial.manager.services.BudgetService;
import be.sagaeva.financial.manager.services.ExpenseService;
import be.sagaeva.financial.manager.services.IncomeService;
import be.sagaeva.financial.manager.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BudgeController {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private final BudgetService budgetService;

    @GetMapping("/createProficit")
    public String createBudget(Model model) {
        model.addAttribute("budget",  new Budget());
        return "budget-form";
    }


    @GetMapping("/budget")
    public String showExpenseTotalAndIncomesTotal(Model model,
                                                  @ModelAttribute("budget") Budget budget) {
        List<ExpenseDto> listExpense = expenseService.getAllExpenses();
        List<IncomeDTO> listIncome = incomeService.getAllIncomes();
        model.addAttribute("expenses", listExpense);
        model.addAttribute("incomes", listIncome);
        String totalIncomes = incomeService.totalIncomes(listIncome);
        model.addAttribute("totalIncomes", totalIncomes);
        model.addAttribute("totalExpenses", expenseService.getAllExpenses());
        budgetService.saveBudget(budget);
        model.addAttribute("surplus", budget.getSurplus());
        model.addAttribute("deficit", budget.getDeficit());
        return "budget-form";

    }

}
