package be.sagaeva.financial.manager.repositories;

import be.sagaeva.financial.manager.data.Expense;
import be.sagaeva.financial.manager.data.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    Optional<Income> findByIncomeId(String id);
    List<Income> findByNameContainingAndDateBetweenAndUserId(String keyword, Date startDate, Date andDate, Long id);
    List<Income> findByUserId(Long id);
    List<Income> findByDateBetweenAndUserId(Date startDate, Date endDate, Long id);

}
