package simulator.hospital.parser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import simulator.hospital.enums.Condition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OutputParser {

    public static String printPatients(List<Condition> patients) {

        Map<Condition, Long> counts = Arrays.stream(Condition.values())
                .collect(Collectors.toMap(s -> s, s -> 0L));

        patients.forEach(s -> counts.put(s, counts.get(s) + 1));

        return String.format(
                "F:%d,H:%d,D:%d,T:%d,X:%d",
                counts.get(Condition.FEVER),
                counts.get(Condition.HEALTHY),
                counts.get(Condition.DIABETES),
                counts.get(Condition.TUBERCULOSIS),
                counts.get(Condition.DEAD));
    }
}
