package com.ordersystem.model;

import java.util.Map;

public interface InterfaceOrder {
  long getId();
  Map<MenuItem, Integer> getItems();
  Map.Entry<MenuItem, Integer> getItem(String name);
  OrderStatus getStatus();
  void setStatus(OrderStatus status);
  void addItem(MenuItem item, int quantity);
  double getTotalPrice();
}
