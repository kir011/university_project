package com.alpha.studio.code.university.project.domain.useCase.product;


import com.alpha.studio.code.university.project.domain.model.Product;
import com.alpha.studio.code.university.project.domain.useCase.UseCase;
import lombok.Builder;
import lombok.Data;

public interface GetProductUseCase extends UseCase<GetProductUseCase.SearchProduct, Product> {

    @Override
    Product apply(SearchProduct command);

    @Data
    @Builder
    class SearchProduct implements UseCase.Query {
        private int id;
    }
}
