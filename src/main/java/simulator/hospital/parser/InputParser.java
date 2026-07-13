package simulator.hospital.parser;

import simulator.hospital.enums.Drug;
import simulator.hospital.enums.Condition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputParser {

    private InputParser() {
        /* This utility class should not be instantiated */
    }


    public static List<Condition> parsePatients(String args) {

        List<Condition> patients = new ArrayList<>();
        for (String code : args.split(",")) {
            patients.add(Condition.fromCode(code));
        }

        return patients;
    }

    public static Set<Drug> parseDrugs(String args) {
        if (args.isEmpty()) {
            return Collections.emptySet();
        }
        Set<Drug> drugs = new HashSet<>();
        for (String code : args.split(",")) {
            drugs.add(Drug.fromCode(code));
        }

        return drugs;
    }
}
