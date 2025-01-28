package com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository;

import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<SalesEntity, Long> {
}
