package com.alpha.studio.code.university.project.domain.useCase.product;

import com.alpha.studio.code.university.project.domain.model.Product;
import com.alpha.studio.code.university.project.domain.useCase.UseCase;

public interface DeleteProductUseCase extends UseCase<Integer, String> {
    @Override
    String apply(Integer command);
}