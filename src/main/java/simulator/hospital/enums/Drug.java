package simulator.hospital.enums;

import java.util.Arrays;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Drug {

    ASPIRIN("As"),
    ANTIBIOTIC("An"),
    INSULIN("I"),
    PARACETAMOL("P");

    private final String code;

    private static final Logger logger = LoggerFactory.getLogger(Drug.class);

    Drug(String code) {
        this.code = code;
    }

    public static Drug fromCode(String code) {
        for (Drug d : values()) {
            if (d.code.equalsIgnoreCase(code)) {
                return d;
            }
        }
        throw new IllegalArgumentException("Unknown drug code: " + code + ". Accepted codes are " + Arrays.toString(values()));
    }

    public Condition apply(Condition state, Set<Drug> allDrugs) {

        logger.info("Applying {} to {} with drugs {}", this, state, allDrugs);

        switch (this) {
            case ASPIRIN -> {
                if (allDrugs.contains(PARACETAMOL)) return Condition.DEAD;
                if (state == Condition.FEVER) return Condition.HEALTHY;
            }
            case PARACETAMOL -> {
                if (allDrugs.contains(ASPIRIN)) return Condition.DEAD;
                if (state == Condition.FEVER) return Condition.HEALTHY;
            }
            case ANTIBIOTIC -> {
                if (state == Condition.TUBERCULOSIS) return Condition.HEALTHY;
                if (allDrugs.contains(INSULIN) && state == Condition.HEALTHY) return Condition.FEVER;
            }
            case INSULIN -> {
                if (allDrugs.contains(ANTIBIOTIC) && state == Condition.HEALTHY) return Condition.FEVER;
                if (state == Condition.DIABETES) return Condition.DIABETES; // prevents death
            }
        }

        return state;
    }

    @Override
    public String toString() {
        return code;
    }
}
