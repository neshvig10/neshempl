package com.neshempl.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class JobSkill {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_skill_id")
    public Long jobSkillId;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonIgnore
    public Job job;


    @ManyToOne
    @JoinColumn(name = "skill_id")
    public Skill skill;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public JobSkill(){

    }

    public JobSkill(Job job, Skill skill) {
        this.job = job;
        this.skill = skill;
    }
}
