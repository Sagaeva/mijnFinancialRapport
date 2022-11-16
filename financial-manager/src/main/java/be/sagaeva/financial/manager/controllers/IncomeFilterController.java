package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.ExpenseFilterDto;
import be.sagaeva.financial.manager.dto.IncomeDTO;
import be.sagaeva.financial.manager.dto.IncomeFilterDto;
import be.sagaeva.financial.manager.services.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IncomeFilterController {

    private final IncomeService incomeService;

    @GetMapping("/filterIncomes")
    public String filterIncomes(@ModelAttribute("filter") IncomeFilterDto incomeFilterDto,
                                 Model model) throws ParseException {
        List<IncomeDTO> list =  incomeService
                .getFilteredIncome(incomeFilterDto);
        model.addAttribute("incomes", list);
        String totalIncomes = incomeService.totalIncomes(list);
        model.addAttribute("totalIncomes", totalIncomes);
        return "incomes-list";
    }
}
