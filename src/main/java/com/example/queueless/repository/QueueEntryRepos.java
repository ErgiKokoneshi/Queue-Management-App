package com.example.queueless.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.example.queueless.models.QueueEntry;
import  com.example.queueless.models.QueueStatus;

public interface QueueEntryRepos extends JpaRepository<QueueEntry, Long>{

    List<QueueEntry> findByQueueStatus(QueueStatus queueStatus);
    List<QueueEntry> findByServiceServiceIdAndQueueStatusOrderByJoinedAtAsc(Long serviceId, QueueStatus entryStatus);
    List<QueueEntry> findByServiceServiceIdAndQueueStatus(Long serviceId, QueueStatus entryStatus);
}
