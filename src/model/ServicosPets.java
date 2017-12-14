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
public class ServicosPets {
    private int codServico;
    private double precoServico;
    private String tipoAtendimento;

    public ServicosPets(int codigo, double preco, String tipoServ){
        this.codServico= codigo;
        this.precoServico= preco;
        this.tipoAtendimento = tipoServ;
    }
    
    public ServicosPets(double preco, String tipoServ){
        this.precoServico= preco;
        this.tipoAtendimento = tipoServ;
    }
    
    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    }

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }
    
}
