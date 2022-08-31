/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1system;

import java.util.ArrayList;

/**
 *
 * @author Notebook
 */
public class Produto {
    private static int cont = 1;
    private int id;
    private String nome;
    private double preco;
    private Fornecedor fornecedor;
    private int estoque;

    public Produto(String nome, double preco, Fornecedor fornecedor) {
        this.id = cont++;
        this.nome = nome;
        this.preco = preco;
        this.fornecedor = fornecedor;
        this.estoque = 0;
    }
    
    //Aumenta o estoque, usado na classe Compra.
    public void aumentaEstoque(int quantidade){
        this.estoque += quantidade;
    }
    // Diminui o estoque, usado na classe Venda.
    public void diminuiEstoque(int quantidade){
        this.estoque -= quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    //Percorre a ArrayList dos produtos para comparar a ID, e em caso de igualdade retornar o produto correspondente.
    public static Produto buscaProdutoID(ArrayList<Produto> produtos, int id){
        Produto retorno = null;
        for(Produto p: produtos){
            if(p.getId() == id){
                retorno = p;
            }
        }
        return retorno;
    }
    
    // Percorre a ArrayList de produtos para comparar o nome, e em caso de igualdade retornar o produto correspondente.
    public static Produto buscaProdutoNome(ArrayList<Produto> produtos, String nome){
        Produto retorno = null;
            for(Produto p: produtos){
                if(p.getNome().equals(nome)){
                    retorno = p;
                }
            }
        return retorno;
    }
    
    //Percorre a ArrayList de produtos para verificar a existência do nome digitado, retornando true ou false.
    public static boolean verificaProdutoNome(ArrayList<Produto> produtos, String nome){
        boolean nomeRepetido = false;
        for(Produto p: produtos){
            if(p.getNome().equals(nome)){
                nomeRepetido = true;
            }
        }
        return nomeRepetido;
    }
    
    // Percorre a ArrayList de produto para verificar se o nome do Produto digitado possui o Fornecedor digitado anteriormente,
    // retornando true ou false.
    public static boolean verificaProdutoFornecedor(ArrayList<Produto> produtos, String nomeProduto, String nomeFornecedor){
        boolean produtoMesmoFornecedor = false;
        for(Produto p: produtos){
            if(p.getNome().equals(nomeProduto) && p.getFornecedor().getNomeFantasia().equals(nomeFornecedor)){
                produtoMesmoFornecedor = true;
            }
        }
        return produtoMesmoFornecedor;
    }
    
    //Verifica se o que foi digitado é a ID ou o Nome do Produto.
    public static boolean verificaIdOuNome(String idNomeProduto){
        boolean idProduto = false;
        if(idNomeProduto.substring(0, 1).matches("[0-9]")){
                idProduto = true;
            }
        return idProduto;       
    }
    
    //Percorre a ArrayList de produtos para verificar se a quantidade digitada pode ser vendida.
    public static boolean verificaEstoque(ArrayList<Produto> produtos, int quantidade){
        boolean possuiEstoque = false;
        for(Produto p: produtos){
            if(quantidade<=p.getEstoque()){
                possuiEstoque = true;
            }
        }
        return possuiEstoque;
    }
    //Percorre a ArrayList de produtos para verificar se existe um produto com a letra ou sequência de letras digitadas.
    //Após verificar, retorna os produtos que se aplicam à verificação.
    public static String parteDoNome(ArrayList<Produto> produtos, String nome){
        String retorno = "\n";
        for(Produto p: produtos){
            if(p.getNome().toLowerCase().contains(nome.toLowerCase())){
                retorno += "ID: " + p.getId() + " - \t Produto: " + p.getNome() + "\n";
            }
        }
        return retorno;
    }
    
    //Percorre a ArrayList de produtos para verificar a existência da ID digitada.
    public static boolean verificaProdutoID(ArrayList<Produto> produtos, int id){
        boolean idExiste = false;
        for(Produto p: produtos){
            if(p.getId() == id){
                idExiste = true;
            }
        }
        return idExiste;
    }
    
}