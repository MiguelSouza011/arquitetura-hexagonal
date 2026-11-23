package io.miguelsouza.hexagonal.application.ports.out;

import io.miguelsouza.hexagonal.application.core.domain.Address;

public interface FindAddressByZipCodeOutputPort {

    Address find(String zipCode);
}
