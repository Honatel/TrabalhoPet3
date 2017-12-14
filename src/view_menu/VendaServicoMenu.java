/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_menu;

/**
 *
 * @author silva
 */
public class VendaServicoMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Vendas\n"
                + "2- Listar Vendas\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    } 
    
}
