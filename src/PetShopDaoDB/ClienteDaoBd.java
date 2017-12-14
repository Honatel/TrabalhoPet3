package PetShopDaoDB;

import PetShopDao.ClienteDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import model.Cliente;

public class ClienteDaoBd extends DaoBd<Cliente> implements ClienteDao {
  

    //Metodo salvar: trabalhar com data e recebe o id auto-increment 
    //e já relaciona no objeto cliente (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    @Override
    public void salvar(Cliente cliente) {
        int id = 0;
        try {
            String sql = "INSERT INTO cliente (rg, nome, telefone, datanascimento) "
                    + "VALUES (?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, cliente.getRg());
            comando.setString(2, cliente.getNome());
            comando.setString(3, cliente.getTelefone());
            //Trabalhando com data: convertendo LocalDate -> Date
            Date dataSql = Date.valueOf(cliente.getDataNascimento());
            comando.setDate(4, dataSql);
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                cliente.setCodCliente(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar cliente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void deletar(Cliente cliente) {
        try {
            String sql = "DELETE FROM cliente WHERE codCliente = ?";

            conectar(sql);
            comando.setInt(1, cliente.getCodCliente());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void atualizar(Cliente cliente) {
        try {
            String sql = "UPDATE cliente SET rg=?, nome=?, datanascimento=?, telefone=? "
                    + "WHERE codCliente=?";

            conectar(sql);
            comando.setString(1, cliente.getRg());
            comando.setString(2, cliente.getNome());

            //Trabalhando com data: convertendo LocalDate -> Date
            Date dataSql = Date.valueOf(cliente.getDataNascimento());
            comando.setDate(3, dataSql);
            comando.setString(4, cliente.getTelefone());
            comando.setInt(5, cliente.getCodCliente());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar cliente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> listaclientes = new ArrayList<>();

        String sql = "SELECT * FROM Cliente";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("codCliente");
                String rg = resultado.getString("rg");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                //Trabalhando com data: convertendo dataSql -> LocalDate
                Date dataSql = resultado.getDate("datanascimento");
                LocalDate dataNascimento = dataSql.toLocalDate();

                Cliente cli = new Cliente(id, rg, nome,telefone, dataNascimento);
                listaclientes.add(cli);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaclientes);
    }

    @Override
    public Cliente procurarPorId(int codCliente) {
        String sql = "SELECT * FROM cliente WHERE codCliente = ?";

        try {
            conectar(sql);
            comando.setInt(1, codCliente);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String rg = resultado.getString("rg");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");

                //Trabalhando com data: convertendo dataSql -> LocalDate
                Date dataSql = resultado.getDate("datanascimento");
                LocalDate dataNascimento = dataSql.toLocalDate();

                Cliente cli = new Cliente(codCliente, rg, nome,telefone, dataNascimento);

                return cli;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o cliente pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Cliente procurarPorRg(String rg) {
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
    public List<Cliente> listarPorNome(String nome) {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("codCliente");
                String rg = resultado.getString("rg");
                String nomeX = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
               //Trabalhando com data: convertendo dataSql -> LocalDate
                Date dataSql = resultado.getDate("datanascimento");
                LocalDate dataNascimento = dataSql.toLocalDate();

                Cliente pac = new Cliente(id, rg, nomeX,telefone, dataNascimento);

                listaClientes.add(pac);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaClientes);
    }
}
