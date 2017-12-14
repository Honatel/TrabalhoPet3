/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewLayout;

import PetShopNegocio.ClienteNegocio;
import PetShopNegocio.NegocioException;
import PetShopNegocio.ServicosNegocio;
import PetShopNegocio.VendaServicoNegocio;
import java.util.List;
import model.Cliente;
import model.Pets;
import model.ServicosPets;
import model.VendaServico;
import util.Console;
import view_menu.ServicosMenu;
import view_menu.VendaServicoMenu;

/**
 *
 * @author silva
 */
public class VendasServicosUI {
    private VendaServicoNegocio vendaServicoNegocio;
    private ClienteNegocio ClienteNegocio;
    private ServicosNegocio servicosNegocio;


    public VendasServicosUI() {
      vendaServicoNegocio = new VendaServicoNegocio();
      ClienteNegocio = new ClienteNegocio();
      servicosNegocio = new ServicosNegocio();

    }
    
    public void menu() throws NegocioException {
        int opcao = -1;
        do {
            System.out.println(ServicosMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case VendaServicoMenu.OP_CADASTRAR:
                    cadastrarServicos();
                    break;
                case VendaServicoMenu.OP_LISTAR:
                    mostrarServicos();
                    break;
                case VendaServicoMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != ServicosMenu.OP_VOLTAR);
    }

    private void cadastrarServicos() throws NegocioException {
        String nomeServico = Console.scanString("Nome Serviço: ");
        String rg = Console.scanString("Rg Cliente: ");
        
        Cliente cliente = ClienteNegocio.procurarPorRg(rg);
        ServicosPets servico = servicosNegocio.procurarPoTipoDeServico(nomeServico);
        
        try {
            vendaServicoNegocio.salvar(new VendaServico(cliente, servico));
            System.out.println("Serviço " + nomeServico + " para o cliente "  + cliente.getNome()  + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }

   public void mostrarServicos() {
        List<VendaServico> vendaServico = vendaServicoNegocio.listar();
        this.mostrarClientes(vendaServico);
    }

    private void mostrarClientes(List<VendaServico> vendaServico) 
    {
        if (vendaServico.isEmpty()) {
            System.out.println("vendas não encontrado!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "Nome Cliente") + "\t"
            + String.format("%-20s", "|Serviço"));
            for (VendaServico serv : vendaServico) {
                System.out.println(String.format("%-10s", serv.getCliente().getNome()) + "\t"
                + String.format("%-20s", "|" + serv.getServico().getTipoAtendimento()));
            }
        }
    }
    
}
