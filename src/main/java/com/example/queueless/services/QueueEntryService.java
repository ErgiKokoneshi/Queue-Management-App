package com.example.queueless.services;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.queueless.models.QueueEntry;
import com.example.queueless.models.QueueStatus;
import com.example.queueless.models.ServiceEntity;
import com.example.queueless.repository.QueueEntryRepos;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QueueEntryService {

    //repository injection
    private final QueueEntryRepos queueEntryRepos;

    public QueueEntryService(QueueEntryRepos queueEntryRepos){
        this.queueEntryRepos = queueEntryRepos;
    }

    //methods 
    public QueueEntry joinQueue(ServiceEntity service){
        QueueEntry ticket = new QueueEntry(null, service, QueueStatus.WAITING, LocalDateTime.now(), null, null);
        return queueEntryRepos.save(ticket); 
    }

    public QueueEntry getNextEntry(Long serviceId, QueueStatus entryStatus){
        QueueEntry current = 
            queueEntryRepos.findFirstByServiceServiceIdAndQueueStatusOrderByJoinedAtAsc(serviceId, entryStatus)
            .orElseThrow(() -> new EntityNotFoundException("No waiting tickets"));

        return current;
    }

    public QueueEntry startWorkingOnCurrentEntry(Long serviceId){
        QueueEntry current = getNextEntry(serviceId, QueueStatus.WAITING);
        current.setQueueStatus(QueueStatus.SERVING);
        current.setStartedAt(LocalDateTime.now());
        return queueEntryRepos.save(current);
    }

    public QueueEntry finishCurrentEntry(Long serviceId){
        QueueEntry finished = queueEntryRepos.findByServiceServiceIdAndQueueStatus(serviceId, QueueStatus.SERVING)
        .stream()
        .findFirst()
        .orElseThrow(() -> new EntityNotFoundException("No serving tickets"));
        finished.setQueueStatus(QueueStatus.DONE);
        finished.setEndedAt(LocalDateTime.now());

        return queueEntryRepos.save(finished);
    }

}
