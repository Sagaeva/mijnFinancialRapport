package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.data.Budget;
import be.sagaeva.financial.manager.dto.IncomeDTO;
import be.sagaeva.financial.manager.repositories.BudgetRepository;
import be.sagaeva.financial.manager.services.BudgetService;
import be.sagaeva.financial.manager.validator.IncomeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
@RequiredArgsConstructor
public class testController {

    private final BudgetService budgetService;

    @GetMapping("/test")
    public String getTest() {
        return "test";

    }

    /*
    @GetMapping("/createProficit")
    public String createBudget(Model model) {
        model.addAttribute("budget",  new Budget());
                return "test";
    }
    @GetMapping("/check")
    public String saveProficitOfDeficit(@ModelAttribute("budget") Budget budget
                                          ) {
        budgetService.saveBudget(budget);
        System.out.println("проверка" + budget);

        return "redirect:/test";
    }

     */

}
