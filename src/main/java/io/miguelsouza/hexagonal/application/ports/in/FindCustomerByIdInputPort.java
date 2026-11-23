package io.miguelsouza.hexagonal.application.ports.in;

import io.miguelsouza.hexagonal.application.core.domain.Customer;

public interface FindCustomerByIdInputPort {

    Customer find(String id);
}
