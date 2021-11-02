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
        this.availability = createAvailability();
        this.id = id;
    }

    @Override
    public Map<Month, boolean[]> createAvailability() {
        Map<Month, boolean[]> map = new HashMap<>();
        //This loops over the values attached to the enum Month and uses the getNumberOfDays method to determine 
        // the size of the array for each month. Days are true by default.
        for (Month month : Month.values()) {
            boolean[] days = new boolean[month.getNumberOfDays()];
            for (Boolean day : days) {
                day = true;
            }
            map.put(month, days);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean book(Month month, int day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
