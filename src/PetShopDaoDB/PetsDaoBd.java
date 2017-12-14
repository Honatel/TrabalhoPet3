package PetShopDaoDB;

import PetShopDao.ClienteDao;
import PetShopDao.PetsDao;
import PetShopNegocio.ClienteNegocio;
import PetShopNegocio.NegocioException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Pets;

public class PetsDaoBd extends DaoBd<Pets> implements PetsDao {
  
    private ClienteNegocio clienteNegocio;
    
    public PetsDaoBd (){
        clienteNegocio = new ClienteNegocio();
    }

    //Metodo salvar: trabalhar com data e recebe o id auto-increment 
    //e já relaciona no objeto cliente (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    @Override
    public void salvar(Pets pets) {
        int id = 0;
        try {
            String sql = "INSERT INTO pets (nome, tipoAnimal, codigoCliente) "
                    + "VALUES (?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, pets.getNome());
            comando.setString(2, pets.getTipoAnimal());
            comando.setInt(3, pets.getDonoAnimal().getCodCliente());

            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                pets.setCodPet(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar pets no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    @Override
    public Cliente procurarPorRGCliente(String rg){
        String sql = "SELECT * FROM cliente WHERE rg = ?";

        try {
            conectar(sql);
            comando.setString(1, rg);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("codCliente");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");

                //Trabalhando com data: convertendo dataSql -> LocalDate
                Date dataSql = resultado.getDate("datanascimento");
                LocalDate dataNascimento = dataSql.toLocalDate();

                Cliente cli = new Cliente(id, rg, nome,telefone, dataNascimento);

                return cli;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o cliente pelo rg do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);    
    }
    
    @Override
    public void deletar(Pets pets) {
        try {
            String sql = "DELETE FROM pets WHERE codPet = ?";

            conectar(sql);
            comando.setInt(1, pets.getCodPet());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar pets no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void atualizar(Pets pets) {
        try {
            String sql = "UPDATE pets SET nome=?, tipoAnimal=?"
                    + "WHERE codPet=?";

            conectar(sql);
            comando.setString(1, pets.getNome());
            comando.setString(2, pets.getTipoAnimal());

            //Trabalhando com data: convertendo LocalDate -> Date
            comando.setInt(3, pets.getCodPet());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar pets no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Pets> listar() {
        List<Pets> listaPets = new ArrayList<>();
        String sql = "SELECT * FROM Pets";

        try {
            conectar(sql);


            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codPet = resultado.getInt("codPet");
                String nome = resultado.getString("nome");
                String tipoAnimal = resultado.getString("tipoAnimal");
                int CodigoClinte = resultado.getInt("codigocliente");
                
                Cliente cliente = clienteNegocio.procurarPorId(CodigoClinte);
                Pets pet = new Pets(codPet, nome,tipoAnimal, CodigoClinte, cliente);
                listaPets.add(pet);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pets do Banco de Dados!");
            throw new BDException(ex);
        } catch (NegocioException ex) {
            Logger.getLogger(PetsDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaPets);
    }

    @Override
    public Pets procurarPorId(int id) {
        String sql = "SELECT * FROM pets WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codPet = resultado.getInt("codPet");
                String nome = resultado.getString("nome");
                String tipoAnimal = resultado.getString("tipoAnimal");
                int CodigoClinte = resultado.getInt("CodigoClinte");

                Cliente cliente = clienteNegocio.procurarPorId(CodigoClinte);

                Pets pet = new Pets(codPet, nome,tipoAnimal, CodigoClinte, cliente);
                return pet;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o pets pelo id do Banco de Dados!");
            throw new BDException(ex);
        } catch (NegocioException ex) {
            Logger.getLogger(PetsDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Pets procurarPorRg(String rg) {
        Cliente cli = procurarPorRGCliente(rg);
        
        String sql = "SELECT * FROM Pets WHERE CodigoCliente = ?";

        try {
            conectar(sql);
            comando.setInt(1, cli.getCodCliente());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("codPet");
                String nome = resultado.getString("nome");
                String tipoAnimal = resultado.getString("tipoAnimal");
                int CodCliente = resultado.getInt("CodigoCliente");

                Pets pet = new Pets(id,nome,tipoAnimal, CodCliente, cli);
                
                return pet;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o Pets pelo rg do cliente do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public List<Pets> listarPorNome(String nome) {
        List<Pets> ListaPets = new ArrayList<>();
        String sql = "SELECT * FROM Pets WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codPet = resultado.getInt("codPet");
                String nomex = resultado.getString("nome");
                String tipoAnimal = resultado.getString("tipoAnimal");
                int CodigoClinte = resultado.getInt("CodigoClinte");

                Pets pet = new Pets(codPet, nome,tipoAnimal, CodigoClinte);
                ListaPets.add(pet);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os Pets pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (ListaPets);
    }
}
