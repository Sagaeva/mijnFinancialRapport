package be.sagaeva.financial.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseFilterDto {

    private String keyword;
    private String sortBy;
    private String startDate;
    private String endDate;
}
