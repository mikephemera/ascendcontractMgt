package com.ascendcargo.contractmgt.model;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Column(name = "doc_type")
    private String docType;

    @Column(name = "s3_key", unique = true)
    private String s3Key;

    @Column(name = "created_by")
    private Integer createdBy; // 根据业务需要可改为关联用户实体

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
// FILE_END