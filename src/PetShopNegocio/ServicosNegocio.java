package PetShopNegocio;

import PetShopDao.ServicosDao;
import PetShopDaoDB.ServicosDaoBd;
import java.util.List;
import model.ServicosPets;

public class ServicosNegocio {

    private final ServicosDao servicosDao;

    public ServicosNegocio() {
        servicosDao = new ServicosDaoBd();
    }

    public void salvar(ServicosPets s) throws NegocioException {
        this.validarCamposObrigatorios(s);
        servicosDao.salvar(s);
    }

    public List<ServicosPets> listar() {
        return (servicosDao.listar());
    }

    public void deletar(ServicosPets servicosPets) throws NegocioException {
        if (servicosPets == null || servicosPets.getTipoAtendimento() == null) {
            throw new NegocioException("Serviço não existe!");
        }
        servicosDao.deletar(servicosPets);
    }

    public void atualizar(ServicosPets servicosPets) throws NegocioException {
        if (servicosPets == null || servicosPets.getTipoAtendimento() == null) {
            throw new NegocioException("Serviço não existe ");
        }
        this.validarCamposObrigatorios(servicosPets);
        servicosDao.atualizar(servicosPets);
    }

    public ServicosPets procurarPoTipoDeServico(String tipoServico) throws NegocioException {
        if (tipoServico == null || tipoServico.isEmpty()) {
            throw new NegocioException("Tipo servico não informado");
        }
        return servicosDao.procurarPoTipoDeServico(tipoServico);
    }

    private void validarCamposObrigatorios(ServicosPets s) throws NegocioException {
        if (s.getTipoAtendimento()== null) {
            throw new NegocioException("O nome do tipo de atendimento é obrigatorio");
        }

        if (s.getPrecoServico() < 0) {
            throw new NegocioException("O preço do serviço é obrigatorio");
        }
    }
    
      public ServicosPets procurarPorId(int id) throws NegocioException {
        if (id < 0){
            throw new NegocioException("Campo id nao informado");
        }
        ServicosPets ser = servicosDao.procurarPorId(id);
        
         if (ser == null) {
            throw new NegocioException("cliente não existe");
        }
        return ser;
    }


}
