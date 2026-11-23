package io.miguelsouza.hexagonal.application.ports.in;

import io.miguelsouza.hexagonal.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert (Customer customer, String zipCode);
}
