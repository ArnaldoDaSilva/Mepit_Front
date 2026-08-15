# MEPIT WMS - PÁGINAS ADICIONAIS
**Exemplos de Implementação das Páginas Restantes**

---

## 📋 ÍNDICE
1. [Página de Saídas](#página-de-saídas)
2. [Página de Estoque](#página-de-estoque)
3. [Página de Funcionários](#página-de-funcionários)
4. [Página de Fornecedores](#página-de-fornecedores)
5. [Página de Clientes](#página-de-clientes)
6. [Página de Utilizadores](#página-de-utilizadores)
7. [Página de Alertas](#página-de-alertas)
8. [Página de Logs](#página-de-logs)
9. [Página de Sucursais](#página-de-sucursais)
10. [Página de Configurações](#página-de-configurações)

---

## 📤 Página de Saídas

### SaidasPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class SaidasPanel extends JPanel {
    public SaidasPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorScheme.BG_PRIMARY);
        
        JLabel titleLabel = new JLabel("📤 Saídas de Estoque");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel, BorderLayout.WEST);
        
        JButton newExitBtn = new StyledButton("+ Nova Saída", StyledButton.ButtonStyle.PRIMARY);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(ColorScheme.BG_PRIMARY);
        buttonPanel.add(newExitBtn);
        header.add(buttonPanel, BorderLayout.EAST);
        
        mainContent.add(header, BorderLayout.NORTH);
        
        // Filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, UIConstants.PADDING_MD));
        filterPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        StyledInput searchInput = new StyledInput("Pesquisar saídas...");
        searchInput.setPreferredSize(new Dimension(320, 36));
        filterPanel.add(searchInput);
        
        JComboBox<String> typeFilter = new JComboBox<>(new String[]{"Todas", "Venda", "Devolução", "Descarte"});
        typeFilter.setPreferredSize(new Dimension(150, 36));
        filterPanel.add(typeFilter);
        
        mainContent.add(filterPanel, BorderLayout.NORTH);
        
        // Tabela
        String[] columns = {"Data", "Número", "Tipo", "Produto", "Quantidade", "Destino", "Status"};
        Object[][] data = {
            {"15/01/2025", "SAI001", "Venda", "Notebook", "3", "Cliente A", "✓ Entregue"},
            {"14/01/2025", "SAI002", "Devolução", "Mouse", "5", "Fornecedor", "✓ Processada"},
            {"13/01/2025", "SAI003", "Venda", "Teclado", "2", "Cliente B", "⏳ Preparando"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(40);
        table.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        mainContent.add(tableScroll, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
}
```

---

## 📦 Página de Estoque

### EstoquePanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class EstoquePanel extends JPanel {
    public EstoquePanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorScheme.BG_PRIMARY);
        
        JLabel titleLabel = new JLabel("🏭 Estoque por Armazém");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel, BorderLayout.WEST);
        
        JButton downloadBtn = new StyledButton("📥 Exportar", StyledButton.ButtonStyle.SECONDARY);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(ColorScheme.BG_PRIMARY);
        buttonPanel.add(downloadBtn);
        header.add(buttonPanel, BorderLayout.EAST);
        
        mainContent.add(header, BorderLayout.NORTH);
        
        // Filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, UIConstants.PADDING_MD));
        filterPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        StyledInput searchInput = new StyledInput("Pesquisar produtos...");
        searchInput.setPreferredSize(new Dimension(320, 36));
        filterPanel.add(searchInput);
        
        JComboBox<String> warehouseFilter = new JComboBox<>(new String[]{"Todos", "Armazém 1", "Armazém 2", "Armazém 3"});
        warehouseFilter.setPreferredSize(new Dimension(150, 36));
        filterPanel.add(warehouseFilter);
        
        mainContent.add(filterPanel, BorderLayout.NORTH);
        
        // Tabela
        String[] columns = {"Produto", "Armazém 1", "Armazém 2", "Armazém 3", "Total", "Mínimo", "Status"};
        Object[][] data = {
            {"Notebook Dell", "10", "5", "3", "18", "5", "✓ OK"},
            {"Mouse Logitech", "25", "15", "20", "60", "10", "✓ OK"},
            {"Teclado Mecânico", "3", "2", "1", "6", "10", "⚠️ Baixo"},
            {"Monitor LG 27\"", "2", "3", "0", "5", "2", "✓ OK"},
            {"Webcam Logitech", "8", "6", "4", "18", "5", "✓ OK"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(40);
        table.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        mainContent.add(tableScroll, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
}
```

---

## 👤 Página de Funcionários

### FuncionariosPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class FuncionariosPanel extends JPanel {
    public FuncionariosPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorScheme.BG_PRIMARY);
        
        JLabel titleLabel = new JLabel("👤 Funcionários");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel, BorderLayout.WEST);
        
        JButton newEmployeeBtn = new StyledButton("+ Novo Funcionário", StyledButton.ButtonStyle.PRIMARY);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(ColorScheme.BG_PRIMARY);
        buttonPanel.add(newEmployeeBtn);
        header.add(buttonPanel, BorderLayout.EAST);
        
        mainContent.add(header, BorderLayout.NORTH);
        
        // Filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, UIConstants.PADDING_MD));
        filterPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        StyledInput searchInput = new StyledInput("Pesquisar funcionários...");
        searchInput.setPreferredSize(new Dimension(320, 36));
        filterPanel.add(searchInput);
        
        JComboBox<String> statusFilter = new JComboBox<>(new String[]{"Todos", "Ativo", "Inativo", "Licença"});
        statusFilter.setPreferredSize(new Dimension(150, 36));
        filterPanel.add(statusFilter);
        
        mainContent.add(filterPanel, BorderLayout.NORTH);
        
        // Tabela
        String[] columns = {"Nome", "Cargo", "Departamento", "Telefone", "Email", "Status", "Ações"};
        Object[][] data = {
            {"João Silva", "Gestor de Armazém", "Operações", "+351 912345678", "joao@mepit.pt", "✓ Ativo", "✏️ 🗑️"},
            {"Maria Santos", "Operária", "Operações", "+351 912345679", "maria@mepit.pt", "✓ Ativo", "✏️ 🗑️"},
            {"Pedro Costa", "Técnico", "Manutenção", "+351 912345680", "pedro@mepit.pt", "✓ Ativo", "✏️ 🗑️"},
            {"Ana Ferreira", "Gestor de Inventário", "Operações", "+351 912345681", "ana@mepit.pt", "✓ Ativo", "✏️ 🗑️"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(40);
        table.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        mainContent.add(tableScroll, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
}
```

---

## 🚚 Página de Fornecedores

### FornecedoresPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class FornecedoresPanel extends JPanel {
    public FornecedoresPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorScheme.BG_PRIMARY);
        
        JLabel titleLabel = new JLabel("🚚 Fornecedores");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel, BorderLayout.WEST);
        
        JButton newSupplierBtn = new StyledButton("+ Novo Fornecedor", StyledButton.ButtonStyle.PRIMARY);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(ColorScheme.BG_PRIMARY);
        buttonPanel.add(newSupplierBtn);
        header.add(buttonPanel, BorderLayout.EAST);
        
        mainContent.add(header, BorderLayout.NORTH);
        
        // Filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, UIConstants.PADDING_MD));
        filterPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        StyledInput searchInput = new StyledInput("Pesquisar fornecedores...");
        searchInput.setPreferredSize(new Dimension(320, 36));
        filterPanel.add(searchInput);
        
        mainContent.add(filterPanel, BorderLayout.NORTH);
        
        // Tabela
        String[] columns = {"Nome", "NIF", "Contacto", "Email", "Categoria", "Ações"};
        Object[][] data = {
            {"Fornecedor XYZ Lda.", "123456789", "+351 212345678", "vendas@xyz.pt", "Eletrônicos", "✏️ 🗑️"},
            {"Distribuidora ABC", "987654321", "+351 212345679", "info@abc.pt", "Periféricos", "✏️ 🗑️"},
            {"Importadora DEF", "456789123", "+351 212345680", "suporte@def.pt", "Equipamentos", "✏️ 🗑️"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(40);
        table.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        mainContent.add(tableScroll, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
}
```

---

## 🤝 Página de Clientes

### ClientesPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class ClientesPanel extends JPanel {
    public ClientesPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorScheme.BG_PRIMARY);
        
        JLabel titleLabel = new JLabel("🤝 Clientes");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel, BorderLayout.WEST);
        
        JButton newClientBtn = new StyledButton("+ Novo Cliente", StyledButton.ButtonStyle.PRIMARY);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(ColorScheme.BG_PRIMARY);
        buttonPanel.add(newClientBtn);
        header.add(buttonPanel, BorderLayout.EAST);
        
        mainContent.add(header, BorderLayout.NORTH);
        
        // Filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, UIConstants.PADDING_MD));
        filterPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        StyledInput searchInput = new StyledInput("Pesquisar clientes...");
        searchInput.setPreferredSize(new Dimension(320, 36));
        filterPanel.add(searchInput);
        
        mainContent.add(filterPanel, BorderLayout.NORTH);
        
        // Tabela
        String[] columns = {"Nome", "NIF", "Contacto", "Cidade", "Total Compras", "Ações"};
        Object[][] data = {
            {"Cliente A", "123456789", "+351 912345678", "Lisboa", "€5.234,50", "✏️ 🗑️"},
            {"Cliente B", "987654321", "+351 912345679", "Porto", "€3.125,00", "✏️ 🗑️"},
            {"Cliente C", "456789123", "+351 912345680", "Braga", "€2.890,75", "✏️ 🗑️"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(40);
        table.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        mainContent.add(tableScroll, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
}
```

---

## 👥 Página de Utilizadores

### UtilizadoresPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class UtilizadoresPanel extends JPanel {
    public UtilizadoresPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorScheme.BG_PRIMARY);
        
        JLabel titleLabel = new JLabel("👥 Utilizadores");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel, BorderLayout.WEST);
        
        JButton newUserBtn = new StyledButton("+ Novo Utilizador", StyledButton.ButtonStyle.PRIMARY);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(ColorScheme.BG_PRIMARY);
        buttonPanel.add(newUserBtn);
        header.add(buttonPanel, BorderLayout.EAST);
        
        mainContent.add(header, BorderLayout.NORTH);
        
        // Filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, UIConstants.PADDING_MD));
        filterPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        StyledInput searchInput = new StyledInput("Pesquisar utilizadores...");
        searchInput.setPreferredSize(new Dimension(320, 36));
        filterPanel.add(searchInput);
        
        JComboBox<String> roleFilter = new JComboBox<>(new String[]{"Todos", "Administrador", "Gestor", "Operador"});
        roleFilter.setPreferredSize(new Dimension(150, 36));
        filterPanel.add(roleFilter);
        
        mainContent.add(filterPanel, BorderLayout.NORTH);
        
        // Tabela
        String[] columns = {"Nome", "Email", "Perfil", "Último Acesso", "Status", "Ações"};
        Object[][] data = {
            {"João Dias", "joao@mepit.pt", "Administrador", "15/01/2025 14:30", "✓ Ativo", "✏️ 🗑️"},
            {"Maria Costa", "maria@mepit.pt", "Gestor", "15/01/2025 10:15", "✓ Ativo", "✏️ 🗑️"},
            {"Pedro Silva", "pedro@mepit.pt", "Operador", "14/01/2025 16:45", "✓ Ativo", "✏️ 🗑️"},
            {"Ana Ferreira", "ana@mepit.pt", "Operador", "13/01/2025 09:20", "✓ Ativo", "✏️ 🗑️"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(40);
        table.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        mainContent.add(tableScroll, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
}
```

---

## 🔔 Página de Alertas

### AlertasPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class AlertasPanel extends JPanel {
    public AlertasPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JLabel titleLabel = new JLabel("🔔 Alertas do Sistema");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        mainContent.add(titleLabel, BorderLayout.NORTH);
        
        // Alertas list
        JPanel alertsPanel = new JPanel();
        alertsPanel.setLayout(new BoxLayout(alertsPanel, BoxLayout.Y_AXIS));
        alertsPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        // Alert items
        alertsPanel.add(createAlertCard("Produto em nível crítico", "Notebook Dell - Quantidade: 3 (Mínimo: 5)", "critical"));
        alertsPanel.add(Box.createVerticalStrut(UIConstants.GAP_MD));
        alertsPanel.add(createAlertCard("Entrada pendente", "Entrada ENT001 de Fornecedor XYZ aguardando confirmação", "warning"));
        alertsPanel.add(Box.createVerticalStrut(UIConstants.GAP_MD));
        alertsPanel.add(createAlertCard("Utilizador inativo", "Último acesso há 30 dias", "info"));
        
        JScrollPane scrollPane = new JScrollPane(alertsPanel);
        scrollPane.setBorder(null);
        mainContent.add(scrollPane, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
    
    private JPanel createAlertCard(String title, String message, String type) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(ColorScheme.BG_CARD);
        card.setBorder(BorderFactory.createLineBorder(getAlertColor(type)));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getAlertColor(type), 2),
            BorderFactory.createEmptyBorder(UIConstants.PADDING_MD, UIConstants.PADDING_MD, 
                                            UIConstants.PADDING_MD, UIConstants.PADDING_MD)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        // Icon
        JLabel iconLabel = new JLabel(getAlertIcon(type));
        iconLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        iconLabel.setForeground(getAlertColor(type));
        iconLabel.setPreferredSize(new Dimension(50, 50));
        card.add(iconLabel, BorderLayout.WEST);
        
        // Content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(ColorScheme.BG_CARD);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FontManager.getSemiBold(UIConstants.FONT_LABEL));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(FontManager.getRegular(UIConstants.FONT_SMALL));
        messageLabel.setForeground(ColorScheme.TEXT_MUTED);
        contentPanel.add(messageLabel, BorderLayout.CENTER);
        
        card.add(contentPanel, BorderLayout.CENTER);
        
        // Action
        JButton dismissBtn = new JButton("Descartar");
        dismissBtn.setPreferredSize(new Dimension(80, 30));
        card.add(dismissBtn, BorderLayout.EAST);
        
        return card;
    }
    
    private Color getAlertColor(String type) {
        switch (type) {
            case "critical": return ColorScheme.ACCENT_RED;
            case "warning": return ColorScheme.ACCENT_YELLOW;
            case "info": return ColorScheme.ACCENT_BLUE;
            default: return ColorScheme.BORDER_COLOR;
        }
    }
    
    private String getAlertIcon(String type) {
        switch (type) {
            case "critical": return "⛔";
            case "warning": return "⚠️";
            case "info": return "ℹ️";
            default: return "●";
        }
    }
}
```

---

## 📝 Página de Logs

### LogsPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledInput;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class LogsPanel extends JPanel {
    public LogsPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JLabel titleLabel = new JLabel("📝 Logs de Auditoria");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        mainContent.add(titleLabel, BorderLayout.NORTH);
        
        // Filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, UIConstants.GAP_MD, UIConstants.PADDING_MD));
        filterPanel.setBackground(ColorScheme.BG_PRIMARY);
        
        StyledInput searchInput = new StyledInput("Pesquisar nos logs...");
        searchInput.setPreferredSize(new Dimension(320, 36));
        filterPanel.add(searchInput);
        
        JComboBox<String> actionFilter = new JComboBox<>(new String[]{"Todas", "Criação", "Edição", "Deleção", "Login"});
        actionFilter.setPreferredSize(new Dimension(150, 36));
        filterPanel.add(actionFilter);
        
        mainContent.add(filterPanel, BorderLayout.NORTH);
        
        // Tabela
        String[] columns = {"Data/Hora", "Utilizador", "Ação", "Tabela", "Registro", "Detalhes"};
        Object[][] data = {
            {"15/01/2025 14:30:15", "João Dias", "Criação", "produtos", "P005", "Novo notebook adicionado"},
            {"15/01/2025 13:45:22", "Maria Costa", "Edição", "entradas", "ENT001", "Quantidade atualizada"},
            {"15/01/2025 12:10:08", "Pedro Silva", "Deleção", "clientes", "CLI003", "Cliente inativo removido"},
            {"15/01/2025 10:55:33", "Ana Ferreira", "Login", "utilizadores", "user_004", "Acesso ao sistema"},
            {"14/01/2025 16:20:45", "João Dias", "Edição", "produtos", "P002", "Preço atualizado"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(40);
        table.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(ColorScheme.BG_SECONDARY);
        header.setForeground(ColorScheme.TEXT_MUTED);
        header.setFont(FontManager.getSemiBold(UIConstants.FONT_TINY));
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        
        mainContent.add(tableScroll, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
}
```

---

## 🏭 Página de Sucursais

### SucursaisPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class SucursaisPanel extends JPanel {
    public SucursaisPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorScheme.BG_PRIMARY);
        
        JLabel titleLabel = new JLabel("🏭 Sucursais / Armazéns");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        header.add(titleLabel, BorderLayout.WEST);
        
        JButton newBranchBtn = new StyledButton("+ Nova Sucursal", StyledButton.ButtonStyle.PRIMARY);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(ColorScheme.BG_PRIMARY);
        buttonPanel.add(newBranchBtn);
        header.add(buttonPanel, BorderLayout.EAST);
        
        mainContent.add(header, BorderLayout.NORTH);
        
        // Grid de sucursais
        JPanel gridPanel = new JPanel(new GridLayout(2, 3, UIConstants.GAP_MD, UIConstants.GAP_MD));
        gridPanel.setBackground(ColorScheme.BG_PRIMARY);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(UIConstants.PADDING_LG, 0, 0, 0));
        
        gridPanel.add(createSucursalCard("Armazém Lisboa", "Lisboa", "20 colaboradores", "1.250 itens"));
        gridPanel.add(createSucursalCard("Armazém Porto", "Porto", "15 colaboradores", "980 itens"));
        gridPanel.add(createSucursalCard("Armazém Braga", "Braga", "10 colaboradores", "650 itens"));
        gridPanel.add(createSucursalCard("Armazém Covilhã", "Covilhã", "8 colaboradores", "420 itens"));
        gridPanel.add(createSucursalCard("Armazém Setúbal", "Setúbal", "12 colaboradores", "890 itens"));
        gridPanel.add(createSucursalCard("Armazém Faro", "Faro", "6 colaboradores", "280 itens"));
        
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(null);
        mainContent.add(scrollPane, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
    
    private JPanel createSucursalCard(String name, String city, String staff, String items) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(ColorScheme.BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR),
            BorderFactory.createEmptyBorder(UIConstants.PADDING_MD, UIConstants.PADDING_MD,
                                            UIConstants.PADDING_MD, UIConstants.PADDING_MD)
        ));
        
        // Icon
        JLabel iconLabel = new JLabel("🏭");
        iconLabel.setFont(new Font("Dialog", Font.PLAIN, 32));
        card.add(iconLabel);
        
        // Name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(FontManager.getBold(UIConstants.FONT_LABEL));
        nameLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        card.add(nameLabel);
        
        // City
        JLabel cityLabel = new JLabel(city);
        cityLabel.setFont(FontManager.getRegular(UIConstants.FONT_SMALL));
        cityLabel.setForeground(ColorScheme.ACCENT_BLUE);
        card.add(cityLabel);
        
        card.add(Box.createVerticalStrut(UIConstants.GAP_MD));
        
        // Info
        JLabel staffLabel = new JLabel(staff);
        staffLabel.setFont(FontManager.getRegular(UIConstants.FONT_SMALL));
        staffLabel.setForeground(ColorScheme.TEXT_MUTED);
        card.add(staffLabel);
        
        JLabel itemsLabel = new JLabel(items);
        itemsLabel.setFont(FontManager.getRegular(UIConstants.FONT_SMALL));
        itemsLabel.setForeground(ColorScheme.TEXT_MUTED);
        card.add(itemsLabel);
        
        card.add(Box.createVerticalGlue());
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, UIConstants.GAP_SM, 0));
        buttonPanel.setBackground(ColorScheme.BG_CARD);
        
        JButton editBtn = new JButton("✏️");
        editBtn.setPreferredSize(new Dimension(36, 36));
        editBtn.setBackground(ColorScheme.BG_SECONDARY);
        editBtn.setBorder(null);
        
        JButton deleteBtn = new JButton("🗑️");
        deleteBtn.setPreferredSize(new Dimension(36, 36));
        deleteBtn.setBackground(ColorScheme.BG_SECONDARY);
        deleteBtn.setBorder(null);
        
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        card.add(buttonPanel);
        
        return card;
    }
}
```

---

## ⚙️ Página de Configurações

### ConfiguracoesPanel.java

```java
package com.mepit.ui.pages;

import com.mepit.ui.components.StyledButton;
import com.mepit.ui.utils.ColorScheme;
import com.mepit.ui.utils.FontManager;
import com.mepit.ui.utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class ConfiguracoesPanel extends JPanel {
    public ConfiguracoesPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // Header
        JLabel titleLabel = new JLabel("⚙️ Configurações do Sistema");
        titleLabel.setFont(FontManager.getBold(UIConstants.FONT_TITLE_LARGE));
        titleLabel.setForeground(ColorScheme.TEXT_PRIMARY);
        mainContent.add(titleLabel, BorderLayout.NORTH);
        
        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Geral", createGeneralTab());
        tabbedPane.addTab("Permissões", createPermissionsTab());
        tabbedPane.addTab("Integração", createIntegrationTab());
        tabbedPane.addTab("Backup", createBackupTab());
        
        mainContent.add(tabbedPane, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
    
    private JPanel createGeneralTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(ColorScheme.BG_PRIMARY);
        panel.setBorder(BorderFactory.createEmptyBorder(UIConstants.PADDING_LG, UIConstants.PADDING_LG,
                                                        UIConstants.PADDING_LG, UIConstants.PADDING_LG));
        
        // Idioma
        JPanel languagePanel = createSettingPanel("Idioma do Sistema", new String[]{"Português", "English"});
        panel.add(languagePanel);
        
        panel.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        
        // Timezone
        JPanel timezonePanel = createSettingPanel("Fuso Horário", new String[]{"WET (GMT+0)", "WEST (GMT+1)"});
        panel.add(timezonePanel);
        
        panel.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        
        // Format
        JPanel formatPanel = createSettingPanel("Formato de Data", new String[]{"DD/MM/YYYY", "MM/DD/YYYY"});
        panel.add(formatPanel);
        
        panel.add(Box.createVerticalGlue());
        
        // Save button
        JButton saveBtn = new StyledButton("Salvar Configurações", StyledButton.ButtonStyle.PRIMARY);
        panel.add(saveBtn);
        
        return panel;
    }
    
    private JPanel createPermissionsTab() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ColorScheme.BG_PRIMARY);
        panel.setBorder(BorderFactory.createEmptyBorder(UIConstants.PADDING_LG, UIConstants.PADDING_LG,
                                                        UIConstants.PADDING_LG, UIConstants.PADDING_LG));
        
        JLabel infoLabel = new JLabel("Configurar permissões por perfil de utilizador");
        infoLabel.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        infoLabel.setForeground(ColorScheme.TEXT_MUTED);
        panel.add(infoLabel, BorderLayout.NORTH);
        
        // Tabela de permissões
        String[] columns = {"Menu", "Administrador", "Gestor", "Operador"};
        Object[][] data = {
            {"Dashboard", "☑️", "☑️", "☑️"},
            {"Produtos", "☑️", "☑️", "☑️"},
            {"Entradas", "☑️", "☑️", "☑️"},
            {"Saídas", "☑️", "☑️", "☐"},
            {"Utilizadores", "☑️", "☐", "☐"},
            {"Configurações", "☑️", "☐", "☐"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(ColorScheme.BORDER_COLOR));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Save
        JButton saveBtn = new StyledButton("Salvar Permissões", StyledButton.ButtonStyle.PRIMARY);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(ColorScheme.BG_PRIMARY);
        buttonPanel.add(saveBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createIntegrationTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(ColorScheme.BG_PRIMARY);
        panel.setBorder(BorderFactory.createEmptyBorder(UIConstants.PADDING_LG, UIConstants.PADDING_LG,
                                                        UIConstants.PADDING_LG, UIConstants.PADDING_LG));
        
        JLabel n8nLabel = new JLabel("Integração n8n");
        n8nLabel.setFont(FontManager.getBold(UIConstants.FONT_SUBTITLE));
        panel.add(n8nLabel);
        
        panel.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        
        JLabel urlLabel = new JLabel("URL n8n:");
        panel.add(urlLabel);
        
        JTextField urlField = new JTextField("http://localhost:5678");
        urlField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        panel.add(urlField);
        
        panel.add(Box.createVerticalGlue());
        
        JButton testBtn = new StyledButton("Testar Conexão", StyledButton.ButtonStyle.SECONDARY);
        panel.add(testBtn);
        
        return panel;
    }
    
    private JPanel createBackupTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(ColorScheme.BG_PRIMARY);
        panel.setBorder(BorderFactory.createEmptyBorder(UIConstants.PADDING_LG, UIConstants.PADDING_LG,
                                                        UIConstants.PADDING_LG, UIConstants.PADDING_LG));
        
        JLabel backupLabel = new JLabel("Backup e Restauração");
        backupLabel.setFont(FontManager.getBold(UIConstants.FONT_SUBTITLE));
        panel.add(backupLabel);
        
        panel.add(Box.createVerticalStrut(UIConstants.PADDING_MD));
        
        JLabel lastBackupLabel = new JLabel("Último backup: 15/01/2025 às 02:00");
        lastBackupLabel.setFont(FontManager.getRegular(UIConstants.FONT_LABEL));
        lastBackupLabel.setForeground(ColorScheme.TEXT_MUTED);
        panel.add(lastBackupLabel);
        
        panel.add(Box.createVerticalStrut(UIConstants.PADDING_LG));
        
        JButton backupNowBtn = new StyledButton("Fazer Backup Agora", StyledButton.ButtonStyle.PRIMARY);
        panel.add(backupNowBtn);
        
        panel.add(Box.createVerticalStrut(UIConstants.GAP_MD));
        
        JButton restoreBtn = new StyledButton("Restaurar Backup", StyledButton.ButtonStyle.SECONDARY);
        panel.add(restoreBtn);
        
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    private JPanel createSettingPanel(String label, String[] options) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ColorScheme.BG_PRIMARY);
        
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(FontManager.getSemiBold(UIConstants.FONT_LABEL));
        labelComponent.setForeground(ColorScheme.TEXT_PRIMARY);
        panel.add(labelComponent, BorderLayout.WEST);
        
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(200, 36));
        panel.add(comboBox, BorderLayout.EAST);
        
        return panel;
    }
}
```

---

## 🔗 Template Pattern para Novas Páginas

Todas as páginas seguem este padrão:

```java
public class NovaPanel extends JPanel {
    public NovaPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.BG_PRIMARY);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(ColorScheme.BG_PRIMARY);
        mainContent.setBorder(BorderFactory.createEmptyBorder(
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL,
            UIConstants.PADDING_LG,
            UIConstants.PADDING_XL
        ));
        
        // 1. Header com título e botões
        JPanel header = createHeader();
        mainContent.add(header, BorderLayout.NORTH);
        
        // 2. Filtros/Pesquisa
        JPanel filters = createFilters();
        mainContent.add(filters, BorderLayout.NORTH);
        
        // 3. Conteúdo (tabela, grid, etc)
        JPanel content = createContent();
        mainContent.add(content, BorderLayout.CENTER);
        
        add(mainContent, BorderLayout.CENTER);
    }
    
    private JPanel createHeader() { /* ... */ }
    private JPanel createFilters() { /* ... */ }
    private JPanel createContent() { /* ... */ }
}
```

---

## ✅ Checklist de Páginas

- [ ] LoginPanel.java
- [ ] DashboardPanel.java
- [ ] ProdutosPanel.java
- [ ] EntradasPanel.java
- [ ] SaidasPanel.java
- [ ] EstoquePanel.java
- [ ] FuncionariosPanel.java
- [ ] FornecedoresPanel.java
- [ ] ClientesPanel.java
- [ ] UtilizadoresPanel.java
- [ ] AlertasPanel.java
- [ ] LogsPanel.java
- [ ] SucursaisPanel.java
- [ ] ConfiguracoesPanel.java
- [ ] InventarioPanel.java (não incluído, usar como template)
- [ ] RelatoriosPanel.java (não incluído, usar como template)

---

**Fim da Documentação de Páginas Adicionais**
