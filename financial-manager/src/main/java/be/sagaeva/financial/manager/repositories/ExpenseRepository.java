package be.sagaeva.financial.manager.repositories;

import be.sagaeva.financial.manager.data.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
