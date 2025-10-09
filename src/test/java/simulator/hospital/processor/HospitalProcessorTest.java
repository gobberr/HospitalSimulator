package simulator.hospital.processor;

import simulator.hospital.enums.Condition;
import simulator.hospital.enums.Drug;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HospitalProcessorTest {

    private static HospitalProcessor processor;

    @BeforeAll
    static void setup() {
        processor = new HospitalProcessor();
    }

    // INSULIN

    @Test
    void testDiabeticPatientWithoutInsulinDies() {
        List<Condition> result = processor.process(List.of(Condition.DIABETES), Collections.emptySet());
        assertEquals(List.of(Condition.DEAD), result);
    }

    @Test
    void testDiabeticPatientWithInsulinSurvives() {
        List<Condition> result = processor.process(List.of(Condition.DIABETES), Set.of(Drug.INSULIN));
        assertEquals(List.of(Condition.DIABETES), result);
    }

    // ASPIRIN

    @Test
    void testFeverPatientWithAspirinBecomesHealthy() {
        List<Condition> result = processor.process(List.of(Condition.FEVER), Set.of(Drug.ASPIRIN));
        assertEquals(List.of(Condition.HEALTHY), result);
    }

    // PARACETAMOL

    @Test
    void testParacetamolWithAspirinCausesDeath() {
        Set<Drug> drugs = Set.of(Drug.PARACETAMOL, Drug.ASPIRIN);
        List<Condition> result = processor.process(List.of(Condition.FEVER), drugs);
        assertEquals(List.of(Condition.DEAD), result);
    }

    @Test
    void testParacetamolCuresFever() {
        List<Condition> result = processor.process(List.of(Condition.FEVER), Set.of(Drug.PARACETAMOL));
        assertEquals(List.of(Condition.HEALTHY), result);
    }

    @Test
    void testParacetamolDoesNotAffectOtherConditions() {
        List<Condition> result = processor.process(List.of(Condition.HEALTHY), Set.of(Drug.PARACETAMOL));
        assertEquals(List.of(Condition.HEALTHY), result);
    }

    // ANTIBIOTIC

    @Test
    void testAntibioticCuresTuberculosis() {
        List<Condition> result = processor.process(List.of(Condition.TUBERCULOSIS), Set.of(Drug.ANTIBIOTIC));
        assertEquals(List.of(Condition.HEALTHY), result);
    }

    @Test
    void testAntibioticWithInsulinCausesFeverInHealthyPatient() {
        Set<Drug> drugs = Set.of(Drug.ANTIBIOTIC, Drug.INSULIN);
        List<Condition> result = processor.process(List.of(Condition.HEALTHY), drugs);
        assertEquals(List.of(Condition.FEVER), result);
    }

    @Test
    void testAntibioticDoesNotAffectOtherConditions() {
        List<Condition> result = processor.process(List.of(Condition.FEVER), Set.of(Drug.ANTIBIOTIC));
        assertEquals(List.of(Condition.FEVER), result);
        List<Condition> result2 = processor.process(List.of(Condition.DIABETES), Set.of(Drug.ANTIBIOTIC));
        assertEquals(List.of(Condition.DEAD), result2);
    }

    // SPAGHETTI MONSTER TESTS

    @Test
    void testDeadPatientResurrectsWithSpaghettiMonster() {
        HospitalProcessor spaghettiProcessor = new HospitalProcessor(1);
        List<Condition> result = spaghettiProcessor.process(List.of(Condition.DEAD), Collections.emptySet());
        assertEquals(List.of(Condition.HEALTHY), result);
    }

}
