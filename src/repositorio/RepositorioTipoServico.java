/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.ServicosPets;

/**
 *
 * @author silva
 */
public class RepositorioTipoServico {
    private List<ServicosPets> servicosPets;
    
    public RepositorioTipoServico() {
        servicosPets = new ArrayList<ServicosPets>();
    }

    public boolean addServicosPetss(ServicosPets servicos) {
        return (servicosPets.add(servicos));
    }

    public List<ServicosPets> getListaServicosPetss() {
        return servicosPets;
    }

    public boolean ServicosPetsExiste(int codServico) {
        for (ServicosPets servicosPets : servicosPets) {
            if (servicosPets.getCodServico() == codServico) {
                return true;
            }
        }
        return false;
    }

    public ServicosPets BuscarServicosPets(int codServico) {
        for (ServicosPets servicosPets : servicosPets) {
            if (servicosPets.getCodServico() == codServico) {
                return servicosPets;
           }
        }
        return null;
    }
    
}
