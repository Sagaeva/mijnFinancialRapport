package be.sagaeva.financial.manager.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_incomes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String incomeId;
    private String name;
    private String description;
    private BigDecimal amount;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

}
