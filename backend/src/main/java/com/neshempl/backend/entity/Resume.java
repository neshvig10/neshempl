package com.neshempl.backend.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "resume")
public class Resume {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resume_id")
    private Long resumeId;

    @Column(name = "user_id")
    private Long resumeUserId;

    @Column(name = "resume_file_path")
    private String resumeFilePath;


    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt = new Date();


    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Long getResumeUserId() {
        return resumeUserId;
    }

    public void setResumeUserId(Long resumeUserId) {
        this.resumeUserId = resumeUserId;
    }

    public String getResumeFilePath() {
        return resumeFilePath;
    }

    public void setResumeFilePath(String resumeFilePath) {
        this.resumeFilePath = resumeFilePath;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
