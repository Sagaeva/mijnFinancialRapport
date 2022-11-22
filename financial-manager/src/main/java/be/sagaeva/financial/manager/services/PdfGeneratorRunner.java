/*

package be.sagaeva.financial.manager.services;

import be.sagaeva.financial.manager.repositories.PdfGenerateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class PdfGeneratorRunner implements CommandLineRunner {

    private PdfGenerateRepository pdfGenerateRepository;
    private ExpenseService expenseService;



    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("expense", expenseService.getAllExpenses());

        pdfGenerateRepository.generatePdfFile("expense-list", data, "expense.pdf");

    }
}
*/