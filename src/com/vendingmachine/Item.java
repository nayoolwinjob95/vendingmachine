package com.vendingmachine;

public class Item {
	private String itemName;
	private int itemPrice;
	private final String UNIT = "yen";

	public Item(String itemName, int itemPrice) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return itemName + " (" + itemPrice + UNIT + ")";
	}

}
