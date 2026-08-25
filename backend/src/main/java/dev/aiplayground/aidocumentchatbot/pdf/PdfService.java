package dev.aiplayground.aidocumentchatbot.pdf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfService {
    public String extractText(MultipartFile file) {
        try(PDDocument document = Loader.loadPDF(file.getBytes())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }catch (Exception e){
            throw new RuntimeException("PDF 읽기 실패",e);
        }
    }
}
