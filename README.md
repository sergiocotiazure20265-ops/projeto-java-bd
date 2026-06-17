# Projeto Java - Cadastro de Produtos (v25)

Descrição
--------
Projeto Java (JDK 25) com Maven para cadastro de produtos em um banco de dados MySQL. O projeto usa JDBC para realizar a conexão e operações (CRUD) no banco. O MySQL é executado em um container Docker (local).

O domínio Produto:
- id (INT, AUTO_INCREMENT, PK)
- nome (VARCHAR)
- preco (DECIMAL)
- quantidade (INT)

Tecnologias
-----------
- Java 25 (JDK 25)
- Maven
- MySQL (rodando em Docker)
- JDBC (mysql-connector-java)

Pré-requisitos
--------------
- JDK 25 instalado
- Maven instalado
- Docker (para subir o container MySQL)
- Variáveis de ambiente ou arquivo de configuração com credenciais do banco

Configuração do MySQL (Docker)
------------------------------
Exemplo rápido com `docker run`:

```bash
docker run --name mysql-projeto \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=produtos_db \
  -e MYSQL_USER=appuser \
  -e MYSQL_PASSWORD=apppass \
  -p 3306:3306 \
  -d mysql:8.1
```

Ou um exemplo de `docker-compose.yml` mínimo:

```yaml
version: "3.8"
services:
  db:
    image: mysql:8.1
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: produtos_db
      MYSQL_USER: appuser
      MYSQL_PASSWORD: apppass
    ports:
      - "3306:3306"
    volumes:
      - db_data:/var/lib/mysql

volumes:
  db_data:
```

String JDBC (exemplo)
---------------------
Formato comum:

```
jdbc:mysql://localhost:3306/produtos_db?useSSL=false&serverTimezone=UTC
```

Usuário/senha: conforme configurado no container (ex.: appuser / apppass)

Criação da tabela (exemplo SQL)
-------------------------------
Execute no banco `produtos_db`:

```sql
CREATE TABLE produtos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  preco DECIMAL(10,2) NOT NULL,
  quantidade INT NOT NULL
);
```

Dependência Maven (mysql-connector)
----------------------------------
Adicione ao `pom.xml`:

```xml
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <version>8.1.0</version> <!-- ajuste para a versão desejada -->
</dependency>
```

Configuração do projeto
-----------------------
- Crie um arquivo de configuração (por exemplo, `application.properties`, `config.properties` ou variáveis de ambiente) contendo:
  - JDBC_URL (ex.: jdbc:mysql://localhost:3306/produtos_db?useSSL=false&serverTimezone=UTC)
  - DB_USER (ex.: appuser)
  - DB_PASS (ex.: apppass)

Exemplo simples (config.properties):

```
jdbc.url=jdbc:mysql://localhost:3306/produtos_db?useSSL=false&serverTimezone=UTC
jdbc.user=appuser
jdbc.password=apppass
```

Exemplo de uso de JDBC (snippet)
-------------------------------
Exemplo genérico onde `conn` é a Connection:

```java
String sqlInsert = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";
try (Connection conn = DriverManager.getConnection(url, user, pass);
     PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
    ps.setString(1, "Caneta");
    ps.setBigDecimal(2, new BigDecimal("2.50"));
    ps.setInt(3, 100);
    ps.executeUpdate();
    try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            int id = rs.getInt(1);
            System.out.println("Produto inserido com id: " + id);
        }
    }
}
```

Build e execução
----------------
1. Build com Maven:

```bash
mvn clean package
```

2. Executar o JAR gerado (ajuste o nome do artefato conforme o `pom.xml`):

```bash
java -jar target/seu-artifact-id-1.0-SNAPSHOT.jar
```

Ou executar a partir da IDE (defina a classe `main` e o classpath).

Testando a aplicação
--------------------
- Verifique se o container MySQL está rodando e a tabela `produtos` existe.
- Configure corretamente `jdbc.url`, `jdbc.user` e `jdbc.password`.
- Execute a aplicação e realize operações de cadastro/listagem/atualização/remoção conforme implementado.

Boas práticas e notas
---------------------
- Nunca armazene senhas em texto puro em repositórios públicos. Use variáveis de ambiente ou vaults em produção.
- Ajuste a versão do conector MySQL conforme compatibilidade com a versão do MySQL do container.
- Considere usar pooling (HikariCP) em aplicações que vão para produção para melhorar performance e gerenciamento de conexões.
- Trate corretamente SQLExceptions e garanta fechamento de recursos (try-with-resources).

Resolução de problemas comuns
-----------------------------
- Erro de conexão: verifique se o container MySQL está ativo, se a porta (3306) está exposta e se as credenciais/JDBC URL estão corretas.
- Driver não encontrado: confirme se `mysql-connector-j` está no `pom.xml` e foi baixado (mvn dependency:resolve).
- Timezone/SSL: adicione parâmetros como `?useSSL=false&serverTimezone=UTC` na URL JDBC para evitar warnings/erros.

Contribuição
------------
Contribuições são bem-vindas. Abra issues para bugs ou funcionalidades e envie pull requests com descrições claras das mudanças.

Licença
-------
Escolha e adicione a licença desejada (ex.: MIT) — atualmente sem licença especificada.

Contato
-------
Para dúvidas, abra uma issue no repositório.
