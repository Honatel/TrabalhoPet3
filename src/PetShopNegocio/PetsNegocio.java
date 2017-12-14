package PetShopNegocio;

import PetShopDao.ClienteDao;
import PetShopDao.PetsDao;
import PetShopDaoDB.ClienteDaoBd;
import PetShopDaoDB.PetsDaoBd;
import java.util.List;
import model.Cliente;
import model.Pets;

public class PetsNegocio {

    private final PetsDao petsDao;
    private final ClienteDao clienteDao;


    public PetsNegocio() {
        petsDao = new PetsDaoBd();
        clienteDao = new ClienteDaoBd();
        
    }

    public void salvar(Pets p) throws NegocioException {
        this.validarCamposObrigatorios(p);
       // this.validarRgExistente(p);
        petsDao.salvar(p);
    }

    public List<Pets> listar() {
        return (petsDao.listar());
    }

    public void deletar(Pets pets) throws NegocioException {
        if (pets == null || pets.getDonoAnimal().getRg() == null) {
            throw new NegocioException("cliente nao existe!");
        }
        petsDao.deletar(pets);
    }

    public void atualizar(Pets pets) throws NegocioException {
        if (pets == null || pets.getDonoAnimal().getRg() == null) {
            throw new NegocioException("cliente nao existe!");
        }
        this.validarCamposObrigatorios(pets);
        petsDao.atualizar(pets);
    }

    public Pets procurarPorRg(String rg) throws NegocioException {
        if (rg == null || rg.isEmpty()) {
            throw new NegocioException("Campo RG nao informado");
        }
        Pets pets = petsDao.procurarPorRg(rg);
        if (pets == null) {
            throw new NegocioException("Animal não encontrado");
        }
        return (pets);
    }
       public Cliente procurarClientePorRg(String rg) throws NegocioException {
        if (rg == null || rg.isEmpty()) {
            throw new NegocioException("Campo RG nao informado");
        }
        Cliente cliente = clienteDao.procurarPorRg(rg);
        if (cliente == null) {
            throw new NegocioException("Cliente não encontrado");
        }
        return (cliente);
    }

    public List<Pets> listarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        return(petsDao.listarPorNome(nome));
    }

    public boolean clienteExiste(String rg) {
        Pets pets = petsDao.procurarPorRg(rg);
        return (pets != null);
    }

    private void validarCamposObrigatorios(Pets p) throws NegocioException {
        if (p.getDonoAnimal().getRg()== null || p.getDonoAnimal().getRg().isEmpty()) {
            throw new NegocioException("Campo RG nao informado");
        }

        if (p.getNome() == null || p.getNome().isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
    }

    private void validarRgExistente(Pets p) throws NegocioException {
        if (clienteExiste(p.getDonoAnimal().getRg())) {
            throw new NegocioException("RG ja existente");
        }
    }

}
