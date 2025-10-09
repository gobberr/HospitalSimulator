package simulator.hospital.enums;

import java.util.Arrays;

public enum Condition {

    FEVER("F"),
    HEALTHY("H"),
    DIABETES("D"),
    TUBERCULOSIS("T"),
    DEAD("X");

    private final String code;

    Condition(String code) {
        this.code = code;
    }

    public static Condition fromCode(String code) {
        for (Condition d : values()) {
            if (d.code.equalsIgnoreCase(code)) {
              return d;
            }
        }
        throw new IllegalArgumentException("Unknown condition code: " + code + ". Accepted codes are " + Arrays.toString(values()));
    }

    @Override
    public String toString() {
        return code;
    }

}
