/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PetShopDao;

import java.util.List;
import model.Cliente;

/**
 *
 * @author silva
 */
public interface ClienteDao extends DomainCrudDao<Cliente>{
    public Cliente procurarPorRg(String rg);
    public List<Cliente> listarPorNome(String nome);
}
