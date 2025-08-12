# Sistema de Gestão de Veículos e Vendas (SGVV)
- **Instituição:** IFBA - Instituto Federal da Bahia
- **Curso:** Análise e Desenvolvimento de Sistemas (ADS)
- **Disciplina:** Engenharia de Software II
- **Projeto:** Criar projeto simulando caso real com implementação de ISO
- **Professor:** Caio Valverde
- **Semestre:** 5
- **Ano:** 2025.1

## Integrantes do Projeto

<table>
  <tr>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/80362674?v=4" width="100px;" alt="Foto do Integrante Janderson"/><br />
      <sub><b><a href="https://github.com/JandersonMota">Janderson Mota</a></b></sub>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/114780494?v=4" width="100px;" alt="Foto da Integrante Marcelo"/><br />
      <sub><b><a href="https://github.com/marceloteclas">Marcelo Jesus</a></b></sub>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/129338943?v=4" width="100px;" alt="Foto da Integrante Ronaldo"/><br />
      <sub><b><a href="https://github.com/Ronaldo-Correia">Ronaldo Correia</a></b></sub>
    </td>
  </tr>
</table>

## 1. Introdução
### 1.1. Propósito do Documento
Este documento tem como objetivo apresentar os requisitos do sistema SGVV – Sistema de Gestão de Veículos e Vendas, desenvolvido no contexto da disciplina de Engenharia de Software. O documento detalha as funcionalidades esperadas, requisitos técnicos, regras de negócio, diagrama de classes e demais aspectos relevantes para o desenvolvimento e a compreensão do sistema.

### 1.2. Escopo do Sistema
O SGVV é um sistema voltado para lojas de veículos que necessitam controlar o cadastro de veículos, clientes, profissionais (como vendedores) e o processo de vendas. O sistema será desenvolvido para ser utilizado por usuários administrativos da loja, permitindo o gerenciamento completo das informações e o registro de vendas.

### 1.3. Definições, Acrônimos e Abreviações
Termo	Definição
SGVV	Sistema de Gestão de Veículos e Vendas
CRUD	Create, Read, Update, Delete (Operações básicas de dados)
CPF	Cadastro de Pessoa Física

## 2. Objetivo do Sistema
O sistema visa registrar as vendas de veículos, cadastro de profissionais e clientes, bem como vendas em lojas de automóveis, proporcionando mais controle, organização e eficiência nos processos internos, substituindo planilhas manuais ou registros físicos.

## 3. Características Gerais do Sistema
- Interface amigável e responsiva para uso em desktop e dispositivos móveis.
- Validação de dados obrigatórios.
- Garantia de unicidade em campos críticos como CPF e placa do veículo.
- Histórico de vendas com filtros por cliente ou profissional.
- Relacionamentos entre entidades para manter integridade dos dados.
- Mensagens de sucesso e erro com feedback ao usuário.

## Ferramentas
- Java: JDK 21.0.6 LTS
- Maven: 3.9.9
test
## Estrutura do Projeto

```
/IFBA--SGVV                                                     # Diretório raiz do projeto.
├── src                                                         # Pasta de arquivos fonte.
│   ├── main                                                    # Código fonte principal e recursos.
│   │   ├── java                                                # Código-fonte Java da aplicação.
│   │   │   └── com                                             # Pacote de domínio de nível superior.
│   │   │       └── sgvv                                        # Subpacote da organização.
│   │   │           └── ifba                                    # Pacote raiz da aplicação.
│   │   │               ├── controller                          # Camada de Apresentação (Controller)
│   │   │               │   └── ClienteController.java
│   │   │               │   └── VeiculoController.java
│   │   │               │   └── EnderecoController.java
│   │   │               ├── dto                                 # Objetos de Transferência de Dados
│   │   │               │   └── ClienteDTO.java
│   │   │               │   └── VeiculoDTO.java
│   │   │               │   └── EnderecoDTO.java
│   │   │               ├── model                               # Camada de Domínio / Entidades
│   │   │               │   └── Veiculo.java
│   │   │               │   └── Endereco.java
│   │   │               │   └── Cliente.java
│   │   │               │   └── Usuario.java
│   │   │               ├── repository                          # Camada de Persistência de Dados
│   │   │               │   └── ClienteRepository.java
│   │   │               │   └── VeiculoRepository.java
│   │   │               │   └── EnderecoRepository.java
│   │   │               │   └── ...
│   │   │               ├── service                             # Interfaces de Lógica de Negócio
│   │   │               |   └── ClienteService.java
│   │   │               |   └── VeiculoService.java
│   │   │               |   └── EnderecoService.java
│   │   │               |   └── impl                            # Implementações da Lógica de Negócio
│   │   │               |       └── VeiculoServiceImpl.java
│   │   │               |       └── EnderecoServiceImpl.java
│   │   │               ├── config                              # Configurações globais da aplicação (ex: segurança, filtros).
│   │   │               ├── exception                           # Classes de exceção personalizadas.
│   │   │               ├── mapping                             # Mapeadores para converter entre DTOs e Entidades.
│   │   │               |   └── ClienteMapper.java
│   │   │               └── IfbaApplication.java                # Classe principal do Spring Boot, ponto de entrada da aplicação.
│   │   └── resources
│   │       ├── application.properties                          # Configurações do Spring Boot
│   │       └── static                                          # Arquivos estáticos (HTML, CSS, JS) - opcional
│   │           └── index.html
│   └── test                                                    # Pasta para arquivos de teste.
│       └── java                                                # Código fonte para testes.
│           └── com
│               └── sgvv
│                   └── ifba
│                       └── IfbaApplicationTests.java           # Testes unitários e de integração
└── pom.xml                                                     # Arquivo de configuração do Maven, gerencia dependências e build.
```

## Configurar o SQL Server

``` application.yml
spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=nome_do_seu_banco
    username: seu_usuario
    password: sua_senha
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate.dialect: org.hibernate.dialect.SQLServer2012Dialect
```

## Banco de Dados
A tabela *Logs_eventos* é opcional e está construida na documentação para caso resolva implementa-lá.

``` SQL SERVER
CREATE DATABASE bd_sgvv;

USE bd_sgvv;

CREATE TABLE Endereco (
  id_endereco INT IDENTITY(1,1) PRIMARY KEY,
  estado CHAR(2) NOT NULL,
  cidade VARCHAR(29) NOT NULL,
  bairro VARCHAR(255) NOT NULL,
  logradouro VARCHAR(255) NOT NULL,
  identificacao_residencial VARCHAR(30) NOT NULL
);
GO -- Comando para separar lotes de comandos no SQL Server


CREATE TABLE Carro (
  id_carro INT IDENTITY(1,1) PRIMARY KEY,
  renavam CHAR(11) UNIQUE NOT NULL,
  placa CHAR(7) UNIQUE NOT NULL,
  marca VARCHAR(20) NOT NULL,
  modelo VARCHAR(80) NOT NULL,
  ano SMALLINT NOT NULL,
  chassi CHAR(17) UNIQUE NOT NULL,
  combustivel VARCHAR(20) NOT NULL,
  potencia SMALLINT,
  cilindrada SMALLINT,
  ativo BIT NOT NULL DEFAULT 1
);
GO


CREATE TABLE Cliente (
  id_cliente INT IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  cpf CHAR(11) UNIQUE NOT NULL,
  cnh CHAR(11) UNIQUE NOT NULL,
  id_endereco INT,
  id_carro INT,
  FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco),
  FOREIGN KEY (id_carro) REFERENCES Carro(id_carro)
);
GO


CREATE TABLE Nivel_acesso (
  id_acesso INT IDENTITY(1,1) PRIMARY KEY,
  nivel VARCHAR(14) NOT NULL
);


CREATE TABLE Cargo (
  id_cargo INT IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(37) UNIQUE NOT NULL
);


CREATE TABLE Usuario (
  id_usuario INT IDENTITY(1,1) PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  login VARCHAR(20) UNIQUE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  ativo BIT NOT NULL DEFAULT 1,
  id_endereco INT,
  id_carro INT,
  id_acesso INT NOT NULL,
  id_cargo INT NOT NULL,
  FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco),
  FOREIGN KEY (id_carro) REFERENCES Carro(id_carro),
  FOREIGN KEY (id_acesso) REFERENCES Nivel_acesso(id_acesso),
  FOREIGN KEY (id_cargo) REFERENCES Cargo(id_cargo)
);
GO

-- OPCIONAL
CREATE TABLE Logs_eventos(
  id_log BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID único para cada evento de log, auto-incrementável
  data_hora_evento DATETIME2(7) NOT NULL DEFAULT GETDATE(), -- Carimbo de data/hora exato do evento, com alta precisão
  tipo_evento VARCHAR(50) NOT NULL, -- Tipo de evento (ex: 'INSERT', 'UPDATE', 'DELETE', 'LOGIN', 'ERRO', 'CONSULTA')
  tabela_afetada VARCHAR(128) NOT NULL, -- Nome da tabela envolvida na operação (ex: 'Cliente', 'Carro'). Nulo se não aplicável.
  id_registro_afetado INT NOT NULL,    -- ID do registro afetado na tabela (ex: id_cliente, id_carro). Nulo se não aplicável.
  usuario_responsavel VARCHAR(80) NOT NULL, -- Usuário que realizou a ação (pode ser o login da tabela 'Usuario' ou 'SUSER_SNAME()')
  detalhes_evento NVARCHAR(MAX) -- Descrição detalhada do evento (ex: 'Cliente ID 10 atualizado: Nome de "Antônio" para "Antônio Silva"'). NVARCHAR(MAX) para texto longo.
);
```

## Dependências utilizadas no **Maven**
Adicione a dependência no pom.xml do driver do SQL Server 2019.

``` xml
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <version>12.6.1.jre11</version>
</dependency>
```