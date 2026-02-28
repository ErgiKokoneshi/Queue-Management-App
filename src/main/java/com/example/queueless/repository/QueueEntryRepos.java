package com.example.queueless.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.example.queueless.models.QueueEntry;
import  com.example.queueless.models.QueueStatus;

public interface QueueEntryRepos extends JpaRepository<QueueEntry, Long>{
    List<QueueEntry> findByQueueEntryIdAndServiceId(Long serviceId);
    QueueEntry findByNextId(Long entryId);
    List<QueueEntry> findByEntryStatus(QueueStatus entryStatus);
    QueueEntry setEntryStatus(QueueStatus entryStatus);
    QueueEntry findCurrentId(Long entryId);
}
