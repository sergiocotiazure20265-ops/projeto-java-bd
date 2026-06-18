package cotiinformatica.entities;

public class Produto {

    //Atributos
    public Integer id;
    public String nome;
    public Double preco;
    public Integer quantidade;

    //Métodos
    public void imprimirDados() {
        System.out.println("\nDados do produto:");
        System.out.println("\tId.........: " + id);
        System.out.println("\tNome.......: " + nome);
        System.out.println("\tPreço......: " + preco);
        System.out.println("\tQuantidade.: " + quantidade);
        System.out.println("\tTotal......: " + (preco * quantidade));
    }
}
