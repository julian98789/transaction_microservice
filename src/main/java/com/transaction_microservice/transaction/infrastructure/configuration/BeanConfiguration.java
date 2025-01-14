package com.transaction_microservice.transaction.infrastructure.configuration;


import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import com.transaction_microservice.transaction.domain.spi.IStockConnectionPersistencePort;
import com.transaction_microservice.transaction.domain.spi.ISupplyModelPersistencePort;
import com.transaction_microservice.transaction.domain.usecase.SupplyModelUseCase;
import com.transaction_microservice.transaction.infrastructure.http.feign.IStockFeignClient;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter.StockConnectionAdapter;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter.SupplyJpaAdapter;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISupplyEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISupplyRepository;
import com.transaction_microservice.transaction.infrastructure.security.adapter.AuthenticationAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;

    @Bean
    public ISupplyModelPersistencePort supplyModelPersistencePort() {
        return new SupplyJpaAdapter(supplyRepository, supplyEntityMapper);
    }

    @Bean
    public IAuthenticationSecurityPort authenticationSecurityPort() {
        return new AuthenticationAdapter();
    }

    @Bean
    public IStockConnectionPersistencePort stockConnectionPersistencePort(IStockFeignClient stockFeignClient) {
        return new StockConnectionAdapter(stockFeignClient);
    }

    @Bean
    public ISupplyModelServicePort supplyModelServicePort(
            ISupplyModelPersistencePort supplyModelPersistencePort,
            IStockConnectionPersistencePort stockConnectionPersistencePort ){
        return new SupplyModelUseCase(supplyModelPersistencePort, stockConnectionPersistencePort);
    }

}
