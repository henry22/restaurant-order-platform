package com.ordersystem.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.BorderLayout; // Java AWT(Abstract Window Toolkit)
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.ordersystem.controller.Consumer;
import com.ordersystem.controller.OrderFileManager;
import com.ordersystem.controller.Producer;
import com.ordersystem.model.AbstractOrder;
import com.ordersystem.model.MenuItem;
import com.ordersystem.model.Order;
import com.ordersystem.model.OrderStatus;

public class OrderSystemUI extends JFrame {
  // UI元件
  private JPanel menuPanel; // 顯示菜單
  private JPanel orderPanel; // 點餐區域
  private JPanel cartListPanel; // 購物車清單
  private JPanel statusPanel; // 訂單狀態
  private JPanel statusDisplay; // 狀態顯示區

  // 狀態標籤
  private JLabel waitingStatusLabel; // 等待中訂單
  private JLabel processingStatusLabel; // 處理中訂單
  private JLabel completedStatusLabel; // 完成訂單
  private JLabel waitingTimeLabel; // 等待時間
  private JLabel processingTimeLabel; // 處理時間
  private JLabel completedTimeLabel; // 完成時間

  // 操作元件
  private JComboBox<MenuItem> itemSelector; // 餐點選擇下拉選單
  private JSpinner quantitySpinner; // 數量選擇器
  private JButton orderButton; // 送出訂單按鈕
  private JButton processButton; // 處理訂單按鈕
  private JButton addButton; // 加入購物車按鈕

  // 業務邏輯相關
  private Producer producer; // 訂單生產者
  private Consumer consumer; // 訂單消費者
  private Map<MenuItem, Integer> cartListItems; // 購物車項目
  private final List<MenuItem> menuItems = new ArrayList<>(); // 菜單項目
  private OrderSummaryPanel orderSummaryPanel; // 訂單摘要面板
  private OrderFileManager orderFileManager; // 訂單檔案管理器

  private static final int MAX_IMAGE_SIZE = 100;

  public OrderSystemUI(Producer producer, Consumer consumer) {
    this.producer = producer;
    this.consumer = consumer;
    this.cartListItems = new HashMap<>();
    this.orderFileManager = new OrderFileManager();

    // 初始化視窗和元件
    initializeFrame();
    createJComponents();
    addListeners();
    loadSavedOrders(); // 載入已存儲的訂單
  }

  // 視窗初始化
  private void initializeFrame() {
    setTitle("餐廳點餐系統");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setLayout(new BorderLayout());

    // 關閉視窗時的處理
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        try {
          // 儲存等待中和已完成的訂單
          List<Order> ordersToSave = new ArrayList<>();

          // 收集等待中的訂單
          for(AbstractOrder order : producer.getTotalOrders()) {
            if(order.getStatus() == OrderStatus.WAITING) {
              ordersToSave.add((Order) order);
            }
          }

          // 收集已完成的訂單
          for(AbstractOrder order : consumer.getCompletedOrders()) {
            if(order.getStatus() == OrderStatus.COMPLETED) {
              ordersToSave.add((Order) order);
            }
          }

          // 儲存訂單
          orderFileManager.saveOrders(ordersToSave);

        } catch(Exception ex) {
          System.err.println("Error saving orders: " + ex.getMessage());
        }
      }
    });
  }

  // 建立主要元件
  private void createJComponents() {
    // 建立主要面板
    // TODO: Finish the rest of the parts.
    this.menuPanel = createMenuPanel();

    this.menuPanel.setBackground(java.awt.Color.GRAY);

    add(this.menuPanel, BorderLayout.NORTH);
  }

  // 建立菜單面板
  private JPanel createMenuPanel() {
    JPanel panel = new JPanel(new GridLayout(1, Food.values().length, 15, 0));
    panel.setBorder(BorderFactory.createTitledBorder("菜單"));

    // 為每個餐點建立卡片
    for(Food food : Food.values()) {
      JPanel menuCardPanel = createMenuCardPanel(food.getName(), food.getFileName(), food.getPrice());

      if(menuCardPanel != null) {
        panel.add(menuCardPanel);
      }
    }

    return panel;
  }

  private JPanel createMenuCardPanel(String name, String filename, double price) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    // 建立圖片面板
    JPanel imageItem = new JPanel(new BorderLayout());
    imageItem.setBorder(BorderFactory.createTitledBorder(name));

    try {
      // 載入並且縮放圖片
      String imagePath = "src/images/" + filename;
      File imageFile = new File(imagePath);

      if(!imageFile.exists()) {
        System.err.println("圖片文件不存在：" + imagePath);
        return null;
      }

      BufferedImage imageBuffer = ImageIO.read(imageFile);

      // 縮小原始菜單的圖片
      int originalWidth = imageBuffer.getWidth(null);
      int originalHeight = imageBuffer.getHeight(null);

      double scale = Math.min((double) MAX_IMAGE_SIZE / originalWidth, (double) MAX_IMAGE_SIZE / originalHeight);
      int scaledWidth = (int) (originalWidth * scale);
      int scaledHeight = (int) (originalHeight * scale);

      Image scaleImage = imageBuffer.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

      JLabel imageLabel = new JLabel(new ImageIcon(scaleImage));
      imageItem.add(imageLabel);
    } catch(IOException e) {
      e.printStackTrace();
    }

    // 產生菜單價格
    JLabel priceLabel = new JLabel("價格：" + price);
    priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    panel.add(imageItem);
    panel.add(priceLabel);

    // 紀錄food加到menuItem
    this.menuItems.add(new MenuItem(name, price, ""));
    System.out.println("menu items: " + this.menuItems);

    return panel;
  }

  private void addListeners() {}

  private void loadSavedOrders() {}
}
