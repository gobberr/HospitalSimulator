package simulator.hospital.parser;

import simulator.hospital.enums.Condition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutputParserTest {

    @Test
    void testPrintPatients() {
        List<Condition> patients = List.of(
                Condition.FEVER, Condition.HEALTHY, Condition.DIABETES,
                Condition.DEAD, Condition.FEVER, Condition.HEALTHY, Condition.HEALTHY
        );
        String result = OutputParser.printPatients(patients);
        assertEquals("F:2,H:3,D:1,T:0,X:1", result);
    }

}