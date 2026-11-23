package io.miguelsouza.hexagonal.adapters.out;

import io.miguelsouza.hexagonal.adapters.out.repository.CustomerRepository;
import io.miguelsouza.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteCustomerByIdAdapter implements DeleteCustomerByIdOutputPort {

    private final CustomerRepository customerRepository;

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}
