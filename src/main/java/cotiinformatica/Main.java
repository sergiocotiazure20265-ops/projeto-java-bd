package cotiinformatica;

import cotiinformatica.services.ProdutoService;

public class Main {

    static void main() {

        System.out.println("\nCadastro de produtos:\n");

        //Criando um objeto da classe de serviço de produto
        var produtoService = new ProdutoService();

        //Executando o método para cadastro de produto
        produtoService.cadastrarProduto();
    }
}
