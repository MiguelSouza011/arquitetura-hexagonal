package io.miguelsouza.hexagonal.adapters.out;

import io.miguelsouza.hexagonal.adapters.out.repository.CustomerRepository;
import io.miguelsouza.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import io.miguelsouza.hexagonal.application.core.domain.Customer;
import io.miguelsouza.hexagonal.application.ports.out.UpdateCustomerOutputPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerAdapter implements UpdateCustomerOutputPort {

   private final  CustomerRepository customerRepository;
   private final CustomerEntityMapper customerEntityMapper;

    public UpdateCustomerAdapter(CustomerRepository customerRepository, CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.customerEntityMapper = customerEntityMapper;
    }

    @Override
    public void update(Customer customer) {
        var customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEntity);
    }
}
