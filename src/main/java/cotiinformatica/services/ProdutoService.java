package cotiinformatica.services;

import cotiinformatica.entities.Produto;
import cotiinformatica.repositories.ProdutoRepository;

import java.util.Scanner;

public class ProdutoService {

    //Método
    public void cadastrarProduto() {

        //Criando um objeto da classe Produto
        var produto = new Produto();

        //Criando um objeto da classe Scanner
        var scanner = new Scanner(System.in);

        System.out.println("\nPreencha os dados do produto:\n");

        System.out.print("Informe o nome.............: ");
        produto.nome = scanner.nextLine();

        System.out.print("Informe o preço............: ");
        produto.preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Informe a quantidade.......: ");
        produto.quantidade = Integer.parseInt(scanner.nextLine());

        //Imprimindo os dados do produto
        produto.imprimirDados();

        System.out.print("\nDeseja salvar este produto no banco de dados? (S,N): ");
        var opcao = scanner.nextLine();

        if(opcao.equalsIgnoreCase("S")) {

            //Criando um objeto da classe ProdutoRepository
            var produtoRepository = new ProdutoRepository();

            //Inserindo o produto no banco de dados
            produtoRepository.inserir(produto);
        }
        else {
            System.out.println("\nOperação cancelada pelo usuário.");
        }
    }
}
