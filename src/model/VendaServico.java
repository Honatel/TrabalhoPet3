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
public class VendaServico {
    private int codVenda;
    private LocalDate dataVenda ;
    private ServicosPets ServicosPets;
    private int codServico;
    private int codCliente;
    private Cliente cliente;

    
    public VendaServico(int codVenda, int codCliente, int codServico){
        this.codCliente = codCliente;
        this.codVenda = codVenda;
        this.codServico = codServico;
    }
    
    public VendaServico(Cliente clinte,ServicosPets servico){
        this.cliente = clinte;
        this.ServicosPets = servico;
    }

    
    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public ServicosPets getServico() {
        return ServicosPets;
    }

    public void setServico(ServicosPets ServicosPets) {
        this.ServicosPets = ServicosPets;
    }

    public int getCodServicos() {
        return codServico;
    }

    public void setCodServicos(int codServico) {
        this.codServico = codServico;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public static void CalculoTotalVenda(){}
}
