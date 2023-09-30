package com.ips.ipsManager.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ips.ipsManager.Dtos.CategoryDto;
import com.ips.ipsManager.Dtos.CustomerRequest;
import com.ips.ipsManager.Exceptions.FoundException;
import com.ips.ipsManager.Exceptions.NotFoundException;
import com.ips.ipsManager.Models.Category;
import com.ips.ipsManager.Models.CustomerModel;
import com.ips.ipsManager.Repositories.CategoryRepository;
import com.ips.ipsManager.Repositories.CustomerRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CustomerRepository customerRepository;

    public CategoryDto createCategory(String ipAddress) {

        if (!isValidIPAddress(ipAddress)) {
            throw new FoundException("Invalid ipAddress");
        }
        Optional<Category> foundCategory = categoryRepository.findByIpAddress(ipAddress);
        if (foundCategory.isPresent()) {
            throw new FoundException("IpAddress already exists");
        }
        Category category = Category.builder().ipAddress(ipAddress).status("available")
                .build();
        categoryRepository.save(category);
        CategoryDto categoryDto = CategoryDto.builder().id(category.getId()).ipAddress(category.getIpAddress())
                .status(category.getStatus()).build();
        return categoryDto;
    }

    public CategoryDto releaseIpAddress(String ip) {
        if (!isValidIPAddress(ip)) {
            throw new FoundException("Invalid ipAddress");
        }
        Optional<Category> foundCategory = categoryRepository.findByIpAddress(ip);

        if (!foundCategory.isPresent()) {
            throw new NotFoundException("IpAddress Not found");
        }
        if (foundCategory.get().getStatus() == "available") {
            throw new NotFoundException("Ipaddress not allocated");
        }
        Category category = foundCategory.get();
        category.setStatus("released");

        categoryRepository.save(category);

        CategoryDto categoryDto = CategoryDto.builder().id(category.getId()).ipAddress(category.getIpAddress())
                .status(category.getStatus()).customerModel(category.getCustomerModel()).build();
        return categoryDto;
    }

    public CategoryDto allocateIp(CustomerRequest customerRequest) {
        System.out.println("hello");
        Optional<CustomerModel> customer = customerRepository.findByEmail(customerRequest.getEmail());
        CustomerModel newCustomer;
        if (!customer.isPresent()) {

            newCustomer = CustomerModel.builder().customerName(customerRequest.getCustomerName())
                    .email(customerRequest.getEmail()).build();
            customerRepository.save(newCustomer);
        } else {
            newCustomer = customer.get();
        }

        Optional<Category> foundAllocation = categoryRepository.findByCustomerModel(newCustomer);
        System.out.println("hello" + foundAllocation);
        if (foundAllocation.isPresent()) {
            throw new FoundException("Customer has already been allocated an ipAddress");
        }

        List<Category> availableIps = categoryRepository.findByStatus("available");
        if (availableIps.size() == 0) {
            throw new NotFoundException("no avalable ips");
        }
        Random rndm = new Random();
        Category category = availableIps.get(rndm.nextInt(availableIps.size()));
        category.setCustomerModel(newCustomer);
        category.setStatus("allocated");
        categoryRepository.save(category);
        return CategoryDto.builder().customerModel(category.getCustomerModel()).status(category.getStatus())
                .id(category.getId()).ipAddress(category.getIpAddress()).build();
    }

    public List<CategoryDto> getAllCategories(String status) {

        List<Category> categories = categoryRepository.findByStatus(status);
        if (categories.size() > 0) {
            return categories.stream().map(
                    e -> CategoryDto.builder().id(e.getId()).ipAddress(e.getIpAddress()).status(e.getStatus())
                            .customerModel(e.getCustomerModel()).build())
                    .toList();

        } else {
            return new ArrayList<>();
        }

    }

    private static boolean isValidIPAddress(String ip) {

        String zeroTo255 = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";
        String regex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;
        Pattern p = Pattern.compile(regex);

        if (ip == null) {
            return false;
        }
        Matcher m = p.matcher(ip);
        return m.matches();
    }

    public List<CategoryDto> getAllIps() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.size() == 0) {
            return new ArrayList<>();
        }
        List<CategoryDto> categoryDtos = categories.stream()
                .map(e -> CategoryDto.builder()
                        .customerModel(e.getCustomerModel())
                        .id(e.getId()).status(e.getStatus())
                        .ipAddress(e.getIpAddress())
                        .build())
                .toList();
        return categoryDtos;
    }

}
