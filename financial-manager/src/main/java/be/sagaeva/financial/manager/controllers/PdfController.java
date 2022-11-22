/*

package be.sagaeva.financial.manager.controllers;

import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.services.ExpenseService;

import be.sagaeva.financial.manager.services.PdfGenerateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PdfController {

    private ExpenseService expenseService;
    private PdfGenerateServiceImpl pdfGenerateService;



    @GetMapping("/getPdf")
    public String getPdf(){
        List<ExpenseDto> expenseDtoList = expenseService.getAllExpenses();
       return "expense-list";
    }
    @GetMapping("/createPdf")
    public String createPDF() {
        List<ExpenseDto> expenseDtoList = expenseService.getAllExpenses();
        pdfGenerateService.generatePdfFile("expenses-list", expenseDtoList, "expenses" );
        return "redirect:/getPdf";
    }
}

 */


