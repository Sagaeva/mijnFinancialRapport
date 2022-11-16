package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.ExpenseFilterDto;
import be.sagaeva.financial.manager.dto.IncomeDTO;
import be.sagaeva.financial.manager.dto.IncomeFilterDto;
import be.sagaeva.financial.manager.services.IncomeService;
import be.sagaeva.financial.manager.util.DateTimeUtil;
import be.sagaeva.financial.manager.validator.ExpenseValidator;
import be.sagaeva.financial.manager.validator.IncomeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @GetMapping("/incomes")
    public String showIncomeList(Model model) {
        List<IncomeDTO> list = incomeService.getAllIncomes();
        model.addAttribute("incomes", list);
        model.addAttribute("filter", new IncomeFilterDto(DateTimeUtil
                .getCurrentMonthStartDate(), DateTimeUtil.getCurrentMonthDate()));
        String totalIncomes = incomeService.totalIncomes(list);
        model.addAttribute("totalIncomes", totalIncomes);
        return "incomes-list";
    }

    @GetMapping("/createIncome")
    public String schowIncomeForm(Model model) {
        model.addAttribute("income", new IncomeDTO());
        return "income-form";
    }

    @PostMapping("/saveOrUpdateIncome")
    public String saveOrdateIncomeDetails(@Valid @ModelAttribute("income") IncomeDTO incomeDTO,
                                           BindingResult result) throws ParseException {
        if(result.hasErrors()) {
            new IncomeValidator().validate(incomeDTO, result);
            return "income-form";
        }
        incomeService.saveIncomeDetails(incomeDTO);
        return "redirect:/incomes";
    }

    @GetMapping("/deleteIncome")
    public String deleteIncome(@RequestParam String id) {
        incomeService.deleteIncome(id);
        return "redirect:/incomes";
    }

    @GetMapping("/updateIncome")
    public String updateIncome(@RequestParam String id, Model model) {
        IncomeDTO income = incomeService.getIncomeById(id);
        model.addAttribute("income", income);
        return "income-form";
    }



}
