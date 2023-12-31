package com.ips.ipsManager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ips.ipsManager.Models.IpAddressModel;
import com.ips.ipsManager.Models.CustomerModel;
import com.ips.ipsManager.Repositories.IpAddressRepository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IpAddressRepository extends JpaRepository<IpAddressModel,String> {

    Optional<IpAddressModel> findByIpAddress(String ipAddress);

    List<IpAddressModel> findByStatus(String status);

    Optional<IpAddressModel> findByCustomerModel(CustomerModel newCustomer);
    
}
