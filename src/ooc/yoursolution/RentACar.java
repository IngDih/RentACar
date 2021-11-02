/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ooc.yoursolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ooc.enums.Make;
import ooc.enums.Month;

/**
 *
 * @author rober
 */
public class RentACar implements RentACarInterface {

    private List<CarInterface> listOfCars; 
    private String name; 
    

   @Override
    public List<CarInterface> getCars() {
           return this.listOfCars;
    }

     @Override
    public void setCars(List<CarInterface> cars) {
        this.listOfCars = cars;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean checkAvailability(Month month, int day, Make make, int lengthOfRent) {
        boolean flag = true;
        boolean isAvailable = false;
        int from = day-1;
        int to = from + lengthOfRent;
        List<CarInterface> listOfMake = this.getAllCarsOfAMake(make, this.listOfCars);
        List<Boolean> dateForID = new ArrayList<>();

        for(CarInterface car: listOfMake){
            Map<Month, boolean[]> tempMap = car.getAvailability();
            
            for(Map.Entry<Month, boolean[]> entry : tempMap.entrySet()) {
                if(entry.getKey().compareTo(month) == 0) {
                    for(int i = from; i < to ; i++) {
                        if(entry.getValue()[i] = false) {
                            flag = false;
                        }
                    }
                }
                if(flag == true) {
                dateForID.add(Boolean.TRUE);
                }
            }
            car.setAvailability(tempMap);
        }
        
        
        for(Boolean b: dateForID) {
            if(b == Boolean.TRUE){
                isAvailable = true;
            } 
        }
            
            
        return isAvailable;
            
    }

    @Override
    public int getCarAvailable(Month month, int day, Make make, int lengthOfRent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean bookCar(Month month, int day, Make make, int lengthOfRent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumberOfCars() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * Helper method, retrevies all cars with the same make from the entire car list
     * 
     * @param make
     * @param list
     * @return ArrayList of CarInterface object with the same Make
     */
    private List<CarInterface> getAllCarsOfAMake(Make make, List<CarInterface> list) {
        List<CarInterface> sameMakeList = new ArrayList<>();
        for(CarInterface car: this.listOfCars) {
            String makeString = car.getMake().toString();
            if(makeString.equals(make.toString())){
                sameMakeList.add(car);
            }
        }
        
        return sameMakeList;
    }
    
}
