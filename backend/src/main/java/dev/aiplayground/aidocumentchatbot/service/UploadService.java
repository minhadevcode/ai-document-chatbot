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
        if(file == null || file.isEmpty()) {
            return new UploadResponse(null, 0, "파일이 없습니다.");
        }
        String fileName = file.getOriginalFilename();

        if(fileName == null || !fileName.endsWith(".pdf")) {
            return new UploadResponse(file.getOriginalFilename(), file.getSize(), "PDF 파일만 업로드할 수 있습니다.");
        }
        String text = pdfService.extractText(file);
        documentService.saveDocument(text);
        return new UploadResponse(file.getOriginalFilename(), file.getSize(), "파일 업로드 성공");
    }
}
