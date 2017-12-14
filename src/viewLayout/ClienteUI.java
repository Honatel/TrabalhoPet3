package viewLayout;


import PetShopNegocio.ClienteNegocio;
import PetShopNegocio.NegocioException;
import java.util.InputMismatchException;
import java.util.List;
import model.Cliente;
import util.Console;
import util.DateUtil;
import view_menu.ClienteMenu;

/**
 *
 * @author lhries
 */
public class ClienteUI {

    private ClienteNegocio clienteNegocio;

    public ClienteUI() {
        clienteNegocio = new ClienteNegocio();
    }

    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println(ClienteMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case ClienteMenu.OP_CADASTRAR:
                        cadastrarCliente();
                        break;
                    case ClienteMenu.OP_DELETAR:
                        deletarCliente();
                        break;
                    case ClienteMenu.OP_ATUALIZAR:
                        atualizarCliente();
                        break;
                    case ClienteMenu.OP_LISTAR:
                        mostrarClientes();
                        break;
                    case ClienteMenu.OP_CONSULTAR:
                        consultarClientesPorNome();
                        break;
                    case ClienteMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != ClienteMenu.OP_SAIR);
    }

    private void cadastrarCliente() {
        String rg = Console.scanString("RG: ");
        String nome = Console.scanString("Nome: ");
        String telefone = Console.scanString("Telefone: ");
        String dataString = Console.scanString("Data de Nascimento: ");
        try {
            Cliente cli = new Cliente(rg, nome,telefone, DateUtil.stringToDate(dataString));
            clienteNegocio.salvar(cli);
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }

    public void mostrarClientes() {
        List<Cliente> listaClientes = clienteNegocio.listar();
        this.mostrarClientes(listaClientes);
    }

    private void deletarCliente() {
        String rg = Console.scanString("RG do cliente a ser deletado: ");
        try {
            Cliente cli = clienteNegocio.procurarPorRg(rg);
            this.mostrarCliente(cli);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
                clienteNegocio.deletar(cli);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void atualizarCliente() {
        String rg = Console.scanString("RG do cliente a ser alterado: ");
        try {
            Cliente cli = clienteNegocio.procurarPorRg(rg);
            this.mostrarCliente(cli);

            System.out.println("Digite os dados do cliente que quer alterar [Vazio caso nao queira]");
            String nome = Console.scanString("Nome: ");
            String telefone = Console.scanString("Telefone: ");
            String dataString = Console.scanString("Data de Nascimento: ");
            if (!nome.isEmpty()) {
                cli.setNome(nome);
            }
            if (!dataString.isEmpty()) {
                cli.setDataNascimento(DateUtil.stringToDate(dataString));
            }

            clienteNegocio.atualizar(cli);
            System.out.println("Cliente " + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }

    private void consultarClientesPorNome() {
        String nome = Console.scanString("Nome: ");
        try {
            List<Cliente> listaCli = clienteNegocio.listarPorNome(nome);
            this.mostrarClientes(listaCli);
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }

    }

    private void mostrarCliente(Cliente c) {
        System.out.println("-----------------------------");
        System.out.println("Cliente");
        System.out.println("RG: " + c.getRg());
        System.out.println("Nome: " + c.getNome());
        System.out.println("Telefone: " + c.getTelefone());
        System.out.println("Data de Nascimento: "
                + DateUtil.dateToString(c.getDataNascimento()));
        System.out.println("-----------------------------");
    }

    private void mostrarClientes(List<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Clientes nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "RG") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|TELEFONE") + "\t"
                    + String.format("%-20s", "|DATA DE NASCIMENTO"));
            for (Cliente cliente : listaClientes) {
                System.out.println(String.format("%-10s", cliente.getRg()) + "\t"
                        + String.format("%-20s", "|" + cliente.getNome()) + "\t"
                        + String.format("%-20s", "|" + cliente.getTelefone()) + "\t"
                        + String.format("%-20s", "|" + DateUtil.dateToString(cliente.getDataNascimento())));
            }
        }
    }
}
