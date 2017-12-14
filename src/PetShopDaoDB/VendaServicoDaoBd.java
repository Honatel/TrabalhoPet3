package PetShopDaoDB;

import PetShopDao.VendaServicoDao;
import PetShopNegocio.ClienteNegocio;
import PetShopNegocio.NegocioException;
import PetShopNegocio.ServicosNegocio;
import PetShopNegocio.VendaServicoNegocio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.VendaServico;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.ServicosPets;

public class VendaServicoDaoBd extends DaoBd<VendaServico> implements VendaServicoDao {
  
    private ClienteNegocio clienteNegocio;
    private ServicosNegocio servicosNegocio;
    
    public VendaServicoDaoBd(){
      clienteNegocio = new ClienteNegocio();
      servicosNegocio = new ServicosNegocio();
    
    }

    
    @Override
    public void salvar(VendaServico vendaServico) 
    {
        int id = 0;
        try {
            String sql = "INSERT INTO vendaServico (dataVenda, codServico, codCliente) "
                    + "VALUES (?,?,?)";

            conectarObtendoId(sql);
            
            Date dataSql = Date.valueOf(vendaServico.getDataVenda());
            comando.setDate(1, dataSql); 
            comando.setInt(2, vendaServico.getServico().getCodServico());
            comando.setInt(3, vendaServico.getCliente().getCodCliente());
           


            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                vendaServico.setCodVenda(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar venda no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void deletar(VendaServico vendaServico) {
        try {
            String sql = "DELETE FROM vendaServico WHERE id = ?";

            conectar(sql);
            comando.setInt(1, vendaServico.getCodVenda());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar venda no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }


    @Override
    public List<VendaServico> listar() {
        List<VendaServico> vendaServicos = new ArrayList<>();
        String sql = "SELECT * FROM vendaServico";

        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codCliente = resultado.getInt("codCliente");
                int codServico = resultado.getInt("codServico");
                
                Cliente cliente = clienteNegocio.procurarPorId(codCliente);
                ServicosPets servico = servicosNegocio.procurarPorId(codServico);

                VendaServico vendaServico = new VendaServico(cliente,servico);
                
                vendaServicos.add(vendaServico);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os venda do Banco de Dados!");
            throw new BDException(ex);
        } catch (NegocioException ex) {
            Logger.getLogger(VendaServicoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (vendaServicos);
    }

    @Override
    public VendaServico procurarPorId(int id) {
        String sql = "SELECT * FROM VendaServico WHERE CodVenda = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codVenda = resultado.getInt("codVenda");
                int codCliente = resultado.getInt("codCliente");
                int codServico = resultado.getInt("codServico");

                VendaServico vendaServico = new VendaServico(codVenda, codCliente,codServico);
                
                return vendaServico;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o venda pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public void atualizar(VendaServico paciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
