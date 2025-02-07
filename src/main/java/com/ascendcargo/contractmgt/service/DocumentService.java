package com.ascendcargo.contractmgt.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ascendcargo.contractmgt.model.Contract;
import com.ascendcargo.contractmgt.model.Document;
import com.ascendcargo.contractmgt.repository.ContractRepository;
import com.ascendcargo.contractmgt.repository.DocumentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ContractRepository contractRepository;

    public Document createDocument(Long contractId, MultipartFile file, 
                                  Document.DocumentType docType, Integer userId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found"));
        
        Document document = new Document();
        document.setContract(contract);
        document.setDocType(docType);
        document.setFileName(file.getOriginalFilename());
        document.setCreatedBy(userId);
        
        return documentRepository.save(document);
    }

    public Document updateDocumentStatus(Long documentId, Document.DocumentStatus newStatus) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        
        if (document.getStatus() == Document.DocumentStatus.ARCHIVED) {
            throw new IllegalStateException("Cannot modify archived documents");
        }
        
        document.setStatus(newStatus);
        return documentRepository.save(document);
    }

    public Document createNewVersion(Long documentId, MultipartFile file, Integer userId) {
        Document existing = documentRepository.findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        
        Document newVersion = new Document();
        newVersion.setContract(existing.getContract());
        newVersion.setDocType(existing.getDocType());
        newVersion.setFileName(file.getOriginalFilename());
        newVersion.setCreatedBy(userId);
        newVersion.setVersion(existing.getVersion() + 1);
        
        existing.setStatus(Document.DocumentStatus.ARCHIVED);
        documentRepository.save(existing);
        
        return documentRepository.save(newVersion);
    }

}
