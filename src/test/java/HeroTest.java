import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class HeroTest {

Hero testHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, 1);

@Before
 public void setUp() {
   DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/squad_test", null, null);
 }

 @After
 public void tearDown() {
   try(Connection con = DB.sql2o.open()) {
     String deleteHeroesQuery = "DELETE FROM heroes *;";
     String deleteSquadsQuery = "DELETE FROM squads *;";
     con.createQuery(deleteHeroesQuery).executeUpdate();
     con.createQuery(deleteSquadsQuery).executeUpdate();
   }
 }

@Test
public void Hero_instantiatesCorrectly_true() {
  // Hero testHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, 1);
  assertEquals(true, testHero instanceof Hero);
}

@Test
public void Hero_instantiatesWithName_String() {
  assertEquals("Hero Name", testHero.getHeroName());
}

@Test
public void Hero_instantiatesWithSecretIdentity_String() {
  assertEquals("Secret Identity", testHero.getSecretIdentity());
}

@Test
public void Hero_instantiatesWithSuperPower_String() {
  assertEquals("Super Power", testHero.getSuperPower());
}

@Test
public void Hero_instantiatesWithWeakness_String() {
  assertEquals("Weakness", testHero.getWeakness());
}

@Test
public void Hero_instantiatesWithStrenghtValue_int() {
  assertEquals(0, testHero.getStrengthValue());
}

@Test
public void Hero_instantiatesWithIntelValue_int() {
  assertEquals(90, testHero.getIntelValue());
}

@Test
public void save_returnsTrueIfHeroNameAretheSame() {
  Hero myHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, 1);
  myHero.save();
  assertTrue(Hero.all().get(0).equals(myHero));
}

@Test
public void all_returnsAllInstancesOfHero_true(){
  Hero firstHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, 1);
  firstHero.save();
  Hero secondHero = new Hero("Second Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, 1);
  secondHero.save();
  assertEquals(true, Hero.all().get(0).equals(firstHero));
  assertEquals(true, Hero.all().get(1).equals(secondHero));
  }

  @Test
  public void getId_heroesInstantiateWithAnID(){
    Hero firstHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, 1);
    firstHero.save();
    assertTrue(firstHero.getId() >0);
  }

  @Test
  public void find_returnsHeroWithSameId_secondHero() {
    Hero firstHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, 1);
    firstHero.save();
    Hero secondHero = new Hero("Second Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, 1);
    secondHero.save();
    assertEquals(Hero.find(secondHero.getId()), secondHero);
  }
}
