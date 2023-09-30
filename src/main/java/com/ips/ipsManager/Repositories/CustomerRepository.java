package com.ips.ipsManager.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ips.ipsManager.Models.CustomerModel;


@Repository
public interface CustomerRepository extends MongoRepository<CustomerModel,String> {

    Optional<CustomerModel>  findByEmail(String email);
    
}
