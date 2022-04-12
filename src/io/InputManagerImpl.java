package io;

import java.util.Scanner;

import commands.CommandWrapper;

import data.*;
import exceptions.*;

/**
 * basic implementation of InputManager
 */
public abstract class InputManagerImpl implements InputManager{
    private Scanner scanner;

    public InputManagerImpl(Scanner sc){
        scanner = sc;
        scanner.useDelimiter("\n");
    }

    public Scanner getScanner(){
        return scanner;
    }

    public void setScanner(Scanner sc){
        scanner = sc;
    }
    public String readName() throws EmptyStringException{
        String s = scanner.nextLine().trim();
        if (s.equals("")){
            throw new EmptyStringException();
        }
        return s;
    }

    public float readXCoord() throws InvalidNumberException{
        float x;
        try{
            x = Float.parseFloat(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        if (Float.isInfinite(x) || Float.isNaN(x)) throw new InvalidNumberException("invalid float value");
        return x;
    }

    public Long readYCoord() throws InvalidNumberException{
        Long y;
        try{
            y = Long.parseLong(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        if (y>=-684) throw new InvalidNumberException("must be greater than -684");
        return y;
    }
    public Coordinates readCoords() throws InvalidNumberException{
        float x = readXCoord();
        Long y = readYCoord();
        Coordinates coord = new Coordinates(x,y);
        return coord;
    }
    public Boolean parseBool(String buf) throws InvalidBooleanException{
        Boolean bool = null;
        if (buf.toLowerCase() == "true"){
            bool = true;
        } if (buf.toLowerCase() == "false"){
            bool = false;
        }
        if (buf.toLowerCase() == ""){
            bool = null;
        }
        else{
            throw new InvalidBooleanException("boolean must be true, false or null");
        }
        return bool;
    }

    public Boolean readRealHero() {
        String buf = scanner.nextLine().trim();
        Boolean bool = null;
        try
        {
            bool = parseBool(buf);
            if (bool == null)
            {
                throw new InvalidBooleanException("realHero must not be null");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return bool;
    }
    public Boolean readHasToothpick() {
        String buf = scanner.nextLine().trim();
        Boolean bool = null;
        try
        {
            bool = parseBool(buf);
            if (bool == null)
            {
                throw new InvalidBooleanException("hasToothpick must not be null");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return bool;
    }

    public float readImpactSpeed() throws InvalidNumberException{
        float impactSpeed;
        try{
            impactSpeed = Float.parseFloat(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        if (Float.isInfinite(impactSpeed) || Float.isNaN(impactSpeed)) throw new InvalidNumberException("invalid float value");
        if (impactSpeed > 64.0f) throw new InvalidNumberException("impact speed must not be greater than 64");
        return impactSpeed;
    }
    public Long readMinutesOfWaiting() throws InvalidNumberException{
        Long minutesOfWaiting;
        try{
            minutesOfWaiting = Long.parseLong(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
       return minutesOfWaiting;
    }
    public WeaponType readWeaponType() throws InvalidEnumException{
        String buf = scanner.nextLine().trim();
        if(buf.equals("")){
            return null;
        } 
        else {
            try{
                return WeaponType.valueOf(buf);
            } catch(IllegalArgumentException e){
                throw new InvalidEnumException();
            }
        }
    }
    public Mood readMood() throws InvalidEnumException{
        String buf = scanner.nextLine().trim();
        if(buf.equals("")){
            return null;
        } 
        else {
            try{
                return Mood.valueOf(buf);
            } catch(IllegalArgumentException e){
                throw new InvalidEnumException();
            }
        }
    }
    public Boolean readCoolness() throws InvalidBooleanException{
        String buf = scanner.nextLine().trim();
        Boolean isCool = null;
        try
        {
            isCool = parseBool(buf);
            if (isCool == null)
            {
                throw new InvalidBooleanException("Coolness must not be null");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return isCool;
    }
    public Car readCar() throws InvalidDataException{
        Boolean isCool = readCoolness();
        return new Car(isCool);
    }
    public HumanBeing readHumanBeing() throws InvalidDataException{
        HumanBeing person = null;

        String name = readName();
        Coordinates coords = readCoords();
        Boolean realHero = readRealHero();
        Boolean hasToothpick = readHasToothpick();
        float impactSpeed = readImpactSpeed();
        long minutesOfWaiting = readMinutesOfWaiting();
        WeaponType weaponType = readWeaponType();
        Mood mood = readMood();
        Car car = readCar();
        person = new HumanBeing(name, coords, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);

        return person;

    }

    public CommandWrapper readCommand(){
        String cmd = scanner.nextLine();
        if (cmd.contains(" ")){ //if command has argument
            String arr [] = cmd.split(" ",2);
            cmd = arr[0];
            String arg = arr[1];
            return new CommandWrapper(cmd,arg);
        } else {
            return new CommandWrapper(cmd);
        }
    }
}