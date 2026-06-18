package cotiinformatica.services;

import cotiinformatica.entities.Produto;

import java.util.Scanner;

public class ProdutoService {

    //Método
    public void cadastrarProduto() {

        //Criando um objeto da classe Produto
        var produto = new Produto();

        //Criando um objeto da classe Scanner
        var scanner = new Scanner(System.in);

        System.out.println("\nPreencha os dados do produto:\n");

        System.out.print("Informe o id do produto....: ");
        produto.id = Integer.parseInt(scanner.nextLine());

        System.out.print("Informe o nome.............: ");
        produto.nome = scanner.nextLine();

        System.out.print("Informe o preço............: ");
        produto.preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Informe a quantidade.......: ");
        produto.quantidade = Integer.parseInt(scanner.nextLine());

        //Imprimindo os dados do produto
        produto.imprimirDados();
    }
}
