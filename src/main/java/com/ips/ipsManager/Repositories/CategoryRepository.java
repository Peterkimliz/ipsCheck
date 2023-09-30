package com.ips.ipsManager.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ips.ipsManager.Models.Category;
import com.ips.ipsManager.Models.CustomerModel;
import com.ips.ipsManager.Repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

    Optional<Category> findByIpAddress(String ipAddress);

    List<Category> findByStatus(String status);

    Optional<Category> findByCustomerModel(CustomerModel newCustomer);
    
}
