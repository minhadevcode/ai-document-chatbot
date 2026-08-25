package dev.aiplayground.aidocumentchatbot.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadResponse {
    private String fileName;
    private long fileSize;
    private String message;
}
