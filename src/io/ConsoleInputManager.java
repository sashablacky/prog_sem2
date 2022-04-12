package io;

import java.util.Scanner;

import data.*;
import static io.OutputManager.*;
public class ConsoleInputManager extends InputManagerImpl{

    public ConsoleInputManager(){
        super(new Scanner(System.in));
        getScanner().useDelimiter("\n");
    }

    @Override
    public String readName(){
        return new Question<String>("enter name:", super::readName).getAnswer();
    }

    @Override
    public float readXCoord(){
        return new Question<Float>("enter x:", super::readXCoord).getAnswer();
    }

    @Override
    public Long readYCoord(){
        return new Question<Long>("enter y:", super::readYCoord).getAnswer();
    }

    @Override
    public Coordinates readCoords(){
        print("enter coordinates");
        float x = readXCoord();
        Long y = readYCoord();
        Coordinates coord = new Coordinates(x,y);
        return coord;
    }

    @Override
    public Boolean readRealHero(){
        return new Question<Boolean>("enter are they a real hero (true/false):",super::readRealHero).getAnswer();
    }
    @Override
    public Boolean readHasToothpick(){
        return new Question<Boolean>("enter do they have a toothpick (true/false):",super::readHasToothpick).getAnswer();
    }
    @Override
    public float readImpactSpeed(){
        return new Question<Float>("enter impact speed:",super::readImpactSpeed).getAnswer();
    }
    @Override
    public Long readMinutesOfWaiting(){
        return new Question<Long>("enter minutes of waiting:",super::readMinutesOfWaiting).getAnswer();
    }
    @Override
    public WeaponType readWeaponType(){
        return new Question<WeaponType>("enter weapon type (AXE, SHOTGUN, MACHINE_GUN):", super::readWeaponType).getAnswer();
    }
    @Override
    public Mood readMood(){
        return new Question<Mood>("enter mood (SORROW, LONGING, GLOOM, CALM):", super::readMood).getAnswer();
    }
    @Override
    public Boolean readCoolness(){
        return new Question<Boolean>("enter if the car is cool (true/false):", super::readCoolness).getAnswer();
    }
    @Override
    public Car readCar(){
        print("enter car");
        Boolean isCool = readCoolness();
        return new Car(isCool);
    }

    @Override
    public HumanBeing readHumanBeing(){
        String name = readName();
        Coordinates coords = readCoords();
        Boolean realHero = readRealHero();
        Boolean hasToothpick = readHasToothpick();
        float impactSpeed = readImpactSpeed();
        long minutesOfWaiting = readMinutesOfWaiting();
        WeaponType weaponType = readWeaponType();
        Mood mood = readMood();
        Car car = readCar();
        HumanBeing person = new HumanBeing(name, coords, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
        return person;
    }
}