package com.app.document.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@ApiModel
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private String id;
    private String newspaperName;
    private double width;
    private double height;
    private double dpi;
    private String filename;
    private Date publicationDate;
    private LocalDateTime uploadTime; // It should have been termed time of upload
}
