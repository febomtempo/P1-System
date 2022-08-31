/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1system;

import java.text.DecimalFormat;

/**
 *
 * @author Notebook
 */
public class Compra {
    private Fornecedor fornecedor;
    private Produto produto;
    private int quantidade;

    public Compra(Fornecedor fornecedor, Produto produto, int quantidade) {
        this.fornecedor = fornecedor;
        this.produto = produto;
        this.quantidade = quantidade;
        produto.aumentaEstoque(quantidade); //Aumenta o estoque após toda compra
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    /* Pega a quantidade e o valor do produto para obter o total*/
    
    @Override
    public String toString() {
        DecimalFormat formatador = new DecimalFormat();
        formatador.setMaximumFractionDigits(2);
        formatador.setMinimumFractionDigits(2);
        double valor = (quantidade*produto.getPreco());
        String retorno = "Total da Compra: R$ " + formatador.format(valor);
        return retorno;
    }
    
}
