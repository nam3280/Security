package com.ssg.wannavapibackend.domain;

import com.ssg.wannavapibackend.common.Category;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> image;

    @Column(name = "cost_price", nullable = false)
    private double costPrice;

    @Column(name = "selling_price", nullable = false)
    private double sellingPrice;

    @Column(name = "discount_rate", nullable = false)
    @ColumnDefault("0")
    private int discountRate;

    @Column(name = "final_price", nullable = false)
    private double finalPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false, columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> description;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("0")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false)
    private Admin createdById;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_id")
    private Admin updatedById;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private LocalDateTime updatedAt;
}
