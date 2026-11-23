package io.miguelsouza.hexagonal.adapters.out;

import io.miguelsouza.hexagonal.adapters.out.repository.CustomerRepository;
import io.miguelsouza.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import io.miguelsouza.hexagonal.application.core.domain.Customer;
import io.miguelsouza.hexagonal.application.ports.out.UpdateCustomerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateCustomerAdapter implements UpdateCustomerOutputPort {

   private final  CustomerRepository customerRepository;
   private final CustomerEntityMapper customerEntityMapper;

    @Override
    public void update(Customer customer) {
        var customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEntity);
    }
}
