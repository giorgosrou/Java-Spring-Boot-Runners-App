package Gero.dev.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

  private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
  private final JdbcClient jdbcClient;

  public RunRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<Run> findAll() {
    return jdbcClient.sql("select * from run")
        .query(Run.class)
        .list();
  }

  public Optional<Run> findById(int id) {
    return jdbcClient.sql("SELECT id,title,started_on,completed_on, miles,location FROM Run WHERE id = :id")
        .param("id", id)
        .query(Run.class)
        .optional();
  }

  public void create(Run run) {
    var updated = jdbcClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) VALUES(?,?,?,?,?,?)")
        .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(), run.miles(), run.location().toString()))
        .update();
  }

  public void update(Run run, Integer id) {
    var updated = jdbcClient.sql("UPDATE run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
        .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(), run.miles(), run.location().toString()))
        .update();
  }

  public void delete(Integer id) {
    var updated = jdbcClient.sql("DELETE FROM run WHERE id = :id")
        .param("id", id)
        .update();
  }







}
