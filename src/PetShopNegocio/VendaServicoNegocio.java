package PetShopNegocio;

import PetShopDao.VendaServicoDao;
import PetShopDaoDB.VendaServicoDaoBd;
import java.util.List;
import model.ServicosPets;
import model.VendaServico;

public class VendaServicoNegocio {

    private final VendaServicoDao vendaServicoDao;

    public VendaServicoNegocio() {
        vendaServicoDao = new VendaServicoDaoBd();
    }

    public void salvar(VendaServico s) throws NegocioException {
        this.validarCamposObrigatorios(s);
        vendaServicoDao.salvar(s);
    }

    public List<VendaServico> listar() {
        return (vendaServicoDao.listar());
    }
    public void deletar(VendaServico vendaServico) throws NegocioException {
        if (vendaServico == null) {
            throw new NegocioException("venda não existe!");
        }
        vendaServicoDao.deletar(vendaServico);
    }

    public void atualizar(VendaServico vendaServico) throws NegocioException {
        if (vendaServico == null) {
            throw new NegocioException("venda não existe ");
        }
        this.validarCamposObrigatorios(vendaServico);
        vendaServicoDao.atualizar(vendaServico);
    }

    private void validarCamposObrigatorios(VendaServico s) throws NegocioException {
        if (s.getCodCliente() < 0) {
            throw new NegocioException("O cadastro do cliente é obrigatório");
        }

        if (s.getCodServicos()< 0) {
            throw new NegocioException("O Cadastro do serviço é Obrigatório");
        }
    }


}
