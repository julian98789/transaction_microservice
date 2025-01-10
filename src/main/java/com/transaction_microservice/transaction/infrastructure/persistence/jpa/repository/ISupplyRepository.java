package com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository;


import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ISupplyRepository extends JpaRepository<SupplyEntity, Long> {


}
