package com.example.queueless.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class QueueEntry {

    //fields
    @Id
    @GeneratedValue
    private Long queueEntryId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @Enumerated(EnumType.STRING)
    private QueueStatus queueStatus;

    @Column(nullable=false)
    private LocalDateTime joinedAt;

    @Column(nullable=true)
    private LocalDateTime startedAt;

    @Column(nullable=true)
    private LocalDateTime endedAt;

    //no-arg constructor
    protected QueueEntry(){}

    //convenience constructor
    
    public QueueEntry(LocalDateTime endedAt, LocalDateTime joinedAt, Long queueEntryId, QueueStatus queueStatus, ServiceEntity service, LocalDateTime startedAt) {
        this.endedAt = endedAt;
        this.joinedAt = joinedAt;
        this.queueEntryId = queueEntryId;
        this.queueStatus = queueStatus;
        this.service = service;
        this.startedAt = startedAt;
    }

    //getters
    public Long getQueueEntryId(){return this.queueEntryId;}

    public ServiceEntity getService(){return this.service;}

    public QueueStatus getQueueStatus(){return this.queueStatus;}

    public LocalDateTime getJoinedAt(){return this.joinedAt;}

    public LocalDateTime getStartedAt(){return this.startedAt;}

    public LocalDateTime getEndedAt(){return this.endedAt;}

    //setters
    public void setJoinedAt(LocalDateTime joinedAt){ this.joinedAt = joinedAt;}

    public void setStartedAt(LocalDateTime startedAt){ this.startedAt = startedAt;}
    
    public void setEndedAt(LocalDateTime endedAt){ this.endedAt = endedAt;}

    public void setQueueStatus(QueueStatus queueStatus){ this.queueStatus = queueStatus;}

}
