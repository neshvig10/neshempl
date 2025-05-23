package com.neshempl.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.neshempl.backend.entity.Job;
import com.neshempl.backend.entity.Resume;
import com.neshempl.backend.repository.JobRepository;
import com.neshempl.backend.repository.ResumeRepository;
import com.neshempl.backend.service.JobService;
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

    @Autowired
    JobService jobService;


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

    public String deleteResume(Long resumeId){
        Resume resume = resumeRepository.getReferenceById(resumeId);
        File resumeFile = new File(resume.getResumeFilePath());
        if (resumeFile.delete()){
            resumeRepository.deleteById(resumeId);
            return "Resume Deleted Successfully !";
        }
        return "Try again";
    }

    @Value(("${GEMINI_API_KEY}"))
    public String GeminiAPIKey;


    public List<String> callAIAPI(String content) throws IOException, InterruptedException, ParseException {

        List<Job> jobList = jobService.listJobs();

        Set <String> jobTitles = new HashSet<>();

        for (Job job : jobList){
            if (!jobTitles.contains(job.getJobTitle())){
                jobTitles.add(job.getJobTitle());
            }
        }


        Client client = Client.builder().apiKey(GeminiAPIKey).build();

        String baseContent0 = "Given the resume and list of jobTitles, give the list of job titles that can match this resume(only job titles separated by commas , no other text)" +
                "Resume Content : " + content +
                "Job Titles list : " + jobTitles.toString();

        GenerateContentResponse listOfJobs =
                client.models.generateContent("gemini-2.0-flash-001",baseContent0, null);
        System.out.println(listOfJobs.text());
        List<String> matchingJobTitlesBeforeTrim = List.of(Objects.requireNonNull(listOfJobs.text()).split(", "));
        List<String> matchingJobTitles = new ArrayList<>();
        for (String matchingjobTitle : matchingJobTitlesBeforeTrim){
            matchingJobTitles.add(matchingjobTitle.trim());
        }
        System.out.println(matchingJobTitles);

        List<String> jobMatchAIOutput = new ArrayList<>();

        for (Job job : jobList) {
            if (matchingJobTitles.contains(job.getJobTitle())) {
                String jobEntry = (
                        "jobId : " + job.getJobId() +
                                ",jobTitle : " + job.getJobTitle() +
                                ",jobDescription : " + job.getDescription() +
                                ",jobExperienceRequired : " + job.getExperienceRequired().toString()
                                + "\n"
                );
                String baseContent =
                        "Resume Content : " + content +
                                "Job Details : " + jobEntry +
                                """ 
                                        Given the user resume and job details, match each of them with a score in range 1 to 10
                                        Give the output as
                                        {
                                            matchScore : how well the job and resume matches
                                            reasonForScore : reason for the score given
                                        }
                                        Return job post only if the score is above 5.
                                                            
                                        """;


                GenerateContentResponse response =
                        client.models.generateContent("gemini-2.0-flash-001", baseContent, null);
                // Gets the text string from the response by the quick accessor method `text()`.
                System.out.println("Unary response: " + response.text());

                String jobMatchScore = response.text().replace("```", "").replace("json", "");
                JSONObject jsonObject = new JSONObject(jobMatchScore);
                jsonObject.append("jobId", job.getJobId());
                jsonObject.append("jobTitle", job.getJobTitle());
                jsonObject.append("jobDescription", job.getDescription());
                jsonObject.append("jobExperienceRequired", job.getExperienceRequired());
                jobMatchAIOutput.add(jsonObject.toString());
            }

        }
        return jobMatchAIOutput;


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
        List<String> AIresponse = callAIAPI(pdfText);
        System.out.println("AIResponse \n"+ AIresponse);
        return AIresponse.toString();
    }


}
