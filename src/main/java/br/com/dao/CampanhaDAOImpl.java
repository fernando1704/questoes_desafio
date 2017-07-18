package br.com.dao;

import br.com.entity.Campanha;
import br.com.entity.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CampanhaDAOImpl implements CampanhaDao {

    public List<Campanha> buscarCampanhaPorCliente(Cliente cliente) {
        List<Campanha> listaCampanhaPorCliente = new ArrayList<Campanha>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM campanha c INNER JOIN cliente_campanha cc ON cc.campanha_id = c.id "
                + " WHERE cliente_id = ? ");
        sql.append("ORDER BY data_vigencia_inicio ");
        try {
            Connection conn = Conexao.abrir();

            PreparedStatement comando = conn.prepareStatement(sql.toString());
            comando.setString(1, "%" + cliente.getId() + "%");

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Campanha campanha = new Campanha();
                campanha.setNome(resultado.getString("nome"));
                campanha.setDataVigenciaInicio(resultado.getDate("data_vigencia_inicio"));
                campanha.setDataVigenciaFim(resultado.getDate("data_vigencia_fim"));
                campanha.setTimeId(resultado.getInt("time_id"));
                listaCampanhaPorCliente.add(campanha);
            }

            resultado.close();
            comando.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return listaCampanhaPorCliente;
    }

    public List<Campanha> buscarCampanhaPorTimeId(Integer timeId) {
        List<Campanha> listaCampanhaPorTimeId = new ArrayList<Campanha>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM campanha c  WHERE time_id = ? ");
        sql.append("ORDER BY data_vigencia_inicio ");
        try {
            Connection conn = Conexao.abrir();

            PreparedStatement comando = conn.prepareStatement(sql.toString());
            comando.setInt(1, timeId);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Campanha campanha = new Campanha();
                campanha.setNome(resultado.getString("nome"));
                campanha.setDataVigenciaInicio(resultado.getDate("data_vigencia_inicio"));
                campanha.setDataVigenciaFim(resultado.getDate("data_vigencia_fim"));
                campanha.setTimeId(resultado.getInt("time_id"));
                listaCampanhaPorTimeId.add(campanha);
            }

            resultado.close();
            comando.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return listaCampanhaPorTimeId;
    }

    public void addCampanha(Campanha campanha) {
        try {
            Connection conn = Conexao.abrir();
            java.sql.Date dataVigenciaInicio = new java.sql.Date(campanha.getDataVigenciaInicio().getTime());
            java.sql.Date dataVigenciaFim = new java.sql.Date(campanha.getDataVigenciaFim().getTime());

            String query = " insert into campanha (nome, data_vigencia_inicio, data_vigencia_fim, time_id)"
                    + " values (?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, campanha.getNome());
            preparedStmt.setDate(2, dataVigenciaInicio);
            preparedStmt.setDate(3, dataVigenciaFim);
            preparedStmt.setInt(4, campanha.getTimeId());
            preparedStmt.execute();

            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /*
    Atualiza a data de vigência de uma campanha, caso exista conflito com alguma outra que está sendo adicionada
     */
    public void atualizaDataVigenciaCampanha(Campanha campanha) {
        try {
            Connection conn = Conexao.abrir();
            java.sql.Date dataVigencia = new java.sql.Date(campanha.getDataVigenciaFim().getTime());

            String query = " update campanha set data_vigencia_fim = ? WHERE id = ? ";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, dataVigencia);
            preparedStmt.setInt(2, campanha.getId());
            preparedStmt.execute();

            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /*
    Busca todas as campanhas no banco de dados que conflitam as datas com a campanha adicionada
     */
    public List<Campanha> buscarCampanhasConflitantes(Campanha campanhaAdicionada) {
        List<Campanha> listaCampanhasConflitantes = new ArrayList<Campanha>();

        StringBuilder sql = new StringBuilder();
        sql.append("select * from campanha c where (data_campanha_inicio between ? and ?) OR (data_campanha_im between ? and ?) ");
        try {
            Connection conn = Conexao.abrir();
            java.sql.Date dataVigenciaInicio = new java.sql.Date(campanhaAdicionada.getDataVigenciaInicio().getTime());
            java.sql.Date dataVigenciaFim = new java.sql.Date(campanhaAdicionada.getDataVigenciaFim().getTime());

            PreparedStatement preparedStmt = conn.prepareStatement(sql.toString());
            preparedStmt.setDate(1, dataVigenciaInicio);
            preparedStmt.setDate(2, dataVigenciaFim);
            preparedStmt.setDate(3, dataVigenciaInicio);
            preparedStmt.setDate(4, dataVigenciaFim);

            ResultSet resultado = preparedStmt.executeQuery();

            while (resultado.next()) {
                Campanha campanha = new Campanha();
                campanha.setId(resultado.getInt("id"));
                campanha.setDataVigenciaInicio(resultado.getDate("data_vigencia_inicio"));
                campanha.setDataVigenciaFim(resultado.getDate("data_vigencia_fim"));
                listaCampanhasConflitantes.add(campanha);
            }

            resultado.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return listaCampanhasConflitantes;
    }

    public void removeCampanha(Campanha campanha) {

        StringBuilder sql = new StringBuilder();
        sql.append("delete from campanha where id = ?");
        try {
            Connection conn = Conexao.abrir();

            PreparedStatement preparedStmt = conn.prepareStatement(sql.toString());
            preparedStmt.setInt(1, campanha.getId());

            preparedStmt.executeQuery();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
