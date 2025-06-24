package com.ai.demo.poc.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class FileUploadController {


    private static String UPLOADED_FOLDER = "src/main/resources/uploadedFile/";

    @Autowired
    SimpleVectorStore simpleVectorStore;
    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Please select a file to upload.");
        }

        try {
            // Create directory if it doesn't exist
            File folder = new File(UPLOADED_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Get the file name and save the file to the server
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, file.getBytes());
            Resource resource = new FileSystemResource(path.toFile());
            generateVectorstoreForUploadedFile(resource);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }

    private void generateVectorstoreForUploadedFile(Resource resource) {
        File vectorStoreFile =
                new File("D:/AI/Chat-bot/src/main/resources/vector_store.json");

        TikaDocumentReader documentReader = new TikaDocumentReader(resource);
        List<Document> documents = documentReader.get();
        TextSplitter textSplitter = new TokenTextSplitter();
        List<Document> splitDocuments = textSplitter.apply(documents);
        simpleVectorStore.add(splitDocuments);
        simpleVectorStore.save(vectorStoreFile);
    }


}
