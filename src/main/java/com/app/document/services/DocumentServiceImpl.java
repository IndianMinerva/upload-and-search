package com.app.document.services;

import com.app.document.dto.DocumentDto;
import com.app.document.dto.FileDto;
import com.app.document.entity.DocumentEntity;
import com.app.document.mapper.DocumentMapper;
import com.app.document.repositories.DocumentRepository;
import com.app.document.utils.XMLUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public boolean saveDocument(FileDto fileDto) throws Exception {
        String fileName = UUID.randomUUID().toString();
        Path path = Paths.get(fileName);
        Files.write(path, fileDto.getFileContent());
        if (XMLUtils.validate(path.toFile())) {
            documentRepository.insert(getDocumentEntityFromFile(fileDto));
            log.info("Inserted a new document");
            path.toFile().delete();
            return true;
        } else {
            path.toFile().delete();
            return false;
        }
    }

    private DocumentEntity getDocumentEntityFromFile(FileDto fileDto) {
        JSONObject jsonObject = XML.toJSONObject(new String(fileDto.getFileContent()));
        JSONObject deviceInfo = jsonObject.getJSONObject("epaperRequest").getJSONObject("deviceInfo");
        String newspaperName = deviceInfo.getJSONObject("appInfo").getString("newspaperName");
        double width = deviceInfo.getJSONObject("screenInfo").getDouble("width");
        double height = deviceInfo.getJSONObject("screenInfo").getDouble("height");
        double dpi = deviceInfo.getJSONObject("screenInfo").getDouble("dpi");
        String publicationDate = jsonObject
                .getJSONObject("epaperRequest")
                .getJSONObject("getPages")
                .getString("publicationDate");

        try {
            return new DocumentEntity(
                    null,
                    newspaperName,
                    width,
                    height,
                    dpi,
                    fileDto.getFileName(),
                    SIMPLE_DATE_FORMAT.parse(publicationDate),
                    LocalDateTime.now()
            );
        } catch (ParseException pe) {
            log.error("Exception occurred parsing the date", pe);
            throw new RuntimeException(pe);
        }
    }
    @Override
    public List<DocumentDto> getAllDocuments(int page, int size, Sort.Direction sortDirection, String property) {
        return documentRepository.findAll(PageRequest.of(page, size, Sort.by(sortDirection, property)))
                .stream()
                .map(DocumentMapper::mapDocumentEntityToDocumentDto)
                .toList();
    }
}
