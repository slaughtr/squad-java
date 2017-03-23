import org.junit.*;
import static org.junit.Assert.*;

public class HeroTest {

Hero testHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90);

@Test
public void Hero_instantiatesCorrectly_true() {
  // Hero testHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90);
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
public void all_returnsAllInstancesofHero_true() {
  Hero testHero2 = new Hero("Hero 2 Name", "Secret Identity", "Super Power", "Weakness", 0, 90);
  assertEquals(true, Hero.all().contains(testHero));
  assertEquals(true, Hero.all().contains(testHero2));
}
@Test
public void clear_emptiesAllInstancesofHerofromArrayList_true() {
  Hero testHero2 = new Hero("Hero 2 Name", "Secret Identity", "Super Power", "Weakness", 0, 90);
  Hero.clear();
  assertEquals(0, Hero.all().size());
}
@Test
public void getId_heroInstantiatesWithID_1() {
  Hero.clear();
  Hero myHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90);
  assertEquals(1, myHero.getId());
}
@Test
public void find_returnsHeroWithSameId_myHero() {
  Hero.clear();
  Hero myHero = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90);
  Hero myHero2 = new Hero("Hero Name", "Secret Identity", "Super Power", "Weakness", 0, 90);
  assertEquals(Hero.find(myHero.getId()), myHero);
}

}
