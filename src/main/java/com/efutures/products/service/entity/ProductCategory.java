package com.efutures.products.service.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "productList")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_category")
public class ProductCategory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true,columnDefinition = "VARCHAR(100)")
    private String name;
    @Column(nullable = false,columnDefinition = "VARCHAR(500)")
    private String description;

    @OneToMany(mappedBy = "productCategory",fetch = FetchType.LAZY)
    private List<Product> productList = new ArrayList<>();
    public ProductCategory(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
