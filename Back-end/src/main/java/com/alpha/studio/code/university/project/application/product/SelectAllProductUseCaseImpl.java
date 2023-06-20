package com.alpha.studio.code.university.project.application.product;

import com.alpha.studio.code.university.project.domain.model.Product;
import com.alpha.studio.code.university.project.domain.port.ProductRepositoryPort;
import com.alpha.studio.code.university.project.domain.useCase.product.SelectAllProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alpha.studio.code.university.project.commons.utils.ClassConverter.converterList;

@RequiredArgsConstructor
@Service
public class SelectAllProductUseCaseImpl implements SelectAllProductUseCase {
    private final ProductRepositoryPort repositoryPort;
    @Override
    public List<Product> apply(Boolean command) {
        return converterList(repositoryPort.findAll());
    }
}
