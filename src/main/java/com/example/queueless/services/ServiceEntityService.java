package com.example.queueless.services;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.queueless.models.Business;
import com.example.queueless.models.ServiceEntity;
import com.example.queueless.repository.ServiceRepos;

@Service
public class ServiceEntityService {

    //Repository Injection
    private final ServiceRepos serviceRepos;

    public ServiceEntityService(ServiceRepos serviceRepos){
        this.serviceRepos = serviceRepos;
    }


    //Methods
    public ServiceEntity createNewServiceForABusiness(String serviceName, int duration, Business business){
        ServiceEntity createdService = new ServiceEntity(serviceName, duration, business);
        return serviceRepos.save(createdService);
    }
    
    public ServiceEntity fetchServiceById(Long businessId, Long serviceId){
        if(checkServiceExistsForABusiness(businessId, serviceId)){
            ServiceEntity fetchedService = serviceRepos.getReferenceById(serviceId);
            return fetchedService;
        } else {
            throw new NoSuchElementException("No Service Found");
        }

    }

    public List<ServiceEntity> listAllServicesOfABusiness(Long businessId){
        List<ServiceEntity> listOfAllServices = serviceRepos.findAllByBusinessBusinessId(businessId);
        return listOfAllServices;
    }

    public boolean checkServiceExistsForABusiness(Long businessId, Long serviceId){
        return serviceRepos.existsByBusinessBusinessIdAndServiceId(businessId, serviceId);
    }

    public boolean checkServiceIsOwnedByOneBusinessOnly(Long businessId,Long serviceId){
        if(serviceRepos.existsByBusinessBusinessIdAndServiceId(businessId, serviceId)){
            return true;
        }else{
            throw new NoSuchElementException("This Service Doesn't belong to this Business");
        }
        
    }
    
}
