package io.miguelsouza.hexagonal.adapters.out.repository.mapper;

import io.miguelsouza.hexagonal.adapters.out.repository.entity.CustomerEntity;
import io.miguelsouza.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity toCustomerEntity(Customer customer);
}
