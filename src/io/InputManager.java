package io;

import java.time.LocalDate;
import java.util.Scanner;

import commands.CommandWrapper;
import data.*;
import exceptions.*;

public interface InputManager {
    /**
     * reads name from input
     * @return
     * @throws EmptyStringException
     */
    public String readName() throws EmptyStringException;

    /**
     * reads x from input
     * @return
     * @throws InvalidNumberException
     */
    public float readXCoord() throws InvalidNumberException;

    /**
     * reads y from input
     * @return
     * @throws InvalidNumberException
     */
    public Long readYCoord() throws InvalidNumberException;

    /**
     * reads coordinates from input
     * @return
     * @throws InvalidNumberException
     */
    public Coordinates readCoords() throws InvalidNumberException;

    /**
     * reads realHero from input
     * @return
     * @throws InvalidBooleanException
     */
    public Boolean readRealHero() throws InvalidBooleanException;

    /**
     * reads hasToothpick from input
     * @return
     * @throws InvalidBooleanException
     */
    public Boolean readHasToothpick() throws InvalidBooleanException;

    /**
     * reads impactSpeed from input
     * @return
     * @throws InvalidNumberException
     */
    public float readImpactSpeed() throws InvalidNumberException;

    /**
     * reads minutesOfWaiting from input
     * @return
     * @throws InvalidNumberException
     */
    public long readMinutesOfWaiting() throws InvalidNumberException;

    /**
     * reads WeaponType from input
     * @return
     * @throws InvalidEnumException
     */
    public WeaponType readWeaponType() throws InvalidEnumException;

    /**
     * reads Mood from input
     * @return
     * @throws InvalidDataException
     */
    public Mood readMood() throws InvalidEnumException;

    /**
     * reads Car from input
     * @return
     * @throws InvalidDataException
     */
    public Car readCar() throws InvalidDataException;

    /**
     * reads HumanBeing from input
     * @return
     * @throws InvalidDataException
     */
    public HumanBeing readHumanBeing() throws InvalidDataException;

    /**
     * reads command-argument pair from input
     * @return
     */
    public CommandWrapper readCommand();

    /**
     * gets input scanner
     * @return
     */
    public Scanner getScanner();
}