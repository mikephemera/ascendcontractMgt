package com.ascendcargo.contractmgt.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.ascendcargo.contractmgt.model.Document;
import com.ascendcargo.contractmgt.service.DocumentService;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/{contractId}")
    public ResponseEntity<Document> uploadDocument(@PathVariable Long contractId,
            @RequestParam("file") MultipartFile file, @RequestParam Document.DocumentType docType,
            @RequestParam Integer userId) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentService.createDocument(contractId, file, docType, userId));
    }

    @GetMapping("/{documentId}/download")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long documentId) {
        Resource resource = documentService.downloadDocument(documentId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}

