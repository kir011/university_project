package com.alpha.studio.code.university.project.domain.useCase.product;

import com.alpha.studio.code.university.project.domain.model.Product;
import com.alpha.studio.code.university.project.domain.useCase.UseCase;

public interface UpdateProductUseCase extends UseCase<Product, Product> {
    @Override
    Product apply(Product command);
}
