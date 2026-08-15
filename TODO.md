# TODO - Melhorias do Sistema MEPIT WMS

## ✅ Concluído - Correção de Login e Cadastro de Produtos
- `repostModel` deixou de ser singleton (`@Component` removido)
- 10 Services usam `new repostModel()`
- Frontend melhorado (`api.js`, `Login.js`)

## ✅ Concluído - Ajuste visual
- Ícone de Saídas virado para a esquerda (`fa-truck-arrow-left`)

## ✅ Concluído - Movimentos com Origem/Destino
- `movimentoModel.java`: adicionados campos `origem`, `destino`, `transportadora`
- `Entradas.js`: formulário com campo "Origem (de onde vem)" + Transportadora
- `Saidas.js`: formulário com campo "Destino (para onde vai)" + Transportadora
- Tabelas mostram Data/Hora, Equipamento, Local de Origem, Destino, Transportadora

## ✅ Concluído - Relatório de Movimentos
- Backend: `relatorioService.gerarRelatorioMovimentos()` + endpoint `GET /relatorios/movimentos`
- `Relatorios.js`: botão "Gerar Relatório" que mostra relatório detalhado com:
  - Data e hora
  - Equipamentos
  - Local de origem (de onde sai)
  - Destino (para onde vai)
  - Quantidade e transportadora
  - Botão "Imprimir"

## ✅ Concluído - Configuração de Idioma (PT/EN)
- `i18n.js`: sistema de traduções Português/Inglês com contexto React
- `index.js`: `I18nProvider` envolvendo a app
- `Configuracoes.js`: seletor de idioma (Português 🇲🇿 / English 🇬🇧)
- Relatórios usam o idioma selecionado para formatar datas

## 🚧 Em desenvolvimento - Módulo Clientes (CRUD completo)
- [x] Backend: `clienteModel.java`, `clienteRepository.java`, `clienteService.java`, `clienteControl.java`
- [x] Frontend API: `clientesApi` em `api.js`
- [x] Frontend: `Clientes.js` ligado à API (removidos dados fictícios)
- [x] Menu: "Clientes" adicionado à secção GESTÃO em `dashboard.js`
- [x] TODO.md atualizado
