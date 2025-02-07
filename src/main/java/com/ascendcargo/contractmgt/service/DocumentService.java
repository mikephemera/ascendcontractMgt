package com.ascendcargo.contractmgt.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import com.ascendcargo.contractmgt.model.Contract;
import com.ascendcargo.contractmgt.model.Document;
import com.ascendcargo.contractmgt.repository.ContractRepository;
import com.ascendcargo.contractmgt.repository.DocumentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ContractRepository contractRepository;
    private final FileStorageService storageService; // 新增依赖

    public Document createDocument(Long contractId, MultipartFile file, 
                                  Document.DocumentType docType, Integer userId) throws IOException {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found"));
        
        // 存储文件
        String storedFilename = storageService.storeFile(file);
        
        Document document = new Document();
        document.setContract(contract);
        document.setDocType(docType);
        document.setFileName(file.getOriginalFilename());
        document.setS3Key(storedFilename); // 使用本地存储路径
        document.setCreatedBy(userId);
        
        return documentRepository.save(document);
    }

    // 新增文件下载方法
    public Resource downloadDocument(Long documentId) {
        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        return storageService.loadFile(doc.getS3Key());
    }
}

