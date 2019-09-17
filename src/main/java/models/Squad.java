package models;

import java.util.ArrayList;

public class Squad {

    private String name;
    private int maxSize;
    private String cause;
//    private Hero heroes;
    private int id;
private static ArrayList<Squad> instances= new ArrayList<>();


    public Squad(String name, int maxSize, String cause) {
        this.name = name;
        this.maxSize= maxSize;
        this.cause = cause;
        instances.add(this);
        this.id = instances.size();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getCause() {
        return cause;
    }

    public static ArrayList<Squad> getAll() {
        return instances;
    }

    public static Squad findById(int id){
        return instances.get(id-1); //why minus 1? See if you can figure it out.
    }
    public void update(String name, int maxSize, String cause) {
        this.name = name;
        this.maxSize= maxSize;
        this.cause= cause;
    }
    public void deleteSquad(){
        instances.remove(id-1);
    }
}
