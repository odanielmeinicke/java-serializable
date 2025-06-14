package codes.laivy.serializable.adapter.provided;

import codes.laivy.serializable.Serializer;
import codes.laivy.serializable.adapter.ReferenceAdapter;
import codes.laivy.serializable.config.Config;
import codes.laivy.serializable.context.Context;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public final class ClassAdapter extends ReferenceAdapter {

    @Override
    public @NotNull Collection<@NotNull Class<?>> getReferences() {
        return Collections.singletonList(Class.class);
    }

    @Override
    public @NotNull String write(@NotNull Class<?> reference, @Nullable Object object, @NotNull Serializer serializer, @NotNull Config config) {
        if (object instanceof Class) {
            return ((Class<?>) object).getName();
        } else {
            throw new UnsupportedOperationException("the class adapter only support java.lang.Class objects!");
        }
    }
    @Override
    public @NotNull Class<?> read(@NotNull Class<?> reference, @NotNull Serializer serializer, @NotNull Context context, @NotNull Config config) throws IOException, InstantiationException {
        if (context.isPrimitive()) try {
            return Class.forName(context.getAsPrimitive().getAsString());
        } catch (@NotNull ClassNotFoundException e) {
            throw new RuntimeException(e);
        } else {
            throw new UnsupportedOperationException("the context must be a primitive containing the class name!");
        }
    }

}
