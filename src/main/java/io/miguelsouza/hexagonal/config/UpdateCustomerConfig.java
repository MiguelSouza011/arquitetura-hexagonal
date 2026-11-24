package io.miguelsouza.hexagonal.config;

import io.miguelsouza.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import io.miguelsouza.hexagonal.adapters.out.FindCustomerByIdAdapter;
import io.miguelsouza.hexagonal.adapters.out.UpdateCustomerAdapter;
import io.miguelsouza.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import io.miguelsouza.hexagonal.application.core.usecase.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerConfig {

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(
            FindCustomerByIdUseCase findCustomerByIdAdapter,
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            UpdateCustomerAdapter updateCustomerAdapter
    ){
        return new UpdateCustomerUseCase(findCustomerByIdAdapter, findAddressByZipCodeAdapter, updateCustomerAdapter);
    }
}
