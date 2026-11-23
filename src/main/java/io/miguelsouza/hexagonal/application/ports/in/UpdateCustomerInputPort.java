package io.miguelsouza.hexagonal.application.ports.in;

import io.miguelsouza.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerInputPort {

    void update(Customer customer, String zipCode);
}
