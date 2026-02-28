package com.example.queueless.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.queueless.models.Business;

public interface BusinessRepos extends JpaRepository<Business, Long>{

}
