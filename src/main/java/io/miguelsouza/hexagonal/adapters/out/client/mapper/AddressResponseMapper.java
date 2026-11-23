package io.miguelsouza.hexagonal.adapters.out.client.mapper;

import io.miguelsouza.hexagonal.adapters.out.client.response.AddressResponse;
import io.miguelsouza.hexagonal.application.core.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    Address toAddress(AddressResponse addressResponse);
}
