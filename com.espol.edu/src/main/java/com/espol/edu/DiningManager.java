package com.espol.edu;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class DiningManager {
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<Integer, Integer> order = new HashMap<>();
        Map<Integer, String> foodNames = new HashMap<>();
        Map<Integer, Integer> menu = new HashMap<>();

        foodNames.put(1, "Hamburguesa");
        foodNames.put(2, "Pizza");
        foodNames.put(3, "Pasta");
        foodNames.put(4, "Filete");
        foodNames.put(5, "Ensalada");

        menu.put(1, 10); // 1: Hamburguesa
        menu.put(2, 12); // 2: Pizza
        menu.put(3, 8); // 3: Pasta
        menu.put(4, 15); // 4: Filete
        menu.put(5, 6); // 5: Ensalada

        double totalCost = 5; // Costo base

        System.out.println("¡Bienvenido al Administrador de Experiencia Gastronómica!");

        while (true) {
            System.out.println("\nMenú:");
            for (int mealNumber : menu.keySet()) {
                System.out.println(mealNumber + ": " + foodNames.get(mealNumber) + " - $" + menu.get(mealNumber));
            }

            System.out.print("Ingrese el número de la comida que desea ordenar (o '0' para finalizar): ");
            try {
                int mealNumber = Integer.parseInt(input.nextLine());
                if (mealNumber == 0) {
                    break;
                }
                if (!menu.containsKey(mealNumber)) {
                    System.out.println("Error: Número de comida no válido. Por favor, seleccione un número del menú.");
                    continue;
                }

                int quantity = 0;
                while (true) {
                    System.out.print("Ingrese la cantidad de " + foodNames.get(mealNumber) + ": ");
                    try {
                        quantity = Integer.parseInt(input.nextLine());
                        if (quantity <= 0 || quantity > 100) {
                            System.out.println("Error: Cantidad inválida. La cantidad debe estar entre 1 y 100.");
                        } else {
                            break; // Cantidad válida, salir del bucle interno
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Cantidad no válida. Por favor, ingrese un número entero válido.");
                    }
                }

                // Validar la disponibilidad de la comida en el menú
                if (menu.containsKey(mealNumber)) {
                    order.put(mealNumber, quantity);
                } else {
                    System.out.println("Error: La comida seleccionada no está en el menú. Por favor, seleccione una comida del menú.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Número de comida no válido. Por favor, seleccione un número del menú.");
            }
        }

        for (int mealNumber : order.keySet()) {
            int quantity = order.get(mealNumber);
            totalCost += menu.get(mealNumber) * quantity;
        }

        int totalQuantity = order.values().stream().mapToInt(Integer::intValue).sum();

        // Aplicar descuentos según los requisitos
        if (totalQuantity > 10) {
            totalCost *= 0.8; // Descuento del 20%
        } else if (totalQuantity > 5) {
            totalCost *= 0.9; // Descuento del 10%
        }

        if (totalCost > 100) {
            totalCost -= 25; // Descuento de $25 si el costo total supera $100
        } else if (totalCost > 50) {
            totalCost -= 10; // Descuento de $10 si el costo total supera $50 pero no llega a $100
        }

        if (totalCost > 0) {
            System.out.println("\nResumen del Pedido:");
            for (int mealNumber : order.keySet()) {
                int quantity = order.get(mealNumber);
                System.out.println(foodNames.get(mealNumber) + " x" + quantity);
            }
            System.out.println("Costo total: $" + (int) totalCost);
        } else {
            System.out.println("Error: Entrada inválida o error de cálculo. Por favor, revise su pedido.");
        }

        input.close();
    }
}
