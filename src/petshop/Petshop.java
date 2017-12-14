/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petshop;

import PetShopNegocio.NegocioException;
import viewLayout.MainMenuUI;
import viewLayout.ServicosUI;

/**
 *
 * @author silva
 */
public class Petshop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NegocioException {
          new MainMenuUI().menu();


    }
    
}
