package simulator.hospital.parser;

import simulator.hospital.enums.Condition;
import simulator.hospital.enums.Drug;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {

    // PATIENTS

    @Test
    void testParsePatientsWithValidInput() {
        List<Condition> result = InputParser.parsePatients("F,H,D");
        assertEquals(List.of(Condition.FEVER, Condition.HEALTHY, Condition.DIABETES), result);
    }

    @Test
    void testParsePatientsWithEmptyInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                InputParser.parsePatients("")
        );
        assertEquals("Unknown condition code: . Accepted codes are [F, H, D, T, X]", exception.getMessage());
    }

    @Test
    void testParsePatientsWithInvalidInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            InputParser.parsePatients("F,Z,H")
        );
        assertEquals("Unknown condition code: Z. Accepted codes are [F, H, D, T, X]", exception.getMessage());
    }

    // DRUGS

    @Test
    void testParseDrugsWithValidInput() {
        Set<Drug> result = InputParser.parseDrugs("I,As");
        assertEquals(Set.of(Drug.INSULIN, Drug.ASPIRIN), result);
    }

    @Test
    void testParseDrugsWithEmptyInput() {
        Set<Drug> result = InputParser.parseDrugs("");
        assertEquals(Collections.emptySet(), result);
    }

    @Test
    void testParseDrugsWithInvalidInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            InputParser.parseDrugs("I,Z")
        );
        assertEquals("Unknown drug code: Z. Accepted codes are [As, An, I, P]", exception.getMessage());
    }
}