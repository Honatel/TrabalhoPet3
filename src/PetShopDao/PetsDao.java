/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PetShopDao;

import java.util.List;
import model.Cliente;
import model.Pets;

/**
 *
 * @author silva
 */
public interface PetsDao extends DomainCrudDao<Pets>{
    public Cliente procurarPorRGCliente(String rg);
    public Pets procurarPorRg(String rg);
    public List<Pets> listarPorNome(String nome);

}
