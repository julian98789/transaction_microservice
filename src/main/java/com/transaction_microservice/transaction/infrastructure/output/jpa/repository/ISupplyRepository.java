package com.transaction_microservice.transaction.infrastructure.output.jpa.repository;

import com.transaction_microservice.transaction.domain.util.Util;
import com.transaction_microservice.transaction.infrastructure.output.jpa.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ISupplyRepository extends JpaRepository<SupplyEntity, Long> {

    @Query(Util.FIND_NEXT_SUPPLY_DATE_BY_ARTICLE_ID_QUERY)
    LocalDate findNextSupplyDateByArticleId(@Param(Util.ARTICLE_ID_PARAM) Long articleId);
}
