package codes.laivy.serializable.adapter;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public abstract class ReferenceAdapter implements Adapter {

    // Getters

    public abstract @NotNull Collection<Class<?>> getReferences();

    // Modules

    @Override
    public final boolean isAssignableFrom(@NotNull Class<?> clazz) {
        return getReferences().contains(clazz);
    }

}
