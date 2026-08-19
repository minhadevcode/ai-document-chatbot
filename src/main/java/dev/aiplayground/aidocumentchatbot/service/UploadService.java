package dev.aiplayground.aidocumentchatbot.service;

import dev.aiplayground.aidocumentchatbot.pdf.PdfService;
import dev.aiplayground.aidocumentchatbot.upload.UploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final PdfService pdfService;
    private final DocumentService documentService;

    public UploadResponse upload(MultipartFile file) {
        String text = pdfService.extractText(file);

        documentService.saveDocument(text);
        System.out.println("text: " + text);

        return new UploadResponse(file.getOriginalFilename(), file.getSize(), "파일 업로드 성공");
    }
}
