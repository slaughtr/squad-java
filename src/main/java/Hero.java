import java.util.ArrayList;
import java.util.List;

public class Hero {
  private String mHeroName;
  private String mSecretIdentity;
  private String mSuperPower;
  private String mWeakness;
  private int mStrengthValue;
  private int mIntelValue;
  private static List<Hero> instances = new ArrayList<Hero>();
  private int mId;

  public Hero(String heroName, String secretIdentity, String superPower, String weakness, int strengthValue, int intelValue){
    mHeroName = heroName;
    mSecretIdentity = secretIdentity;
    mSuperPower = superPower;
    mWeakness = weakness;
    mStrengthValue = strengthValue;
    mIntelValue = intelValue;
    instances.add(this);
    mId = instances.size();
  }

  public String getHeroName(){
    return mHeroName;
  }

  public String getSecretIdentity(){
    return mSecretIdentity;
  }

  public String getSuperPower(){
    return mSuperPower;
  }

  public String getWeakness(){
    return mWeakness;
  }

  public int getStrengthValue(){
    return mStrengthValue;
  }

  public int getIntelValue(){
    return mIntelValue;
  }

  public static List<Hero> all(){
    return instances;
  }

  public static void clear(){
    instances.clear();
  }
  public int getId(){
    return mId;
  }
  public static Hero find(int id){
    try{
      return instances.get(id - 1);
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }
}
