import codes.laivy.serializable.Serializer;
import codes.laivy.serializable.annotations.Concrete;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

public final class DebugTest {

    @Test
    public void test() {
        @NotNull Cool[] cools = new Cool[] {
                new CoolImpl(),
                new CoolImpl()
        };

        System.out.println(Serializer.toJson(cools));
        Serializer.fromJson(Cool[].class, Serializer.toJson(cools));
    }

    @Concrete(value = CoolImpl.class)
    public static abstract class Cool {
    }
    public static final class CoolImpl extends Cool {

    }

}
