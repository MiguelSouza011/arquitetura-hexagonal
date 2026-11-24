package io.miguelsouza.hexagonal.config;

import io.miguelsouza.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import io.miguelsouza.hexagonal.adapters.out.InsertCustomerAdapter;
import io.miguelsouza.hexagonal.adapters.out.SendCpfValidationAdapter;
import io.miguelsouza.hexagonal.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            InsertCustomerAdapter insertCustomerAdapter,
            SendCpfValidationAdapter sendCpfValidationAdapter){
        return new InsertCustomerUseCase(
                findAddressByZipCodeAdapter,
                insertCustomerAdapter,
                sendCpfValidationAdapter);
    }
}
