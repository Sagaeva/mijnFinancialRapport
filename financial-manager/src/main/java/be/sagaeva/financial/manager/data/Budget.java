package be.sagaeva.financial.manager.data;

import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.IncomeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_budget")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Expense> expenses;
    @OneToMany
    private List<Income> incomes;
    private BigDecimal surplus;
    private BigDecimal deficit;
    private Date date;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

}
