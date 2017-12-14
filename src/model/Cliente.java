/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author silva
 */
public class Cliente {
    private String rg;
    private String nome;
    private String telefone;
    private int codCliente;


    public Cliente( String rg, String nome, String telefone, LocalDate dataNascimento){
        this.nome = nome;   
        this.rg = rg;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        
    }
     public Cliente(int id, String rg, String nome, String telefone, LocalDate dataNascimento) {
        this.codCliente = id;
        this.rg = rg;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
    
    public String getRg() {
        return rg;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    private LocalDate dataNascimento;

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }
    
    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
