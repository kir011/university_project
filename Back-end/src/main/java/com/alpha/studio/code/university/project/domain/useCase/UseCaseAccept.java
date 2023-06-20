package com.alpha.studio.code.university.project.domain.useCase;

import java.util.function.Consumer;

@FunctionalInterface
public interface UseCaseAccept<C> extends Consumer<C> {
    void accept(C command);

    interface Query extends In {
    }

    interface In {
    }
}
