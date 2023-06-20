package com.alpha.studio.code.university.project.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String name;
    private String archieve;
    private int quantity;
    private String description;
    private String type;
    private double price;

}
