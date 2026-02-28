package com.example.queueless.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.queueless.models.ServiceEntity;

public interface ServiceRepos extends JpaRepository<ServiceEntity, Long> {
    public boolean existsByBusinessIdAndId(Long BusinessId, Long ServiceId);
    List<ServiceEntity> findAllByBusinessId(Long businessId);
}

