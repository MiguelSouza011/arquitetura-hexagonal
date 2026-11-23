package io.miguelsouza.hexagonal.application.ports.out;

import io.miguelsouza.hexagonal.application.core.domain.Customer;

public interface InsertCustomerOutputPort {

    void insert(Customer customer);
}
