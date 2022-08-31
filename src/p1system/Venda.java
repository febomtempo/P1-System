/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1system;


import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Notebook
 */
public class Venda {
    private Produto produto;
    private int quantidade;
    private LocalDateTime dataVenda;

    public Venda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        produto.diminuiEstoque(quantidade); //Diminuir o estoque a cada venda efetuada.
        this.dataVenda = LocalDateTime.now(); // Salvar a data a cada venda efetuada.
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

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    //Percorre a Arraylist de vendas para somar o total de todas as vendas.
    public static String totalVenda(ArrayList<Venda> vendas){
        DecimalFormat formatadorValor = new DecimalFormat();
        formatadorValor.setMaximumFractionDigits(2);
        formatadorValor.setMinimumFractionDigits(2);
        double totalVenda = 0;
        for(Venda v: vendas){
            totalVenda += v.getProduto().getPreco()*v.getQuantidade();
        }   
        
        return "Valor Total: R$ " + formatadorValor.format(totalVenda);
    }
   
    
    
    
    //Imprimir dados de venda.
       @Override
    public String toString() {
        DecimalFormat formatadorValor = new DecimalFormat();
        formatadorValor.setMaximumFractionDigits(2);
        formatadorValor.setMinimumFractionDigits(2);
        double valorUnitario = (produto.getPreco());
        double valorTotal = (quantidade*produto.getPreco());
        String retorno = "Produto: " + produto.getNome() 
                + " // Valor Unitário: R$ " + formatadorValor.format(valorUnitario) + " // Quantidade: "  
                + quantidade + " // Venda Total: R$ " + formatadorValor.format(valorTotal) + "\n";
        return retorno;
    }
   
    
}
