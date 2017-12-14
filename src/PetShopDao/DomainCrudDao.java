/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PetShopDao;

import java.util.List;

/**
 *
 * @author 181501236
 */
public interface DomainCrudDao<T> {
    public void salvar(T Object);
    public void deletar(T Object);
    public void atualizar(T Object);
    public List<T> listar();
    public T procurarPorId(int id);  
}
