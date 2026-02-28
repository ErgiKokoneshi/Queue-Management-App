package com.example.queueless.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.queueless.models.Business;
import com.example.queueless.repository.BusinessRepos;

@Service
public class BusinessService {

//repository injection
private final BusinessRepos businessRepos; 

public BusinessService(BusinessRepos businessRepos){
    this.businessRepos = businessRepos;
}

//methods

public Business createBusiness(String name, String location){
    Business businessCreated = new Business(name, location);
    return businessRepos.save(businessCreated);
}

public Business fetchBusinessById(Long BusinessId){
    if (checkBusinessExists(BusinessId) == true){
        Business fetchedBusiness = businessRepos.getReferenceById(BusinessId);
        return fetchedBusiness;}
    else {
        throw new NoSuchElementException("Business Not Found!!");
    }
}

public boolean checkBusinessExists(Long BusinessId){
    return businessRepos.existsById(BusinessId);
}

public List<Business> listAllBusinesses(){
    List<Business> listOfAllBusinesses = businessRepos.findAll();
    return listOfAllBusinesses;
}

}

