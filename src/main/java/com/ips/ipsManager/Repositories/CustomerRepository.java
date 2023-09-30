package com.ips.ipsManager.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ips.ipsManager.Models.CustomerModel;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,String> {

    Optional<CustomerModel>  findByEmail(String email);
    
}
