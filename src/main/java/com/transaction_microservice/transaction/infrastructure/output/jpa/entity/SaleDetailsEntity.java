package com.transaction_microservice.transaction.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sale_details")
public class SaleDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_details_id")
    private Long saleDetailsId;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private SalesEntity sale;

    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    public Double calculateSubtotal() {
        setSubtotal(this.price * this.quantity);
        return this.subtotal;
    }
}
