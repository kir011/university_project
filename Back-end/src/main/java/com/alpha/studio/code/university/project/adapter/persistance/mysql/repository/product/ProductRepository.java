package com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.product;

import com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.product.model.ProductDocument;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<ProductDocument, Integer> {
    ProductDocument findByName(String name);
}
