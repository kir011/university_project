package com.alpha.studio.code.university.project.commons.utils;

import com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.product.model.ProductDocument;
import com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.user.UserDocument;
import com.alpha.studio.code.university.project.domain.model.Product;
import com.alpha.studio.code.university.project.domain.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassConverter {
    public static Product converter(ProductDocument product) {
        return Product.builder()
                .id(product.getId())
                .quantity(product.getQuantity())
                .type(product.getType())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .archieve(product.getArchieve())
                .build();
    }

    public static ProductDocument converterDocument(Product product) {
        return ProductDocument.builder()
                .id(product.getId())
                .quantity(product.getQuantity())
                .type(product.getType())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .archieve(product.getArchieve())
                .build();
    }

    public static User converterUser(UserDocument user) {
        return User.builder()
                .admin(user.isAdmin())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    public static UserDocument converterDocumentUser(User user) {
        return UserDocument.builder()
                .admin(user.isAdmin())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    public static UserDocument converterUserOptional(Optional<UserDocument> user) {
        return UserDocument.builder()
                .admin(user.get().isAdmin())
                .login(user.get().getLogin())
                .password(user.get().getPassword())
                .build();
    }

    public static List<Product> converterList(List<ProductDocument> productDocuments) {
        List<Product> productList = new ArrayList<>();
        productDocuments.forEach(element -> productList.add(
                Product
                        .builder()
                        .id(element.getId())
                        .type(element.getType())
                        .price(element.getPrice())
                        .archieve(element.getArchieve())
                        .description(element.getDescription())
                        .name(element.getName())
                        .quantity(element.getQuantity())
                        .build()));
        return productList;
    }
}
