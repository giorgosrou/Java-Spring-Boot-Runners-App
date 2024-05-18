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

  void create(Run run) {
    runs.add(run);
  }

  void update(Run run, Integer id) {
    Optional<Run> existingRun = findById(id);
    if (existingRun.isPresent()) {
      runs.set(runs.indexOf(run), run);
    }
  }

  void delete(Integer id) {
    runs.removeIf(run -> run.id() == id);
  }

  @PostConstruct
  private void init() {
    runs.add(new Run(1,
        "Monday run",
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(68),
        3,
        Location.Outdoor));

    runs.add(new Run(2,
        "Tuesday run",
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(68),
        5,
        Location.Outdoor));
  }

}
