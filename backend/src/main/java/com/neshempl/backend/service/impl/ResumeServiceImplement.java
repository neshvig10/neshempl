package com.neshempl.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.neshempl.backend.entity.Resume;
import com.neshempl.backend.repository.ResumeRepository;
import com.neshempl.backend.service.ResumeService;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
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
import java.util.*;

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
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(resource);
    }

    @Value("${AI_API_KEY}")
    private String API_KEY;

    private static final String BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";
    private static final String baseContent = "This is the content of a user's resume. We have to search jobs for this user. Give the keywords for job search separated by commas. { \"keywords\" : job-related-keywords-separated-by-commas } \n";

    public String callAIAPI(String content) throws IOException, InterruptedException, ParseException {

        String fullContent = baseContent + content;
        fullContent=fullContent.replace("\"","\\\"");
        fullContent = "\"\"\"" + fullContent + "\"\"\"";
        System.out.println(fullContent);

        Map<String, Object> message = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts",List.of(
                                        Map.of(
                                                "text",fullContent
                                        )
                                )
                        )
                )
        );

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(message);

        var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+API_KEY))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        var client = HttpClient.newHttpClient();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();

    }

    @Override
    public String callRemotiveApi(String category, String title) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://remotive.io/api/remote-jobs?"+"category="+category.replace(" ","-")+"&search="+title.replace(" ","-")))
                .build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Value("${RAPID_LINKEDIN_API_KEY}")
    private String rapidLinkedinAPIKey;

    @Override
    public String callRapidLinkedinAPI(String keywords) throws IOException, InterruptedException {
        keywords=keywords.replace(",","%20").replace(" ","");
        System.out.println(keywords);
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://linkedin-api8.p.rapidapi.com/search-jobs?"+"keywords="+keywords+"&locationId=102713980&datePosted=anyTime&sort=mostRelevant"))
                .header("x-rapidapi-host","linkedin-api8.p.rapidapi.com")
                .header("x-rapidapi-key",rapidLinkedinAPIKey)
                .build();

        var client = HttpClient.newHttpClient();
        var response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Override
    public String analyzeResume(Long resumeId) throws IOException, InterruptedException, ParseException {
        Resume resume = resumeRepository.getReferenceById(resumeId);
        File pdfFile = new File(resume.getResumeFilePath());
        PDDocument pdDocument = Loader.loadPDF(pdfFile);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String pdfText = pdfTextStripper.getText(pdDocument);
        System.out.println(pdfText);
        pdDocument.close();
        String AIresponse = callAIAPI(pdfText);
        JSONParser parser = new JSONParser(AIresponse);
        LinkedHashMap jsonMap = (LinkedHashMap) parser.parse();
        ArrayList candidatesList = (ArrayList) jsonMap.get("candidates");
        LinkedHashMap candidatesListFirst = (LinkedHashMap) candidatesList.getFirst();
        LinkedHashMap content = (LinkedHashMap) candidatesListFirst.get("content");
        ArrayList parts = (ArrayList) content.get("parts");
        LinkedHashMap jsonMapText = (LinkedHashMap)parts.get(0);
        String jsonText = (String) jsonMapText.get("text");
        Integer sizeOfJsonText = jsonText.length();
        String finalJsonText = jsonText.substring(9,sizeOfJsonText-5).replace("\\n","").replace("\\","");
        System.out.println(finalJsonText);
        JSONObject jsonObject1 = new JSONObject(finalJsonText);
        String jobSearchOutput = callRapidLinkedinAPI(jsonObject1.getString("keywords"));
        // printing the contents of resume
        // System.out.println("AIResponse \n"+ AIresponse);
        return jobSearchOutput;
    }


}
