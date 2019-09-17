import javax.print.DocFlavor;
import java.util.ArrayList;

public class Hero {
    private String name;
    private String age;
    private String power;
    private String weakness;
    private static ArrayList<Hero> instances= new ArrayList<>();
    private int id;

    public Hero(String name, String age, String power, String weakness) {
        this.name = name;
        this.age= age;
        this.power = power;
        this.weakness= weakness;
        instances.add(this);
        this.id= instances.size();
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPower() {
        return power;
    }

    public String getWeakness() {
        return weakness;
    }

    public static ArrayList<Hero> getAll() {
        return instances;
    }

    public int getId() {
        return id;
    }
    public static void clearAllHeroes(){

        instances.clear();
    }
    public static Hero findById(int id){
        return instances.get(id-1); //why minus 1? See if you can figure it out.
    }
    public void update(String name, String age, String power, String weakness) {
        this.name = name;
        this.age = age;
        this.power = power;
        this.weakness = weakness;
    }
    public void deleteHero(){
        instances.remove(id-1);
    }
}
