package com.ordersystem.model;

public class MenuItem {
  private String name; // 餐點名稱
  private double price; // 餐點價格
  private String description; // 餐點描述

  public MenuItem(String name, double price, String description) {
    this.name = name;
    this.price = price;
    this.description = description;
  }

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }
}
