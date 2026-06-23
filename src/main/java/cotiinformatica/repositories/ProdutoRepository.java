package cotiinformatica.repositories;

import cotiinformatica.entities.Produto;

import java.sql.DriverManager;

public class ProdutoRepository {

    /*
        Método para receber um objeto do tipo 'Produto'
        e grava-lo na tabela do banco de dados
     */
    public void inserir(Produto produto) {
        try {

            //Definindo os parametros para conexão com o banco de dados
            var host = "jdbc:postgresql://localhost:5434/bd-produtos";
            var user = "coti";
            var pass = "coti";

            //Abrir conexão com o banco de dados
            var connection = DriverManager.getConnection(host, user, pass);

            //Escrever uma sentença SQL para inserir um produto no banco de dados
            var statement = connection.prepareStatement("insert into produtos(nome, preco, quantidade) values(?,?,?)");
            statement.setString(1, produto.nome);
            statement.setDouble(2, produto.preco);
            statement.setInt(3, produto.quantidade);
            statement.execute();

            //Fechar a conexão com o banco de dados
            connection.close();

            System.out.println("\nProduto cadastrado com sucesso.");
        }
        catch(Exception e) {
            System.out.println("\nErro ao inserir produto no banco de dados: " + e.getMessage());
        }
    }
}
