# 🧬 Projeto Aalife

## 🚀 Visão Geral
**Aalife** é um serviço **RESTful** desenvolvido em **Java 21** e **Spring Boot 3.5.6**, projetado para auxiliar no **controle de hábitos, treinos e produtividade**, oferecendo também recursos para **profissionais de saúde** (nutricionistas, psicólogos, personal trainers) interagirem com usuários comuns.

A aplicação possui **arquitetura em camadas**, autenticação segura com **JWT**, e suporte a diferentes tipos de usuários:  
- 👤 **Usuário comum** — cria hábitos, treinos e utiliza o sistema para monitoramento pessoal.  
- 🧑‍⚕️ **Profissional** — atua como psicólogo, nutricionista ou personal trainer, podendo também possuir um perfil de usuário.

---

## 🧱 Arquitetura do Projeto
**Padrão:** Camadas organizadas por responsabilidade.
---

## 🔐 Autenticação e Segurança
- **Spring Security 6** para controle de acesso  
- **JWT (JSON Web Token)** para autenticação stateless  
- **BCryptPasswordEncoder** para criptografia de senhas  
- **CORS configurado globalmente** para permitir consumo via frontend  
- Perfis de usuário com roles:  
  - `ROLE_USER`  
  - `ROLE_PROFISSIONAL`  
  - `ROLE_ADMIN`

**Endpoints principais:**  
- `/auth/login`  
- `/auth/register`  
- `/auth/refresh`

**Documentação oficial:**  
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)  
- [JWT.io Introduction](https://jwt.io/introduction)

---

## 👥 Módulos Principais

### 🧑 Usuário
Representa o perfil pessoal que utiliza o sistema.

**Campos principais:**
- `id`, `nome`, `email`, `senha`  
- `altura`, `peso`, `idade`  
- `medidas corporais`

**Relacionamentos:**
- Um usuário pode ter múltiplos **hábitos** e **treinos**

---

### 🧑‍⚕️ Profissional
Representa o profissional da área de saúde que também pode atuar como usuário.

**Campos principais:**
- `id`, `nome`, `email`, `senha`  
- `documento` (ex: CRN, CREF, CRP)  
- `especialidade` (`NUTRICIONISTA`, `PERSONAL`, `PSICOLOGO`)

**Relacionamentos:**
- Pode estar vinculado a usuários para acompanhamento  
- Pode criar treinos ou planos de hábitos personalizados

---

### 🧩 Hábitos
Controle de hábitos e rotinas pessoais.

**Campos principais:**
- `id`, `nome`, `descricao`, `frequencia`, `status`  
- Registro de progresso e histórico de cumprimento

---

### ⏱️ Pomodoro
Ferramenta integrada para estudos e foco.

**Campos principais:**
- `id`, `tempoFoco`, `tempoPausa`, `ciclos`  

---

### 🏋️ Treinos
Gestão de treinos físicos e planos personalizados.

**Campos principais:**
- `id`, `titulo`, `tipo`, `duracao`, `observacao`  
- Registro de progresso e histórico de execução

**Relacionamentos:**
- Criado por **profissionais** e usado por **usuários**  

---

## ⚙️ Configuração e Infraestrutura
- **Spring Boot** para injeção de dependências e configuração simplificada  
- **PostgreSQL** como banco de dados principal  
- **Spring Data JPA** para persistência  
- **application.yml** para configuração de datasource, perfis (`dev`, `prod`) e segurança  
- **Logs:** SLF4J / Logback  
- **Documentação de API:** SpringDoc OpenAPI (Swagger UI)  

---

## 🧠 Tecnologias e Dependências Principais

| Categoria       | Tecnologias |
|-----------------|-------------|
| Linguagem       | Java 21 |
| Framework       | Spring Boot 3.5.6 |
| Segurança       | Spring Security, JWT, BCrypt |
| Persistência    | Spring Data JPA, PostgreSQL |
| Documentação    | SpringDoc OpenAPI |
| Logs            | SLF4J / Logback |
| Build           | Maven |
| Configuração    | YAML Profiles |

---

## 🧩 Estrutura Modular (Pacotes principais)
- `usuario`  
- `profissional`  
- `habito`  
- `treino`  
- `pomodoro`  

Cada módulo contém suas próprias **entidades, DTOs, serviços e controladores**, mantendo o código isolado e escalável.
