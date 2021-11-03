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
        /* listOfCars is an array that stores the info present in the text file 
        as a list of car objects.        
         */
        List<CarInterface> listOfCars = new ArrayList<>();

        //First line in the file is the business name
        String line = in.readLine();

        String bussinessName = line;
        int id = 0; // we want to create unique id for every car


        /*While loop to split each line to obtain the values of Make, rent price and 
        number of cars available for rental.
         */
        while ((line = in.readLine()) != null) {
            String[] details = line.split(":"); //Make:Price:number of cars available
            Make make = Make.valueOf(details[0]);
            double rate = Double.parseDouble(details[1]);
            int numberOfCars = Integer.parseInt(details[2]);

            /*For loop to create a new car the number of times it 
            says in the file.
             */
            for (int i = 0; i < numberOfCars; i++) {
                id++;
                //creating new car
                listOfCars.add(new Car(make, rate, id));
            }
        }
        return new RentACar(listOfCars, bussinessName);
    }
}
