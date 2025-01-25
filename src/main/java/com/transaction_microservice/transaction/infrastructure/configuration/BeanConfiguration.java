package com.transaction_microservice.transaction.infrastructure.configuration;


import com.transaction_microservice.transaction.application.mapper.cart_mapper.ICartResponseMapper;
import com.transaction_microservice.transaction.application.mapper.sale_mapper.ISaleReportResponseMapper;
import com.transaction_microservice.transaction.application.mapper.sale_mapper.ISaleRequestMapper;
import com.transaction_microservice.transaction.domain.api.ISaleModelServicePort;
import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import com.transaction_microservice.transaction.domain.spi.*;
import com.transaction_microservice.transaction.domain.usecase.SaleModelUseCase;
import com.transaction_microservice.transaction.domain.usecase.SupplyModelUseCase;
import com.transaction_microservice.transaction.infrastructure.http.feign.ICartFeignClient;
import com.transaction_microservice.transaction.infrastructure.http.feign.IReportFeignClient;
import com.transaction_microservice.transaction.infrastructure.http.feign.IStockFeignClient;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter.*;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISaleDetailsEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISaleEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISupplyEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISaleDetailsRepository;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISaleRepository;
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
    private final ISaleEntityMapper saleEntityMapper;
    private final ISaleDetailsEntityMapper saleDetailsEntityMapper;
    private final ISaleDetailsRepository saleDetailsRepository;

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

    @Bean
    public ICartConnectionPersistencePort cartConnectionPersistencePort(ICartFeignClient cartFeignClient, ICartResponseMapper cartResponseMapper) {
        return new CartConnectionAdapter(cartFeignClient, cartResponseMapper);
    }

    @Bean
    public ISaleModelPersistencePort saleModelPersistencePort(ISaleRepository saleRepository) {
        return new SaleAdapter(saleRepository,saleDetailsRepository,saleEntityMapper,saleDetailsEntityMapper);
    }

    @Bean
    public ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort(IReportFeignClient iReportFeignClient, ISaleRequestMapper saleRequestMapper, ISaleReportResponseMapper saleReportResponseMapper){
        return new ISaleReportConnectionAdapter(iReportFeignClient, saleRequestMapper, saleReportResponseMapper);
    }

    @Bean
    public ISaleModelServicePort saleModelServicePort(
            ICartConnectionPersistencePort cartConnectionPersistencePort,
            IAuthenticationSecurityPort authenticationPersistencePort,
            ISaleModelPersistencePort saleModelPersistencePort,
            IStockConnectionPersistencePort stockConnectionPersistencePort,
            ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort,
            ISupplyModelServicePort supplyModelServicePort){

        return new SaleModelUseCase(
                cartConnectionPersistencePort,
                authenticationPersistencePort,
                saleModelPersistencePort,
                stockConnectionPersistencePort,
                saleReportConnectionPersistencePort,
                supplyModelServicePort);
    }

}
