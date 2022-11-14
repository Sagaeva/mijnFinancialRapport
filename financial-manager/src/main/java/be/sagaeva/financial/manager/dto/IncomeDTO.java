package be.sagaeva.financial.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTO {

    private Long id;
    private String incomeId;
    @NotBlank(message = "Income name should not be empty")
    @Size(min = 3, message = "Income name should be at least {min} characters")
    private String name;
    private String description;
    @NotNull(message = "Income amount should not be null")
    @Min(value = 1, message = "Income amount should not be less than 1")
    private BigDecimal amount;
    private Date date;
    private String dateString;

}
