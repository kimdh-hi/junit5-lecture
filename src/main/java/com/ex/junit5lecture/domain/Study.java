package com.ex.junit5lecture.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;
    private StudyStatus status = StudyStatus.DRAFT;
    private String name;
    private int limitCount;
    private LocalDateTime openDateTime;

    @ManyToOne
    private Member owner;

    public void publish() {
        this.openDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;
    }

    public Study() {
    }

    public Study(Long studyId, StudyStatus status, String name, int limitCount, LocalDateTime openDateTime, Member owner) {
        this.studyId = studyId;
        this.status = status;
        this.name = name;
        this.limitCount = limitCount;
        this.openDateTime = openDateTime;
        this.owner = owner;
    }

    public Study(String name, int limitCount) {
        if (limitCount < 0) throw new IllegalArgumentException("limit must be lager then 0");
        this.name = name;
        this.limitCount = limitCount;
    }

    public Study(int limitCount) {
        if (limitCount < 0) throw new IllegalArgumentException("limit must be lager then 0");
        this.limitCount = limitCount;
    }

    public Long getStudyId() {
        return studyId;
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public LocalDateTime getOpenDateTime() {
        return openDateTime;
    }

    public void setOpenDateTime(LocalDateTime openDateTime) {
        this.openDateTime = openDateTime;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }
}
