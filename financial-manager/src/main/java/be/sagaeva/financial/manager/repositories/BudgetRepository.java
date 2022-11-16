package be.sagaeva.financial.manager.repositories;

import be.sagaeva.financial.manager.data.Budget;
import be.sagaeva.financial.manager.data.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {


    List<Budget> findByUserId(Long id);

}
