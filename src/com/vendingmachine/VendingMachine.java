package com.vendingmachine;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingMachine {

	public static void main(String[] args) {
		int cashIn = 0;
		Item purchaseItem = null;
		int itemToPurchase = 0;

		Scanner scan = new Scanner(System.in);
		ArrayList<Item> items = getInitialItems();
		int minItemPrice = getMinItemPrice(items);

		do {
			purchaseItem = null; // reset the purchaseItem
			System.out.println("Welcome from vending machine.");

			displayAllItemsList(items);

			do {
				try {
					System.out.print("Please input your cash: ");
					cashIn += scan.nextInt();
					break; // break point
				} catch (InputMismatchException e) {
					System.out.println("Please input valid cash amount!");
				}
				scan.nextLine(); // clear the buffer
			} while (true);

			// checking the customer's balance is enough to buy at least one item
			if (cashIn < minItemPrice) {
				System.out.println("Your input amount is not enough.\n");
				cashIn = refundAndResetTheAmount(cashIn);
				continue;
			}

			System.out.println("\nChoose the following Items-");

			displayItemsListByCash(items, cashIn);

			do {
				try {
					System.out.print("Please choose an item to purchase: ");
					itemToPurchase = scan.nextInt();
					break; // break point
				} catch (InputMismatchException e) {
					System.out.println("Please input valid item number!");
				}
				scan.nextLine(); // clear the buffer
			} while (true);
			scan.nextLine(); // clear the buffer

			if (itemToPurchase > 0 && itemToPurchase <= items.size())
				purchaseItem = items.get(itemToPurchase - 1);

			if (purchaseItem != null && purchaseItem.getItemPrice() <= cashIn) {
				cashIn -= purchaseItem.getItemPrice();
				System.out.println("Thank you!\n");

				if (!isContinueOrNot(scan))
					cashIn = refundAndResetTheAmount(cashIn);

			} else {
				System.out.println("Your input amount is not enough.\n");
				cashIn = refundAndResetTheAmount(cashIn);
			}

		} while (true);
	}

	/*
	 * set the initial items list and return them as array list
	 */
	public static ArrayList<Item> getInitialItems() {
		ArrayList<Item> items = new ArrayList<Item>();

		items.add(new Item("Tea", 190));
		items.add(new Item("Pure Water", 100));
		items.add(new Item("Pepsi", 130));
		items.add(new Item("Coffee", 150));
		items.add(new Item("Energy Drink", 220));

		return items;
	}

	/*
	 * display available all items list
	 */
	public static void displayAllItemsList(ArrayList<Item> items) {
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i + 1) + ". " + items.get(i).toString());
		}
	}

	/*
	 * display items list by customer's balance
	 */
	public static void displayItemsListByCash(ArrayList<Item> items, int cashIn) {
		for (int i = 0; i < items.size(); i++) {
			if (cashIn >= items.get(i).getItemPrice())
				System.out.println((i + 1) + ". " + items.get(i).toString());
		}
	}

	/*
	 * get the minimum value of items
	 */
	public static int getMinItemPrice(ArrayList<Item> items) {
		int min = Integer.MAX_VALUE;

		for (Item item : items) {
			if (item.getItemPrice() < min)
				min = item.getItemPrice();
		}

		return min;
	}

	/*
	 * continue or not, return true/false as boolean
	 */
	public static boolean isContinueOrNot(Scanner scan) {
		do {
			System.out.print("Would you like to continue? (Yes/No): ");
			switch (scan.nextLine()) {
			case "Yes":
				return true;
			case "No":
				return false;
			default:
				System.out.println("Please enter the valid input.");
			}
		} while (true);
	}

	/*
	 * display remaining amount and set the cash to zero as default
	 */
	public static int refundAndResetTheAmount(int cashIn) {
		System.out.println("Your changes: " + cashIn + "\n");
		return 0; // reset the cash
	}

}
