package com.alpha.studio.code.university.project.adapter.rest.v1.product.controller;

import com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.product.model.ProductDocument;
import com.alpha.studio.code.university.project.domain.model.Product;
import com.alpha.studio.code.university.project.domain.useCase.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alpha.studio.code.university.project.commons.utils.ClassConverter.converter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final GetProductUseCase getProductUseCase;
    private final AddProductUseCase addProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final SelectAllProductUseCase selectAllProductUseCase;

    //@CrossOrigin(value = "*")
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody ProductDocument productDocument){
        return addProductUseCase.apply(converter(productDocument));
    }

    //@CrossOrigin(value = "*")
    @GetMapping("/getProduct/{id}")
    public Product getById(@PathVariable int id){
        return getProductUseCase.apply(GetProductUseCase.SearchProduct.builder().id(id).build());
    }

    //@CrossOrigin(value = "*")
    @GetMapping("/getProduct")
    public List<Product> getAll(){
        return selectAllProductUseCase.apply(true);
    }

    //@CrossOrigin(value = "*")
    @PutMapping("/updateProduct")
    public Product updateDocument(@RequestBody ProductDocument document){
        return updateProductUseCase.apply(converter(document));
    }

    //@CrossOrigin(value = "*")
    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return deleteProductUseCase.apply(id);
    }

}
