package codes.laivy.serializable.adapter.provided;

import codes.laivy.serializable.Serializer;
import codes.laivy.serializable.adapter.ReferenceAdapter;
import codes.laivy.serializable.config.Config;
import codes.laivy.serializable.context.ArrayContext;
import codes.laivy.serializable.context.Context;
import codes.laivy.serializable.context.PrimitiveContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.io.EOFException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public final class CharacterArrayAdapter extends ReferenceAdapter {

    @Override
    public @NotNull Collection<@NotNull Class<?>> getReferences() {
        return Arrays.asList(
                Character[].class,
                char[].class
        );
    }

    @Override
    public @Nullable Context write(@NotNull Class<?> reference, @Nullable Object object, @NotNull Serializer serializer, @NotNull Config config) {
        if (object == null) {
            return null;
        }

        if (object instanceof Character[]) {
            @Nullable Character[] instance = (Character[]) object;

            boolean anyNull = Arrays.stream(instance).anyMatch(Objects::isNull);

            if (anyNull) { // There's an impostor here...
                @NotNull ArrayContext context = ArrayContext.create(serializer);

                for (@Nullable Character c : instance) {
                    context.write(c, Config.builder(serializer, Character.class).build());
                }

                return context;
            } else { // All clean to be a plain text!
                @NotNull StringBuilder builder = new StringBuilder();
                for (@UnknownNullability Character c : instance) {
                    builder.append(c.charValue());
                }

                return PrimitiveContext.create(builder.toString());
            }
        } else {
            return PrimitiveContext.create(new String((char[]) object));
        }
    }

    @Override
    public @NotNull Object read(@NotNull Class<?> reference, @NotNull Serializer serializer, @NotNull Context context, @NotNull Config config) throws EOFException {
        if (reference == char[].class) {
            if (context.isPrimitive()) {
                return context.getAsPrimitive().getAsString().toCharArray();
            } else if (context.isArray()) {
                @NotNull ArrayContext iterable = context.getAsArray();
                @NotNull StringBuilder builder = new StringBuilder();

                while (true) {
                    try {
                        builder.append(iterable.readChar());
                    } catch (@NotNull EOFException ignore) {
                        return builder.toString().toCharArray();
                    }
                }
            } else {
                throw new UnsupportedOperationException("context not supported by char[] adapter '" + context + "'");
            }
        } else if (reference == Character[].class) {
            if (context.isPrimitive()) {
                char[] original = context.getAsPrimitive().getAsString().toCharArray();
                @NotNull Character[] characters = new Character[original.length];

                for (int index = 0; index < original.length; index++) {
                    characters[index] = original[index];
                }

                return characters;
            } else if (context.isArray()) {
                @NotNull ArrayContext iterable = context.getAsArray();
                @NotNull StringBuilder builder = new StringBuilder();

                while (true) {
                    try {
                        builder.append((Character) iterable.readChar());
                    } catch (@NotNull EOFException ignore) {
                        return builder.toString().toCharArray();
                    }
                }
            } else {
                throw new UnsupportedOperationException("context not supported by Character[] adapter '" + context + "'");
            }
        } else {
            throw new UnsupportedOperationException("the reference '" + reference + "' cannot be used at the character array adapter");
        }
    }

}
