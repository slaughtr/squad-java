import org.junit.*;
import static org.junit.Assert.*;

public class SquadTest {

  @Test
  public void squad_instantiatesCorrectly_true(){
    Squad testSquad = new Squad("", "");
    assertEquals(true, testSquad instanceof Squad);
  }

  @Test
  public void getName_squadInstantiatesWithName_Avengers(){
    Squad testSquad = new Squad("Avengers", "");
    assertEquals("Avengers", testSquad.getName());
  }

  @Test
  public void getGoal_squadInstantiatesWithGoal_GetMoney(){
    Squad testSquad = new Squad("Wu Tang Clan", "Get Money");
    assertEquals("Get Money", testSquad.getGoal());
  }

  @Test
  public void all_returnsAllInstancesOfSquad_true(){
    Squad firstSquad = new Squad("Avengers", "protect the planet and its people from both domestic and extraterrestrial threats");
    Squad secondSquad = new Squad("Wu Tang Clan", "Get Money");
    assertEquals(true, Squad.all().contains(firstSquad));
    assertTrue(Squad.all().contains(secondSquad));
  }

  @Test
  public void clear_emptiesAllSquadsFromList_0(){
    Squad testSquad = new Squad("Fantastic Four", "I don't know I think they're worthless");
    Squad.clear();
    assertEquals(0, Squad.all().size());
  }

  @Test
  public void getId_squadInstantiatesWithAnID_1(){
    Squad.clear();
    Squad testSquad = new Squad("Team Rocket", "To protect the world from devestation; to unite all peoples within our nation; to denounce the evils of truth and love; to extend out reach to the stars above; Jessie! James! Team Rocket blast off at the speed of light! Surrender now or prepare to fight! Meowth! That's Right!!!");
    assertEquals(1, testSquad.getId());
  }

  @Test
  public void find_returnsSquadWithSameId_secondSquad(){
    Squad.clear();
    Squad firstSquad = new Squad("The Mole Men", "Build an underground empire");
    Squad secondSquad = new Squad("The Fish Men", "SPAWN!!!");
    assertEquals(Squad.find(secondSquad.getId()), secondSquad);
  }

  @Test
  public void getHeroes_initiallyReturnsEmptyList_ArrayList(){
    Squad.clear();
    Squad testSquad = new Squad("The Boy Scouts of America", "Always Be Prepared");
    assertEquals(0, testSquad.getHeroes().size());
  }

  @Test
  public void addHero_addsHeroToSquad_true(){
    Squad testSquad = new Squad("Wu Tang Clan", "Get this money");
    Hero testHero = new Hero("RZA", "Robert Fitzgerald Diggs", "Iron Fist", "C.R.E.A.M.", 100, 140);
    testSquad.addHero(testHero);
    assertTrue(testSquad.getHeroes().contains(testHero));
  }

  @Test
  public void calculateSquadStrength_returnsSumOfHeroesIntelAndStrength_int(){
    Squad potatoSquad = new Squad("Potato Heads", "Keep All of their Parts");
    Hero mrPotato = new Hero("Mr. Potato Head", "Spud Lee", "Interchangable body parts", "potato peelers", 4, 10);
    Hero mrsPotato = new Hero("Mrs. Potato Head", "TatterAnn Lee", "Interchangable body parts", "missing eyes", 3, 12);
    potatoSquad.addHero(mrPotato);
    assertEquals(4, potatoSquad.getSquadStrength());
    assertEquals(10, potatoSquad.getSquadIntel());
    potatoSquad.addHero(mrsPotato);
    assertEquals(7, potatoSquad.getSquadStrength());
    assertEquals(22, potatoSquad.getSquadIntel());
  }
  @Test
  public void find_returnsNullwhenNoSquadFound_null(){
    assertTrue(Squad.find(999) == null);
  }
}
