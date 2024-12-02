package com.ordersystem.view;

public enum Food {
  BURGER("漢堡", "burger.jpg", 50),
  FRIES("薯條", "fries.jpg", 20),
  COFFEE("咖啡", "coffee.jpg", 40),
  COLA("可樂", "cola.jpg", 25);

  private final String name;
  private final String filename;
  private final double price;

  Food(String name, String filename, double price) {
    this.name = name;
    this.filename = filename;
    this.price = price;
  }

  public String getName() {
    return this.name;
  }

  public String getFileName() {
    return this.filename;
  }

  public double getPrice() {
    return this.price;
  }
}