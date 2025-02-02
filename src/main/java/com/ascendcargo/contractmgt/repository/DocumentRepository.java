package com.ascendcargo.contractmgt.repository;

import com.ascendcargo.contractmgt.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}