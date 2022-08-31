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
public class Fornecedor {
    private static int cont = 1;
    private int id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String cep;

    public Fornecedor(String cnpj, String razaoSocial, String nomeFantasia, String cep) {
        this.id = cont++;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cep = cep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    // Percorrerá a ArrayList para verificar se o cnpj inserido já existe
    public static boolean verificaFornecedorCNPJ(ArrayList<Fornecedor> fornecedores, String cnpj){
        boolean cnpjRepetido = false;
        for(Fornecedor f: fornecedores){
            if(f.getCnpj().equals(cnpj)){
                cnpjRepetido = true;
            }
        }
        return cnpjRepetido;
    }
    
    //Uma busca por toda a ArrayList para comparar o nome do Fornecedor e em caso de igualdade retornar o fornecedor desejado.
    public static Fornecedor buscaFornecedorNome(ArrayList<Fornecedor> fornecedores, String nome){
        Fornecedor retorno = null;
            for(Fornecedor f: fornecedores){
                if(f.getNomeFantasia().equals(nome)){
                    retorno = f;
                }
            }
        return retorno;
    }
    
    //Uma busca por toda a ArrayList para comparar a ID do Fornecedor e em casa de igualdade retornar o fornecedor desejado.
    public static Fornecedor buscaFornecedorID(ArrayList<Fornecedor> fornecedores, int id){
        Fornecedor retorno = null;
            for(Fornecedor f: fornecedores){
                if(f.getId() == id){
                    retorno = f;
                }
            }
        return retorno;
    }
    
    //Percorre a ArrayList dos fornecedores para verificar se a ID digitada existe, retornando true ou false.
    public static boolean verificaFornecedorID(ArrayList<Fornecedor> fornecedores, int id){
        boolean idExiste = false;
        for(Fornecedor f: fornecedores){
            if(f.getId() == id){
                idExiste = true;
            }
        }
        return idExiste;
    }
        
    //Percorre a ArrayList dos fornecedores para verificar se o nome digitado existe, retornando true ou false.
    public static boolean verificaFornecedorNome(ArrayList<Fornecedor> fornecedores, String nome){
        boolean nomeExiste = false;
        for(Fornecedor f: fornecedores){
            if(f.getNomeFantasia().equals(nome)){
                nomeExiste = true;
            }
        }
        return nomeExiste;
    }
    
    // Percorre a ArrayList dos fornecedores e lista a ID e Nome de cada um deles.
    public static String listaFornecedores(ArrayList<Fornecedor> fornecedores){
        String retorno = "\n";
        for(Fornecedor f: fornecedores){
            retorno += "ID: " + f.getId() + " - Fornecedor: " + f.getNomeFantasia();
            retorno += "\n";
        }
        return retorno;
    }
   
}
