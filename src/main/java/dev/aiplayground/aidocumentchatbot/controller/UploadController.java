package dev.aiplayground.aidocumentchatbot.controller;

import dev.aiplayground.aidocumentchatbot.service.UploadService;
import dev.aiplayground.aidocumentchatbot.upload.UploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/upload")
    public UploadResponse upload(@RequestParam("file")MultipartFile file){
        return uploadService.upload(file);
    }
}
