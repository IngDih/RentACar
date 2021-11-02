/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooc.yoursolution;

import java.util.HashMap;
import java.util.Map;
import ooc.enums.Make;
import ooc.enums.Month;

/**
 *
 * @author Ingrid
 */
public class Car implements CarInterface {

    //Variables declaration\\
    private Make make;
    private double rate;
    private Map<Month, boolean[]> availability;
    private int id;

    //Constructor\\
    public Car(Make make, double rate, int id) {
        this.make = make;
        this.rate = rate;
        this.availability = this.createAvailability();
        this.id = id;
    }

    @Override
    public Map<Month, boolean[]> createAvailability() {
        Map<Month, boolean[]> map = new HashMap<>(); 
        Month tempMonth;  
        boolean[] tempBoolean; 
        for(Month month: Month.values()){

           tempMonth = month; 
           tempBoolean = new boolean[month.getNumberOfDays()];

           map.put(month, tempBoolean);
            for(Map.Entry<Month, boolean[]> m: map.entrySet()){
                for(int i = 0 ; i < m.getValue().length; i++){
                    m.getValue()[i] = true;
                }
            }
        }
       return map;
    }

    @Override
    public Make getMake() {
        return this.make;
    }

    @Override
    public void setMake(Make make) {
        this.make = make;
    }

    @Override
    public double getRate() {
        return this.rate;
    }

    @Override
    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public Map<Month, boolean[]> getAvailability() {
        return this.availability;
    }

    @Override
    public void setAvailability(Map<Month, boolean[]> availability) {
        this.availability = availability;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isAvailable(Month month, int day) {
        //This method checks the map created to see if a day is booked or not
        //It gets the array for the month and takes out one slot in it
        return this.availability.get(month)[day - 1];
    }

    @Override
    public boolean book(Month month, int day) {
        //If the month and day are available it will return true and turn that day of the month to false.
        //If it's not available it just returns false straight away.
        if (this.isAvailable(month, day)) {
            this.availability.get(month)[day - 1] = false;
            return true;
        } else {
            return false;
        }
    }

}
