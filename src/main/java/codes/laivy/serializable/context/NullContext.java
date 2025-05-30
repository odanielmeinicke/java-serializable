package codes.laivy.serializable.context;

import codes.laivy.serializable.annotations.Concrete;
import org.jetbrains.annotations.NotNull;

@Concrete(value = NullContextImpl.class)
public interface NullContext extends Context {

    static @NotNull NullContext create() {
        return new NullContextImpl();
    }

}
