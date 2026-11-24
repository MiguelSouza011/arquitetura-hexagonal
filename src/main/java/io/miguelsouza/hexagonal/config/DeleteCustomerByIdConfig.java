package io.miguelsouza.hexagonal.config;

import io.miguelsouza.hexagonal.adapters.out.DeleteCustomerByIdAdapter;
import io.miguelsouza.hexagonal.application.core.usecase.DeleteCustomerByIdUseCase;
import io.miguelsouza.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteCustomerByIdConfig {

    @Bean
    public DeleteCustomerByIdUseCase deleteCustomerByIdUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            DeleteCustomerByIdAdapter deleteCustomerByIdAdapter){
        return new DeleteCustomerByIdUseCase(findCustomerByIdUseCase, deleteCustomerByIdAdapter);
    }
}
