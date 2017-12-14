/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewLayout;

import PetShopNegocio.NegocioException;
import PetShopNegocio.ServicosNegocio;
import java.util.List;
import model.ServicosPets;
import util.Console;
import view_menu.ServicosMenu;

/**
 *
 * @author silva
 */
public class ServicosUI {
    private ServicosNegocio servicosNegocio;

    public ServicosUI() {
      servicosNegocio = new ServicosNegocio();
    }
    
    public void menu() throws NegocioException {
        int opcao = -1;
        do {
            System.out.println(ServicosMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case ServicosMenu.OP_CADASTRAR:
                    cadastrarServicos();
                    break;
                case ServicosMenu.OP_DELETAR:
                    deletarServico();
                    break;
                case ServicosMenu.OP_ATUALIZAR:
                    atualizarServico();
                    break;
                case ServicosMenu.OP_LISTAR:
                    mostrarServicos();
                    break;                    
                case ServicosMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != ServicosMenu.OP_VOLTAR);
    }

    private void atualizarServico() {
         String nomeSer = Console.scanString("Informe o nome do serviço: ");
        try {
            ServicosPets servico = servicosNegocio.procurarPoTipoDeServico(nomeSer);
            this.mostrarSer(servico);

            System.out.println("Informe os dados a serem atualizados");
            String tipoAtendimento = Console.scanString("Nome: ");
            double valor = Console.scanDouble("Informe o Valor: ");
            if (!tipoAtendimento.isEmpty()) {
                servico.setTipoAtendimento(tipoAtendimento);
            }
            if (valor != servico.getCodServico() ) {
                servico.setPrecoServico(valor);
            }
            servicosNegocio.atualizar(servico);
            System.out.println("Serviço" + tipoAtendimento + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
        
   private void deletarServico() {
        String nomeSer = Console.scanString("Informe o nome do serviço ");
        try {
            ServicosPets servico = servicosNegocio.procurarPoTipoDeServico(nomeSer);
            this.mostrarSer(servico);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
                servicosNegocio.deletar(servico);
                System.out.println("Serviço deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
   
   private void mostrarSer(ServicosPets c) {
        System.out.println("-----------------------------");
        System.out.println("Servicos");
        System.out.println(" Tipo Serviço: " + c.getTipoAtendimento());
        System.out.println("Preço: " + c.getPrecoServico());
        System.out.println("-----------------------------");
    }    
   
   private void cadastrarServicos() throws NegocioException {
        String tipoAtendimento = Console.scanString("Tipo Atendimento: ");
        double precoServico = Console.scanDouble("Preço Servico: ");
        
        try {
            servicosNegocio.salvar(new ServicosPets(precoServico, tipoAtendimento));
            System.out.println("Serviço " + tipoAtendimento + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }

   public void mostrarServicos() {
        List<ServicosPets> listaServicos = servicosNegocio.listar();
        this.mostrarClientes(listaServicos);
    }

   private void mostrarClientes(List<ServicosPets> listaServicos) 
    {
        if (listaServicos.isEmpty()) {
            System.out.println("Serviços não encontrado!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "Tipo Servico") + "\t"
            + String.format("%-20s", "|Preco Serviço"));
            for (ServicosPets serv : listaServicos) {
                System.out.println(String.format("%-10s", serv.getTipoAtendimento()) + "\t"
                + String.format("%-20s", "|" + serv.getPrecoServico()));
            }
        }
    }
    
}
