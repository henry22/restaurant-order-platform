package com.ordersystem.controller;

import java.util.Random;

import com.ordersystem.model.MenuItem;
import com.ordersystem.model.Order;

public class OrderFactory {
  private static final Random random = new Random(); // 隨機數產生器

  // 訂單創建方法
  public static Order createNextOrder(MenuItem item, int quantity) {
    // 驗證數量
    if(OrderValidator.isValidQuantity(quantity + "")) {
      // 生成訂單 ID
      long id = Long.parseLong(
        System.currentTimeMillis() + // 時間戳記 -> 13位數
        String.format("%04d", random.nextInt(10000)) // 隨機數 -> 4位數
      );

      // 創建訂單
      Order order = new Order(id);
      // 添加指定餐點項目和數量
      order.addItem(item, quantity);

      return order;
    }

    return null;
  }
}
