package com.ordersystem.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class OrderSummaryPanel extends JPanel {
  private final JLabel totalAmountLabel;
  private final JLabel dailyRevenueLabel;

  public OrderSummaryPanel() {
    this.totalAmountLabel = new JLabel("總金額: $0.00");
    this.dailyRevenueLabel = new JLabel("今日營業額: $0.00");

    MatteBorder matteBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, Color.GRAY);
    TitledBorder title = BorderFactory.createTitledBorder(matteBorder, "訂單摘要");

    title.setTitleJustification(TitledBorder.CENTER);
    setBorder(title);

    add(this.totalAmountLabel);
    add(this.dailyRevenueLabel);
  }
}
