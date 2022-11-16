package be.sagaeva.financial.manager.validator;

import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.IncomeDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class IncomeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return IncomeDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        IncomeDTO incomeDTO = (IncomeDTO) target;
        if(incomeDTO.getDateString().equals("")
                || incomeDTO.getDateString().isEmpty()
                || incomeDTO.getDateString() == null) {
            errors.rejectValue("dateString", null,
                    "Income date should not be null");
        }

    }
}
