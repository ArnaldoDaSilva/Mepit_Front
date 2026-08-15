# MEPIT WMS - GUIA DE IMPLEMENTAÇÃO EM JAVA
**Instruções para Recriar a Interface em Java Swing/JavaFX**

---

## 📋 ÍNDICE
1. [Estrutura do Projeto](#estrutura-do-projeto)
2. [Cores e Temas](#cores-e-temas)
3. [Estrutura Principal da UI](#estrutura-principal-da-ui)
4. [Implementação de Componentes](#implementação-de-componentes)
5. [Exemplos de Código](#exemplos-de-código)
6. [Tabelas e Dados](#tabelas-e-dados)
7. [Formulários](#formulários)
8. [Ícones](#ícones)
9. [Conexão com API](#conexão-com-api)

---

## 📂 Estrutura do Projeto

```
mepitDesktop/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mepit/
│   │   │       ├── MepitDesktopApp.java          (Main class)
│   │   │       ├── ui/
│   │   │       │   ├── MainWindow.java           (Frame principal)
│   │   │       │   ├── LoginPanel.java           (Página de login)
│   │   │       │   ├── DashboardPanel.java       (Dashboard)
│   │   │       │   ├── pages/
│   │   │       │   │   ├── ProdutosPanel.java
│   │   │       │   │   ├── EntradasPanel.java
│   │   │       │   │   ├── SaidasPanel.java
│   │   │       │   │   ├── EstoquePanel.java
│   │   │       │   │   └── [outros panels]
│   │   │       │   ├── components/
│   │   │       │   │   ├── Sidebar.java
│   │   │       │   │   ├── Navbar.java
│   │   │       │   │   ├── KPICard.java
│   │   │       │   │   ├── StyledButton.java
│   │   │       │   │   ├── StyledTable.java
│   │   │       │   │   └── StyledInput.java
│   │   │       │   └── utils/
│   │   │       │       ├── ColorScheme.java
│   │   │       │       ├── UIConstants.java
│   │   │       │       └── FontManager.java
│   │   │       ├── api/
│   │   │       │   ├── ApiClient.java
│   │   │       │   ├── endpoints/
│   │   │       │   │   ├── ProdutosApi.java
│   │   │       │   │   ├── EntradasApi.java
│   │   │       │   │   └── [outros apis]
│   │   │       ├── models/
│   │   │       │   ├── Produto.java
│   │   │       │   ├── Entrada.java
│   │   │       │   ├── Usuario.java
│   │   │       │   └── [outros modelos]
│   │   │       └── config/
│   │   │           └── AppConfig.java
│   │   └── resources/
│   │       ├── icons/
│   │       ├── fonts/
│   │       └── config.properties
│   └── test/
├── pom.xml (Maven)
└── README.md
```

---

## 🎨 Cores e Temas

### ColorScheme.java

```java
package com.mepit.ui.utils;

import java.awt.Color;

public class ColorScheme {
    // Cores primárias
    public static final Color ACCENT_BLUE = new Color(37, 99, 235);      // #2563eb
    public static final Color ACCENT_CYAN = new Color(6, 182, 212);      // #06b6d4
    public static final Color ACCENT_GREEN = new Color(16, 185, 129);    // #10b981
    public static final Color ACCENT_YELLOW = new Color(245, 158, 11);   // #f59e0b
    public static final Color ACCENT_RED = new Color(239, 68, 68);       // #ef4444
    public static final Color ACCENT_PURPLE = new Color(139, 92, 246);   // #8b5cf6
    public static final Color ACCENT_ORANGE = new Color(249, 115, 22);   // #f97316
    
    // Cores de fundo
    public static final Color BG_PRIMARY = new Color(255, 255, 255);     // #ffffff
    public static final Color BG_SECONDARY = new Color(248, 250, 252);   // #f8fafc
    public static final Color BG_CARD = new Color(255, 255, 255);        // #ffffff
    public static final Color BG_HOVER = new Color(241, 245, 249);       // #f1f5f9
    public static final Color BG_NAVBAR = new Color(255, 255, 255);      // #ffffff
    
    // Sidebar (escura)
    public static final Color BG_SIDEBAR = new Color(13, 19, 33);        // #0d1321
    public static final Color SIDEBAR_HOVER = new Color(36, 48, 68);     // #243044
    public static final Color SIDEBAR_TEXT = new Color(136, 153, 180);   // #8899b4
    public static final Color SIDEBAR_TEXT_MUTED = new Color(85, 102, 136); // #556688
    public static final Color SIDEBAR_BORDER = new Color(30, 45, 69);    // #1e2d45
    
    // Texto
    public static final Color TEXT_PRIMARY = new Color(17, 24, 39);      // #111827
    public static final Color TEXT_SECONDARY = new Color(75, 85, 99);    // #4b5563
    public static final Color TEXT_MUTED = new Color(156, 163, 175);     // #9ca3af
    
    // Borda
    public static final Color BORDER_COLOR = new Color(229, 231, 235);   // #e5e7eb
    
    // Gradient colors (para uso em GradientPaint)
    public static Color[] gradientLogin = {
        new Color(13, 19, 33),    // #0d1321
        new Color(30, 41, 59),    // #1e293b
        new Color(37, 99, 235)    // #2563eb
    };
    
    public static Color[] gradientLogo = {
        ACCENT_BLUE,
        ACCENT_CYAN
    };
    
    public static Color[] gradientButton = {
        ACCENT_BLUE,
        new Color(29, 78, 216)    // #1d4ed8
    };
}
```

---

### UIConstants.java

```java
package com.mepit.ui.utils;

import java.awt.Dimension;
import java.awt.Insets;

public class UIConstants {
    // Dimensões
    public static final int SIDEBAR_WIDTH = 260;
    public static final int NAVBAR_HEIGHT = 60;
    public static final int WINDOW_MIN_WIDTH = 1024;
    public static final int WINDOW_MIN_HEIGHT = 768;
    
    // Border Radius (para rounded corners)
    public static final int RADIUS = 12;
    public static final int RADIUS_SM = 8;
    public static final int RADIUS_XS = 4;
    
    // Padding/Margin
    public static final int PADDING_XL = 28;
    public static final int PADDING_LG = 20;
    public static final int PADDING_MD = 16;
    public static final int PADDING_SM = 12;
    public static final int PADDING_XS = 8;
    public static final int PADDING_XXS = 4;
    
    // Font sizes
    public static final int FONT_TITLE_LARGE = 20;
    public static final int FONT_TITLE_MEDIUM = 18;
    public static final int FONT_TITLE_SMALL = 16;
    public static final int FONT_SUBTITLE = 15;
    public static final int FONT_BODY = 14;
    public static final int FONT_LABEL = 13;
    public static final int FONT_SMALL = 12;
    public static final int FONT_TINY = 11;
    public static final int FONT_EXTRA_TINY = 10;
    
    // Gaps
    public static final int GAP_LG = 16;
    public static final int GAP_MD = 12;
    public static final int GAP_SM = 8;
    public static final int GAP_XS = 6;
    public static final int GAP_XXS = 4;
}
```

---

### FontManager.java

```java
package com.mepit.ui.utils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class FontManager {
    private static Font interFont;
    private static Font interBold;
    private static Font interSemiBold;
    
    static {
        try {
            // Tentar carregar "Inter" ou fallback para "Dialog"
            String fontName = "Inter";
            if (!isFontAvailable(fontName)) {
                fontName = "Segoe UI";
            }
            if (!isFontAvailable(fontName)) {
                fontName = "Dialog";
            }
            
            interFont = new Font(fontName, Font.PLAIN, UIConstants.FONT_BODY);
            interBold = new Font(fontName, Font.BOLD, UIConstants.FONT_BODY);
            interSemiBold = new Font(fontName, Font.BOLD, UIConstants.FONT_BODY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static boolean isFontAvailable(String fontName) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();
        for (String font : fonts) {
            if (font.equalsIgnoreCase(fontName)) {
                return true;
            }
        }
        return false;
    }
    
    public static Font getRegular(int size) {
        return interFont.deriveFont(Font.PLAIN, size);
    }
    
    public static Font getBold(int size) {
        return interBold.deriveFont(Font.BOLD, size);
    }
    
    public static Font getSemiBold(int size) {
        return interSemiBold.deriveFont(Font.BOLD, size);
    }
    
    public static Font getTitle() {
        return getBold(UIConstants.FONT_TITLE_LARGE);
    }
    
    public static Font getSubtitle() {
        return getBold(UIConstants.FONT_SUBTITLE);
    }
    
    public static Font getLabel() {
        return getSemiBold(UIConstants.FONT_LABEL);
    }
}
```

---

## 📐 Estrutura Principal da UI

### MainWindow.java

```java
package com.mepit.ui;

import com.mepit.ui.components.Sidebar;
import com.mepit.ui.components.Navbar;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Sidebar sidebar;
    private Navbar navbar;
    private JPanel mainContent;
    private CardLayout cardLayout;
    
    public MainWindow() {
        setTitle("MEPIT - WMS Sistema de Gestão de Armazém");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(
            UIConstants.WINDOW_MIN_WIDTH,
            UIConstants.WINDOW_MIN_HEIGHT
        ));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        
        // Layout principal: BorderLayout
        // WEST: Sidebar
        // NORTH: Navbar
        // CENTER: Main content
        setLayout(new BorderLayout());
        
        // Sidebar
        sidebar = new Sidebar(this::switchPage);
        add(sidebar, BorderLayout.WEST);
        
        // Main container (Navbar + Content)
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(ColorScheme.BG_PRIMARY);
        
        // Navbar
        navbar = new Navbar();
        mainContainer.add(navbar, BorderLayout.NORTH);
        
        // Content area com CardLayout para múltiplas páginas
        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        
        // Adicionar páginas
        mainContent.add(new DashboardPanel(), "dashboard");
        mainContent.add(new ProdutosPanel(), "produtos");
        // ... adicionar outras páginas
        
        mainContainer.add(mainContent, BorderLayout.CENTER);
        add(mainContainer, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private void switchPage(String pageName) {
        cardLayout.show(mainContent, pageName);
        navbar.setPageTitle(getPageTitle(pageName));
    }
    
    private String getPageTitle(String pageName) {
        switch (pageName) {
            case "dashboard": return "Início";
            case "produtos": return "Produtos";
            case "entradas": return "Entradas";
            // ... mais páginas
            default: return "MEPIT WMS";
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}
```

---

## 🔧 Implementação de Componentes

### StyledButton.java (Botão Customizado)

```java
package com.mepit.ui.components;

import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class StyledButton extends JButton {
    private Color backgroundColor;
    private Color hoverColor;
    private Color pressedColor;
    private boolean isHovered = false;
    private boolean isPressed = false;
    
    public enum ButtonStyle {
        PRIMARY,      // Azul
        SECONDARY,    // Transparente com borda
        ICON,         // Ícone com fundo suave
        DANGER        // Vermelho
    }
    
    public StyledButton(String text, ButtonStyle style) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(FontManager.getSemiBold(UIConstants.FONT_LABEL));
        
        setStyle(style);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                isPressed = false;
                repaint();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }
    
    private void setStyle(ButtonStyle style) {
        switch (style) {
            case PRIMARY:
                backgroundColor = ColorScheme.ACCENT_BLUE;
                hoverColor = new Color(29, 78, 216); // #1d4ed8
                pressedColor = new Color(23, 60, 190);
                setForeground(Color.WHITE);
                break;
            case SECONDARY:
                backgroundColor = Color.TRANSPARENT;
                hoverColor = ColorScheme.BG_HOVER;
                pressedColor = new Color(209, 213, 219);
                setForeground(ColorScheme.TEXT_SECONDARY);
                break;
            case ICON:
                backgroundColor = ColorScheme.BG_SECONDARY;
                hoverColor = ColorScheme.BG_HOVER;
                pressedColor = new Color(207, 250, 254);
                setForeground(ColorScheme.TEXT_SECONDARY);
                break;
            case DANGER:
                backgroundColor = ColorScheme.ACCENT_RED;
                hoverColor = new Color(220, 38, 38); // Vermelho mais escuro
                pressedColor = new Color(185, 28, 28);
                setForeground(Color.WHITE);
                break;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Determinar cor baseada no estado
        Color fillColor = backgroundColor;
        if (isPressed) {
            fillColor = pressedColor;
        } else if (isHovered) {
            fillColor = hoverColor;
        }
        
        // Desenhar fundo arredondado
        RoundRectangle2D.Float rect = new RoundRectangle2D.Float(
            0, 0, getWidth() - 1, getHeight() - 1,
            UIConstants.RADIUS_SM, UIConstants.RADIUS_SM
        );
        
        g2d.setColor(fillColor);
        g2d.fill(rect);
        
        // Desenhar borda se houver
        if (backgroundColor == Color.TRANSPARENT || !isHovered) {
            g2d.setColor(ColorScheme.BORDER_COLOR);
            g2d.setStroke(new BasicStroke(1));
            g2d.draw(rect);
        }
        
        g2d.dispose();
        super.paintComponent(g);
    }
    
    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        return new Dimension(d.width + 16, d.height + 8);
    }
}
```

---

### KPICard.java (Card de KPI)

```java
package com.mepit.ui.components;

import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class KPICard extends JPanel {
    private String label;
    private String value;
    private String change;
    private String icon;
    private Color iconColor;
    private Color iconBgColor;
    private boolean isPositive = true;
    
    public enum KPIColor {
        BLUE(ColorScheme.ACCENT_BLUE, 37, 99, 235),
        GREEN(ColorScheme.ACCENT_GREEN, 16, 185, 129),
        ORANGE(ColorScheme.ACCENT_ORANGE, 249, 115, 22),
        CYAN(ColorScheme.ACCENT_CYAN, 6, 182, 212),
        RED(ColorScheme.ACCENT_RED, 239, 68, 68),
        PURPLE(ColorScheme.ACCENT_PURPLE, 139, 92, 246);
        
        Color color;
        int r, g, b;
        KPIColor(Color c, int r, int g, int b) {
            this.color = c;
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
    
    public KPICard(String label, String value, String change, KPIColor colorScheme) {
        this.label = label;
        this.value = value;
        this.change = change;
        this.iconColor = colorScheme.color;
        this.iconBgColor = new Color(
            colorScheme.r, colorScheme.g, colorScheme.b, 25
        );
        this.isPositive = !change.startsWith("-");
        
        setBackground(ColorScheme.BG_CARD);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        setPreferredSize(new Dimension(180, 120));
        setLayout(null);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fundo
        RoundRectangle2D.Float bg = new RoundRectangle2D.Float(
            0, 0, getWidth() - 1, getHeight() - 1,
            UIConstants.RADIUS, UIConstants.RADIUS
        );
        g2d.setColor(ColorScheme.BG_CARD);
        g2d.fill(bg);
        
        // Borda
        g2d.setColor(ColorScheme.BORDER_COLOR);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(bg);
        
        // Label
        g2d.setColor(ColorScheme.TEXT_SECONDARY);
        g2d.setFont(FontManager.getSemiBold(UIConstants.FONT_TINY));
        g2d.drawString(label.toUpperCase(), UIConstants.PADDING_SM, 25);
        
        // Icon background
        g2d.setColor(iconBgColor);
        RoundRectangle2D.Float iconBg = new RoundRectangle2D.Float(
            getWidth() - 50, UIConstants.PADDING_SM, 36, 36,
            UIConstants.RADIUS_SM, UIConstants.RADIUS_SM
        );
        g2d.fill(iconBg);
        
        // Icon color
        g2d.setColor(iconColor);
        g2d.drawString("●", getWidth() - 35, UIConstants.PADDING_SM + 22);
        
        // Value
        g2d.setColor(ColorScheme.TEXT_PRIMARY);
        g2d.setFont(FontManager.getBold(28));
        g2d.drawString(value, UIConstants.PADDING_SM, 70);
        
        // Change
        Color changeColor = isPositive ? ColorScheme.ACCENT_GREEN : ColorScheme.ACCENT_RED;
        g2d.setColor(changeColor);
        g2d.setFont(FontManager.getSemiBold(UIConstants.FONT_TINY));
        g2d.drawString(change, UIConstants.PADDING_SM, 95);
    }
}
```

---

## 📊 Tabelas e Dados

### StyledTable.java (Tabela Customizada)

```java
package com.mepit.ui.components;

import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class StyledTable extends JTable {
    public StyledTable(Object[][] data, String[] columns) {
        super(data, columns);
        setupTable();
    }
    
    private void setupTable() {
        // Header customizado
        JTableHeader header = getTableHeader();
        header.setBackground(ColorScheme.BG_SECONDARY);
        header.setForeground(ColorScheme.TEXT_MUTED);
        header.setFont(FontManager.getSemiBold(UIConstants.FONT_TINY));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column
                );
                ((JLabel) c).setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
                c.setBackground(ColorScheme.BG_SECONDARY);
                c.setForeground(ColorScheme.TEXT_MUTED);
                return c;
            }
        });
        
        // Rows customizadas
        setRowHeight(40);
        setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        setForeground(ColorScheme.TEXT_SECONDARY);
        setBackground(Color.WHITE);
        setGridColor(ColorScheme.BORDER_COLOR);
        setShowGrid(true);
        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        
        // Cell renderer
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column
                );
                
                if (isSelected) {
                    c.setBackground(ColorScheme.BG_HOVER);
                } else {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : ColorScheme.BG_SECONDARY);
                }
                
                ((JLabel) c).setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
                return c;
            }
        };
        
        setDefaultRenderer(Object.class, cellRenderer);
    }
}
```

---

## 📝 Formulários

### StyledInput.java (Input Customizado)

```java
package com.mepit.ui.components;

import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

public class StyledInput extends JTextField {
    private Color borderColor;
    private Color focusColor;
    private boolean isFocused = false;
    
    public StyledInput(String placeholder) {
        setText(placeholder);
        setForeground(ColorScheme.TEXT_MUTED);
        setCaretColor(ColorScheme.TEXT_PRIMARY);
        setBorder(null);
        setOpaque(false);
        setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        
        borderColor = ColorScheme.BORDER_COLOR;
        focusColor = ColorScheme.ACCENT_BLUE;
        
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                isFocused = true;
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(ColorScheme.TEXT_PRIMARY);
                }
                repaint();
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                isFocused = false;
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(ColorScheme.TEXT_MUTED);
                }
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fundo
        RoundRectangle2D.Float bg = new RoundRectangle2D.Float(
            0, 0, getWidth() - 1, getHeight() - 1,
            UIConstants.RADIUS_SM, UIConstants.RADIUS_SM
        );
        
        g2d.setColor(Color.WHITE);
        g2d.fill(bg);
        
        // Borda
        Color border = isFocused ? focusColor : borderColor;
        g2d.setColor(border);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(bg);
        
        // Shadow ao focar
        if (isFocused) {
            g2d.setColor(new Color(37, 99, 235, 40));
            g2d.fillOval(0, 0, getWidth(), getHeight());
        }
        
        super.paintComponent(g);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, 36);
    }
}
```

---

### LoginPanel.java (Página de Login)

```java
package com.mepit.ui;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;
import com.mepit.api.ApiClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;

public class LoginPanel extends JPanel {
    private StyledInput emailInput;
    private StyledInput senhaInput;
    private JLabel errorLabel;
    private JButton loginButton;
    private Runnable onLoginSuccess;
    
    public LoginPanel(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
        setLayout(null);
        setBackground(ColorScheme.BG_PRIMARY);
        
        // Card de login centralizado
        JPanel loginCard = createLoginCard();
        add(loginCard);
        
        // Layout centralizado
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(UIConstants.PADDING_XL, UIConstants.PADDING_XL,
                                UIConstants.PADDING_XL, UIConstants.PADDING_XL);
        add(loginCard, gbc);
    }
    
    private JPanel createLoginCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                
                RoundRectangle2D.Float rect = new RoundRectangle2D.Float(
                    0, 0, getWidth() - 1, getHeight() - 1, 16, 16
                );
                g2d.setColor(Color.WHITE);
                g2d.fill(rect);
                
                g2d.setColor(new Color(0, 0, 0, 50));
                g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };
        
        card.setPreferredSize(new Dimension(400, 500));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(40, 36, 40, 36));
        
        // Logo
        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setMaximumSize(new Dimension(400, 100));
        JLabel logoLabel = new JLabel("MEPIT");
        logoLabel.setFont(FontManager.getBold(28));
        logoLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        logoPanel.add(logoLabel);
        card.add(logoPanel);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("WHM System");
        subtitleLabel.setFont(FontManager.getSemiBold(UIConstants.FONT_SMALL));
        subtitleLabel.setForeground(ColorScheme.ACCENT_BLUE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(subtitleLabel);
        
        card.add(Box.createVerticalStrut(UIConstants.PADDING_LG));
        
        // Bem-vindo
        JLabel welcomeLabel = new JLabel("Bem-vindo! Faça login para aceder ao sistema.");
        welcomeLabel.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        welcomeLabel.setForeground(ColorScheme.TEXT_MUTED);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(welcomeLabel);
        
        card.add(Box.createVerticalStrut(UIConstants.PADDING_LG));
        
        // Email input
        JLabel emailLabel = new JLabel("📧 Email");
        emailLabel.setFont(FontManager.getSemiBold(UIConstants.FONT_SMALL));
        emailLabel.setForeground(ColorScheme.TEXT_SECONDARY);
        card.add(emailLabel);
        
        emailInput = new StyledInput("seu@email.com");
        emailInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        card.add(emailInput);
        
        card.add(Box.createVerticalStrut(UIConstants.GAP_MD));
        
        // Password input
        JLabel senhaLabel = new JLabel("🔒 Senha");
        senhaLabel.setFont(FontManager.getSemiBold(UIConstants.FONT_SMALL));
        senhaLabel.setForeground(ColorScheme.TEXT_SECONDARY);
        card.add(senhaLabel);
        
        senhaInput = new JPasswordField();
        senhaInput.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        senhaInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        card.add(senhaInput);
        
        card.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        
        // Error label
        errorLabel = new JLabel();
        errorLabel.setForeground(ColorScheme.ACCENT_RED);
        errorLabel.setFont(FontManager.getRegular(UIConstants.FONT_SMALL));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(errorLabel);
        
        card.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        
        // Login button
        loginButton = new StyledButton("Entrar", StyledButton.ButtonStyle.PRIMARY);
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        loginButton.addActionListener(this::handleLogin);
        card.add(loginButton);
        
        card.add(Box.createVerticalGlue());
        
        return card;
    }
    
    private void handleLogin(ActionEvent e) {
        String email = emailInput.getText();
        String senha = new String(((JPasswordField)senhaInput).getPassword());
        
        if (email.isEmpty() || senha.isEmpty()) {
            errorLabel.setText("Por favor, preencha todos os campos");
            return;
        }
        
        // Chamar API
        new Thread(() -> {
            try {
                var response = ApiClient.login(email, senha);
                if (response.isOk()) {
                    SwingUtilities.invokeLater(() -> {
                        onLoginSuccess.run();
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        errorLabel.setText("Email ou senha incorretos");
                    });
                }
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    errorLabel.setText("Erro ao conectar ao servidor");
                });
            }
        }).start();
    }
}
```

---

## 🌐 Conexão com API

### ApiClient.java

```java
package com.mepit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiClient {
    private static final String API_BASE_URL = "http://localhost:8080";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static class ApiResponse<T> {
        public boolean ok;
        public int status;
        public T data;
        
        public boolean isOk() {
            return ok;
        }
    }
    
    public static ApiResponse<String> login(String email, String senha) throws Exception {
        String json = gson.toJson(new LoginRequest(email, senha));
        return post("/utilizadores/login", json, String.class);
    }
    
    public static <T> ApiResponse<T> get(String path, Class<T> responseType) throws Exception {
        URL url = new URL(API_BASE_URL + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        
        int status = conn.getResponseCode();
        String response = readResponse(conn);
        
        ApiResponse<T> result = new ApiResponse<>();
        result.ok = status >= 200 && status < 300;
        result.status = status;
        result.data = gson.fromJson(response, responseType);
        
        return result;
    }
    
    public static <T> ApiResponse<T> post(String path, String jsonBody, Class<T> responseType) throws Exception {
        URL url = new URL(API_BASE_URL + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        
        int status = conn.getResponseCode();
        String response = readResponse(conn);
        
        ApiResponse<T> result = new ApiResponse<>();
        result.ok = status >= 200 && status < 300;
        result.status = status;
        result.data = gson.fromJson(response, responseType);
        
        return result;
    }
    
    private static String readResponse(HttpURLConnection conn) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
        );
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
    
    static class LoginRequest {
        String email;
        String senha;
        
        LoginRequest(String email, String senha) {
            this.email = email;
            this.senha = senha;
        }
    }
}
```

---

## 🚀 Maven Dependencies (pom.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.mepit</groupId>
    <artifactId>mepit-desktop</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <name>MEPIT WMS Desktop</name>
    <description>Warehouse Management System - Desktop Application</description>
    
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <!-- GSON para JSON -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>
        
        <!-- JFreeChart para gráficos -->
        <dependency>
            <groupId>org.jfree</groupId>
            <artifactId>jfreechart</artifactId>
            <version>1.5.3</version>
        </dependency>
        
        <!-- Apache Commons -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.12.0</version>
        </dependency>
        
        <!-- Logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.5</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.4.5</version>
        </dependency>
        
        <!-- JUnit para testes -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.10.1</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
            
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.4.1</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>com.mepit.MepitDesktopApp</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## ⚙️ Dicas Práticas de Implementação

### 1. **Rounded Corners em Java Swing**
```java
// Usar RoundRectangle2D para desenhar bordas arredondadas
RoundRectangle2D.Float rect = new RoundRectangle2D.Float(
    0, 0, width - 1, height - 1, cornerRadius, cornerRadius
);
g2d.fill(rect); // ou g2d.draw(rect)
```

### 2. **Gradientes**
```java
// Criar gradiente linear
GradientPaint gradient = new GradientPaint(
    0, 0, colorStart,
    getWidth(), getHeight(), colorEnd
);
g2d.setPaint(gradient);
g2d.fillRect(0, 0, getWidth(), getHeight());
```

### 3. **Sombras**
```java
// Usar DropShadowBorder ou desenhar manualmente
g2d.setColor(new Color(0, 0, 0, 20)); // Semi-transparent black
for (int i = 1; i <= 5; i++) {
    g2d.setColor(new Color(0, 0, 0, 255 / (i * i)));
    RoundRectangle2D.Float shadow = new RoundRectangle2D.Float(
        i, i, width - i*2, height - i*2, radius, radius
    );
    g2d.draw(shadow);
}
```

### 4. **Efeitos de Hover**
```java
private boolean isHovered = false;

addMouseListener(new MouseAdapter() {
    @Override
    public void mouseEntered(MouseEvent e) {
        isHovered = true;
        repaint();
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        isHovered = false;
        repaint();
    }
});

@Override
protected void paintComponent(Graphics g) {
    Color color = isHovered ? hoverColor : normalColor;
    // ... usar 'color'
}
```

### 5. **Animações Suaves**
```java
private int xPosition = 0;
private Timer animationTimer;

animationTimer = new Timer(10, e -> {
    xPosition += 2;
    if (xPosition >= targetX) {
        animationTimer.stop();
    }
    repaint();
});
animationTimer.start();
```

---

## 🎯 Próximos Passos

1. ✅ Configurar projeto Maven
2. ✅ Criar classes de cores e constantes
3. ✅ Implementar componentes base (Button, Input, Card)
4. ✅ Criar layout principal (MainWindow)
5. ✅ Implementar LoginPanel
6. ✅ Criar Sidebar e Navbar
7. ✅ Implementar DashboardPanel com KPI cards
8. ✅ Implementar páginas de CRUD (Produtos, Entradas, etc)
9. ✅ Adicionar gráficos (JFreeChart)
10. ✅ Configurar conexão com API
11. ✅ Testes e ajustes finais

---

**Fim do Guia de Implementação**
