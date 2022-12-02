package com.app.document.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class DocumentEntity {
    private String id;

    private String newspaperName;
    private double width;
    private double height;
    private double dpi;
    private String filename;
    private Date publicationDate;
    private LocalDateTime uploadTime; // It should have been termed time of upload
}
