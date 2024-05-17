package Gero.dev.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

  private List<Run> runs = new ArrayList<Run>();

  List<Run> findAll() {
    return runs;
  }

  Optional<Run> findById(Integer id) {
    return runs.stream()
        .filter(run -> run.id() == id)
        .findFirst();
  }

  @PostConstruct
  private void init() {
    runs.add(new Run(1,
        "Monday run",
        LocalDateTime.now(),
        LocalDateTime.now().plus(68, ChronoUnit.MINUTES),
        3,
        Location.Outdoor));

    runs.add(new Run(2,
        "Tuesday run",
        LocalDateTime.now(),
        LocalDateTime.now().plus(68, ChronoUnit.MINUTES),
        5,
        Location.Outdoor));
  }

}
