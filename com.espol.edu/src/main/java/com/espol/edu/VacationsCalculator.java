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
        Scanner input = new Scanner(System.in);

        String destination;
        int numTravelers;
        int duration;

        while (true) {
            System.out.print("Enter the destination of the vacation: ");
            destination = input.nextLine();
            if (isValidDestination(destination)) {
                break;
            } else {
                System.out.println("Invalid destination. Please enter a valid alphabetic destination name.");
            }
        }

        while (true) {
            System.out.print("Enter the number of travelers (1-80): ");
            if (input.hasNextInt()) {
                numTravelers = input.nextInt();
                if (numTravelers >= 1 && numTravelers <= 80) {
                    break;
                } else {
                    System.out.println("Invalid number of travelers. Please enter a number between 1 and 80.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number between 1 and 80.");
                input.next(); 
            }
        }

        while (true) {
            System.out.print("Enter the duration of the vacation in days: ");
            if (input.hasNextInt()) {
                duration = input.nextInt();
                if (duration > 0) {
                    break;
                } else {
                    System.out.println("Invalid duration. Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid. Please enter a valid duration.");
                input.next(); // Clear the invalid input
            }
        }

        int totalCost = calculateVacationCost(destination, numTravelers, duration);

        if (totalCost == -1) {
            System.out.println("Invalid input or exceeds group limit.");
        } else {
            int addOnsCost = calculateAddOnsCost(numTravelers);
            int totalCostWithAddOns = totalCost + addOnsCost;
            System.out.println("Total cost of the vacation package including add-ons: $" + totalCostWithAddOns);
        }

        input.close();
    }

    public static int calculateVacationCost(String destination, int numTravelers, int duration) {
        int totalCost = 1000;

        if (isPopularDestination(destination)) {
            if (destination.equalsIgnoreCase("Paris")) {
                totalCost += 500;
            } else if (destination.equalsIgnoreCase("New York City")) {
                totalCost += 600;
            }
        }

        if (numTravelers > 4 && numTravelers < 10) {
            totalCost = (int) (totalCost * 0.9);
        } else if (numTravelers >= 10) {
            totalCost = (int) (totalCost * 0.8);
        }

        if (duration < 7) {
            totalCost += 200;
        }

        if (duration > 30 || (numTravelers == 2)) {
            totalCost -= 200;
        }

        if (numTravelers > 80) {
            return -1;  // Exceeds group limit
        }

        return totalCost;
    }

    public static boolean isPopularDestination(String destination) {
        return destination.equalsIgnoreCase("Paris") || destination.equalsIgnoreCase("New York City");
    }

    public static boolean isValidDestination(String input) {
        return input != null && !input.isEmpty() && input.matches("^[a-zA-Z ]+$");
    }

    public static int calculateAddOnsCost(int numTravelers) {
        int allInclusiveCost = 200;
        int adventureActivitiesCost = 150;
        int spaAndWellnessCost = 100;

        int totalAddOnsCost = (allInclusiveCost + adventureActivitiesCost + spaAndWellnessCost) * numTravelers;

        return totalAddOnsCost;
    }
}
