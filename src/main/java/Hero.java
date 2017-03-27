import java.util.List;
import org.sql2o.*;
import java.util.HashMap;


public class Hero {
  private String heroName;
  private String secret_identity;
  private String superpower;
  private String weakness;
  private int strength;
  private int intel;
  private int id;
  private int squadId;
  // Map<String, String> colMaps = new HashMap<String, String>();
  // colMaps.put("name", "heroName");
  // sql2o.setDefaultColumnMappings(colMaps);

  public Hero(String name, String secret_identity, String superpower, String weakness, int strength, int intel, int squadId){
    this.heroName = name;
    this.secret_identity = secret_identity;
    this.superpower = superpower;
    this.weakness = weakness;
    this.strength = strength;
    this.intel = intel;
    this.squadId = squadId;
  }

  public String getHeroName(){
    return heroName;
  }

  public String getSecretIdentity(){
    return secret_identity;
  }

  public String getSuperPower(){
    return superpower;
  }

  public String getWeakness(){
    return weakness;
  }

  public int getStrengthValue(){
    return strength;
  }

  public int getIntelValue(){
    return intel;
  }

  public int getSquadId() {
    return squadId;
  }

  public static List<Hero> all(){
    String sql = "SELECT * FROM heroes";
    try(Connection con = DB.sql2o.open()) {

      return con.createQuery(sql)
      //pulling information from the database, maps info from column called "name" to class variable "heroName"
      .addColumnMapping("name", "heroName")
      .executeAndFetch(Hero.class);
    }
  }


  public int getId(){
    return id;
  }


  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO heroes (name, secret_identity, superpower, weakness, strength, intel, squadId) VALUES (:name, :secret_identity, :superpower, :weakness, :strength, :intel, :squadId)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.heroName)
      .addParameter("secret_identity", this.secret_identity)
      .addParameter("superpower", this.superpower)
      .addParameter("weakness", this.weakness)
      .addParameter("strength", this.strength)
      .addParameter("intel", this.intel)
      .addParameter("squadId", this.squadId)
      .executeUpdate()
      .getKey();
    }
  }

  public static Hero find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM heroes WHERE id = :id";
      Hero hero = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        //pulling information from the database, maps info from column called "name" to class variable "heroName"
        .addColumnMapping("name", "heroName")
        .executeAndFetchFirst(Hero.class);
      return hero;
    }
  }

  @Override
  public boolean equals(Object otherHero) {
    if (!(otherHero instanceof Hero)) {
      return false;
    } else {
      Hero newHero = (Hero) otherHero;
      return this.getHeroName().equals(newHero.getHeroName()) && this.getId() == newHero.getId() && this.getSquadId() == newHero.getSquadId();

    }
  }
}
