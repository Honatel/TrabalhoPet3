/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author silva
 */
public class RepositorioCliente {
     private List<Cliente> clientes;
    
    public RepositorioCliente() {
        clientes = new ArrayList<Cliente>();
    }

    public boolean addClientes(Cliente cliente) {
        return (clientes.add(cliente));
    }

    public List<Cliente> getListaClientes() {
        return clientes;
    }

    public boolean ClienteExiste(String rg) {
        for (Cliente cliente : clientes) {
            if (cliente.getRg().equals(rg)) {
                return true;
            }
        }
        return false;
    }

    public Cliente BuscarCliente(String rg) {
        for (Cliente cliente : clientes) {
            if (cliente.getRg().equals(rg)) {
                return cliente;
           }
        }
        return null;
    }

}