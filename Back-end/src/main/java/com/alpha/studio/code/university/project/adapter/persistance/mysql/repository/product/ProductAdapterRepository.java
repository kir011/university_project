package com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.product;

import com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.product.model.ProductDocument;
import com.alpha.studio.code.university.project.domain.port.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ProductAdapterRepository implements ProductRepositoryPort {
    private final ProductRepository repository;

    @Override
    public ProductDocument saveProduct(ProductDocument productDocument){
        return repository.save(productDocument);
    }
    @Override
    public List<ProductDocument> saveProducts(List<ProductDocument> productDocuments){
        return repository.saveAll(productDocuments);
    }
    @Override
    public List<ProductDocument> getProducts(){
        return repository.findAll();
    }
    @Override
    public ProductDocument getProductById(int id){
        return repository.findById(id).orElse(null);
    }
    @Override
    public ProductDocument getProductByName(String name){
        return repository.findByName(name);
    }
    @Override
    public String deleteProduct(int id){
        repository.deleteById(id);
        return "Objeto deletado";
    }

    @Override
    public ProductDocument updateProduct(ProductDocument productDocument){
        ProductDocument existingProductDocument = repository.findById(productDocument.getId()).orElse(null);
        existingProductDocument.setName(productDocument.getName());
        existingProductDocument.setType(productDocument.getType());
        existingProductDocument.setDescription(productDocument.getDescription());
        existingProductDocument.setQuantity(productDocument.getQuantity());
        existingProductDocument.setPrice(productDocument.getPrice());
        return repository.save(existingProductDocument);
    }
    @Override
    public List<ProductDocument> findAll(){
        return repository.findAll();
    }
}
