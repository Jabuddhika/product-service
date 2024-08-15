package com.efutures.products.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,columnDefinition = "VARCHAR(100)")
    private String name;
    @Column(columnDefinition = "VARCHAR(500)")
    private String description;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price;
    @Column(columnDefinition = "CHAR(1)")
    private Character status;
    @Column(name = "launch_date",nullable = false)
    private Date launchDate;

    @ManyToOne
    @JoinColumn(name = "product_category_id",referencedColumnName = "id",nullable = false)
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<ProductComment> productCommentList = new ArrayList<>();

    public Product(String name, String description, BigDecimal price, Character status, Date launchDate, ProductCategory productCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.launchDate = launchDate;
        this.productCategory = productCategory;
    }
}
