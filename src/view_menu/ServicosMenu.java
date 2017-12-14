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
public class ServicosMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_DELETAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Servico\n"
                + "2- Deletar Servico\n"
                + "3- Atualizar dados do Servico\n"
                + "4- Listar Servico\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
    
}
