package PetShopDaoDB;

import PetShopDao.ServicosDao;
import PetShopNegocio.NegocioException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ServicosPets;

public class ServicosDaoBd extends DaoBd<ServicosPets> implements ServicosDao {
  
    @Override
    public void salvar(ServicosPets servicosPets) 
    {
        int id = 0;
        try {
            String sql = "INSERT INTO servicosPets (precoServico, tipoatendimento) "
                    + "VALUES (?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setDouble(1, servicosPets.getPrecoServico());
            comando.setString(2, servicosPets.getTipoAtendimento());

            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                servicosPets.setCodServico(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar Serviços no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    @Override
    public ServicosPets procurarPoTipoDeServico(String TipoAtendimento){
        String sql = "SELECT * FROM servicospets WHERE tipoatendimento LIKE ?";
        try {
            conectar(sql);
             comando.setString(1, "%" + TipoAtendimento + "%");
            
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("CodServico");
                String tipoAtendimento = resultado.getString("tipoAtendimento");
                Double precoServico = resultado.getDouble("precoServico");

                ServicosPets serv = new ServicosPets(id, precoServico,tipoAtendimento );

                return serv;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o tipo de servico pelo rg do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);    
    }
    
    @Override
    public void deletar(ServicosPets servicosPets) {
        try {
            String sql = "DELETE FROM servicosPets WHERE codServico = ?";

            conectar(sql);
            comando.setInt(1, servicosPets.getCodServico());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar Servico no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void atualizar(ServicosPets servicosPets) {
        try {
            String sql = "UPDATE servicospets SET tipoAtendimento=?, precoServico=?"
                    + "WHERE codServico=?";

            conectar(sql);
            comando.setString(1, servicosPets.getTipoAtendimento());
            comando.setDouble(2, servicosPets.getPrecoServico());

            //Trabalhando com data: convertendo LocalDate -> Date
            comando.setInt(3, servicosPets.getCodServico());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar Servico no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<ServicosPets> listar() {
        List<ServicosPets> listaServico = new ArrayList<>();
        String sql = "SELECT * FROM ServicosPets";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codservico = resultado.getInt("codServico");
                String tipoAtendimento = resultado.getString("tipoAtendimento");
                double precoServico = resultado.getDouble("precoServico");

                ServicosPets serv = new ServicosPets(codservico, precoServico,tipoAtendimento);
                listaServico.add(serv);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os servicos do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaServico);
    }

    @Override
    public ServicosPets procurarPorId(int id) {
        String sql = "SELECT * FROM ServicosPets WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codServico = resultado.getInt("codServico");
                String tipoAtendimento = resultado.getString("tipoAtendimento");
                double precServico = resultado.getDouble("precoServico");

                ServicosPets serv = new ServicosPets(codServico, precServico,tipoAtendimento);
                return serv;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o Serviços pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }
    
  

}
