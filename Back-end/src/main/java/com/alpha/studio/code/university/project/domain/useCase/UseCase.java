package com.alpha.studio.code.university.project.domain.useCase;

import java.util.function.Function;

@FunctionalInterface
public interface UseCase<C, R> extends Function<C, R> {
    R apply(C command);

    interface Query extends In {
    }

    interface In {
    }
}
