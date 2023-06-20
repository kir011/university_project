package com.alpha.studio.code.university.project.domain.useCase.product;

import com.alpha.studio.code.university.project.domain.model.Product;
import com.alpha.studio.code.university.project.domain.useCase.UseCase;

import java.util.List;

public interface SelectAllProductUseCase extends UseCase<Boolean, List<Product>> {
    @Override
    List<Product> apply(Boolean command);
}
