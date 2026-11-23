package io.miguelsouza.hexagonal.application.ports.in;

public interface DeleteCustomerByIdInputPort {

    void delete(String id);
}
