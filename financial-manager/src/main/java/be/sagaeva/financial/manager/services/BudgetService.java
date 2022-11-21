

package be.sagaeva.financial.manager.services;

import be.sagaeva.financial.manager.data.Budget;
import be.sagaeva.financial.manager.data.Expense;
import be.sagaeva.financial.manager.data.Income;
import be.sagaeva.financial.manager.data.User;
import be.sagaeva.financial.manager.repositories.BudgetRepository;
import be.sagaeva.financial.manager.repositories.ExpenseRepository;
import be.sagaeva.financial.manager.repositories.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.startup.ExpandWar;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;
    private final IncomeRepository incomeRepository;
    private final BudgetRepository budgetRepository;

    private BigDecimal check(List<Income> incomes, List<Expense> expenses) {
        BigDecimal sumIncome = new BigDecimal(0);
        BigDecimal sumExpense = new BigDecimal(0);
        BigDecimal totalIncomes = incomes.stream().map(x -> x.getAmount().add(sumIncome))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalExpenses = expenses.stream().map(x -> x.getAmount().add(sumExpense))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal res = totalIncomes.subtract(totalExpenses);
            return res;
    }


    public void saveBudget(Budget budget) {
        User user = userService.getLoggedInUser();
        Long userId = user.getId();
        List<Income> income = incomeRepository.findByUserId(userId);
        List<Expense> expense = expenseRepository.findByUserId(userId);
        BigDecimal result = check(income, expense);
        BigDecimal test = new BigDecimal(0);
        if(result.compareTo(test) >= 0) {
            budget.setUser(user);
            budget.setSurplus(result);
            budgetRepository.save(budget);
        } else {
            budget.setUser(user);
            budget.setDeficit(result);
            budgetRepository.save(budget);
        }



    }

}










