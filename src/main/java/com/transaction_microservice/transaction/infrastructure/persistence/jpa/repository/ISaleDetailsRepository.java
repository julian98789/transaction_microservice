package com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository;

import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SaleDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleDetailsRepository extends JpaRepository<SaleDetailsEntity, Long> {
}
