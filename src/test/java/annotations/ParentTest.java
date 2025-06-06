package annotations;

import codes.laivy.serializable.Serializer;
import codes.laivy.serializable.annotations.Parent;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class ParentTest {

    @Test
    @DisplayName("Test normally")
    public void normal() {
        @NotNull A object = new A();
        @NotNull JsonObject json = Serializer.toJson(object).getAsJsonObject();

        System.out.println(json);
    }

    // Classes

    private static final class A {

        private final @NotNull B field = new B(this);

        private A() {
        }

    }
    private static final class B {

        @Parent
        private final @NotNull A field;

        private B(@NotNull A field) {
            this.field = field;
        }

    }

}
