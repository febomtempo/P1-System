/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1system;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Notebook
 */
public class P1System {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList <Venda> vendas = new ArrayList <>();
        ArrayList <Fornecedor> fornecedores = new ArrayList<>();
        ArrayList <Produto> produtos = new ArrayList<>();
        ArrayList <Compra> compras = new ArrayList<>();
        
        
        //Menu Principal
        String opMenu;
        boolean sair = false;
        String menu = "";
        menu+= "1 - Vendas\n";
        menu+= "2 - Cadastros\n";
        menu+= "3 - Compras\n";
        menu+= "4 - Relatórios\n";
        menu+= "5 - Sair\n";
        
        do{
            opMenu = JOptionPane.showInputDialog(menu);
            switch(opMenu){
                case "1":
                    vendas.contains(vendaProduto(produtos, vendas));
                    break;
               
                case "2":
                    String[] opCadastro = {"2.1 - Fornecedor","2.2 - Produto"};
                    int op = JOptionPane.showOptionDialog(null, "O que deseja Cadastrar?", "2 - Cadastros", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opCadastro, opCadastro[0]);
                    if(op==0){
                        fornecedores.add(cadastroFornecedor(fornecedores));
                    }else{
                        produtos.add(cadastroProduto(produtos, fornecedores));
                    }
                    break;
                    
                case "3":
                    compras.add(compraProduto(fornecedores, produtos));
                    break;
                    
                    case "4":
                    String[] opRelatorio = {"1 - Nível Crítico","2 - Relatório de Vendas"};
                    int op2 = JOptionPane.showOptionDialog(null, "Qual Relatório deseja acessar?", "4 - Relatórios", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opRelatorio, opRelatorio[0]);
                    if(op2==0){
                        int qtd = 0;
                        nivelCritico(produtos, qtd);
                    }else{
                        int mes = 0;
                        int ano = 0;
                        relatorioVenda(vendas, mes, ano);
                    }
                    break;
                    
                case "5":
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    sair = true;
            }
        }while(!sair);
        
       
    }
    
    //Cadastro do fornecedor, verificando se possui um cnpj repetido.
    public static Fornecedor cadastroFornecedor(ArrayList<Fornecedor> fornecedores){
        String cnpj = JOptionPane.showInputDialog("CNPJ: ");
        while(Fornecedor.verificaFornecedorCNPJ(fornecedores, cnpj) == true){
            cnpj = JOptionPane.showInputDialog("Esse CNPJ já existe, digite outro: ");
        }
        String razaoSocial = JOptionPane.showInputDialog("Razão Social: ");
        String nomeFantasia = JOptionPane.showInputDialog("Nome Fantasia: ");
        String cep = JOptionPane.showInputDialog("CEP: ");
        return new Fornecedor(cnpj, razaoSocial, nomeFantasia, cep);
    }
    
    //Cadastro de produto, verificando se o nome já existe e também a ID do Fornecedor.
    public static Produto cadastroProduto(ArrayList<Produto> produtos, ArrayList<Fornecedor> fornecedores){
        String nome = JOptionPane.showInputDialog("Nome do Produto: ");
        while(Produto.verificaProdutoNome(produtos, nome)){
            nome = JOptionPane.showInputDialog("Esse Nome de Produto já existe, digite outro: ");
        }
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço: "));
        int idFornecedor = Integer.parseInt(JOptionPane.showInputDialog("Digite a ID do Fornecedor desse Produto: " + Fornecedor.listaFornecedores(fornecedores)));
        while(Fornecedor.verificaFornecedorID(fornecedores, idFornecedor) == false){
            idFornecedor = Integer.parseInt(JOptionPane.showInputDialog("Essa ID não existe, digite outra: "));
        }
        Fornecedor fornecedor = Fornecedor.buscaFornecedorID(fornecedores, idFornecedor);
        return new Produto(nome, preco, fornecedor);
    }
    
    //Compra de Produto, verificando os nomes do Fornecedor e Produto, e comparando se um pertence ao outro. No final, exibe o total.
    public static Compra compraProduto(ArrayList<Fornecedor> fornecedores, ArrayList<Produto> produtos){
        String nomeFornecedor = JOptionPane.showInputDialog("Nome do Fornecedor do Produto que deseja Comprar: ");
        while(Fornecedor.verificaFornecedorNome(fornecedores, nomeFornecedor) == false){
            nomeFornecedor = JOptionPane.showInputDialog("Fornecedor não cadastrado, digite outro Nome de Fornecedor: ");
        }
        String nomeProduto = JOptionPane.showInputDialog("Nome do Produto que deseja Comprar: ");
        while(Produto.verificaProdutoFornecedor(produtos, nomeProduto, nomeFornecedor) == false){
            nomeProduto = JOptionPane.showInputDialog("O Produto não condiz com o Fornecedor indicado anteriormente, digite o Nome do Produto novamente: ");
        }
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade do Produto que deseja comprar: "));
        Fornecedor fornecedor = Fornecedor.buscaFornecedorNome(fornecedores, nomeFornecedor);
        Produto produto = Produto.buscaProdutoNome(produtos, nomeProduto);
        Compra compra = new Compra(fornecedor, produto, quantidade);
        JOptionPane.showMessageDialog(null, compra.toString());
        return compra;       
    }
    
    //Venda de produtos: Verificando se foi digitado ID ou Nome/Parte do Nome e verificando os mesmos.
    //Após confirmar que possui estoque, uma mensagem com 2 escolhas aparece perguntando se deseja vender mais,
    //caso a resposta seja afirmativa, repete-se todo o processo, do contrário, completa a venda.
    public static Venda vendaProduto(ArrayList<Produto> produtos, ArrayList<Venda> vendas){
        String idNomeProduto = JOptionPane.showInputDialog("Digite o Nome ou ID do Produto: ");
        if(Produto.verificaIdOuNome(idNomeProduto) == false){
            do{
                idNomeProduto = JOptionPane.showInputDialog("Digite a ID do Produto desejado:\n" + Produto.parteDoNome(produtos, idNomeProduto));
            }while(Produto.verificaIdOuNome(idNomeProduto) == false);
        }
        int id = Integer.parseInt(idNomeProduto);
        if(Produto.verificaProdutoID(produtos, id) == false){
            do{
                id = Integer.parseInt(JOptionPane.showInputDialog("ID digitada não existe, digite uma ID válida:\n" + Produto.parteDoNome(produtos, idNomeProduto)));
            }while(Produto.verificaProdutoID(produtos, id) == false);
        }
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade a ser vendida: "));
        if(Produto.verificaEstoque(produtos, quantidade) == false){
            do{
                JOptionPane.showMessageDialog(null, "Estoque insuficiente, Venda não realizada!!!");
                Produto produto = Produto.buscaProdutoID(produtos, id);
                quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor menor ou igual a " + produto.getEstoque()));
            }while(Produto.verificaEstoque(produtos, quantidade) == false);
        }
        int escolha = JOptionPane.showConfirmDialog(null, "Deseja realizar mais uma venda?", "Vendas", JOptionPane.YES_NO_OPTION);
        while(escolha==JOptionPane.YES_OPTION){
            Produto produto = Produto.buscaProdutoID(produtos, id);
            Venda venda = new Venda(produto, quantidade);
            vendas.add(venda);
            idNomeProduto = JOptionPane.showInputDialog("Digite o Nome ou ID do Produto: ");
        if(Produto.verificaIdOuNome(idNomeProduto) == false){
            do{
                idNomeProduto = JOptionPane.showInputDialog("Digite a ID do Produto desejado:\n" + Produto.parteDoNome(produtos, idNomeProduto));
            }while(Produto.verificaIdOuNome(idNomeProduto) == false);
        }
        id = Integer.parseInt(idNomeProduto);
        if(Produto.verificaProdutoID(produtos, id) == false){
            do{
                id = Integer.parseInt(JOptionPane.showInputDialog("ID digitada não existe, digite uma ID válida:\n" + Produto.parteDoNome(produtos, idNomeProduto)));
            }while(Produto.verificaProdutoID(produtos, id) == false);
        }
        quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade a ser vendida: "));
        if(Produto.verificaEstoque(produtos, quantidade) == false){
            do{
                JOptionPane.showMessageDialog(null, "Estoque insuficiente, Venda não realizada!!!");
                produto = Produto.buscaProdutoID(produtos, id);
                quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor menor ou igual a " + produto.getEstoque()));
            }while(Produto.verificaEstoque(produtos, quantidade) == false);
        }
        escolha = JOptionPane.showConfirmDialog(null, "Deseja realizar mais uma venda?", "Vendas", JOptionPane.YES_NO_OPTION);
        }
        Produto produto = Produto.buscaProdutoID(produtos, id);
        Venda venda = new Venda(produto, quantidade);
        vendas.add(venda);
        JOptionPane.showMessageDialog(null, "==================Vendas Efetuadas=================\n" + vendas.toString() + "\n" + Venda.totalVenda(vendas));
        return venda;
    }
    
    //Pede a quantidade desejada e exibe os produtos em nível crítico.
    public static void nivelCritico(ArrayList<Produto> produtos, int quantidade){
        quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade mínima desejada: "));
        JOptionPane.showMessageDialog(null, Relatorio.relatorioNivelCritico(produtos, quantidade));
    }
    
    //Pede o mês e o ano e exibe o relatório de vendas.
    public static void relatorioVenda(ArrayList<Venda> vendas, int mes, int ano){
        mes = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor correspondente ao mês desejado\n " + Relatorio.meses()));
        while(mes<1 && mes >12){
            mes = Integer.parseInt(JOptionPane.showInputDialog("Valor Inválido - Digite o valor correspondente ao mês desejado: \n" + Relatorio.meses()));
        }
        ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano desejado: \n" + "Exemplos: 1998, 2020, 2019, 2010"));
        JOptionPane.showMessageDialog(null, Relatorio.relatorioDeVendas(vendas, mes, ano));
    }
    
    
}
    
   


