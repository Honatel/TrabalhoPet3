/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author silva
 */
public class Pets {
    private String nome,tipoAnimal;
    private Cliente donoAnimal;
    private int CodigoCliente;
    private int codPet;

    public int getCodigoCliente() {
        return CodigoCliente;
    }

    public void setCodigoCliente(int CodigoCliente) {
        this.CodigoCliente = CodigoCliente;
    }

    public int getCodPet() {
        return codPet;
    }

    public void setCodPet(int codPet) {
        this.codPet = codPet;
    }
    
    public Pets(String nome, String tipoAnimal, Cliente donoAnimal){
        this.nome = nome;
        this.tipoAnimal = tipoAnimal;
        this.donoAnimal = donoAnimal;
    }
       public Pets(int codPet, String nome, String tipoAnimal, int CodigoCliente){
        this.nome = nome;
        this.tipoAnimal = tipoAnimal;
        this.CodigoCliente = CodigoCliente;
        this.codPet = codPet;
    }
       
    public Pets(int codPet, String nome, String tipoAnimal, int CodigoCliente, Cliente cliente){
        this.nome = nome;
        this.tipoAnimal = tipoAnimal;
        this.CodigoCliente = CodigoCliente;
        this.codPet = codPet;
        this.donoAnimal = cliente;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {  
        this.nome = nome;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public Cliente getDonoAnimal() {
        return donoAnimal;
    }

    public void setDonoAnimal(Cliente donoAnimal) {
        this.donoAnimal = donoAnimal;
    }
}
