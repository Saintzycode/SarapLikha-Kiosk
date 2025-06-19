

// Import necessary Swing GUI components
import javax.swing.*;

// Import AWT (Abstract Window Toolkit) components for basic GUI operations
import java.awt.*;
// Import event handling classes
import java.awt.event.*;
// Import Rectangle2D for geometric operations
import java.awt.geom.Rectangle2D;
// Import BufferedImage and AlphaComposite for image processing
import java.awt.image.BufferedImage;


// Main class definition for the application
public class Mainapp extends JFrame { // Replacing MainPageUIFrame with JFrame
    // Main method - entry point of the application
    
    public static void main(String[] args) {        // Create a new JFrame window with title
        JFrame frame = new JFrame("SARAP LIKHA KIOSK");   
        // Set the window size to 1366x768 pixels
        frame.setSize(1366, 768);
        // Center the window on the screen
        frame.setLocationRelativeTo(null);
        // Set the close operation to exit the application when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the window title
        frame.setTitle("SARAP LIKHA Kiosk");

        // Set window icon (small icon in the frame)
        try {
            // Create an ImageIcon object from the specified path
            ImageIcon frameIcon = new ImageIcon("D://NEW VERSION KIOSK//java//icon//n" +  "ew logo.png");
            // Scale the icon to 768x768 pixels with smooth scaling
            Image scaledFrameIcon = frameIcon.getImage().getScaledInstance(768, 768, Image.SCALE_SMOOTH);
            // Set the scaled icon as the window icon
            frame.setIconImage(scaledFrameIcon);
        } catch (Exception e) {
            // Print error message if icon loading fails        
            System.out.println("Error loading frame icon: " + e.getMessage());
        }

        // Create a custom background panel with specified image
        JPanel background = new JPanel() {
            private Image backgroundImage = new ImageIcon("D://NEW VERSION KIOSK//java//icon//background.jpg").getImage();
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Set the layout manager for the background panel
        background.setLayout(new GridBagLayout());
        // Set the background panel as the frame's content pane
        frame.setContentPane(background);

        // Create main panel with GridBagLayout for component arrangement
        JPanel mainPanel = new JPanel(new GridBagLayout());
        // Make the main panel transparent
        mainPanel.setOpaque(false);

        // Create welcome label with custom rendering
        JLabel welcomeLabel = new JLabel("Welcome to Sarap Likha Kiosk") {
            // Define constants for shadow and styling
            private final int SHADOW_SIZE = 10;
            // Define brown color for container
            private final Color CONTAINER_COLOR = new Color(139, 69, 19);
            // Define shadow offset for text
            private final int TEXT_SHADOW_OFFSET = 2;
            
            // Override paintComponent for custom rendering
            @Override
            protected void paintComponent(Graphics g) {
                // Create a Graphics2D object for advanced rendering
                Graphics2D g2 = (Graphics2D) g.create();
                // Enable antialiasing for smoother edges
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Enable text antialiasing for smoother text
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                
                // Draw the shadow effect
                g2.setColor(new Color(0, 0, 0, 80));
                // Create rounded rectangle for shadow
                g2.fillRoundRect(SHADOW_SIZE, SHADOW_SIZE, getWidth() - SHADOW_SIZE, getHeight() - SHADOW_SIZE, 20, 20);
                
                // Draw the container background
                g2.setColor(CONTAINER_COLOR);
                // Create rounded rectangle for container
                g2.fillRoundRect(0, 0, getWidth() - SHADOW_SIZE, getHeight() - SHADOW_SIZE, 20, 20);
                
                // Configure text properties
                g2.setFont(new Font("Times New Roman", Font.BOLD, 42));
                // Get font metrics for text positioning
                FontMetrics fm = g2.getFontMetrics();
                // Calculate x position for centered text
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                // Calculate y position for centered text
                int textY = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                
                // Create text shadow effect
                g2.setColor(new Color(0, 0, 0, 90));
                // Draw multiple shadow layers for softer effect
                for (int i = 1; i <= 3; i++) {
                    g2.drawString(getText(), textX + (i * TEXT_SHADOW_OFFSET), textY + (i * TEXT_SHADOW_OFFSET));
                }
                
                // Draw the main text in white
                g2.setColor(Color.WHITE);
                g2.drawString(getText(), textX, textY);
                
                // Clean up graphics resources
                g2.dispose();
            }
            
            // Override getPreferredSize for proper sizing
            @Override
            public Dimension getPreferredSize() {
                // Get font metrics for size calculation
                FontMetrics fm = getFontMetrics(new Font("Times New Roman", Font.BOLD, 42));
                // Calculate width with padding
                int width = fm.stringWidth(getText()) + 60;
                // Calculate height with padding
                int height = fm.getHeight() + 40;
                // Return dimension including shadow size
                return new Dimension(width + SHADOW_SIZE, height + SHADOW_SIZE);
            }
        };
        
        // Set welcome label properties
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 42));
        // Set text color to white
        welcomeLabel.setForeground(Color.WHITE);
        // Center align the text
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Add padding around the label
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        

        // Begin try-catch block for loading the main logo
        try {
            // Load the main logo image
            ImageIcon mainLogoIcon = new ImageIcon("D://NEW VERSION KIOSK//java//icon//n" + "ew logo.png");
            // Scale the logo to 400x400 pixels
            Image scaledMainLogo = mainLogoIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            // Create label for the logo
            JLabel logoLabel = new JLabel(new ImageIcon(scaledMainLogo));
            // Center align the logo
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            // Create constraints for component positioning
            GridBagConstraints gbc = new GridBagConstraints();

            // Create image slider with food images
            String[] imagePaths = {
                "D://NEW VERSION KIOSK//java//img_of_food//sinigang.jpg",
                "D://NEW VERSION KIOSK//java//img_of_food//karekare.jpg",
                "D://NEW VERSION KIOSK//java//img_of_food//sisig.jpg",
                "D://NEW VERSION KIOSK//java//img_of_food//embutido.jpg",
                "D://NEW VERSION KIOSK//java//img_of_food//Instant-Pot-Leche-Flan-1-1.jpg",
                "D://NEW VERSION KIOSK//java//img_of_food//halo halo.jpg",
            };
            
            // Create the image slider with 5 second delay
            ImageSlider slider = new ImageSlider(imagePaths, 5000);
            
            // Add the image slider to the left side
            gbc.gridx = 0; // Position on the left
            gbc.gridy = 0;
            gbc.gridheight = 3; // Span across welcome label, logo, and button
            gbc.insets = new Insets(0, 0, 0, 20); // Add spacing to the right
            gbc.anchor = GridBagConstraints.CENTER;
            mainPanel.add(slider, gbc);

            // Adjust positions of other components
            gbc.gridx = 1; // Move welcome label, logo, and button to the right
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.insets = new Insets(10, 0, 20, 0);
            mainPanel.add(welcomeLabel, gbc);

            gbc.gridy = 1;
            gbc.insets = new Insets(0, 0, 0, 0);
            mainPanel.add(logoLabel, gbc);

            // Create custom start ordering button
            JButton startButton = new JButton("START ORDERING") {
                // Define button properties
                private Timer fadeTimer;
                private float shadowOpacity = 0.3f;
                private boolean isHovered = false;
                private final int SHADOW_SIZE = 5;
                private final Color BUTTON_COLOR = new Color(139, 69, 19);

                // Initialize button properties
                {
                    // Disable focus painting
                    setFocusPainted(false);
                    // Disable content area filling
                    setContentAreaFilled(false);
                    // Make button transparent
                    setOpaque(false);
                    // Remove border
                    setBorderPainted(false);
                    
                    // Create timer for fade effect
                    fadeTimer = new Timer(50, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Increase opacity when hovered
                            if (isHovered && shadowOpacity < 0.8f) {
                                shadowOpacity += 0.1f;
                            // Decrease opacity when not hovered
                            } else if (!isHovered && shadowOpacity > 0.3f) {
                                shadowOpacity -= 0.1f;
                            // Stop timer when target opacity reached
                            } else {
                                fadeTimer.stop();
                            }
                            // Repaint button
                            repaint();
                        }
                    });

                    // Add mouse hover listeners
                    addMouseListener(new MouseAdapter() {
                        // Handle mouse enter
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            isHovered = true;
                            fadeTimer.start();
                        }

                        // Handle mouse exit
                        @Override
                        public void mouseExited(MouseEvent e) {
                            isHovered = false;
                            fadeTimer.start();
                        }
                    });
                }

                // Custom button painting
                @Override
                protected void paintComponent(Graphics g) {
                    // Create Graphics2D object
                    Graphics2D g2 = (Graphics2D) g.create();
                    // Enable antialiasing
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    // Draw button shadow
                    g2.setColor(new Color(0, 0, 0, (int)(shadowOpacity * 255)));
                    g2.fillRoundRect(SHADOW_SIZE, SHADOW_SIZE, getWidth() - SHADOW_SIZE, getHeight() - SHADOW_SIZE, 25, 25);
                    
                    // Draw button background
                    g2.setColor(BUTTON_COLOR);
                    g2.fillRoundRect(0, 0, getWidth() - SHADOW_SIZE, getHeight() - SHADOW_SIZE, 25, 25);
                    
                    // Configure text properties
                    g2.setColor(Color.WHITE);
                    g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
                    // Get font metrics
                    FontMetrics fm = g2.getFontMetrics();
                    Rectangle2D rect = fm.getStringBounds(getText(), g2);
                    
                    // Calculate text position
                    int textX = (getWidth() - SHADOW_SIZE - (int) rect.getWidth()) / 2;
                    int textY = (getHeight() - SHADOW_SIZE - (int) rect.getHeight()) / 2 + fm.getAscent();
                    
                    // Draw button text
                    g2.drawString(getText(), textX, textY);
                    // Clean up graphics resources
                    g2.dispose();
                }

                // Override preferred size
                @Override
                public Dimension getPreferredSize() {
                    // Get base size
                    Dimension size = super.getPreferredSize();
                    // Add shadow size to dimensions
                    size.width += SHADOW_SIZE;
                    size.height += SHADOW_SIZE;
                    return size;
                }
            };

            // Set button size
            startButton.setPreferredSize(new Dimension(250, 50));

            // Configure button position
            gbc.gridy = 2;
            gbc.insets = new Insets(0, 0, 90, 0);
            mainPanel.add(startButton, gbc);

            // Add button click handler
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Close current window
                    frame.dispose();
                    // Open restaurant order GUI
                    new RestaurantOrderGUI();
                }
            });

        // Handle any exceptions during logo loading
        } catch (Exception e) {
            // Print error message
            System.out.println("Error loading main logo: " + e.getMessage());
        }

        // Add main panel to background
        background.add(mainPanel);
        // Make the frame visible
        frame.setVisible(true);
    }
}
// End of Mainapp.java

// ImageSlider class definition
class ImageSlider extends JPanel {
    private JLabel imageLabel;
    private JLabel nameLabel;
    private String[] foodNames; // Names for each food
    private int currentIndex = 0;
    private Timer timer;
    private Timer nameTimer;
    private final Color CONTAINER_COLOR = new Color(139, 69, 19, 180); // Brown with transparency
    private final int SHADOW_SIZE = 5;
    private final int CORNER_RADIUS = 70; // Corner radius for rounded rectangle

    public ImageSlider(String[] imagePaths, int delay) {
        // Update this array with your food names in the same order as your images
        this.foodNames = new String[]{"Sinigang na Baka", "Kare-Kare", "Sisig", "Embutido", "Leche Flan", "Halo-Halo"};
        
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create title label
        JLabel titleLabel = new JLabel("Featured Foods");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        // Create panel for rounded rectangle image
        JPanel imageContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw shadow
                g2.setColor(new Color(0, 0, 0, 80));
                g2.fillRoundRect(SHADOW_SIZE, SHADOW_SIZE, getWidth() - SHADOW_SIZE*2, 
                                getHeight() - SHADOW_SIZE*2, CORNER_RADIUS, CORNER_RADIUS);
                
                // Draw rounded rectangle background
                g2.setColor(CONTAINER_COLOR);
                g2.fillRoundRect(0, 0, getWidth() - SHADOW_SIZE, 
                                getHeight() - SHADOW_SIZE, CORNER_RADIUS, CORNER_RADIUS);
                
                // Add glass effect
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(255, 255, 255, 30),
                    0, getHeight()/2, new Color(255, 255, 255, 0)
                );
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth() - SHADOW_SIZE, 
                                getHeight()/2, CORNER_RADIUS, CORNER_RADIUS);
                
                g2.dispose();
            }
        };
        imageContainer.setOpaque(false);
        imageContainer.setPreferredSize(new Dimension(280, 280));
        imageContainer.setLayout(new BorderLayout());
        
        // Create image label
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageContainer.add(imageLabel, BorderLayout.CENTER);
        
        // Create name label with animation effect
        nameLabel = new JLabel() {
            private float alpha = 0.0f;
            
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                
                // Set composite for transparency
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                
                // Draw background for name
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Draw text
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(getText(), textX, textY);
                
                g2.dispose();
            }
        };
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setPreferredSize(new Dimension(200, 40));
        nameLabel.setOpaque(false);
        
        // Create panel for name label
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        namePanel.setOpaque(false);
        namePanel.add(nameLabel);
        
        // Create main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(imageContainer, BorderLayout.CENTER);
        contentPanel.add(namePanel, BorderLayout.SOUTH);
        
        // Add content panel to main panel
        add(contentPanel, BorderLayout.CENTER);
        
        // Create timer for name fade effect
        nameTimer = new Timer(50, new ActionListener() {
            private boolean fadeIn = true;
            private float alpha = 0.0f;
            private int delayCounter = 0;
            private final int HOLD_TIME = 100; // 5 seconds (100 * 50ms = 5000ms)
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fadeIn) {
                    alpha += 0.1f;
                    if (alpha >= 1.0f) {
                        alpha = 1.0f;
                        fadeIn = false;
                        delayCounter = 0; // Reset counter when fully faded in
                    }
                } else if (delayCounter < HOLD_TIME) {
                    delayCounter++; // Hold the name visible for 5 seconds
                } else {
                    alpha -= 0.1f;
                    if (alpha <= 0.0f) {
                        alpha = 0.0f;
                        fadeIn = true; // Reset to fade in again
                        delayCounter = 0; // Reset counter
                    }
                }
                
                // Apply alpha to nameLabel
                try {
                    java.lang.reflect.Field field = nameLabel.getClass().getDeclaredField("alpha");
                    field.setAccessible(true);
                    field.set(nameLabel, alpha);
                    nameLabel.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        // Load first image
        if (imagePaths.length > 0) {
            loadImage(imagePaths[0], 0);
        }
        
        // Start timer for slideshow
        timer = new Timer(delay, e -> {
            currentIndex = (currentIndex + 1) % imagePaths.length;
            loadImage(imagePaths[currentIndex], currentIndex);
        });
        timer.start();
    }
    
    private void loadImage(String path, int index) {
        try {
            // Load image
            ImageIcon originalIcon = new ImageIcon(path);
            Image originalImage = originalIcon.getImage();
            
            // Create rounded rectangle image
            int width = 260;
            int height = 260;
            BufferedImage roundedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = roundedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Create rounded rectangle clip
            g2.setClip(new java.awt.geom.RoundRectangle2D.Float(0, 0, width, height, CORNER_RADIUS, CORNER_RADIUS));
            
            // Scale and draw the image
            g2.drawImage(originalImage, 0, 0, width, height, null);
            g2.dispose();
            
            // Set the rounded image as icon
            imageLabel.setIcon(new ImageIcon(roundedImage));
            
            // Update name label and start fade animation
            if (index < foodNames.length) {
                nameLabel.setText(foodNames[index]);
                
                // Reset alpha and restart timer
                try {
                    java.lang.reflect.Field field = nameLabel.getClass().getDeclaredField("alpha");
                    field.setAccessible(true);
                    field.set(nameLabel, 0.0f);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                nameTimer.restart();
            }
          // catch block for handling image loading errors 
        } catch (Exception e) {
            System.out.println("Error loading image: " + path + " - " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
    
    // Override getPreferredSize for proper sizing
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 450); // Increased height to accommodate name label
    }
}
