package com.example;

import io.micronaut.configuration.hibernate.jpa.proxy.GenerateProxy;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@Entity
@Serdeable
@GenerateProxy
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Hibernate and Micronaut Data only
     */
    @Creator
    @Deprecated
    Product() {}

    public Product(
            String title,
            BigDecimal price,
            Clock clock
    ) {
        this.title = title;
        this.price = price;
        this.createdAt = LocalDateTime.now(clock);
        this.updatedAt = LocalDateTime.now(clock);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        assert this.id == null;

        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update(BigDecimal price, Clock clock) {
        this.price = price;
        this.updatedAt = LocalDateTime.now(clock);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
