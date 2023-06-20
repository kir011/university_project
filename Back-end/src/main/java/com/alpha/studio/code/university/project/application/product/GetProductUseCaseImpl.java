package com.alpha.studio.code.university.project.application.product;

import com.alpha.studio.code.university.project.domain.model.Product;
import com.alpha.studio.code.university.project.domain.port.ProductRepositoryPort;
import com.alpha.studio.code.university.project.domain.useCase.product.GetProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.alpha.studio.code.university.project.commons.utils.ClassConverter.converter;

@Service
@RequiredArgsConstructor
public class GetProductUseCaseImpl implements GetProductUseCase {

    private final ProductRepositoryPort repositoryPort;

    @Override
    public Product apply(SearchProduct command) {
        return converter(repositoryPort.getProductById(command.getId()));
    }
}
