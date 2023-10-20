// Copyright (C) 2020
package com.espol.edu;
import java.util.Scanner;




public final class VacationsCalculator {
static final int FEE = 200;
static final int MAXPERSON=80;
static final int DISCOUNT=10;
static  int totalCost = 1000;
	/*
	* .Constructor
	*/
	private VacationsCalculator() { }

	/*
	* .Main function for the workshop
	* @args 
	*/
	public static void main(String[] args) {
		/*
		* .Main function for the workshop
		*/
		Scanner input = new Scanner(System.in);

        String destination;
        int nTravelers;
        int duration;
        System.out.println("******Vacation Package Cost Estimator******");
        while (true) {
            System.out.print("Enter the destination of the vacation: ");
            destination = input.nextLine();
            if (isValidDestination(destination)) {
                break;
            } else {
                System.out.println("Please enter a valid destination name.");
            }
        }

        while (true) {
            System.out.print("Enter the number of travelers (1-MAXPERSON): ");
            if (input.hasNextInt()) {
                nTravelers = input.nextInt();
                if (nTravelers >= 1 && nTravelers <= MAXPERSON) {
                    break;
                } else {
                    System.out.println("Please enter number between 1 and MAXPERSON");
                }
            } else {
                System.out.println("Please enter a number between 1 and MAXPERSON");
                input.next(); // Clear the invalid input
            }
        }

        while (true) {
            System.out.print("Enter the duration of the vacation in days: ");
            if (input.hasNextInt()) {
                duration = input.nextInt();
                if (duration > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } else {
                System.out.println("Please enter a number for the duration.");
                input.next(); // Clear the invalid input
            }
        }

        int totalCost = calculateVacationCost(destination, nTravelers, duration);

        if (totalCost == -1) {
            System.out.println("Invalid input or exceeds group limit.");
        } else {
            System.out.println("Total cost of the package: $" + totalCost);
        }

        input.close();
    }
//CHECKSTYLE:ON
	/**
     * Say something.
     * @param destination nTravelers duration
     */
    private static int calculateVacationCost(String destination, int nTravelers, int duration) {
    	/*
		* .Calculate the cost of vacations
		*/
        // Base cost
        

        // Additional cost based on destination
        if (isPopularDestination(destination)) {
            if (destination.equalsIgnoreCase("Paris")) {
                totalCost += 500;
            } else if (destination.equalsIgnoreCase("New York City")) {
                totalCost += 600;
            }
        }

        // Group discount
        if (nTravelers > 4 && nTravelers < DISCOUNT) {
            totalCost = (int) (totalCost * 0.9);
        } else if (nTravelers >= DISCOUNT) {
            totalCost = (int) (totalCost * 0.8);
        }

        // Penalty fee for vacation duration
        if (duration < 7) {
            totalCost += FEE;
        }

        // Promotion policy
        if (duration > 30 || (nTravelers == 2)) {
            totalCost -= FEE;
        }

        // Vacation package limit
        if (nTravelers > MAXPERSON) {
            return -1;  // Exceeds group limit
        }

        return totalCost;
    }
    /**
     * Say something.
     * @param destination
     * @return validation
     */
    public static boolean isPopularDestination(String destination) {
        return destination.equalsIgnoreCase("Paris") || destination.equalsIgnoreCase("New York City");
    }

    /**
     * Say something.
     * @param input
     * @return validation
     */
    public static boolean isValidDestination(String input) {
    	/*
    	* .Popular destination validator
    	*/
        // Check if the input contains only alphabetic characters and spaces
        return input != null && !input.isEmpty() && input.matches("^[a-zA-Z ]+$");
    }
}
