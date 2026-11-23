package io.miguelsouza.hexagonal.adapters.out;

import io.miguelsouza.hexagonal.adapters.out.repository.CustomerRepository;
import io.miguelsouza.hexagonal.adapters.out.repository.entity.CustomerEntity;
import io.miguelsouza.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import io.miguelsouza.hexagonal.application.core.domain.Customer;
import io.miguelsouza.hexagonal.application.ports.out.InsertCustomerOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertCustomerAdapter implements InsertCustomerOutputPort {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;


    @Override
    public void insert(Customer customer) {
        var customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEntity);
    }
}
