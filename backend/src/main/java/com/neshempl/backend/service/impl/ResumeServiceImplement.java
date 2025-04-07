package com.neshempl.backend.service.impl;


import com.neshempl.backend.entity.Resume;
import com.neshempl.backend.repository.ResumeRepository;
import com.neshempl.backend.service.ResumeService;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ResumeServiceImplement implements ResumeService {


    @Value(("${FILE_DESTINATION}"))
    private String fileDestination;

    @Autowired
    ResumeRepository resumeRepository;


    @Override
    public List<Resume> getResumeForUser(Long userId) {
        return resumeRepository.getReferenceByResumeUserId(userId);
    }

    @Override
    public String postResume(MultipartFile resumeFile,Long userId) {
        String fileName = resumeFile.getOriginalFilename();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());


        try{
//            if (resumeFile.isEmpty()) {
//                throw new StoreException("Failed to store empty file.",new Throwable());
//            }
            File dir = new File(fileDestination);
            if (!dir.exists()) {
                dir.mkdirs(); // Create directory if it doesn't exist
            }
            Path path = Paths.get(fileDestination, resumeFile.getOriginalFilename()+timeStamp);
            resumeFile.transferTo(path);

        }catch(Exception e){
            System.out.println(e);
            return "Error occurred while uploading";
        }
        Resume resume = new Resume();
        resume.setResumeFilePath(fileDestination+resumeFile.getOriginalFilename()+timeStamp);
        resume.setResumeUserId(userId);
        resume.setResumeName(resumeFile.getOriginalFilename());
        resumeRepository.save(resume);
        return "Resume uploaded successfully";
    }

    @Override
    public ResponseEntity<Resource> getResume(Long resumeId) throws IOException, InterruptedException {
        Resume resume = resumeRepository.getReferenceById(resumeId);
        Path path = Paths.get(resume.getResumeFilePath());
        Resource resource = new UrlResource(path.toUri());
        analyzeResume(resumeId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(resource);
    }

    @Value("${AI_API_KEY}")
    private String API_KEY;

    private static final String BASE_URL = "https://api.deepseek.com";
    private static final String baseContent = "This is the content of a user's resume. Give the skills, experiences and tech stack in a json form. \n";

    public String callAIAPI(String content) throws IOException, InterruptedException {

        String fullContent = baseContent + content;
        System.out.println(fullContent);
        
        var body = "{\n" +
                "        \"model\": \"deepseek-chat\",\n" +
                "        \"messages\": [\n" +
                "          {         \"role\": \"user\"," +
                "                    \"content\": " + fullContent +
                "           }\n" +
                "        ],\n" +
                "        \"stream\": false\n" +
                "      }";

        var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/chat/completions"))
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        var client = HttpClient.newHttpClient();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }

    @Override
    public String analyzeResume(Long resumeId) throws IOException, InterruptedException {
        Resume resume = resumeRepository.getReferenceById(resumeId);
        File pdfFile = new File(resume.getResumeFilePath());
        PDDocument pdDocument = Loader.loadPDF(pdfFile);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String pdfText = pdfTextStripper.getText(pdDocument);
        System.out.println(pdfText);
        pdDocument.close();
        String AIresponse = callAIAPI(pdfText);
        // printing the contents of resume
        System.out.println("AIResponse \n"+ AIresponse);
        return null;
    }


}
