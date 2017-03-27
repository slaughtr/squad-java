import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.sql2o.*;

public class Squad {
  private String squadName;
  private String squadGoal;
  private int id;
  private int squadStrength;
  private int squadIntel;

  public Squad(String name, String goal){
    squadName = name;
    squadGoal = goal;

  }

  public String getSquadName(){
    return squadName;
  }

  public String getGoal(){
    return squadGoal;
  }

  public static List<Squad> all(){
    String sql = "SELECT * FROM squads";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addColumnMapping("name", "squadName")
      .addColumnMapping("goal", "squadGoal")
      .addColumnMapping("strength", "squadStrength")
      .addColumnMapping("intel", "squadIntel")
      .executeAndFetch(Squad.class);
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO squads (name) VALUES (:name)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.squadName)
      .executeUpdate()
      .getKey();
  }
}

  public int getId(){
    return id;
  }

  public static Squad find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM squads where id=:id";
        Squad squad = con.createQuery(sql)
          .addParameter("id", id)
          .addColumnMapping("name", "squadName")
          .addColumnMapping("goal", "squadGoal")
          .addColumnMapping("strength", "squadStrength")
          .addColumnMapping("intel", "squadIntel")
          .executeAndFetchFirst(Squad.class);
        return squad;
      }
    }
    
  public int getSquadStrength(){
    return squadStrength;
  }

  public int getSquadIntel(){
    return squadIntel;
  }

  public List<Hero> getHeroes() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM heroes where squadId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .addColumnMapping("name", "heroName")
        .executeAndFetch(Hero.class);
    }
  }

  @Override
  public boolean equals(Object otherSquad) {
    if (!(otherSquad instanceof Squad)) {
      return false;
    } else {
      Squad newSquad = (Squad) otherSquad;
      return this.getSquadName().equals(newSquad.getSquadName()) && this.getId() == newSquad.getId();
    }
  }
}
