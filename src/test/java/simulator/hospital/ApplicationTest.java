package simulator.hospital;

import org.junit.jupiter.api.Test;

import static simulator.hospital.Application.USAGE_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationTest {

    @Test
    void testMainWithInvalidNumberOfArguments() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Application.main(new String[]{}); // No arguments
        });
        assertEquals(USAGE_MESSAGE, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Application.main(new String[]{"F,H,D", "I,As", "ExtraArg"}); // More than 2 arguments
        });
        assertEquals(USAGE_MESSAGE, exception.getMessage());
    }
}