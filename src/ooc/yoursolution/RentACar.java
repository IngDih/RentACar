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
        boolean isAvailable = false; // init
        int from = day-1;
        int to = from + lengthOfRent;
        List<CarInterface> listOfMake = this.getAllCarsOfAMake(make, this.listOfCars); // list of all cars of given make 
        List<Boolean> booleanList = new ArrayList<>();

        for(CarInterface car: listOfMake){
            Map<Month, boolean[]> tempMap = car.getAvailability();
            
            // Itetating through Map
            for(Map.Entry<Month, boolean[]> entry : tempMap.entrySet()) { 
                if(entry.getKey().compareTo(month) == 0) {
                    for(int i = from; i < to ; i++) {
                        if(entry.getValue()[i] = false) {
                            //updating flag value
                            flag = false;
                        }
                    }
                }
                //if 
                if(flag == true) {
                booleanList.add(Boolean.TRUE);
                }
            }
            car.setAvailability(tempMap);
        }
        
        // if array contains true, the car is available 
        if(booleanList.contains(Boolean.TRUE)){
            isAvailable = true;
        }
            
            
        return isAvailable;
            
    }

    @Override
    public int getCarAvailable(Month month, int day, Make make, int lengthOfRent) {
        // retreving list of cars of the same make
        List<CarInterface> listOfMake = this.getAllCarsOfAMake(make, this.listOfCars);
        boolean isAvailable = this.checkAvailability(month, day, make, lengthOfRent);
        //list of available ids
        List<Integer> idList = new ArrayList<>();
        boolean testBoolean = true;
        
        int id = 0; // if -1 is returned, the car is not available 
        
        int from = day-1;
        int to = from + lengthOfRent;
        List<Boolean> dateForID = new ArrayList<>();
    
        if(isAvailable) {
            
            
            
            for(CarInterface car: listOfMake){
                
                // test
//                Map<Month, boolean[]> tempMap = car.getAvailability();
//                
//                for(Map.Entry<Month, boolean[]> entry : tempMap.entrySet()){
//                    if(entry.getKey().compareTo(month)== 0) {
//                        for(int i = from; i < to ; i++) {
//                            if(entry.getValue()[i] == false) {
//                            //if any of the days is not available, test boolean is false 
//                            testBoolean = false;
//                            }
//                        }
//                        if(testBoolean == true) {
//                            System.out.println(car.getId());
//                            idList.add(car.getId());
//                        }
//                    }
//                }

                boolean[] tempCheckArray = car.getAvailability().get(month);

                for(int i = from; i < to ; i++) {
                   if(tempCheckArray[i] = false) {
                       //if any of the days is not available, test boolean is false 
                       testBoolean = false;
                   }
                }
                // if all days available 
                if(testBoolean == true) {
                    idList.add(car.getId());
                }
            }


            for(Integer i: idList) {
                //assigns last available 
               id=i;

            }
        
        }
        
        return id;
    }

    @Override
    public boolean bookCar(Month month, int day, Make make, int lengthOfRent) {
        
        boolean bookingCompleted = false;
        
        int from = day - 1;
        int to = from + lengthOfRent;
        int carID = this.getCarAvailable(month, day, make, lengthOfRent);
        List<CarInterface> carsOfMake = this.getAllCarsOfAMake(make, listOfCars);
//        Map<Month,boolean[]> newAvailability; 
//        boolean[] updatedDays;
        
        if(carID > 0) {
            CarInterface tempCar; 
            for(CarInterface car : carsOfMake) {
                if(car.getId() == carID) {
                    for(int i = from ; i < to ; i++) {
                        car.book(month, i);
                    }
                    
                }
            }
            
            bookingCompleted = true;
        } 
        return bookingCompleted;
     
    }

    @Override
    public int getNumberOfCars() {
        return this.listOfCars.size();
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
