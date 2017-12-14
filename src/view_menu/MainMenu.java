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
public class MainMenu {

    public static final int OP_CLIENTES = 1;
    public static final int OP_CADASTRO_PETS = 2;
    public static final int OP_CADASTRO_SERVICOS = 3;
    public static final int OP_VENDA_SERVICOS = 4;
    public static final int OP_RELATORIOS = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Menu Clientes\n"
                + "2- Menu Pets\n"
                + "3- Menu Cadastro Serviços\n"
                + "4- Menu Venda Serviços\n"
                + "5- Menu Relatorios\n"
                + "0- Sair da Aplicação"
                + "\n--------------------------------------");
    }
    
}
