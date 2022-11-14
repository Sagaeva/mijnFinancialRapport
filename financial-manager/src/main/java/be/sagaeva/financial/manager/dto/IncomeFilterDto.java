package be.sagaeva.financial.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeFilterDto {

    private String keyword;
    private String sortBy;
    private String startDate;
    private String endDate;

    public IncomeFilterDto(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;

    }
}
