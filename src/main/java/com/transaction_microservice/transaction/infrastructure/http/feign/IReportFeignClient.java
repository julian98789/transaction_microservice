package com.transaction_microservice.transaction.infrastructure.http.feign;

import com.transaction_microservice.transaction.application.dto.saledto.SaleReportResponse;
import com.transaction_microservice.transaction.application.dto.saledto.SaleRequest;
import com.transaction_microservice.transaction.domain.util.Util;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = Util.REPORT_SERVICE_NAME, url = Util.REPORT_SERVICE_URL)
public interface IReportFeignClient {

    @PostMapping("/api/reports/sale")
    SaleReportResponse createSaleReport(@RequestBody SaleRequest saleRequest);
}
