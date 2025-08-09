# CRM – Projeto de Curso (4 Semanas)

## 📖 Definição do Produto

O **CRM** (*Customer Relationship Management*, ou Gestão de Relacionamento com o Cliente) é um conjunto de práticas e tecnologias voltadas ao relacionamento da empresa com prospects e clientes, visando aumentar as vendas e melhorar a gestão comercial.

O sistema desenvolvido neste curso permite:

- Reunir dados de clientes.

- Gerenciar histórico de negociações.

- Acompanhar tarefas do time comercial.

- Integrar e otimizar processos de vendas.

---

## 🎯 Requisitos Funcionais

- Usuário poderá se cadastrar no sistema.

- Usuário poderá realizar login.

- Usuário poderá cadastrar um *lead* com:
  
  - Email
  
  - Nome
  
  - Telefone
  
  - Descrição da venda

- Usuário poderá consultar todos os*leads*.

- Usuário poderá consultar os seus *lead*.

---

## ⚙️ Requisitos Não Funcionais

- Interface intuitiva.

- Micro-interações para melhorar a experiência.

- Tempo de resposta ≤ 100ms.

---

## 📌 Endpoints da API

Os endpoints a seguir foram extraídos do arquivo **Requisições.json**.

### 👤 Usuário

| Método   | Rota                   | Descrição           |
| -------- | ---------------------- | ------------------- |
| `GET`    | `/user`                | Consultar usuários. |
| `POST`   | `/user/signup`         | Cadastrar usuário.  |
| `POST`   | `/user/login`          | Login do usuário.   |
| `DELETE` | `/user/remove-account` | Deletar conta.      |

### 🏢 Empresa

| Método   | Rota               | Descrição                    |
| -------- | ------------------ | ---------------------------- |
| `GET`    | `/enterprise`      | Consultar todas as empresas. |
| `POST`   | `/enterprise`      | Criar empresa.               |
| `PATCH`  | `/enterprise/{id}` | Atualizar empresa.           |
| `DELETE` | `/enterprise/{id}` | Deletar empresa.             |

### 📋 Leads

| Método | Rota          | Descrição                                |
| ------ | ------------- | ---------------------------------------- |
| `GET`  | `/leads`      | Consultar todos os leads.                |
| `GET`  | `/leads/user` | Consulta todos os leads do usuário atual |
| `POST` | `/leads`      | Criar um lead.                           |

---

## 🔑 Autenticação JWT no Swagger

A API utiliza **JWT Bearer Token** para autenticação.

### Passos para usar o JWT no Swagger

1. **Realizar login**
   
   - Envie uma requisição `POST` para `/user/login` com:
     
     ```json
     {
      "email": "seuemail@teste.com",
      "password": "suasenha"
     }
     ```
   
   - A resposta conterá um token JWT no campo `token`.

2. **Configurar no Swagger**
   
   - No Swagger UI, clique no botão **Authorize** (ícone de cadeado).
   
   - No campo **Value**, insira:
     
     ```
     SEU_TOKEN_AQUI
     ```
   
   - Clique em **Authorize** e depois em **Close**.

3. **Fazer requisições autenticadas**
   
   - Todos os endpoints protegidos serão acessíveis com o token configurado. Exceto as de login e signup

💡 **Dica:** Tokens têm tempo de expiração. Caso expire, faça login novamente.

---

---

## 📚 Referências

- [CRM: o que é, vantagens, como usar e tipos – RD Station](https://www.rdstation.com/blog/vendas/o-que-e-crm)

---
