package viewLayout;


import PetShopNegocio.ClienteNegocio;
import PetShopNegocio.NegocioException;
import PetShopNegocio.PetsNegocio;
import java.util.InputMismatchException;
import java.util.List;
import model.Cliente;
import model.Pets;
import util.Console;
import util.DateUtil;
import view_menu.ClienteMenu;
import view_menu.PetsMenu;

/**
 *
 * @author lhries
 */
public class PetsUI2 {

    private PetsNegocio petsNegocio;
    private ClienteNegocio clienteNegocio;

    public PetsUI2() {
        petsNegocio = new PetsNegocio();
        clienteNegocio = new ClienteNegocio();
    }

    public void menu() throws NegocioException {
        int opcao = -1;
        do {
            try {
                System.out.println(PetsMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case PetsMenu.OP_CADASTRAR:
                    cadastrarPets();
                    break;
                case PetsMenu.OP_DELETAR:
                    deletarPets();
                        break;
                 case PetsMenu.OP_ATUALIZAR:
                        atualizarPets();
                        break;                        
                case PetsMenu.OP_LISTAR:
                    mostrarPets();
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
   
    private void atualizarPets() {
        String rg = Console.scanString("RG do cliente a ser alterado para busca o pet: ");
        try {
            Pets pets = petsNegocio.procurarPorRg(rg);
            this.mostrarPets(pets);

            System.out.println("Digite os dados do Pet que quer alterar [Vazio caso nao queira]");
            String nome = Console.scanString("Nome: ");
            String tipoAnimal = Console.scanString("Tipo Animal: ");
            if (!nome.isEmpty()) {
                pets.setNome(nome);
            }
            if (!tipoAnimal.isEmpty()) {
                pets.setTipoAnimal(tipoAnimal);
            }
            petsNegocio.atualizar(pets);
            System.out.println("Pets" + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    private void deletarPets() {
        String rg = Console.scanString("RG do cliente a ser deletado: ");
        try {
            Pets pets = petsNegocio.procurarPorRg(rg);
            this.mostrarPets(pets);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
                petsNegocio.deletar(pets);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
   
    private void cadastrarPets() throws NegocioException {
        String nome = Console.scanString("Nome: ");
        String tipoAnimal = Console.scanString("Tipo Animal: ");
        String rg = Console.scanString("Rg Cliente: ");
        
        Cliente cliente = petsNegocio.procurarClientePorRg(rg);
        
        try {
            petsNegocio.salvar(new Pets(nome,tipoAnimal, cliente));
            System.out.println("Pets " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }

    public void mostrarPets() {
        List<Pets> listaPets = petsNegocio.listar();
        this.mostrarClientes(listaPets);
    }

    private void mostrarPets(Pets c) {
        System.out.println("-----------------------------");
        System.out.println("Pet");
        System.out.println(" cliente: " + c.getDonoAnimal().getNome());
        System.out.println("Nome: " + c.getNome());
        System.out.println("Telefone: " + c.getDonoAnimal().getTelefone());
        System.out.println("-----------------------------");
    }

    private void mostrarClientes(List<Pets> listaPets) {
        if (listaPets.isEmpty()) {
            System.out.println("Clientes nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|TipoAnimal") + "\t"
                    + String.format("%-20s", "|Cliente"));
            for (Pets pets : listaPets) {
                System.out.println(String.format("%-10s", pets.getNome()) + "\t"
                        + String.format("%-20s", "|" + pets.getTipoAnimal()) + "\t"
                        + String.format("%-20s", "|" + pets.getDonoAnimal().getNome()));
            }
        }
    }
}
