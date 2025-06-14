package codes.laivy.serializable;

import codes.laivy.serializable.adapter.Adapter;
import codes.laivy.serializable.adapter.ReferenceAdapter;
import codes.laivy.serializable.adapter.provided.*;
import codes.laivy.serializable.config.Config;
import codes.laivy.serializable.context.Context;
import codes.laivy.serializable.exception.IncompatibleReferenceException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractTypeSerializer<T> implements TypeSerializer<T> {

    // Object

    protected final @NotNull Set<Adapter> adapters = new LinkedHashSet<>();

    public AbstractTypeSerializer() {
        @NotNull Adapter[] adapters = new Adapter[]{
                new TemporalAdapter(),
                new CharacterArrayAdapter(),
                new UUIDAdapter(),
                new CollectionAdapter(),
                new GsonAdapter(),
                new MapAdapter(),
                new ClassAdapter()
        };

        this.adapters.addAll(Arrays.asList(adapters));
    }

    // Adapters

    @Override
    public @NotNull Collection<Adapter> getAdapters() {
        return adapters;
    }
    @Override
    public @NotNull Optional<Adapter> getAdapter(@NotNull Class<?> reference) {
        @NotNull List<Adapter> adapters = this.adapters.stream().filter(adapter -> {
            if (adapter instanceof ReferenceAdapter) {
                return ((ReferenceAdapter) adapter).getReferences().contains(reference);
            } else {
                return adapter.isAssignableFrom(reference);
            }
        }).collect(Collectors.toList());

        if (adapters.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(adapters.get(adapters.size() - 1));
        }
    }

    // Serializable

    @Override
    public @Nullable T serialize(@Nullable Serializable object) {
        return serialize(object, object != null ? Config.builder(this, object.getClass()).build() : Config.builder().build());
    }

    @Override
    public @NotNull Iterable<T> serialize(@Nullable Serializable @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Serializable object : array) {
            if (object != null) {
                list.add(serialize(object, Config.builder(this, object.getClass()).build()));
            } else {
                list.add(null);
            }
        }

        return list;
    }

    @Override
    public @NotNull Iterable<T> serialize(@NotNull Iterable<@Nullable Serializable> iterable) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Serializable object : iterable) {
            if (object != null) {
                list.add(serialize(object, Config.builder(this, object.getClass()).build()));
            } else {
                list.add(null);
            }
        }

        return list;
    }
    @Override
    public @NotNull Iterable<T> serialize(@NotNull Iterable<@Nullable Serializable> iterable, @NotNull Config config) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Serializable object : iterable) {
            if (object != null) {
                list.add(serialize(object, config));
            } else {
                list.add(null);
            }
        }

        return list;
    }

    // Enum

    @Override
    public @Nullable T serialize(@Nullable Enum<?> e) {
        return serialize(e, e != null ? Config.builder(this, e.getClass()).build() : Config.builder().build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable Enum<?> @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Enum<?> e : array) {
            if (e != null) {
                list.add(serialize(e, Config.builder(this, e.getClass()).build()));
            } else {
                list.add(null);
            }
        }

        return list;
    }

    // Boolean

    @Override
    public @NotNull T serialize(boolean b, @NotNull Config config) {
        return Objects.requireNonNull(serialize((Boolean) b, config));
    }

    @Override
    public @Nullable T serialize(@Nullable Boolean b) {
        return serialize(b, Config.builder(this, Boolean.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable Boolean @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Boolean bool : array) {
            list.add(serialize(bool));
        }

        return list;
    }

    @Override
    public @NotNull T serialize(boolean b) {
        return Objects.requireNonNull(serialize(b, Config.builder(this, boolean.class).build()));
    }
    @Override
    public @NotNull Iterable<T> serialize(boolean @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (boolean bool : array) {
            list.add(serialize(bool));
        }

        return list;
    }

    // Short

    @Override
    public @NotNull T serialize(short s, @NotNull Config config) {
        return Objects.requireNonNull(serialize((Short) s, config));
    }

    @Override
    public @Nullable T serialize(@Nullable Short s) {
        return serialize(s, Config.builder(this, Short.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable Short @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Short s : array) {
            list.add(serialize(s));
        }

        return list;
    }

    @Override
    public @NotNull T serialize(short s) {
        return Objects.requireNonNull(serialize(s, Config.builder(this, short.class).build()));
    }
    @Override
    public @NotNull Iterable<T> serialize(short @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (short s : array) {
            list.add(serialize(s));
        }

        return list;
    }

    // Integer

    @Override
    public @NotNull T serialize(int i, @NotNull Config config) {
        return Objects.requireNonNull(serialize((Integer) i, config));
    }

    @Override
    public @Nullable T serialize(@Nullable Integer i) {
        return serialize(i, Config.builder(this, Integer.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable Integer @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Integer i : array) {
            list.add(serialize(i));
        }

        return list;
    }

    @Override
    public @NotNull T serialize(int i) {
        return Objects.requireNonNull(serialize(i, Config.builder(this, int.class).build()));
    }
    @Override
    public @NotNull Iterable<T> serialize(int @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (int i : array) {
            list.add(serialize(i));
        }

        return list;
    }

    // Long

    @Override
    public @NotNull T serialize(long l, @NotNull Config config) {
        return Objects.requireNonNull(serialize((Long) l, config));
    }

    @Override
    public @Nullable T serialize(@Nullable Long l) {
        return serialize(l, Config.builder(this, Long.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable Long @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Long l : array) {
            list.add(serialize(l));
        }

        return list;
    }

    @Override
    public @NotNull T serialize(long l) {
        return Objects.requireNonNull(serialize(l, Config.builder(this, long.class).build()));
    }
    @Override
    public @NotNull Iterable<T> serialize(long @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (long l : array) {
            list.add(serialize(l));
        }

        return list;
    }

    // Float

    @Override
    public @NotNull T serialize(float f, @NotNull Config config) {
        return Objects.requireNonNull(serialize((Float) f, config));
    }

    @Override
    public @Nullable T serialize(@Nullable Float f) {
        return serialize(f, Config.builder(this, Float.class).build());
    }
    @Override
    public @NotNull T serialize(float f) {
        return Objects.requireNonNull(serialize(f, Config.builder(this, float.class).build()));
    }

    @Override
    public @NotNull Iterable<T> serialize(@Nullable Float @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Float f : array) {
            list.add(serialize(f));
        }

        return list;
    }
    @Override
    public @NotNull Iterable<T> serialize(float @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (float f : array) {
            list.add(serialize(f));
        }

        return list;
    }

    // Double

    @Override
    public @NotNull T serialize(double d, @NotNull Config config) {
        return Objects.requireNonNull(serialize((Double) d, config));
    }

    @Override
    public @Nullable T serialize(@Nullable Double d) {
        return serialize(d, Config.builder(this, Double.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable Double @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Double d : array) {
            list.add(serialize(d));
        }

        return list;
    }

    @Override
    public @NotNull T serialize(double d) {
        return Objects.requireNonNull(serialize(d, Config.builder(this, double.class).build()));
    }
    @Override
    public @NotNull Iterable<T> serialize(double @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (double d : array) {
            list.add(serialize(d));
        }

        return list;
    }

    // Character

    @Override
    public @NotNull T serialize(char c, @NotNull Config config) {
        return Objects.requireNonNull(serialize((Character) c, config));
    }

    @Override
    public @Nullable T serialize(@Nullable Character c) {
        return serialize(c, c != null ? Config.builder(this, c.getClass()).build() : Config.builder(this, Character.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable Character @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Character c : array) {
            list.add(serialize(c));
        }

        return list;
    }

    @Override
    public @NotNull T serialize(char c) {
        return serialize(c, Config.builder(this, char.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(char @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (char c : array) {
            list.add(serialize(c));
        }

        return list;
    }

    // Byte

    @Override
    public @NotNull T serialize(byte b, @NotNull Config config) {
        return Objects.requireNonNull(serialize((Byte) b, config));
    }

    @Override
    public @Nullable T serialize(@Nullable Byte b) {
        return serialize(b, Config.builder(this, Byte.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable Byte @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Byte b : array) {
            list.add(serialize(b));
        }

        return list;
    }

    @Override
    public @NotNull T serialize(byte b) {
        return serialize(b, Config.builder(this, byte.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(byte @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (byte b : array) {
            list.add(serialize(b));
        }

        return list;
    }

    // String

    @Override
    public @Nullable T serialize(@Nullable String string) {
        return serialize(string, Config.builder(this, String.class).build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable String @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable String string : array) {
            list.add(serialize(string));
        }

        return list;
    }

    // Pure

    @Override
    public @Nullable T serialize(@Nullable Object object) {
        return serialize(object, object != null ? Config.builder(this, object.getClass()).build() : Config.builder().build());
    }
    @Override
    public @NotNull Iterable<T> serialize(@Nullable Object @NotNull ... array) {
        @NotNull List<T> list = new LinkedList<>();

        for (@Nullable Object object : array) {
            if (object != null) {
                list.add(serialize(object));
            } else {
                list.add(null);
            }
        }

        return list;
    }

    // Redirect to the #serialize(Object, Config) method

    @Override
    public @Nullable T serialize(@Nullable Serializable object, @NotNull Config config) {
        return serialize((Object) object, config);
    }
    @Override
    public @Nullable T serialize(@Nullable Enum<?> e, @NotNull Config config) {
        return serialize((Object) e, config);
    }
    @Override
    public @Nullable T serialize(@Nullable Boolean b, @NotNull Config config) {
        return serialize((Object) b, config);
    }
    @Override
    public @Nullable T serialize(@Nullable Short s, @NotNull Config config) {
        return serialize((Object) s, config);
    }
    @Override
    public @Nullable T serialize(@Nullable Integer i, @NotNull Config config) {
        return serialize((Object) i, config);
    }
    @Override
    public @Nullable T serialize(@Nullable Long l, @NotNull Config config) {
        return serialize((Object) l, config);
    }
    @Override
    public @Nullable T serialize(@Nullable Float f, @NotNull Config config) {
        return serialize((Object) f, config);
    }
    @Override
    public @Nullable T serialize(@Nullable Double d, @NotNull Config config) {
        return serialize((Object) d, config);
    }
    @Override
    public @Nullable T serialize(@Nullable Character c, @NotNull Config config) {
        return serialize((Object) c, config);
    }
    @Override
    public @Nullable T serialize(@Nullable Byte b, @NotNull Config config) {
        return serialize((Object) b, config);
    }
    @Override
    public @Nullable T serialize(@Nullable String string, @NotNull Config config) {
        return serialize((Object) string, config);
    }

    // Deserialization redirects

    @Override
    public @NotNull <E> Iterable<@Nullable E> deserialize(@NotNull Class<E> reference, @Nullable T @NotNull [] array) throws IncompatibleReferenceException {
        @NotNull List<E> list = new LinkedList<>();

        for (@Nullable T serialized : array) {
            list.add(deserialize(reference, serialized));
        }

        return list;
    }
    @Override
    public @NotNull <E> Iterable<@Nullable E> deserialize(@NotNull Class<E> reference, @NotNull Iterable<@Nullable T> array) throws IncompatibleReferenceException {
        @NotNull List<E> list = new LinkedList<>();

        for (@Nullable T serialized : array) {
            list.add(deserialize(reference, serialized));
        }

        return list;
    }

    @Override
    public <E> @Nullable E deserialize(@NotNull Class<E> reference, @Nullable T element) throws IncompatibleReferenceException {
        return deserialize(reference, element, Config.builder(this, reference).build());
    }

    @Override
    public @NotNull <E> Iterable<@Nullable E> deserialize(@NotNull Class<E> reference, @NotNull Iterable<@Nullable T> iterable, @NotNull Config config) throws IncompatibleReferenceException {
        @NotNull List<E> list = new LinkedList<>();

        for (@Nullable T serialized : iterable) {
            list.add(deserialize(reference, serialized, config));
        }

        return list;
    }

    @Override
    public <E> @Nullable E deserialize(@NotNull Class<E> reference, @NotNull Context context, @NotNull Config config) throws IncompatibleReferenceException {
        @Nullable Object object = deserializeUnsafe(reference, context, config);

        if (object != null && !Allocator.isAssignableFromIncludingPrimitive(reference, object.getClass())) {
            throw new ClassCastException("the returned object from deserialization is '" + object.getClass() + "' and cannot be cast to '" + reference.getName() + "'");
        }

        //noinspection unchecked
        return (E) object;
    }
    @Override
    public <E> @Nullable E deserialize(@NotNull Class<E> reference, @Nullable T element, @NotNull Config config) throws IncompatibleReferenceException {
        @Nullable Object object = deserializeUnsafe(reference, element, config);

        if (object != null && !Allocator.isAssignableFromIncludingPrimitive(reference, object.getClass())) {
            throw new ClassCastException("the returned object from deserialization is '" + object.getClass() + "' and cannot be cast to '" + reference.getName() + "'");
        }

        //noinspection unchecked
        return (E) object;
    }

    @Override
    public <E> @Nullable E deserialize(@NotNull Class<E> reference, @NotNull Context context) throws IncompatibleReferenceException {
        return deserialize(reference, context, Config.builder(this, reference).build());
    }
    @Override
    public @Nullable Object deserializeUnsafe(@NotNull Class<?> reference, @NotNull Context context) throws IncompatibleReferenceException {
        return deserializeUnsafe(reference, context, Config.builder(this, reference).build());
    }
    @Override
    public @Nullable Object deserializeUnsafe(@NotNull Class<?> reference, @Nullable T element) throws IncompatibleReferenceException {
        return deserializeUnsafe(reference, element, Config.builder(this, reference).build());
    }

    @Override
    public @NotNull Context toContext(@Nullable Object object) {
        return toContext(object, object != null ? Config.builder(this, object.getClass()).build() : Config.builder().build());
    }

}
