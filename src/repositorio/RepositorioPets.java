/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Pets;

/**
 *
 * @author silva
 */
public class RepositorioPets {
    private List<Pets> listaPets;
    
    public RepositorioPets() {
        listaPets = new ArrayList<Pets>();
    }
    
    public boolean addPets(Pets pets) {
        return (listaPets.add(pets));
    }

    public List<Pets> getListaPets() {
        return listaPets;
    }

    public boolean PetsExiste(String rg, String nomeAnimal) {
        for (Pets pets : listaPets) {
            if (pets.getDonoAnimal().getRg().equals(rg) && pets.getNome().equals(nomeAnimal)) {
                return true;
            }
        }
        return false;
    }

    public Pets BuscarPets(String rg, String nomeAnimal) {
        for (Pets  pets: listaPets) {
            if (pets.getDonoAnimal().getRg().equals(rg) && pets.getNome().equals(nomeAnimal)) {
                return pets;
           }
        }
        return null;
    }
}
