package annotations;

import codes.laivy.serializable.Serializer;
import codes.laivy.serializable.annotations.KnownAs;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class KnownAsTest {

    @Test
    @DisplayName("Test the @KnownAs annotation")
    public void normal() {
        @NotNull Cool cool = new Cool("Laivy", 19);
        @NotNull JsonObject object = Serializer.toJson(cool).getAsJsonObject();

        Assertions.assertTrue(object.has("name"), "missing 'name' field at the json object");
        Assertions.assertTrue(object.has("age"), "missing 'age' field at the json object");
        Assertions.assertTrue(object.has("without"), "missing 'without' field at the json object");
    }

    @Test
    @DisplayName("Expect fail with multiples annotations with same name")
    public void multiples() {
        try {
            @NotNull MultiplesWithSameName object = new MultiplesWithSameName("I'm", "Cool!");
            Serializer.toJson(object).getAsJsonObject();

            Assertions.fail("Didn't failed even with two @KnownAs annotations with the same name");
        } catch (@NotNull IllegalStateException ignore) {
        }
    }

    // Classes

    @SuppressWarnings("FieldCanBeLocal")
    private static final class Cool {

        @KnownAs(value = "name")
        private final @NotNull String daiwdnaidn;

        @KnownAs(value = "age")
        private final int daifjaiwjd;

        private final int without = -1;

        private Cool(@NotNull String name, int age) {
            this.daiwdnaidn = name;
            this.daifjaiwjd = age;
        }

    }
    @SuppressWarnings("FieldCanBeLocal")
    private static final class MultiplesWithSameName {

        @KnownAs(value = "name")
        private final @NotNull String name_1;
        @KnownAs(value = "name")
        private final @NotNull String name_2;

        private MultiplesWithSameName(@NotNull String name_1, @NotNull String name_2) {
            this.name_1 = name_1;
            this.name_2 = name_2;
        }

    }

}
