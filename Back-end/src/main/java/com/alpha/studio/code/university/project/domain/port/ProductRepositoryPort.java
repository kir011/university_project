package com.alpha.studio.code.university.project.domain.port;

import com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.product.model.ProductDocument;

import java.util.List;

public interface ProductRepositoryPort {
    public ProductDocument saveProduct(ProductDocument productDocument);

    public List<ProductDocument> saveProducts(List<ProductDocument> productDocuments);

    public List<ProductDocument> getProducts();

    public ProductDocument getProductById(int id);

    public ProductDocument getProductByName(String name);

    public String deleteProduct(int id);

    public ProductDocument updateProduct(ProductDocument productDocument);
    public List<ProductDocument> findAll();
}
