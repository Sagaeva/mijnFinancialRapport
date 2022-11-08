package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.ExpenseFilterDto;
import be.sagaeva.financial.manager.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExpenseFilterController {

    private final ExpenseService expenseService;

    @GetMapping("/filterExpenses")
    public String filterExpenses(@ModelAttribute("filter") ExpenseFilterDto expenseFilterDto,
                                 Model model) throws ParseException {
       List<ExpenseDto> list =  expenseService
               .getFilteredExpense(expenseFilterDto);
       model.addAttribute("expenses", list);
        String totalExpenses = expenseService.totalExpenses(list);
        model.addAttribute("totalExpenses", totalExpenses);
        return "expenses-list";
    }
}
