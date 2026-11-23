package io.miguelsouza.hexagonal.application.ports.out;

import io.miguelsouza.hexagonal.application.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerByIdOutputPort {

    Optional<Customer> find(String id);

}
