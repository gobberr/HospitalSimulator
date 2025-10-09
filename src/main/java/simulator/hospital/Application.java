package simulator.hospital;

import simulator.hospital.enums.Drug;
import simulator.hospital.enums.Condition;
import java.util.*;

import simulator.hospital.parser.InputParser;
import simulator.hospital.parser.OutputParser;
import simulator.hospital.processor.HospitalProcessor;

public class Application {

  public static final String USAGE_MESSAGE = "Usage: java -jar target/EvooqHospitalSimulator-1.0-SNAPSHOT-jar-with-dependencies.jar [<patients>] [<drugs>]";

  public static void main(String[] args) {

    if (args.length < 1 || args.length > 2) {
      throw new IllegalArgumentException(USAGE_MESSAGE);
    }

    List<Condition> patients = InputParser.parsePatients(args[0]);
    Set<Drug> drugs = InputParser.parseDrugs(args.length > 1 ? args[1] : "");

    HospitalProcessor hospital = new HospitalProcessor();
    List<Condition> result = hospital.process(patients, drugs);

    System.out.println(OutputParser.printPatients(result));
  }

}
