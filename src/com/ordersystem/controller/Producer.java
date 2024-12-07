package com.ordersystem.controller;

import java.util.concurrent.BlockingQueue;

import com.ordersystem.model.AbstractOrder;
import com.ordersystem.model.AbstractProducer;

public class Producer extends AbstractProducer {
  private BlockingQueue<AbstractOrder> queue; // 訂單佇列

  public Producer(BlockingQueue<AbstractOrder> queue) {
    super(); // 呼叫父類別的建構子
    this.queue = queue; // 初始化佇列
  }

  @Override
  public void addOrder(AbstractOrder order) {
    super.addOrder(order); // 呼叫父類別方法，將訂單加入列表
    queue.offer(order);
  }

  public BlockingQueue<AbstractOrder> getQueue() {
    return this.queue;
  }
}
