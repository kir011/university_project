package com.alpha.studio.code.university.project.application.product;

import com.alpha.studio.code.university.project.domain.model.Product;
import com.alpha.studio.code.university.project.domain.port.ProductRepositoryPort;
import com.alpha.studio.code.university.project.domain.useCase.product.AddProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.alpha.studio.code.university.project.commons.utils.ClassConverter.converter;
import static com.alpha.studio.code.university.project.commons.utils.ClassConverter.converterDocument;

@Service
@RequiredArgsConstructor
public class AddProductUseCaseImpl implements AddProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Product apply(Product command) {
        return converter(productRepositoryPort.saveProduct(converterDocument(command)));
    }
}
