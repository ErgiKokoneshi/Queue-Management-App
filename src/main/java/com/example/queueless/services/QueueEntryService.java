package com.example.queueless.services;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.queueless.models.QueueEntry;
import com.example.queueless.models.QueueStatus;
import com.example.queueless.models.ServiceEntity;
import com.example.queueless.repository.QueueEntryRepos;

@Service
public class QueueEntryService {

    //repository injection
    private final QueueEntryRepos queueEntryRepos;

    public QueueEntryService(QueueEntryRepos queueEntryRepos){
        this.queueEntryRepos = queueEntryRepos;
    }

    //methods 
    public QueueEntry joinQueue(LocalDateTime endedAt, LocalDateTime joinedAt,ServiceEntity service, LocalDateTime startedAt){
        QueueEntry ticket = new QueueEntry(endedAt, joinedAt, null, QueueStatus.WAITING,service, startedAt);
        return ticket; 
    }

    public QueueEntry getCurrentQueueEntry(Long entryId){
        QueueEntry currentQueueEntry = queueEntryRepos.findCurrentId(entryId);
        return currentQueueEntry;
    }

    public void serveWaitingQueueEntry(Long entryId){
        QueueEntry servingTicket = getCurrentQueueEntry(entryId);
        servingTicket = queueEntryRepos.setEntryStatus(QueueStatus.SERVING);
    }

    public QueueEntry getNextQueueEntry(Long entryId){
        QueueEntry nextQueueEntry = queueEntryRepos.findByNextId(entryId);
        return nextQueueEntry;
    }

    public void completeCurrentService(Long entryId){
        QueueEntry completingTicket = getCurrentQueueEntry(entryId);
        completingTicket = queueEntryRepos.setEntryStatus(QueueStatus.DONE);
        getNextQueueEntry(entryId);
    }


    public List<QueueEntry> getCompletedQueueEntries(){
        List<QueueEntry> completedQueueEntries = queueEntryRepos.findByEntryStatus(QueueStatus.DONE);
        return completedQueueEntries;
    }
}
