package io.miguelsouza.hexagonal.application.ports.out;

import io.miguelsouza.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerOutputPort {

    void update(Customer customer);
}
