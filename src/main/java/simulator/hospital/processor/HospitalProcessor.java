package simulator.hospital.processor;

import simulator.hospital.enums.Drug;
import simulator.hospital.enums.Condition;
import java.util.*;
import java.util.stream.Collectors;

public class HospitalProcessor {

    private final int spaghettiMonsterProbability;

    public HospitalProcessor() {
        this.spaghettiMonsterProbability = 1_000_000;
    }

    public HospitalProcessor(int spaghettiMonsterProbability) {
        this.spaghettiMonsterProbability = spaghettiMonsterProbability;
    }

    public List<Condition> process(List<Condition> patients, Set<Drug> drugs) {

        return patients.stream()
            .map(condition -> applyDrugs(condition, drugs))
            .map(this::processSpaghettiMonster)
            .collect(Collectors.toList());
    }

    private Condition applyDrugs(Condition state, Set<Drug> drugs) {

        if (state == Condition.DIABETES && !drugs.contains(Drug.INSULIN)) {
            return Condition.DEAD;
        }

        Condition result = state;
        for (Drug d : drugs) {
            result = d.apply(result, drugs);
            if (result == Condition.DEAD) break;
        }
        return result;
    }

    private Condition processSpaghettiMonster(Condition state) {

        int flipRandom = new Random().nextInt(0, spaghettiMonsterProbability);
        return state == Condition.DEAD && flipRandom == 0 ? Condition.HEALTHY : state;
    }

}
