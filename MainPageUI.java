
import javax.swing.*;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class MainPageUI extends JFrame {
    private double subtotal;
    private JLabel subtotalLabel;
    private JPanel orderItemsPanel;
    private JPanel foodGrid;
    private final List<FoodItem> orderedItems;
    private final Map<String, List<FoodItem>> categoryMap = new HashMap<>();
    private List<FoodItem> currentItems = new ArrayList<>();
    private JRadioButton counterPayment;
    private JRadioButton qrPayment;

    public MainPageUI(String restaurantName) {
        this.subtotal = 0;
        this.orderedItems = new ArrayList<>();

        initializeCategoryMap();

        setTitle(restaurantName + " SarapLikha KITCHEN");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        add(createTopBar(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createOrderPanel(), BorderLayout.EAST);

        setVisible(true);
    }
  
    private void initializeCategoryMap() {
        categoryMap.put("Dinner", List.of(
            new FoodItem("Sinigang", 69, "D://NEW VERSION KIOSK//java//img_of_food//discover_facebook.jpg"),
            new FoodItem("Bulalo", 69, "D://NEW VERSION KIOSK//java//img_of_food//filipino_nilaga.jpg"),
            new FoodItem("Bagnet", 69, "D://NEW VERSION KIOSK//java//img_of_food//bagnet.jpg"),
            new FoodItem("Lechon", 69, "D://NEW VERSION KIOSK//java//img_of_food//LECHON.jpg"),
            new FoodItem("Chicken adobo", 69, "D://NEW VERSION KIOSK//java//img_of_food//chicken_adobo.jpg"),
            new FoodItem("Chicken curry", 69, "D://NEW VERSION KIOSK//java//img_of_food//chicken_curry.jpg")
        ));

        categoryMap.put("Lunch", List.of(
            new FoodItem("Burger", 99, "D://NEW VERSION KIOSK//java//img_of_food//burger.jpg"),
            new FoodItem("Pizza",12, "D://NEW VERSION KIOSK//java//img_of_food//pizza.jpg"),
            new FoodItem("Pasta", 110, "D://NEW VERSION KIOSK//java//img_of_food//pasta.jpg"),
            new FoodItem("Salad", 80, "D://NEW VERSION KIOSK//java//img_of_food//salad.jpg"),
            new FoodItem("Soup", 70, "D://NEW VERSION KIOSK//java//img_of_food//soup.jpg")
        ));

        categoryMap.put("Breakfast", List.of(
            new FoodItem("Pancakes", 50, "D://NEW VERSION KIOSK//java//img_of_food//pancakes.jpg"),
            new FoodItem("Omelette", 60, "D://NEW VERSION KIOSK//java//img_of_food//omelette.jpg"),
            new FoodItem("Toast", 40, "D://NEW VERSION KIOSK//java//img_of_food//toast.jpg"),
            new FoodItem("Cereal", 30, "D://NEW VERSION KIOSK//java//img_of_food//cereal.jpg"),
            new FoodItem("Smoothie", 45, "D://NEW VERSION KIOSK//java//img_of_food//smoothie.jpg")
        ));
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        topBar.setBackground(new Color(245, 245, 245));

        JLabel logoLabel = new JLabel("SarapLikha", SwingConstants.LEFT);
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        topBar.add(logoLabel, BorderLayout.WEST);


        ImageIcon logoIcon = new ImageIcon(getClass().getClassLoader().getResource("icon//new logo.png"));
        Image scaledImage = logoIcon.getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledImage);

        JLabel scaledLogoLabel = new JLabel(scaledLogoIcon);
        scaledLogoLabel.setPreferredSize(new Dimension(50, 40));

        JLabel profileIcon = new JLabel("👤", SwingConstants.CENTER);
        profileIcon.setFont(new Font("SansSerif", Font.PLAIN, 22));
        profileIcon.setPreferredSize(new Dimension(100, 40));
        profileIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add MouseListener to open the Contacts window
        profileIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Open the Contacts window
                SwingUtilities.invokeLater(() -> {
                    new contacts().createUI();
                });

                // Dispose of the MainPageUI window
                dispose();
            }
        });

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        iconPanel.setOpaque(false);
        iconPanel.add(scaledLogoLabel);
        iconPanel.add(profileIcon);

        topBar.add(iconPanel, BorderLayout.EAST);

        return topBar;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Banner panel
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.setPreferredSize(new Dimension(600, 150));
        bannerPanel.setBackground(new Color(255, 204, 128));
        bannerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel bannerImageLabel = new JLabel();
        bannerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bannerImageLabel.setPreferredSize(new Dimension(600, 150));
        bannerPanel.add(bannerImageLabel, BorderLayout.CENTER);

        List<String> imagePaths = List.of(
            "rotationpanelads/1304280.png",
            "rotationpanelads/1304281.png",
            "rotationpanelads/Screenshot_2025_03_08_222053.png",
            "rotationpanelads/wp3745669-yuru-camp-wallpapers.png"
        );

        setBannerImage(bannerImageLabel, imagePaths.get(0));

        new javax.swing.Timer(8000, e -> {
            int currentIndex = imagePaths.indexOf(bannerImageLabel.getName());
            int nextIndex = (currentIndex >= 0 ? currentIndex + 1 : 1) % imagePaths.size();
            setBannerImage(bannerImageLabel, imagePaths.get(nextIndex));
        }).start();

        mainPanel.add(bannerPanel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton breakfastButton = createStyledButton("Breakfast", new Color(255, 140, 0));
        JButton lunchButton = createStyledButton("Lunch", new Color(255, 204, 0));
        JButton dinnerButton = createStyledButton("Dinner", new Color(255, 87, 34));

        buttonPanel.add(breakfastButton);
        buttonPanel.add(lunchButton);
        buttonPanel.add(dinnerButton);

        // Food grid (3x3)
        foodGrid = new JPanel(new GridLayout(4, 4, 20, 20));
        foodGrid.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        foodGrid.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(foodGrid);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll

        // Populate grid
        updateFoodGrid("Dinner");

        breakfastButton.addActionListener(e -> updateFoodGrid("Breakfast"));
        lunchButton.addActionListener(e -> updateFoodGrid("Lunch"));
        dinnerButton.addActionListener(e -> updateFoodGrid("Dinner"));

        centerPanel.add(buttonPanel);
        centerPanel.add(scrollPane);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        return mainPanel;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void setBannerImage(JLabel label, String path) {
        URL imageUrl = getClass().getClassLoader().getResource(path);
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaled = icon.getImage().getScaledInstance(label.getWidth() > 0 ? label.getWidth() : 1570, 150, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaled));
            label.setText("");
            label.setName(path);
        } else {
            label.setIcon(null);
            label.setText("[Image not found: " + path + "]");
            label.setName("");
        }
    }

    private void updateFoodGrid(String category) {
        currentItems = categoryMap.getOrDefault(category, new ArrayList<>());
        foodGrid.removeAll();
        for (FoodItem item : currentItems) {
            foodGrid.add(createRestaurantCard(item));
        }
        foodGrid.revalidate();
        foodGrid.repaint();
    }

    private JPanel createRestaurantCard(FoodItem item) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        card.setBackground(Color.WHITE);

        JLabel imgLabel = new JLabel("[No Image]", SwingConstants.CENTER);
        if (item.getImagePath() != null) {
            URL imageUrl = getClass().getClassLoader().getResource(item.getImagePath());
            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image scaled = icon.getImage().getScaledInstance(540, 350, Image.SCALE_SMOOTH);
                imgLabel = new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);
            } else {
                imgLabel.setText("[Image not found]");
            }
        }

        imgLabel.setPreferredSize(new Dimension(540, 350));
        imgLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));

        JLabel priceLabel = new JLabel("₱" + item.getPrice());
        priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton orderButton = new JButton("Order");
        orderButton.setFocusPainted(false);
        orderButton.setBackground(new Color(255, 140, 0));
        orderButton.setForeground(Color.WHITE);
        orderButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        orderButton.setPreferredSize(new Dimension(100, 40));
        orderButton.addActionListener(e -> addToOrder(item));

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(infoPanel, BorderLayout.CENTER);
        bottomPanel.add(orderButton, BorderLayout.EAST);

        card.add(imgLabel, BorderLayout.CENTER);
        card.add(bottomPanel, BorderLayout.SOUTH);

        return card;
    }

    private void addToOrder(FoodItem item) {
        boolean itemExists = false;

        // Check if the item already exists in the order
        for (FoodItem orderedItem : orderedItems) {
            if (orderedItem.getName().equals(item.getName())) {
                orderedItem.incrementQuantity(); // Increment quantity if item exists
                itemExists = true;
                break;
            }
        }

        // If the item does not exist, add it to the order
        if (!itemExists) {
            orderedItems.add(new FoodItem(item.getName(), item.getPrice(), item.getImagePath(), 1)); // Add with quantity 1
        }

        updateOrderPanel();
    }

    private void updateOrderPanel() {
        orderItemsPanel.removeAll();

        for (FoodItem item : orderedItems) {
            // Create a box-style panel for each order item
            JPanel itemBox = new JPanel();
            itemBox.setLayout(new BorderLayout(10, 0));
            itemBox.setBackground(Color.WHITE);
            itemBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true)
            ));
            
            // Left panel for item name and quantity controls
            JPanel leftPanel = new JPanel(new BorderLayout(5, 0));
            leftPanel.setOpaque(false);
            
            // Item name
            JLabel nameLabel = new JLabel(item.getName());
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            leftPanel.add(nameLabel, BorderLayout.NORTH);
            
            // Quantity control panel
            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            quantityPanel.setOpaque(false);
            
            // Quantity label with "x" prefix
            JLabel quantityLabel = new JLabel("x" + item.getQuantity());
            quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            quantityLabel.setForeground(new Color(100, 100, 100));
            
            quantityPanel.add(quantityLabel);
            leftPanel.add(quantityPanel, BorderLayout.CENTER);
            
            // Right panel for price and remove button
            JPanel rightPanel = new JPanel(new BorderLayout(5, 0));
            rightPanel.setOpaque(false);
            
            // Price label
            JLabel priceLabel = new JLabel("₱" + (item.getPrice() * item.getQuantity()));
            priceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            rightPanel.add(priceLabel, BorderLayout.NORTH);
            
            // Remove button
            JButton removeButton = new JButton("Remove");
            removeButton.setFocusPainted(false);
            removeButton.setBackground(new Color(255, 140, 0));
            removeButton.setForeground(Color.WHITE);
            removeButton.setFont(new Font("SansSerif", Font.BOLD, 12));
            removeButton.setPreferredSize(new Dimension(80, 25)); // Smaller, more compact size
            removeButton.setBorderPainted(false); // Remove border for cleaner look
            removeButton.addActionListener(e -> {
                orderedItems.remove(item);
                updateOrderPanel();
            });
            rightPanel.add(removeButton, BorderLayout.SOUTH);
            
            // Add panels to the item box
            itemBox.add(leftPanel, BorderLayout.WEST);
            itemBox.add(rightPanel, BorderLayout.EAST);
            
            // Add the item box to the order panel
            orderItemsPanel.add(itemBox);
            orderItemsPanel.add(Box.createVerticalStrut(5)); // Add spacing between items
        }

        updateSubtotal();
        orderItemsPanel.revalidate();
        orderItemsPanel.repaint();
    }

    private void updateSubtotal() {
        subtotal = orderedItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity()) // Multiply price by quantity
                .sum();

        subtotalLabel.setText("Sub Total: ₱" + subtotal);
    }

private JPanel createOrderPanel() {
    JPanel orderPanel = new JPanel();
    orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
    orderPanel.setPreferredSize(new Dimension(400, 0));
    orderPanel.setBackground(Color.WHITE);
    orderPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    // Header row with "My Orders"
    JLabel header = new JLabel("My Orders");
    header.setFont(new Font("Arial", Font.BOLD, 20));
    header.setForeground(new Color(50, 50, 50));  // Darker header color
    header.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
    header.setAlignmentX(Component.LEFT_ALIGNMENT); // Ensure alignment in BoxLayout
    orderPanel.add(header);

    // Order items panel with scrolling
    orderItemsPanel = new JPanel();
    orderItemsPanel.setLayout(new BoxLayout(orderItemsPanel, BoxLayout.Y_AXIS));
    orderItemsPanel.setBackground(Color.WHITE);

    JScrollPane scrollPane = new JScrollPane(orderItemsPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(360, 300)); // Adjusted width to prevent horizontal scrollbar
    scrollPane.getViewport().setBackground(Color.WHITE);
    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
    orderPanel.add(scrollPane);

    // Panel for subtotal, clear button, and checkout button
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
    bottomPanel.setBackground(Color.WHITE);
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

    // Subtotal label
    subtotalLabel = new JLabel("Sub Total: ₱" + subtotal);
    subtotalLabel.setFont(new Font("Arial", Font.BOLD, 16));
    subtotalLabel.setForeground(new Color(33, 33, 33));
    bottomPanel.add(subtotalLabel);

    // Clear All button
    JButton clearAllButton = new JButton("Clear All");
    clearAllButton.setFont(new Font("Arial", Font.BOLD, 14));
    clearAllButton.setBackground(new Color(255, 87, 34));  // Warm orange color
    clearAllButton.setForeground(Color.WHITE);
    clearAllButton.setFocusPainted(false);
    clearAllButton.setBorderPainted(false);
    clearAllButton.setPreferredSize(new Dimension(100, 40)); // Smaller than checkout button
    clearAllButton.addActionListener(e -> {
        orderedItems.clear();
        updateOrderPanel();
    });
    
    // Add some spacing between buttons
    bottomPanel.add(Box.createHorizontalGlue());  // Push the button to the right
    bottomPanel.add(clearAllButton);
    bottomPanel.add(Box.createHorizontalStrut(10)); // 10px spacing between buttons

    // Checkout button
    JButton checkoutBtn = new JButton("Check out");
    checkoutBtn.setBackground(new Color(76, 175, 80));  // Green color
    checkoutBtn.setForeground(Color.WHITE);
    checkoutBtn.setFont(new Font("Arial", Font.BOLD, 16));
    checkoutBtn.setFocusPainted(false);
    checkoutBtn.setBorderPainted(false);
    checkoutBtn.setPreferredSize(new Dimension(120, 40)); // Larger, more prominent size

    // Add ActionListener to display order summary
    checkoutBtn.addActionListener(e -> {
        if (orderedItems.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "Your order list is empty!",
                "Order Summary",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            if (qrPayment.isSelected()) {
                // Show QR code for payment
                JDialog qrDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(orderPanel), "Scan QR Code", true);
                qrDialog.setLayout(new BorderLayout()); // Use BorderLayout for the dialog
                qrDialog.setSize(400, 500);// Set size of the dialog
                qrDialog.setLocationRelativeTo(null);// Center the dialog on the screen
                
                // Create a panel for QR code
                JPanel qrPanel = new JPanel(new BorderLayout());
                qrPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                qrPanel.setBackground(Color.WHITE);
                
                // QR code image (placeholder - in real app, generate based on order)
                JLabel qrImageLabel = new JLabel();
                qrImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                
                // Try to load a sample QR code image
                try {
                    URL qrUrl = getClass().getClassLoader().getResource("icon//gcash.jpg");
                    if (qrUrl != null) {
                        ImageIcon qrIcon = new ImageIcon(qrUrl);
                        Image scaledQr = qrIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                        qrImageLabel.setIcon(new ImageIcon(scaledQr));
                    } else {
                        // Create a placeholder QR code
                        BufferedImage qrPlaceholder = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = qrPlaceholder.createGraphics();
                        g2d.setColor(Color.WHITE);
                        g2d.fillRect(0, 0, 250, 250);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(0, 0, 249, 249);
                        g2d.drawString("QR Code Placeholder", 70, 125);
                        g2d.dispose();
                        qrImageLabel.setIcon(new ImageIcon(qrPlaceholder));
                    }
                } catch (Exception ex) {
                    qrImageLabel.setText("QR Code not available");
                }
                
                JLabel scanInstructionLabel = new JLabel("Scan this QR code to pay for your order");
                scanInstructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
                scanInstructionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                
                JLabel totalAmountLabel = new JLabel("Total Amount: ₱" + String.format("%.2f", subtotal + (subtotal * 0.08)));
                totalAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
                totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
                
                JPanel instructionPanel = new JPanel();
                instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
                instructionPanel.setOpaque(false);
                instructionPanel.add(scanInstructionLabel);
                instructionPanel.add(Box.createVerticalStrut(10));
                instructionPanel.add(totalAmountLabel);
                
                JButton paidButton = new JButton("I've Paid");
                paidButton.setBackground(new Color(76, 175, 80));
                paidButton.setForeground(Color.WHITE);
                paidButton.setFont(new Font("Arial", Font.BOLD, 16));
                paidButton.setFocusPainted(false);
                
                paidButton.addActionListener(event -> {
                    qrDialog.dispose();
                    
                    // Generate a 2-digit random order number
                    int orderNumber = (int) (Math.random() * 90) + 10; // Random number between 10 and 99
                    
                    // Get current date and time
                    java.util.Date date = new java.util.Date();// Get current date and time
                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// Format date and time
                    String dateTime = dateFormat.format(date);

                    // Create receipt-style summary for QR payment
                    StringBuilder receipt = new StringBuilder();
                    receipt.append("=======================================\n");
                    receipt.append("            SARAP LIKHA KITCHEN        \n");
                    receipt.append("=======================================\n\n");
                    receipt.append("Order #: ").append(orderNumber).append("\n");
                    receipt.append("Date: ").append(dateTime).append("\n");
                    receipt.append("Payment Method: QR Code Payment (PAID)\n");
                    receipt.append("---------------------------------------\n\n");
                    receipt.append("ITEMS                      QTY    PRICE\n");
                    receipt.append("---------------------------------------\n");
                    
                    // Add each item with proper spacing and alignment
                    for (FoodItem item : orderedItems) {
                        String name = item.getName();
                        int qty = item.getQuantity();
                        double price = item.getPrice() * qty;
                        
                        // Truncate name if too long
                        if (name.length() > 20) {
                            name = name.substring(0, 17) + "...";
                        }
                        
                        // Pad name to align quantities
                        String paddedName = String.format("%-24s", name);
                        
                        // Format quantity and price
                        String formattedQty = String.format("%2d", qty);
                        String formattedPrice = String.format("%8.2f", price);
                        
                        receipt.append(paddedName).append(formattedQty).append("  ₱").append(formattedPrice).append("\n");
                    }
                    
                    receipt.append("---------------------------------------\n");
                    
                    // Add subtotal, tax, and total
                    double tax = subtotal * 0.08; // Assuming 8% tax
                    double total = subtotal + tax;
                    
                    receipt.append(String.format("%-26s ₱%8.2f\n", "Subtotal:", subtotal));
                    receipt.append(String.format("%-26s ₱%8.2f\n", "Tax (8%):", tax));
                    receipt.append("---------------------------------------\n");
                    receipt.append(String.format("%-26s ₱%8.2f\n", "TOTAL:", total));
                    receipt.append("\n=======================================\n");
                    receipt.append("          PAYMENT RECEIVED!            \n");
                    receipt.append("  PROCEED TO COUNTER TO COLLECT ORDER  \n");
                    receipt.append("=======================================\n");

                    // Create a monospaced font for the receipt
                    Font receiptFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
                    
                    // Create a JTextArea to display the receipt with proper formatting
                    JTextArea receiptArea = new JTextArea(receipt.toString());
                    receiptArea.setFont(receiptFont);
                    receiptArea.setEditable(false);
                    receiptArea.setBackground(new Color(255, 255, 240)); // Light yellow background
                    
                    // Show the receipt in a scrollable pane
                    JScrollPane receiptScrollPane = new JScrollPane(receiptArea);
                    receiptScrollPane.setPreferredSize(new Dimension(400, 500));
                    
                    // Show the order summary
                    JOptionPane.showMessageDialog(
                        null,
                        receiptScrollPane,
                        "Payment Receipt",
                        JOptionPane.PLAIN_MESSAGE
                    );

                    // Show the "Thanks for ordering" message
                    int response = JOptionPane.showConfirmDialog(
                        null,
                        "Your order has been placed! Press OK to return to the main menu.",
                        "Thank You",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    // If "OK" is clicked, open Mainapp and close MainPageUI
                    if (response == JOptionPane.OK_OPTION) {
                        SwingUtilities.invokeLater(() -> {
                            dispose(); // Close MainPageUI first
                            Mainapp.main(new String[0]); // Open Mainapp by calling its main method
                        });
                    }
                });
                
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setBackground(new Color(255, 87, 34));
                cancelButton.setForeground(Color.WHITE);
                cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
                cancelButton.setFocusPainted(false);
                
                cancelButton.addActionListener(event -> qrDialog.dispose());
                
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
                buttonPanel.setOpaque(false);
                buttonPanel.add(paidButton);
                buttonPanel.add(cancelButton);
                
                qrPanel.add(qrImageLabel, BorderLayout.CENTER);
                qrPanel.add(instructionPanel, BorderLayout.NORTH);
                qrPanel.add(buttonPanel, BorderLayout.SOUTH);
                
                qrDialog.add(qrPanel);
                qrDialog.setVisible(true);
            } else {
                // Pay at counter - show receipt
                // Generate a 2-digit random order number
                int orderNumber = (int) (Math.random() * 90) + 10; // Random number between 10 and 99
                
                // Get current date and time
                java.util.Date date = new java.util.Date();
                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = dateFormat.format(date);

                // Create receipt-style summary
                StringBuilder receipt = new StringBuilder();
                receipt.append("=======================================\n");
                receipt.append("            SARAP LIKHA KITCHEN        \n");
                receipt.append("=======================================\n\n");
                receipt.append("Order #: ").append(orderNumber).append("\n");
                receipt.append("Date: ").append(dateTime).append("\n");
                receipt.append("Payment Method: Pay at Counter\n");
                receipt.append("---------------------------------------\n\n");
                receipt.append("ITEMS                      QTY    PRICE\n");
                receipt.append("---------------------------------------\n");
                
                // Add each item with proper spacing and alignment
                for (FoodItem item : orderedItems) {
                    String name = item.getName();
                    int qty = item.getQuantity();
                    double price = item.getPrice() * qty;
                    
                    // Truncate name if too long
                    if (name.length() > 20) {
                        name = name.substring(0, 17) + "...";
                    }
                    
                    // Pad name to align quantities
                    String paddedName = String.format("%-24s", name);
                    
                    // Format quantity and price
                    String formattedQty = String.format("%2d", qty);
                    String formattedPrice = String.format("%8.2f", price);
                    
                    receipt.append(paddedName).append(formattedQty).append("  ₱").append(formattedPrice).append("\n");
                }
                
                receipt.append("---------------------------------------\n");
                
                // Add subtotal, tax, and total
                double tax = subtotal * 0.08; // Assuming 8% tax
                double total = subtotal + tax;
                
                receipt.append(String.format("%-26s ₱%8.2f\n", "Subtotal:", subtotal));
                receipt.append(String.format("%-26s ₱%8.2f\n", "Tax (8%):", tax));
                receipt.append("---------------------------------------\n");
                receipt.append(String.format("%-26s ₱%8.2f\n", "TOTAL:", total));
                receipt.append("\n=======================================\n");
                receipt.append("          THANK YOU FOR ORDERING!      \n");
                receipt.append("          PLEASE PAY AT COUNTER        \n");
                receipt.append("=======================================\n");

                // Create a monospaced font for the receipt
                Font receiptFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
                
                // Create a JTextArea to display the receipt with proper formatting
                JTextArea receiptArea = new JTextArea(receipt.toString());
                receiptArea.setFont(receiptFont);
                receiptArea.setEditable(false);
                receiptArea.setBackground(new Color(255, 255, 240)); // Light yellow background
                
                // Show the receipt in a scrollable pane
                JScrollPane receiptScrollPane = new JScrollPane(receiptArea);
                receiptScrollPane.setPreferredSize(new Dimension(400, 500));
                
                // Show the order summary
                JOptionPane.showMessageDialog(
                    null,
                    receiptScrollPane,
                    "Order Receipt",
                    JOptionPane.PLAIN_MESSAGE
                );

                // Show the "Thanks for ordering" message
                int response = JOptionPane.showConfirmDialog(
                    null,
                    "Your order has been placed! Press OK to return to the main menu.",
                    "Thank You",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
                );

                // If "OK" is clicked, open Mainapp and close MainPageUI
                if (response == JOptionPane.OK_OPTION) {
                    SwingUtilities.invokeLater(() -> {
                        dispose(); // Close MainPageUI first
                        Mainapp.main(new String[0]); // Open Mainapp by calling its main method
                    });
                }
            }
        }
    });

    bottomPanel.add(Box.createHorizontalGlue());  // Push the button to the right
    bottomPanel.add(checkoutBtn);

    orderPanel.add(bottomPanel);

    // Payment method panel
    JPanel paymentMethodPanel = new JPanel();
    paymentMethodPanel.setLayout(new BoxLayout(paymentMethodPanel, BoxLayout.Y_AXIS));
    paymentMethodPanel.setBackground(Color.WHITE);
    paymentMethodPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    
    JLabel paymentLabel = new JLabel("Payment Method:");
    paymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
    paymentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    radioPanel.setOpaque(false);
    
    ButtonGroup paymentGroup = new ButtonGroup();
    counterPayment = new JRadioButton("Pay at Counter");
    qrPayment = new JRadioButton("Scan QR Code");
    
    counterPayment.setSelected(true);
    counterPayment.setOpaque(false);
    qrPayment.setOpaque(false);
    
    paymentGroup.add(counterPayment);
    paymentGroup.add(qrPayment);
    
    radioPanel.add(counterPayment);
    radioPanel.add(qrPayment);
    
    paymentMethodPanel.add(paymentLabel);
    paymentMethodPanel.add(radioPanel);
    
    orderPanel.add(paymentMethodPanel);

    return orderPanel;
}

    public static class FoodItem {
        private final String name;
        private final double price;
        private final String imagePath;
        private int quantity;

        public FoodItem(String name, double price, String imagePath) {
            this.name = name;
            this.price = price;
            this.imagePath = imagePath;
            this.quantity = 1;
        }

        public FoodItem(String name, double price, String imagePath, int quantity) {
            this.name = name;
            this.price = price;
            this.imagePath = imagePath;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getImagePath() {
            return imagePath;
        }

        public int getQuantity() {
            return quantity;
        }

        public void incrementQuantity() {
            this.quantity++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPageUI("SarapLikha"));
    }
}
