import java.util.List;
import java.util.ArrayList;

public class Squad {
  private String mName;
  private String mGoal;
  private static List<Squad> instances = new ArrayList<Squad>();
  private int mId;
  private List<Hero> mHeroes;

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
    return instances.get(id - 1);
  }

  public List<Hero> getHeroes() {
    return mHeroes;
  }

  public void addHero(Hero hero){
    mHeroes.add(hero);
  }
}
