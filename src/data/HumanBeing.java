package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * Human class
 */
public class HumanBeing implements Collectionable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private Boolean hasToothpick; //Поле не может быть null
    private float impactSpeed; //Максимальное значение поля: 64
    private long minutesOfWaiting;
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле не может быть null

    /**
     * Constructor, set fields
     * @param name
     * @param coordinates
     * @param realHero
     * @param hasToothpick
     * @param impactSpeed
     * @param minutesOfWaiting
     * @param weaponType
     * @param mood
     * @param car
     */
    public HumanBeing(String name, Coordinates coordinates, Boolean realHero, Boolean hasTooothpick, float impactSpeed, long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car){
        creationDate = new java.util.Date();
        
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasTooothpick;
        this.impactSpeed = impactSpeed;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    /** 
     * @return int
     */
    public int getId(){
        return id;
    }

    /** change ID for replacing objects in collection 
     * @param NewId
     */
    public void setId(int NewId){
        id = NewId;
    }

    /** 
     * @return String
     */
    public String getName(){
        return name;
    }

    /** 
     * @return Boolean
     */
    public Boolean isRealHero(){
        return realHero;
    }

    /** 
     * @return Boolean
     */
    public Boolean doesHaveToothpick(){
        return hasToothpick;
    }

    /** 
     * @return float
     */
    public float getImpactSpeed(){
        return impactSpeed;
    }

    /** 
     * @return long
     */
    public long getMinutesOfWaiting(){
        return minutesOfWaiting;
    }

    /**
     * @return WeaponType
     */
    public WeaponType getWeaponType(){
        return weaponType;
    }

    /**
     * @return Mood
     */
    public Mood getMood(){
        return mood;
    }

    /**
     * @return Car
     */
    public Car getCar(){
        return car;
    }
    
    /**
     * @return String
     */
    @Override
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strCreationDate = dateFormat.format(creationDate);
        String s = "";
        s += "{\n";
        s += "  \"id\" : " + Integer.toString(id) + ",\n";
        s += "  \"name\" : " + "\"" + name + "\"" + ",\n";
        s += "  \"coordinates\" : " + coordinates.toString() + ",\n";
        s += "  \"creationDate\" : " + "\"" + strCreationDate + "\"" + ",\n";
        s += "  \"realHero\" : " + Boolean.toString(realHero) + ",\n";
        s += "  \"hasToothpick\" : " + Boolean.toString(hasToothpick) + ",\n";
        s += "  \"impactSpeed\" : " + Float.toString(impactSpeed) + ",\n";
        s += "  \"minutesOfWaiting\" : " + Long.toString(minutesOfWaiting) + ",\n";
        if (mood!=null) s += "  \"weaponType\" : " + weaponType.toString() + ",\n";
        if (mood!=null) s += "  \"mood\" : " + mood.toString() + ",\n";
        s += "  \"car\" : " + car.toString() + "\n";
        s += "}";
        return s;
    }
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass()!= obj.getClass()) return false;
        HumanBeing another = (HumanBeing)obj;
        return this.getId() == another.getId();
    }

    /** 
     * @param HumanBeing
     * @return int
     */
    public int compareTo(Collectionable HumanBeing){
        int res = Float.compare(this.impactSpeed, HumanBeing.getImpactSpeed());
        return res;
    }

    /** 
     * @return boolean
     */
    public boolean validate(){
        return (
            (id>0) && name!=null && name != "" &&
            coordinates != null && coordinates.validate() && creationDate!=null && 
            realHero != null && hasToothpick != null && (impactSpeed<=64) &&
            car != null && car.validate()
        );

    }
}
