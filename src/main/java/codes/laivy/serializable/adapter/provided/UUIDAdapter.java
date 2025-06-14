package codes.laivy.serializable.adapter.provided;

import codes.laivy.serializable.Serializer;
import codes.laivy.serializable.adapter.ReferenceAdapter;
import codes.laivy.serializable.config.Config;
import codes.laivy.serializable.context.Context;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.EOFException;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public final class UUIDAdapter extends ReferenceAdapter {

    @Override
    public @NotNull Collection<@NotNull Class<?>> getReferences() {
        return Collections.singletonList(UUID.class);
    }

    @Override
    public @Nullable Object write(@NotNull Class<?> reference, @Nullable Object object, @NotNull Serializer serializer, @NotNull Config config) {
        if (object == null) {
            return null;
        }

        return object.toString();
    }
    @Override
    public @NotNull Object read(@NotNull Class<?> reference, @NotNull Serializer serializer, @NotNull Context context, @NotNull Config config) throws EOFException {
        return UUID.fromString(context.getAsPrimitive().getAsString());
    }

}
