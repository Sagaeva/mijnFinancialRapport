package be.sagaeva.financial.manager.repositories;

import be.sagaeva.financial.manager.data.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findByExpenseId(String id);
    List<Expense> findByNameContainingAndDateBetween(String keyword, Date startDate, Date andDate);
}
