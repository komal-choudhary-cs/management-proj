package com.common.management.Service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
@Service
public class UserServicePdf{

	public  byte[] generateLoginPdf(String username) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();
            document.add(new Paragraph("Login Successful"));
            document.add(new Paragraph("User: " + username ));
            document.add(new Paragraph(" you logeed in successfully here   "  ));
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}



