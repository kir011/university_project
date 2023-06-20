package com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_product")
public class ProductDocument {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String archieve;
    private int quantity;
    private String description;
    private String type;
    private double price;

}
