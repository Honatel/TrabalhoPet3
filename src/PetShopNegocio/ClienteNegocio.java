package PetShopNegocio;

import PetShopDao.ClienteDao;
import PetShopDaoDB.ClienteDaoBd;
import java.util.List;
import model.Cliente;

public class ClienteNegocio {

    private final ClienteDao clienteDao;

    public ClienteNegocio() {
        clienteDao = new ClienteDaoBd();
    }

    public void salvar(Cliente p) throws NegocioException {
        this.validarCamposObrigatorios(p);
        this.validarRgExistente(p);
        clienteDao.salvar(p);
    }

    public List<Cliente> listar() {
        return (clienteDao.listar());
    }

    public void deletar(Cliente cliente) throws NegocioException {
        if (cliente == null || cliente.getRg() == null) {
            throw new NegocioException("cliente nao existe!");
        }
        clienteDao.deletar(cliente);
    }

    public void atualizar(Cliente cliente) throws NegocioException {
        if (cliente == null || cliente.getRg() == null) {
            throw new NegocioException("cliente nao existe!");
        }
        this.validarCamposObrigatorios(cliente);
        clienteDao.atualizar(cliente);
    }

    public Cliente procurarPorRg(String rg) throws NegocioException {
        if (rg == null || rg.isEmpty()) {
            throw new NegocioException("Campo RG nao informado");
        }
        Cliente cliente = clienteDao.procurarPorRg(rg);
        if (cliente == null) {
            throw new NegocioException("cliente nao encontrado");
        }
        return (cliente);
    }

    public List<Cliente> listarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        return(clienteDao.listarPorNome(nome));
    }

    public boolean clienteExiste(String rg) {
        Cliente cliente = clienteDao.procurarPorRg(rg);
        return (cliente != null);
    }

    private void validarCamposObrigatorios(Cliente c) throws NegocioException {
        if (c.getRg() == null || c.getRg().isEmpty()) {
            throw new NegocioException("Campo RG nao informado");
        }

        if (c.getNome() == null || c.getNome().isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
    }

    private void validarRgExistente(Cliente c) throws NegocioException {
        if (clienteExiste(c.getRg())) {
            throw new NegocioException("RG ja existente");
        }
    }
    
     public Cliente procurarPorId(int id) throws NegocioException {
        if (id < 0){
            throw new NegocioException("Campo id nao informado");
        }
        Cliente cliente = clienteDao.procurarPorId(id);
         if (cliente == null) {
            throw new NegocioException("cliente não existe");
        }
        return cliente;
    }
}
