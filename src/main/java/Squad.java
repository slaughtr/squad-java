import java.util.List;
import java.util.ArrayList;

public class Squad {
  private String mName;
  private String mGoal;
  private static List<Squad> instances = new ArrayList<Squad>();
  private int mId;
  private List<Hero> mHeroes;
  private int mSquadStrength;
  private int mSquadIntel;

  public Squad(String name, String goal){
    mName = name;
    mGoal = goal;
    instances.add(this);
    mId = instances.size();
    mHeroes = new ArrayList<Hero>();

  }

  public String getName(){
    return mName;
  }

  public String getGoal(){
    return mGoal;
  }

  public static List<Squad> all(){
    return instances;
  }

  public static void clear(){
    instances.clear();
  }

  public int getId(){
    return mId;
  }

  public static Squad find(int id) {
    try{
      return instances.get(id - 1);
    } catch (IndexOutOfBoundsException exception){
      return null;
    }
  }

  public List<Hero> getHeroes() {
    return mHeroes;
  }

  public void addHero(Hero hero){
    mHeroes.add(hero);
    (this).calculateSquadStrength();
    (this).calculateSquadIntel();
  }

  public void calculateSquadStrength(){
    mSquadStrength = 0;
    for (Hero hero : mHeroes) {
      mSquadStrength += hero.getStrengthValue();
    }
  }
  public int getSquadStrength(){
    return mSquadStrength;
  }

  public void calculateSquadIntel(){
    mSquadIntel = 0;
    for (Hero hero : mHeroes) {
      mSquadIntel += hero.getIntelValue();
    }
  }
  public int getSquadIntel(){
    return mSquadIntel;
  }
}
