package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;
import org.example.springtask1.service.additional.FileDownloadStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cover")
//@Setter
//@Getter
public class Cover {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cover_id_seq")
    @SequenceGenerator(
            name = "cover_id_seq",
            sequenceName = "cover_id_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_format")
    private String fileFormat;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private FileDownloadStatus status;

    @Column(name = "error_message")
    private String errorMessage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;


}
