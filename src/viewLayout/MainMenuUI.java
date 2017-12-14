package viewLayout;


import PetShopNegocio.NegocioException;
import java.util.InputMismatchException;
import util.Console;
import view_menu.ClienteMenu;
import view_menu.MainMenu;
import view_menu.PetsMenu;

/**
 *
 * @author lhries
 */
public class MainMenuUI {
    ClienteUI clienteUI;
    PetsUI2 petsUI;
    ServicosUI servicosUI;
    VendasServicosUI vendaServicoUI;


    public MainMenuUI()
    {
        clienteUI = new ClienteUI();
        petsUI = new PetsUI2();
        servicosUI = new ServicosUI();
        vendaServicoUI = new VendasServicosUI();

 
    }
    public void menu() throws NegocioException {
        int opcao = -1;
        do {
            try {
                System.out.println(MainMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case MainMenu.OP_CLIENTES:
                    clienteUI.menu();
                    break;
                case MainMenu.OP_CADASTRO_PETS:
                    petsUI.menu();
                    break;
                case MainMenu.OP_CADASTRO_SERVICOS:
                    servicosUI.menu();
                    break;
                case MainMenu.OP_VENDA_SERVICOS:
                    vendaServicoUI.menu();
                    break;
                case MainMenu.OP_RELATORIOS:
                    break;
                case PetsMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != ClienteMenu.OP_SAIR);
    }
}
