
/*

eerste variant

package be.sagaeva.financial.manager.services;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfObjectWrapper;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class CreatePdfFileService {

    public void createPdf()  {

        String filePdf = "/Users/sagaevayana/Documents/pdf";
        try {
            PdfWriter pdfWriter = new PdfWriter(filePdf);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            Paragraph p1 = new Paragraph("Hello");
            document.add(p1);
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

 */
