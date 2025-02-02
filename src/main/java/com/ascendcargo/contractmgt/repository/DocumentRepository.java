package com.ascendcargo.contractmgt.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ascendcargo.contractmgt.model.Contract;
import com.ascendcargo.contractmgt.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByContract_IdOrderByCreatedAtDesc(Long contractId);
    Optional<Document> findByS3Key(String s3Key);
    List<Document> findByDocTypeAndContract_Status(String docType, Contract.ContractStatus status);
    List<Document> findByContract_Id(Long contractId);
    List<Document> findByContract_IdAndDocType(Long contractId, String docType);
}