/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PetShopDao;

import model.Pets;
import model.ServicosPets;

/**
 *
 * @author silva
 */
public interface ServicosDao extends DomainCrudDao<ServicosPets>{
    public ServicosPets procurarPoTipoDeServico(String tipoSerrvico );
}
