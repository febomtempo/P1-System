/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1system;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Notebook
 */

//Percorre a ArrayList de produtos para verificar e imprimir quais deles possuem estoque menor ou igual ao digitado.
public class Relatorio {
     public static String relatorioNivelCritico(ArrayList <Produto> produtos, int nivelCritico){
        String retorno = "Nível Crítico - Produtos com Estoque menor ou igual a " + nivelCritico + "\n";
        for(Produto p: produtos){
            if(nivelCritico>=p.getEstoque()){
                retorno += "\n";
                retorno += "Produto: " + p.getNome() + " - Estoque: " + p.getEstoque();
                retorno += "\n";
            }
        }
        return retorno;
    }
     //Percorre o ArrayList de vendas no mês e ano requisitado para imprimir as informações de venda.
     public static String relatorioDeVendas(ArrayList<Venda> vendas, int mes, int ano){
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss");
        DecimalFormat formatadorValor = new DecimalFormat();
        formatadorValor.setMaximumFractionDigits(2);
        formatadorValor.setMinimumFractionDigits(2);
        String retorno = "Relatório de Vendas - Vendas do mês " + mes + " do ano de " + ano;
        retorno+= "\n";
        int qtdTotal = 0;
        int quantidade = 0;
        double valorTotal = 0;
        double totalVenda = 0;
        for(Venda v: vendas){
            if(v.getDataVenda().getMonthValue() == mes && v.getDataVenda().getYear() == ano){
                 valorTotal = v.getProduto().getPreco() * v.getQuantidade();
                 totalVenda += v.getProduto().getPreco() * v.getQuantidade();
                 retorno += "\n";
                 retorno += "Produto: " + v.getProduto().getNome() + " - Qtd: " + v.getQuantidade() 
                         + " - Valor Total: R$ " + formatadorValor.format(valorTotal)
                         + " - Data/Hora - " + formatadorData.format(v.getDataVenda());
                 retorno += "\n";
            }else{
                 retorno += "\nNão foram realizadas vendas no mês " + "do ano de " +ano;
            }
        }
        return retorno + "\nVenda Total: R$ " + formatadorValor.format(totalVenda);
    }
     
     
     //Lista o Código de cada mês do ano.
     public static String meses(){
         String retorno = "\n1 - Janeiro";
         retorno += "\n2 - Fevereiro";
         retorno += "\n3 - Março";
         retorno += "\n4 - Abril";
         retorno += "\n5 - Maio";
         retorno += "\n6 - Junho";
         retorno += "\n7 - Julho";
         retorno += "\n8 - Agosto";
         retorno += "\n9 - Setembro";
         retorno += "\n10 - Outubro";
         retorno += "\n11 - Novembro";
         retorno += "\n12 - Dezembro";
         return retorno;
     }
}
