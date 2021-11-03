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
    
    public RentACar(List<CarInterface> list, String businessName) {
        this.listOfCars = list;
        this.name = businessName;
    }
    

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
        int from = day;
        int to = from + lengthOfRent;
        List<CarInterface> listOfMake = this.getAllCarsOfAMake(make, this.listOfCars);
      

        for(CarInterface car: listOfMake){
            //updating a flag
            flag = true;
            
            for(int i = from; i < to ; i ++) {
                if(!car.isAvailable(month, i)){
                    //if any of the required days is unavailable, flag changes
                    flag = false;
                }
            }
            
            if(flag) {
                // at this point, if flag is still true, it means that at least on of the required cars is available
                //if not we loop over next car, if this is th elast iteration, flaske is returend later
                return flag;
            } 
            
        }
        //finall return    
    return flag;
    }
   
    @Override
    public int getCarAvailable(Month month, int day, Make make, int lengthOfRent) {
        // retreving list of cars of the same make
        List<CarInterface> listOfMake = this.getAllCarsOfAMake(make, this.listOfCars);
        boolean isAvailable = this.checkAvailability(month, day, make, lengthOfRent);
        //list of available ids
        List<Integer> idList = new ArrayList<>();
        boolean testBoolean = true;
        
        int id = 0; // if 0 is returned, the car is not available 
        
        int from = day;
        int to = from + lengthOfRent;   
    
        if(isAvailable) {
    
            for(CarInterface car: listOfMake){

                boolean[] tempCheckArray = car.getAvailability().get(month);

                for(int i = from; i < to ; i++) {
                   if(!tempCheckArray[i]) {
                       //if any of the days is not available, test boolean is false 
                       testBoolean = false;
                   }
                }
                // if all days available 
                if(testBoolean) {
                    idList.add(car.getId());
                }
            }

            for(Integer i: idList) {
                //assigns last available ID value
               id=i;
            }    
        }
        
        return id;
    }
    
    @Override
    public boolean bookCar(Month month, int day, Make make, int lengthOfRent) {
        
        boolean bookingCompleted = false; // flag set tu false; 
        
        int from = day ;
        int to = from + lengthOfRent;
        // checks if there is an ID available, if not false is returned at the end
        int carID = this.getCarAvailable(month, day, make, lengthOfRent);
        List<CarInterface> carsOfMake = this.getAllCarsOfAMake(make, listOfCars);

        
        if(carID > 0) {
            CarInterface tempCar; 
            for(CarInterface car : carsOfMake) {
                // if id retreaved from iterated car matches carID checked before, 
                // we have a car that is available, therefor, we can book it 
                if(car.getId() == carID) { 
                    for(int i = from ; i < to ; i++) {
                        // book each day of the lease
                        bookingCompleted = car.book(month, i);
                    }
                    
                }
            }
            
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
