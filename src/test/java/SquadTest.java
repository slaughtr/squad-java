import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SquadTest {

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
  public void equals_returnsTrueIfNamesAretheSame() {
    Squad firstSquad = new Squad("Household", "Chores");
    Squad secondSquad = new Squad("Household", "Chores");
    assertTrue(firstSquad.equals(secondSquad));
  }

  @Test
  public void squad_instantiatesCorrectly_true(){
    Squad testSquad = new Squad("", "");
    assertEquals(true, testSquad instanceof Squad);
  }

  @Test
  public void getSquadName_squadInstantiatesWithName_Avengers(){
    Squad testSquad = new Squad("Avengers", "");
    assertEquals("Avengers", testSquad.getSquadName());
  }

  @Test
  public void getGoal_squadInstantiatesWithGoal_GetMoney(){
    Squad testSquad = new Squad("Wu Tang Clan", "Get Money");
    assertEquals("Get Money", testSquad.getGoal());
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Squad mySquad = new Squad("Household", "Chores");
    mySquad.save();
    assertTrue(Squad.all().get(0).equals(mySquad));
  }

  @Test
  public void all_returnsAllInstancesOfSquad_true() {
    Squad firstSquad = new Squad("Home", "Work");
    firstSquad.save();
    Squad secondSquad = new Squad("Work", "Home");
    secondSquad.save();
    assertEquals(true, Squad.all().get(0).equals(firstSquad));
    assertEquals(true, Squad.all().get(1).equals(secondSquad));
  }

  @Test
 public void save_assignsIdToObject() {
   Squad mySquad = new Squad("Household", "Chores");
   mySquad.save();
   Squad savedSquad = Squad.all().get(0);
   assertEquals(mySquad.getId(), savedSquad.getId());
 }
//
//   @Test
//   public void clear_emptiesAllSquadsFromList_0(){
//     Squad testSquad = new Squad("Fantastic Four", "I don't know I think they're worthless");
//     assertEquals(0, Squad.all().size());
//   }
//

@Test
 public void getId_squadInstantiateWithAnId_1() {
   Squad testSquad = new Squad("Home", "Work");
   testSquad.save();
   assertTrue(testSquad.getId() > 0);
 }
//   @Test
//   public void getId_squadInstantiatesWithAnID_1(){
//     Squad testSquad = new Squad("Team Rocket", "To protect the world from devestation; to unite all peoples within our nation; to denounce the evils of truth and love; to extend out reach to the stars above; Jessie! James! Team Rocket blast off at the speed of light! Surrender now or prepare to fight! Meowth! That's Right!!!");
//     assertEquals(1, testSquad.getId());
//   }
//
@Test
public void find_returnsSquadWithSameId_secondSquad() {
  Squad firstSquad = new Squad("Home", "Work");
  firstSquad.save();
  Squad secondSquad = new Squad("Work", "Home");
  secondSquad.save();
  assertEquals(Squad.find(secondSquad.getId()), secondSquad);
}
//
//   @Test
//   public void getHeroes_initiallyReturnsEmptyList_ArrayList(){
//     Squad testSquad = new Squad("The Boy Scouts of America", "Always Be Prepared");
//     assertEquals(0, testSquad.getHeroes().size());
//   }
//
//   @Test
//   public void addHero_addsHeroToSquad_true(){
//     Squad testSquad = new Squad("Wu Tang Clan", "Get this money");
//     Hero testHero = new Hero("RZA", "Robert Fitzgerald Diggs", "Iron Fist", "C.R.E.A.M.", 100, 140);
//     testSquad.addHero(testHero);
//     assertTrue(testSquad.getHeroes().contains(testHero));
//   }
//
//   @Test
//   public void calculateSquadStrength_returnsSumOfHeroesIntelAndStrength_int(){
//     Squad potatoSquad = new Squad("Potato Heads", "Keep All of their Parts");
//     Hero mrPotato = new Hero("Mr. Potato Head", "Spud Lee", "Interchangable body parts", "potato peelers", 4, 10);
//     Hero mrsPotato = new Hero("Mrs. Potato Head", "TatterAnn Lee", "Interchangable body parts", "missing eyes", 3, 12);
//     potatoSquad.addHero(mrPotato);
//     assertEquals(4, potatoSquad.getSquadStrength());
//     assertEquals(10, potatoSquad.getSquadIntel());
//     potatoSquad.addHero(mrsPotato);
//     assertEquals(7, potatoSquad.getSquadStrength());
//     assertEquals(22, potatoSquad.getSquadIntel());
//   }
//   @Test
//   public void find_returnsNullwhenNoSquadFound_null(){
//     assertTrue(Squad.find(999) == null);
//   }

@Test
  public void save_savesSquadIdIntoDB_true() {
    Squad mySquad = new Squad("Household", "Chores");
    mySquad.save();
    Hero myHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, mySquad.getId());
    myHero.save();
    Hero savedHero = Hero.find(myHero.getId());
    assertEquals(savedHero.getSquadId(), mySquad.getId());
  }

  @Test
 public void getHeroes_retrievesALlHeroesFromDatabase_tasksList() {
   Squad mySquad = new Squad("Household", "Chores");
   mySquad.save();
   Hero firstHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, mySquad.getId());
   firstHero.save();
   Hero secondHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90, mySquad.getId());
   secondHero.save();
   Hero[] heroes = new Hero[] { firstHero, secondHero };
   assertTrue(mySquad.getHeroes().containsAll(Arrays.asList(heroes)));
 }
}
