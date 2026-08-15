# MEPIT WMS - ESPECIFICAÇÃO COMPLETA DA INTERFACE
**Sistema de Gestão de Armazém**  
Data: 2025 | Versão: 1.0

---

## 📋 ÍNDICE
1. [Visão Geral](#visão-geral)
2. [Paleta de Cores](#paleta-de-cores)
3. [Tipografia](#tipografia)
4. [Layout Geral](#layout-geral)
5. [Página de Login](#página-de-login)
6. [Dashboard Principal](#dashboard-principal)
7. [Componentes Reutilizáveis](#componentes-reutilizáveis)
8. [Páginas Funcionais](#páginas-funcionais)
9. [Responsividade](#responsividade)
10. [Animações e Transições](#animações-e-transições)

---

## 🎯 Visão Geral

**Nome do Sistema:** MEPIT - Warehouse Management System  
**Tipo:** Aplicação Web React com Backend Spring Boot  
**Arquitetura de UI:** Layout tradicional com Sidebar + Navbar + Content  
**Responsável pela Interface:** React 19.2.8 + CSS3  
**Bibliotecas de UI Utilizadas:**
- Recharts (para gráficos)
- Material-UI (para alguns ícones/componentes)
- FontAwesome (para ícones)

---

## 🎨 Paleta de Cores

### Cores CSS Raiz (arquivo: Dashboard.css)

```css
:root {
  /* Fundo Principal */
  --bg-primary: #ffffff;           /* Branco puro */
  --bg-secondary: #f8fafc;         /* Cinza muito claro */
  --bg-card: #ffffff;              /* Fundo de cards */
  --bg-hover: #f1f5f9;             /* Cor de hover */
  --bg-navbar: #ffffff;            /* Fundo navbar */
  
  /* Sidebar Escura */
  --bg-sidebar: #0d1321;           /* Azul muito escuro */
  --sidebar-hover: #243044;        /* Azul escuro para hover */
  --sidebar-text: #8899b4;         /* Texto cinza claro */
  --sidebar-text-muted: #556688;   /* Texto cinza mais escuro */
  --sidebar-border: #1e2d45;       /* Borda cinza */
  
  /* Cores de Texto */
  --text-primary: #111827;         /* Preto/Cinza escuro */
  --text-secondary: #4b5563;       /* Cinza médio */
  --text-muted: #9ca3af;           /* Cinza claro */
  
  /* Cores de Borda */
  --border-color: #e5e7eb;         /* Cinza muito claro */
  
  /* Cores de Acentuação */
  --accent-blue: #2563eb;          /* AZUL PRINCIPAL */
  --accent-cyan: #06b6d4;          /* Ciano/Turquesa */
  --accent-green: #10b981;         /* Verde */
  --accent-yellow: #f59e0b;        /* Amarelo/Laranja claro */
  --accent-red: #ef4444;           /* Vermelho */
  --accent-purple: #8b5cf6;        /* Roxo */
  --accent-orange: #f97316;        /* Laranja */
  
  /* Sombras e Espaçamento */
  --shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  --radius: 12px;                  /* Border radius padrão */
  --radius-sm: 8px;
  --radius-xs: 4px;
}
```

### Gradientes Utilizados

| Uso | Gradiente |
|-----|-----------|
| Logo | `linear-gradient(135deg, #2563eb, #06b6d4)` |
| Botão Login | `linear-gradient(135deg, #2563eb, #1d4ed8)` |
| Fundo Login | `linear-gradient(135deg, #0d1321 0%, #1e293b 50%, #2563eb 130%)` |
| Avatar Usuário | `linear-gradient(135deg, #8b5cf6, #2563eb)` |
| Sucursal Icon | `linear-gradient(135deg, #2563eb, #06b6d4)` |

### Esquema de Cores por Estado

```
Status de Entrada: Verde (#10b981) - rgba(16, 185, 129, 0.12) fundo
Status de Saída:   Vermelho (#ef4444) - rgba(239, 68, 68, 0.12) fundo
Status Transfer:   Azul (#2563eb) - rgba(37, 99, 235, 0.12) fundo
Status Crítico:    Vermelho (#ef4444)
Status Warning:    Amarelo (#f59e0b) - rgba(245, 158, 11, 0.12) fundo
Status OK:         Verde (#10b981)
```

---

## 🔤 Tipografia

### Família de Fonte
```
Primária: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif
Monoespacial: source-code-pro, Menlo, Monaco, Consolas, 'Courier New'
```

### Tamanhos e Pesos

| Elemento | Tamanho | Peso | Uso |
|----------|---------|------|-----|
| Título Page | 20px | 700 | Título de página |
| Título Section | 18px | 800 | Logo/Marca principal |
| Título Card | 16px | 700 | Cabeçalho de formulário |
| Título Label | 15px | 600 | Subtítulos/charts |
| Padrão | 14px | 400-500 | Texto corpo |
| Label | 13px | 600 | Labels de formulário |
| Small | 12px | 500-600 | Texto menor |
| Tiny | 11px | 500-700 | Texto muito pequeno (badges) |
| Extra Tiny | 10px | 600 | Seções de menu |

### Espaçamento de Letra
- Standard: 0px
- Section Titles: 1.5px
- Menu Titles: 1.5px-3px
- Uppercase: 2px-3px

---

## 📐 Layout Geral

### Estrutura Geral do Dashboard

```
┌─────────────────────────────────────────────────────┐
│                    NAVBAR                            │ 60px
├───────────────────┬─────────────────────────────────┤
│                   │                                  │
│     SIDEBAR       │       MAIN CONTENT AREA         │
│  (260px fixed)    │     (flex, scrollável)          │
│   (dark theme)    │      (light theme)              │
│                   │                                  │
│                   │                                  │
└───────────────────┴─────────────────────────────────┘
```

### Dimensões Principais

| Elemento | Dimensão | Descrição |
|----------|----------|-----------|
| Sidebar | 260px (fixo) | Largura sidebar |
| Navbar | 60px | Altura navbar |
| Main Content | Flex | Toma espaço restante |
| Max Page Width | Sem limite | Full width |
| Viewport Mínimo | 320px | Mobile |
| Desktop Padrão | 1024px+ | Full layout |
| Tablet | 768px-1023px | Sidebar hidden |

---

## 🔐 Página de Login

### Estrutura

```
┌─────────────────────────────────────────┐
│                                         │
│    FUNDO: Gradiente azul/escuro         │
│                                         │
│        ┌──────────────────────┐         │
│        │   LOGIN CARD         │         │
│        │  (Card branco)       │         │
│        │                      │         │
│        │  [Logo] MEPIT        │         │
│        │  WHM System          │         │
│        │                      │         │
│        │  Bem-vindo!          │         │
│        │                      │         │
│        │  [Email Input]       │         │
│        │  [Senha Input]       │         │
│        │  [Erro - se houver]  │         │
│        │  [Botão Entrar]      │         │
│        │                      │         │
│        │  © 2025 MEPIT        │         │
│        └──────────────────────┘         │
│                                         │
└─────────────────────────────────────────┘
```

### Componentes Login

#### Login Page Container
- **Classe:** `.login-page`
- **Altura:** `min-height: 100vh`
- **Display:** `flex` (centralizado)
- **Fundo:** `linear-gradient(135deg, #0d1321 0%, #1e293b 50%, #2563eb 130%)`
- **Padding:** `20px`

#### Login Card
- **Classe:** `.login-card`
- **Cor Fundo:** `#ffffff`
- **Border Radius:** `16px`
- **Padding:** `40px 36px`
- **Largura Máx:** `400px`
- **Sombra:** `0 20px 60px rgba(0, 0, 0, 0.35)`
- **Animação:** `loginFadeIn 0.5s ease` (fade in + slide up)

#### Logo
- **Classe:** `.login-logo`
- **Tamanho:** `72px x 72px`
- **Border Radius:** `18px`
- **Fundo:** `linear-gradient(135deg, #2563eb, #06b6d4)`
- **Sombra:** `0 8px 20px rgba(37, 99, 235, 0.35)`
- **Imagem:** 52px x 52px (logo_mepit_transparent.png)

#### Título
- **Classe:** `.login-brand h1`
- **Texto:** "MEPIT"
- **Font Size:** `28px`
- **Font Weight:** `800`
- **Cor:** `#0d1321`
- **Letter Spacing:** `1px`

#### Subtitle
- **Classe:** `.login-subtitle`
- **Texto:** "WHM System"
- **Font Size:** `11px`
- **Font Weight:** `700`
- **Cor:** `#2563eb`
- **Text Transform:** `uppercase`
- **Letter Spacing:** `3px`

#### Campos de Entrada

```
Padrão para todos os inputs:
┌───────────────────────────────┐
│ <i> Label                     │  (label com ícone)
│ ┌─────────────────────────────┤
│ │ Input field                 │  (placeholder cinza)
│ └─────────────────────────────┤
└───────────────────────────────┘
```

- **Label Font Size:** `13px` | **Weight:** `600` | **Cor:** `#374151`
- **Input Padding:** `12px 14px`
- **Input Border:** `1px solid #e5e7eb`
- **Input Border Radius:** `10px`
- **Input Fundo:** `#f9fafb`
- **Input Font Size:** `14px`
- **Input Focus:** 
  - Border: `#2563eb`
  - Background: `#ffffff`
  - Box Shadow: `0 0 0 3px rgba(37, 99, 235, 0.15)`
- **Placeholder Cor:** `#9ca3af`
- **Ícone Cor:** `#2563eb`

#### Erro (se houver)
- **Classe:** `.login-error`
- **Fundo:** `rgba(239, 68, 68, 0.08)` (vermelho muito claro)
- **Border:** `1px solid rgba(239, 68, 68, 0.3)`
- **Cor Texto:** `#dc2626` (vermelho)
- **Padding:** `10px 14px`
- **Border Radius:** `8px`
- **Font Size:** `13px`
- **Display:** flex com ícone + texto

#### Botão de Login
- **Classe:** `.login-btn`
- **Largura:** `100%`
- **Padding:** `13px`
- **Fundo:** `linear-gradient(135deg, #2563eb, #1d4ed8)`
- **Cor Texto:** `#ffffff`
- **Border Radius:** `10px`
- **Font Size:** `15px`
- **Font Weight:** `700`
- **Display:** flex (centralizado) com ícone
- **Transição:** `all 0.2s`
- **Hover:** 
  - Transform: `translateY(-1px)`
  - Sombra: `0 8px 20px rgba(37, 99, 235, 0.35)`
- **Disabled:** `opacity: 0.7` | `cursor: not-allowed`
- **Loading:** Mostra spinner com "Autenticando..."

#### Footer
- **Texto:** © YYYY MEPIT — Sistema de Gestão de Armazém
- **Font Size:** `12px`
- **Cor:** `#9ca3af`
- **Text Align:** `center`

---

## 📊 Dashboard Principal

### Layout Geral

O dashboard está dividido em 2 partes:

#### 1. SIDEBAR (Esquerda - Escura)

**Estrutura:**
```
┌──────────────────┐
│     LOGO         │ (44x44 com gradiente)
│   MEPIT          │
│   WHM            │
├──────────────────┤
│ PRINCIPAL        │
│ ├ Início         │
│ ├ Produtos       │
│ ├ Entradas       │
│ ├ Saídas         │
│ ├ Estoque        │
│ ├ Solicitar      │
│                  │
│ GESTÃO           │
│ ├ Sucursais      │
│ ├ Funcionários   │
│ ├ Inventário     │
│ ├ Fornecedores   │
│ ├ Clientes       │
│ ├ Utilizadores   │
│                  │
│ MONITORAMENTO    │
│ ├ Alertas        │
│ ├ Logs           │
│ ├ n8n            │
│ ├ Técnico        │
│ ├ Relatórios     │
│ ├ Configurações  │
│                  │
│ AJUDA | SAIR     │
└──────────────────┘
```

**Dimensões:**
- Largura: `260px` (fixo)
- Altura: `100vh`
- Overflow: `scroll`

**Cores:**
- Fundo: `#0d1321`
- Texto: `#8899b4`
- Border Right: `1px solid #1e2d45`

**Logo Sidebar:**
- **Classe:** `.sidebar-logo`
- **Padding:** `20px 20px 16px`
- **Border Bottom:** `1px solid #1e2d45`
- **Icon:** 44x44 com gradiente azul-ciano
- **Título:** "MEPIT" (18px, bold, #e8edf5)
- **Subtitle:** "WHM" (10px, #06b6d4)

**Menu Sections:**

```
.menu-section-title:
  - Font Size: 10px
  - Text Transform: uppercase
  - Letter Spacing: 1.5px
  - Padding: 8px 22px
  - Color: #556688
  - Font Weight: 600
  - Margin Top: 8px
```

**Menu Items:**

```
.menu-item (Normal):
  - Padding: 10px 20px
  - Font Size: 14px
  - Color: #8899b4
  - Display: flex + gap 12px
  - Border Left: 3px solid transparent
  - Cursor: pointer
  - Transition: all 0.2s

.menu-item:hover:
  - Background: #243044
  - Color: #e8edf5

.menu-item.active:
  - Background: rgba(37, 99, 235, 0.12)
  - Color: #2563eb (azul)
  - Border Left Color: #2563eb
```

**Icon:**
- Tamanho: `16px`
- Largura Fixa: `22px`
- Alinhamento: center

**Badge (se houver):**
- Background: `#ef4444` (vermelho)
- Cor: `#fff`
- Padding: `2px 8px`
- Border Radius: `10px`
- Font Size: `10px`
- Font Weight: `700`
- Min Width: `20px`
- Margem: auto (aligned right)

**Scrollbar Sidebar:**
- Largura: `4px`
- Track: `transparent`
- Thumb: `#1e2d45`
- Border Radius: `4px`

---

#### 2. AREA PRINCIPAL (Direita - Clara)

**Estrutura:**
```
┌────────────────────────────────────┐
│          NAVBAR                    │ 60px
├────────────────────────────────────┤
│                                    │
│      DASHBOARD CONTENT             │
│                                    │
│  ┌────────────────────────────┐   │
│  │ KPI Cards Grid (8 cards)   │   │
│  └────────────────────────────┘   │
│                                    │
│  ┌──────────────────┬──────────┐   │
│  │  Bar Chart       │ Pie Chart│   │
│  │  (Movimentos)    │          │   │
│  └──────────────────┴──────────┘   │
│                                    │
│  ┌──────────────────┬──────────┐   │
│  │  Table Entradas  │  Critical│   │
│  │                  │  Products│   │
│  └──────────────────┴──────────┘   │
│                                    │
└────────────────────────────────────┘
```

---

### NAVBAR (Topo - Clara)

**Estrutura:**
```
┌─────────────────────────────────────┐
│ [Toggle] Título │ [Search]  │ [Icons] [User] │
└─────────────────────────────────────┘
```

**Dimensões:**
- Altura: `60px`
- Padding: `12px 28px`
- Display: flex | justify-content: space-between

**Cores:**
- Fundo: `#ffffff`
- Border Bottom: `1px solid #e5e7eb`
- Backdrop Filter: `blur(12px)`

**Navbar Left:**
- Display: flex
- Gap: `16px`
- Align Items: center

**Page Title:**
- Font Size: `18px`
- Font Weight: `600`
- Cor: `#111827`

**Search Bar:**
- Display: flex | align-items: center
- Background: `#f8fafc`
- Border: `1px solid #e5e7eb`
- Border Radius: `8px`
- Padding: `6px 14px`
- Largura: `320px`
- **Focus:**
  - Border Color: `#2563eb`
  - Box Shadow: `0 0 0 3px rgba(37, 99, 235, 0.15)`
- **Input:**
  - Background: transparent
  - Border: none
  - Outline: none
  - Font Size: `13px`
  - Margin Left: `8px`

**Navbar Right:**
- Display: flex
- Gap: `8px`

**Nav Icon Button:**
```
.nav-icon-btn:
  - Largura/Altura: 36px
  - Border Radius: 8px
  - Background: transparent
  - Border: 1px solid #e5e7eb
  - Cor: #4b5563
  - Font Size: 15px
  - Cursor: pointer
  - Transition: all 0.2s
  
  :hover:
    - Background: #f1f5f9
    - Cor: #111827
```

**Notification Dot:**
- Position: absolute (top 4px, right 4px)
- Tamanho: 8px
- Background: `#ef4444`
- Border: `2px solid #ffffff`
- Border Radius: `50%`

**Language Popover:**
- Position: absolute (top calc(100% + 8px), right 0)
- Largura: `180px`
- Background: `#ffffff`
- Border: `1px solid #e5e7eb`
- Border Radius: `14px`
- Box Shadow: `0 22px 48px rgba(15, 23, 42, 0.12)`
- Padding: `8px 0`
- Z-Index: `101`

**Popover Item:**
```
.lang-popover-item:
  - Padding: 10px 14px
  - Font Size: 13px
  - Display: flex | justify-content: space-between
  - Cursor: pointer
  - Transition: background 0.2s
  
  :hover/.active:
    - Background: #f1f5f9
```

**User Profile:**
- Display: flex
- Gap: `10px`
- Padding: `4px 12px 4px 4px`
- Border Radius: `8px`
- Cursor: pointer
- Transition: all 0.2s

**User Avatar:**
- Tamanho: `32px x 32px`
- Border Radius: `8px`
- Background: `linear-gradient(135deg, #8b5cf6, #2563eb)`
- Display: flex (centralizado)
- Font Size: `13px`
- Font Weight: `700`
- Cor: `#fff`
- Conteúdo: Iniciais do usuário (ex: "JD")

**User Info:**
- Line Height: `1.2`

```
.user-name:
  - Font Size: 13px
  - Font Weight: 600
  - Cor: #111827

.user-role:
  - Font Size: 11px
  - Cor: #9ca3af
```

---

### DASHBOARD CONTENT

**Container:**
- **Classe:** `.dashboard-content`
- **Padding:** `24px 28px`
- **Flex:** 1
- **Overflow Y:** auto
- **Max Height:** `calc(100vh - 72px)`

---

#### KPI CARDS

**Grid:**
```
.kpi-grid:
  - Display: grid
  - Grid Template Columns: repeat(auto-fill, minmax(180px, 1fr))
  - Gap: 16px
  - Margin Bottom: 24px
```

**Card Individual:**

```
.kpi-card:
  - Background: #ffffff
  - Border: 1px solid #e5e7eb
  - Border Radius: 12px
  - Padding: 18px 20px
  - Transition: all 0.3s ease
  - Cursor: pointer
  - Position: relative
  - Overflow: hidden
  - Box Shadow: 0 1px 2px rgba(0, 0, 0, 0.04)
  
  :hover:
    - Transform: translateY(-2px)
    - Box Shadow: 0 4px 20px rgba(0, 0, 0, 0.08)
    - Border Color: rgba(37, 99, 235, 0.3)
```

**Componentes do Card:**

```
.kpi-header:
  - Display: flex
  - Align Items: center
  - Justify Content: space-between
  - Margin Bottom: 10px

.kpi-label:
  - Font Size: 12px
  - Cor: #4b5563
  - Font Weight: 500
  - Text Transform: uppercase
  - Letter Spacing: 0.5px

.kpi-icon:
  - Tamanho: 36px x 36px
  - Border Radius: 8px
  - Display: flex (centralizado)
  - Font Size: 16px
  - Flex Shrink: 0

.kpi-value:
  - Font Size: 28px
  - Font Weight: 700
  - Cor: #111827
  - Line Height: 1
  - Margin Bottom: 4px

.kpi-change:
  - Font Size: 12px
  - Font Weight: 600
  - Display: flex + gap 4px
  
  .positive: Cor #10b981 (verde)
  .negative: Cor #ef4444 (vermelho)

.kpi-bg-icon:
  - Position: absolute (bottom right)
  - Font Size: 60px
  - Opacity: 0.05
  - Pointer Events: none
```

**Cores de Icon por Tipo:**

```
.kpi-card.blue .kpi-icon:
  - Background: rgba(37, 99, 235, 0.1)
  - Cor: #2563eb

.kpi-card.green .kpi-icon:
  - Background: rgba(16, 185, 129, 0.1)
  - Cor: #10b981

.kpi-card.orange .kpi-icon:
  - Background: rgba(249, 115, 22, 0.1)
  - Cor: #f97316

.kpi-card.cyan .kpi-icon:
  - Background: rgba(6, 182, 212, 0.1)
  - Cor: #06b6d4

.kpi-card.red .kpi-icon:
  - Background: rgba(239, 68, 68, 0.1)
  - Cor: #ef4444

.kpi-card.purple .kpi-icon:
  - Background: rgba(139, 92, 246, 0.1)
  - Cor: #8b5cf6
```

**KPI Cards Mostrados:**
1. **Total Produtos** (Azul) - ícone: box
2. **Entradas Hoje** (Verde) - ícone: truck-arrow-right
3. **Saídas Hoje** (Laranja) - ícone: truck-arrow-right (mirrored)
4. **Total Estoque** (Ciano) - ícone: warehouse
5. **Total Alertas** (Vermelho) - ícone: bell
6. **Total Utilizadores** (Roxo) - ícone: users
7. **Total Sucursais** (Amarelo) - ícone: industry
8. **Total Fornecedores** (Teal) - ícone: truck

---

#### GRÁFICOS

**Grid Layout:**
```
.charts-grid:
  - Display: grid
  - Grid Template Columns: 2fr 1fr
  - Gap: 16px
  - Margin Bottom: 24px
```

**Chart Card:**
```
.chart-card:
  - Background: #ffffff
  - Border: 1px solid #e5e7eb
  - Border Radius: 12px
  - Padding: 20px
  - Box Shadow: 0 1px 2px rgba(0, 0, 0, 0.04)

.chart-card.full-width:
  - Grid Column: 1 / -1 (ocupa toda a largura)
```

**Chart Header:**
```
.chart-header:
  - Display: flex
  - Align Items: center
  - Justify Content: space-between
  - Margin Bottom: 16px

.chart-title:
  - Font Size: 15px
  - Font Weight: 600
  - Cor: #111827
  - Display: flex + gap 8px

  i:
    - Cor: #2563eb
    - Font Size: 14px
```

**Chart Actions:**
```
.chart-actions:
  - Display: flex
  - Gap: 8px

.chart-action-btn:
  - Padding: 4px 12px
  - Border: 1px solid #e5e7eb
  - Border Radius: 6px
  - Background: transparent
  - Cor: #4b5563
  - Font Size: 12px
  - Cursor: pointer
  - Transition: all 0.2s
  - Display: inline-flex + gap 6px
  
  :hover/:active:
    - Background: #2563eb
    - Border Color: #2563eb
    - Cor: #fff
```

**Gráficos Utilizados:**

1. **Bar Chart** (Movimentos por Dia)
   - Libraria: Recharts BarChart
   - Tipo: Bar horizontal ou vertical
   - Cores: #2563eb (primary)

2. **Pie Chart** (Distribuição por Categoria)
   - Libraria: Recharts PieChart
   - Cores: ['#2563eb', '#06b6d4', '#f59e0b', '#8b5cf6', '#10b981', '#ef4444', '#f97316']
   - Legend: Abaixo do gráfico

---

#### TABELAS

**Container:**
```
.table-wrapper:
  - Background: #ffffff
  - Border: 1px solid #e5e7eb
  - Border Radius: 12px
  - Overflow: hidden
  - Padding: 6px
  - Box Shadow: 0 1px 2px rgba(0, 0, 0, 0.04)
```

**Table Layout:**
```
.table-responsive:
  - Overflow X: auto
  - Largura: 100%

.data-table:
  - Largura: 100%
  - Border Collapse: collapse
  - Font Size: 13px
```

**Header:**
```
.data-table thead th:
  - Padding: 10px 14px
  - Text Align: left
  - Cor: #9ca3af
  - Font Weight: 600
  - Font Size: 11px
  - Text Transform: uppercase
  - Letter Spacing: 0.5px
  - Border Bottom: 1px solid #e5e7eb
  - White Space: nowrap
  - Background: #f8fafc
```

**Body:**
```
.data-table tbody td:
  - Padding: 10px 14px
  - Border Bottom: 1px solid #f1f5f9
  - Cor: #4b5563
  - White Space: nowrap

.data-table tbody tr:
  - Background: #ffffff
  
  :hover:
    - Background: #f1f5f9
```

**Status Badges:**

```
.status-badge:
  - Display: inline-flex + gap 6px
  - Padding: 3px 10px
  - Border Radius: 20px
  - Font Size: 11px
  - Font Weight: 600

.status-badge.entry:
  - Background: rgba(16, 185, 129, 0.12)
  - Cor: #10b981

.status-badge.exit:
  - Background: rgba(239, 68, 68, 0.12)
  - Cor: #ef4444

.status-badge.transfer:
  - Background: rgba(37, 99, 235, 0.12)
  - Cor: #2563eb

.status-badge.critical:
  - Background: rgba(239, 68, 68, 0.12)
  - Cor: #ef4444

.status-badge.warning:
  - Background: rgba(245, 158, 11, 0.12)
  - Cor: #f59e0b

.status-badge.ok:
  - Background: rgba(16, 185, 129, 0.12)
  - Cor: #10b981

.status-badge.purple:
  - Background: rgba(139, 92, 246, 0.12)
  - Cor: #8b5cf6
```

---

## 🔧 Componentes Reutilizáveis

### Buttons

#### Botão Primário
```
.btn-primary:
  - Background: #2563eb
  - Cor: #fff
  - Border: none
  - Padding: 8px 18px
  - Border Radius: 8px
  - Font Size: 13px
  - Font Weight: 600
  - Cursor: pointer
  - Display: inline-flex + gap 8px
  - Transition: all 0.2s
  
  :hover:
    - Background: #1d4ed8
    - Transform: translateY(-1px)
    - Box Shadow: 0 4px 12px rgba(37, 99, 235, 0.25)
```

#### Botão Secundário
```
.btn-secondary:
  - Background: transparent
  - Cor: #4b5563
  - Border: 1px solid #e5e7eb
  - Padding: 8px 18px
  - Border Radius: 8px
  - Font Size: 13px
  - Font Weight: 600
  - Cursor: pointer
  - Transition: all 0.2s
  
  :hover:
    - Background: #f1f5f9
```

#### Botão com Ícone
```
.btn-icon:
  - Display: inline-flex + gap 6px
  - Background: #f8fafc
  - Border: 1px solid #e5e7eb
  - Border Radius: 6px
  - Padding: 5px 12px
  - Font Size: 12px
  - Font Weight: 500
  - Cor: #4b5563
  - Cursor: pointer
  - Transition: all 0.2s
  
  :hover:
    - Background: #f1f5f9
    - Cor: #111827
  
  &.primary:hover:
    - Background: rgba(37, 99, 235, 0.1)
    - Cor: #2563eb
    - Border Color: rgba(37, 99, 235, 0.3)
  
  &.danger:hover:
    - Background: rgba(239, 68, 68, 0.1)
    - Cor: #ef4444
    - Border Color: rgba(239, 68, 68, 0.3)
```

---

### Formulários

**Container:**
```
.form-panel:
  - Background: #ffffff
  - Border: 1px solid #e5e7eb
  - Border Radius: 12px
  - Padding: 20px
  - Margin Bottom: 20px
  - Animation: fadeInUp 0.3s ease
  
  h3:
    - Margin: 0 0 16px
    - Font Size: 16px
    - Font Weight: 700
    - Cor: #111827
    - Display: flex + gap 8px
    
    i:
      - Cor: #2563eb
```

**Grid de Formulário:**
```
.form-grid:
  - Display: grid
  - Grid Template Columns: repeat(auto-fit, minmax(220px, 1fr))
  - Gap: 16px
```

**Grupo de Formulário:**
```
.form-group:
  - Display: flex (flex-direction: column)
  - Gap: 6px

label:
  - Font Size: 12px
  - Font Weight: 600
  - Cor: #4b5563
```

**Inputs e Selects:**
```
.form-control, .input-select:
  - Padding: 8px 12px
  - Border: 1px solid #e5e7eb
  - Border Radius: 8px
  - Font Size: 13px
  - Cor: #111827
  - Background: #fff
  - Outline: none
  - Transition: all 0.2s
  
  :focus:
    - Border Color: #2563eb
    - Box Shadow: 0 0 0 3px rgba(37, 99, 235, 0.15)
```

**Ações do Formulário:**
```
.form-actions:
  - Display: flex
  - Gap: 10px
  - Margin Top: 18px
```

---

### Cards

**Card Padrão:**
```
Background: #ffffff
Border: 1px solid #e5e7eb
Border Radius: 12px
Padding: 18px
Box Shadow: 0 1px 2px rgba(0, 0, 0, 0.04)
Transition: all 0.2s

:hover:
  - Box Shadow: 0 4px 20px rgba(0, 0, 0, 0.08)
  - Transform: translateY(-2px)
```

---

### Input Search

**Container:**
```
.search-input-group:
  - Position: relative
  - Display: inline-flex
  - Align Items: center

.search-input-icon:
  - Position: absolute (left 10px)
  - Cor: #9ca3af
  - Font Size: 13px
  - Pointer Events: none

input:
  - Padding Left: 32px (para espaço do ícone)
```

---

## 📄 Páginas Funcionais

Todas as páginas funcionam com a mesma estrutura:

### Estrutura Padrão da Página

```
┌─────────────────────────────────┐
│ [Título da Página] [Botões]     │ (page-header)
├─────────────────────────────────┤
│ [Filtros/Search]                │
├─────────────────────────────────┤
│                                 │
│ [TABELA DE DADOS]               │
│                                 │
└─────────────────────────────────┘

[FORMULÁRIO] (ao clicar em Novo/Editar)
```

**Page Header:**
```
.page-header:
  - Display: flex
  - Align Items: center
  - Justify Content: space-between
  - Flex Wrap: wrap
  - Gap: 12px
  - Margin Bottom: 20px
  
.page-title:
  - Font Size: 20px
  - Font Weight: 700
  - Cor: #111827
  - Display: flex + gap 10px
```

**Page Filters:**
```
.page-filters:
  - Display: flex
  - Gap: 12px
  - Margin Bottom: 16px

input:
  - Padding: 8px 14px
  - Border: 1px solid #e5e7eb
  - Border Radius: 8px
  - Font Size: 13px
  - Cor: #111827
  - Background: #fff
  - Largura: 280px
  
  :focus:
    - Border Color: #2563eb
    - Box Shadow: 0 0 0 3px rgba(37, 99, 235, 0.15)
```

---

### Páginas Disponíveis

1. **Dashboard** (`dashboard`) - Página inicial com KPIs e gráficos
2. **Produtos** (`produtos`) - CRUD de produtos/equipamentos
3. **Entradas** (`entradas`) - Registro de entradas de estoque
4. **Saídas** (`saidas`) - Registro de saídas de estoque
5. **Estoque** (`estoque`) - Visualização de estoque por local
6. **Funcionários** (`funcionarios`) - Gestão de funcionários
7. **Fornecedores** (`fornecedores`) - Gestão de fornecedores
8. **Clientes** (`clientes`) - Gestão de clientes
9. **Utilizadores** (`utilizadores`) - Gestão de utilizadores (Administrador)
10. **Alertas** (`alertas`) - Visualização de alertas do sistema
11. **Logs** (`logs`) - Auditoria de ações do sistema
12. **Inventário** (`inventario`) - Agenda de contagens de inventário
13. **Sucursais** (`sucursais`) - Gestão de filiais/armazéns
14. **Relatórios** (`relatorios`) - Geração de relatórios
15. **Configurações** (`configuracoes`) - Definições do sistema
16. **n8n** (`n8n`) - Integração com automação
17. **Técnico** (`tecnico`) - Página técnica

---

## 🎯 Componentes Específicos de Páginas

### Sucursais (Branches)

**Grid de Cards:**
```
.sucursais-grid:
  - Display: grid
  - Grid Template Columns: repeat(auto-fill, minmax(280px, 1fr))
  - Gap: 16px

.sucursal-card:
  - Background: #ffffff
  - Border: 1px solid #e5e7eb
  - Border Radius: 12px
  - Padding: 18px
  - Box Shadow: 0 1px 2px rgba(0, 0, 0, 0.04)
  - Transition: all 0.2s
  
  :hover:
    - Box Shadow: 0 4px 20px rgba(0, 0, 0, 0.08)
    - Transform: translateY(-2px)
```

**Card Header:**
```
.sucursal-card-header:
  - Display: flex + gap 12px
  - Margin Bottom: 10px

.sucursal-icon:
  - Tamanho: 44x44
  - Border Radius: 10px
  - Background: linear-gradient(135deg, #2563eb, #06b6d4)
  - Cor: #fff
  - Display: flex (centralizado)
  - Font Size: 18px
  - Flex Shrink: 0

h3:
  - Margin: 0
  - Font Size: 15px
  - Font Weight: 700
  - Cor: #111827
```

**Provincia Card:**
```
.provincia-card:
  - Background: #f8fafc
  - Border: 1px solid #e5e7eb
  - Border Radius: 8px
  - Padding: 14px 16px

.provincia-name:
  - Font Size: 14px
  - Font Weight: 700
  - Cor: #111827

.provincia-qty:
  - Font Size: 20px
  - Font Weight: 800
  - Cor: #111827

.provincia-bar:
  - Altura: 8px
  - Background: #e5e7eb
  - Border Radius: 6px
  - Overflow: hidden

.provincia-bar-fill:
  - Altura: 100%
  - Border Radius: 6px
  - Transition: width 0.5s ease
  - Cor: #2563eb (ou outra conforme tipo)
```

---

### Categorias Panel

**Container:**
```
.categorias-panel:
  - Background: #ffffff
  - Border: 1px solid #e5e7eb
  - Border Radius: 12px
  - Padding: 16px 20px
  - Margin Bottom: 20px

.categorias-header:
  - Display: flex + justify-content: space-between
  - Margin Bottom: 12px
  
h3:
  - Margin: 0
  - Font Size: 15px
  - Font Weight: 700
  - Display: flex + gap 8px
```

**Categoria Chips:**
```
.categoria-chip:
  - Display: inline-flex + gap 6px
  - Background: #f8fafc
  - Border: 1px solid #e5e7eb
  - Border Radius: 20px
  - Padding: 5px 12px
  - Font Size: 13px
  - Font Weight: 500
  - Cor: #111827

.categoria-remove:
  - Display: inline-flex (centralizado)
  - Tamanho: 18x18
  - Border: none
  - Border Radius: 50%
  - Background: rgba(239, 68, 68, 0.12)
  - Cor: #ef4444
  - Font Size: 11px
  - Cursor: pointer
  - Transition: all 0.2s
  
  :hover:
    - Background: #ef4444
    - Cor: #fff
```

---

### Alert Message

```
.alert-message:
  - Display: flex + gap 8px
  - Padding: 10px 14px
  - Border Radius: 8px
  - Background: rgba(37, 99, 235, 0.08)
  - Border: 1px solid rgba(37, 99, 235, 0.25)
  - Cor: #2563eb
  - Font Size: 13px
  - Font Weight: 500
  - Margin Bottom: 16px
```

---

### Notifications

**Lista:**
```
.notification-list:
  - Display: flex (flex-direction: column)
  - Gap: 6px

.notification-item:
  - Display: flex + gap 12px
  - Padding: 10px 14px
  - Border Radius: 8px
  - Background: #f8fafc
  - Transition: all 0.2s
  - Cursor: pointer
  
  :hover:
    - Background: #f1f5f9
```

**Icon:**
```
.notif-icon:
  - Tamanho: 32x32
  - Border Radius: 8px
  - Display: flex (centralizado)
  - Font Size: 14px
  - Flex Shrink: 0

.notif-icon.red:
  - Background: rgba(239, 68, 68, 0.12)
  - Cor: #ef4444

.notif-icon.green:
  - Background: rgba(16, 185, 129, 0.12)
  - Cor: #10b981

.notif-icon.yellow:
  - Background: rgba(245, 158, 11, 0.12)
  - Cor: #f59e0b

.notif-icon.blue:
  - Background: rgba(37, 99, 235, 0.12)
  - Cor: #2563eb
```

**Texto:**
```
.notif-text:
  - Flex: 1
  - Font Size: 13px
  - Cor: #4b5563

.notif-time:
  - Font Size: 11px
  - Cor: #9ca3af
  - White Space: nowrap
```

---

### Agenda de Inventário

```
.agenda-item:
  - Display: flex + gap 14px
  - Padding: 10px 14px
  - Border Radius: 8px
  - Background: #f8fafc
  - Margin Bottom: 6px
  - Transition: all 0.2s
  - Cursor: pointer
  
  :hover:
    - Background: #f1f5f9

.agenda-date:
  - Largura: 44px
  - Text Align: center
  - Flex Shrink: 0
  
  .day:
    - Font Size: 18px
    - Font Weight: 700
    - Cor: #111827
    - Display: block
  
  .month:
    - Font Size: 10px
    - Text Transform: uppercase
    - Cor: #9ca3af
    - Letter Spacing: 0.5px

.agenda-title:
  - Font Size: 13px
  - Font Weight: 600
  - Cor: #111827

.agenda-desc:
  - Font Size: 11px
  - Cor: #9ca3af
```

---

## 📱 Responsividade

### Breakpoints

```css
@media (max-width: 1200px) {
  /* Tablets grandes */
  .charts-grid { grid-template-columns: 1fr; }
  .two-col-grid { grid-template-columns: 1fr; }
}

@media (max-width: 768px) {
  /* Tablets e móvel */
  .sidebar {
    width: 0;
    overflow: hidden;
  }
  
  .sidebar.open {
    width: 260px;
    position: fixed;
    left: 0;
    top: 0;
    height: 100vh;
    box-shadow: 4px 0 30px rgba(0, 0, 0, 0.5);
    z-index: 200;
  }
  
  .navbar {
    padding: 10px 16px;
  }
  
  .search-bar {
    width: 160px;
  }
  
  .dashboard-content,
  .page-container {
    padding: 16px;
  }
  
  .kpi-grid {
    grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
    gap: 10px;
  }
  
  .kpi-card .kpi-value {
    font-size: 22px;
  }
}
```

---

## ✨ Animações e Transições

### Keyframes Principais

```css
@keyframes loginFadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
```

### Transições Padrão

- **Menu Items:** `transition: all 0.2s ease;`
- **Hover Buttons:** `transition: all 0.2s;`
- **Card Hover:** `transition: all 0.3s ease;`
- **Search Focus:** `transition: all 0.3s;`
- **Form Focus:** `transition: all 0.2s;`

### Efeitos de Hover Comuns

| Elemento | Hover Effect |
|----------|--------------|
| Buttons | `translateY(-1px)` + Shadow aumenta |
| Cards | `translateY(-2px)` + Shadow aumenta |
| Menu Items | Background color change + Border color |
| Links | Color change + Border bottom |
| Icons | Scale up (implícito via shadow) |

---

## 🔐 Permissões por Perfil

O sistema usa 4 perfis de utilizador:
1. **Administrador** - Acesso completo
2. **Gestor** - Acesso a gestão + monitoramento
3. **Operador** - Acesso a operações básicas
4. **Técnico** - Acesso restrito a técnico

Cada menu item tem uma array de `roles` que definem quem pode acessá-lo.

---

## 📊 Estrutura de Dados Esperada

### Exemplo de KPI
```javascript
{
  key: 'totalProdutos',
  labelKey: 'produtos',
  value: 150,
  icon: 'fas fa-box',
  color: 'blue'
}
```

### Exemplo de Menu Item
```javascript
{
  icon: 'fas fa-box',
  labelKey: 'produtos',
  key: 'produtos',
  badge: null,
  roles: ['Administrador', 'Gestor', 'Operador']
}
```

### Exemplo de Produto
```javascript
{
  id: 1,
  nome: "Produto Teste",
  codigo: "P001",
  categoria: "Eletrônicos",
  quantidade: 100,
  sku: "SKU001",
  minStock: 10,
  descricao: "Descrição do produto",
  preco: 99.99,
  unidade: "Unidade"
}
```

---

## 🎯 Resumo para Implementação em Java

### Requisitos de UI para Java Swing/JavaFX:

1. **Cores:** Use as cores definidas na paleta CSS
2. **Tipografia:** Use `Inter` ou similar sans-serif
3. **Componentes:**
   - Dark Sidebar (260px fixed)
   - Light Navbar (60px height)
   - Main content area with scrolling
   - KPI cards grid (responsive)
   - Charts (use jfreechart ou similar)
   - Tables (JTable styled)
   - Forms with validation
   
4. **Interações:**
   - Menu expand/collapse em móvel
   - Tooltips em ícones
   - Hover effects em cards e botões
   - Transições suaves
   
5. **Layouting:**
   - BorderLayout (sidebar + main)
   - GridLayout (KPI cards)
   - BoxLayout (navbar)
   
6. **Ícones:**
   - FontAwesome icons (se disponível em Java)
   - Material Design Icons alternativa
   
7. **Temas:**
   - Light theme para main content
   - Dark theme para sidebar
   - Consistent colors across app

---

## 📝 Notas Importantes

- **DPI Awareness:** Considerar scaling em displays de alta resolução
- **Font Rendering:** Usar antialiasing para suavizar fontes
- **Shadow Effects:** Implementar usando proper graphics context
- **Animations:** Usar timers/tasks para smooth transitions
- **Responsive:** Não obrigatório em desktop, mas facilita redimensionamento
- **Storage:** Usar localStorage para persistir preferências (equiv: preferences/config files em Java)
- **Internacionalização:** Sistema suporta PT/EN (usar ResourceBundle em Java)

---

**Fim da Especificação**
