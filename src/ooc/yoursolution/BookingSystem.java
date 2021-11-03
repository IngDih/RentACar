/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ooc.yoursolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ooc.enums.Make;

/**
 *
 * @author rober
 */
public class BookingSystem implements BookingSystemInterface {

    @Override
    public RentACarInterface setupRentACar(BufferedReader in) throws IOException {

        List<CarInterface> listOfCars = new ArrayList<>();

        String line = in.readLine(); // first line on the file is a business name 
        String bussinessName = line;
        int id = 0; // we want to create unique id for every car

        // we loop throught the document, untill it is empty 
        while ((line = in.readLine()) != null) {

            String[] details = line.split(":"); // each line contains 3 parts 
            Make make = Make.valueOf(details[0]);
            double rate = Double.parseDouble(details[1]);
            int numberOfCars = Integer.parseInt(details[2]);
            // we loop over the amount of the cars from the same make to be created 
            for (int i = 0; i < numberOfCars; i++) {
               id++;
               //creatig new car
                listOfCars.add(new Car(make, rate, id)); 
            }
        }
        
        return new RentACar(listOfCars, bussinessName);
    }

}
