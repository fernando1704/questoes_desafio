package br.com.dao;

import br.com.entity.Cliente;
import br.com.entity.Campanha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClienteDAOImpl implements ClienteDao {

    public Cliente buscarClientePorEmail(Cliente clienteBusca) {
        List<Cliente> lista = new ArrayList<Cliente>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM cliente WHERE email = ? ");
        try {
            Connection conn = Conexao.abrir();

            PreparedStatement comando = conn.prepareStatement(sql.toString());
            comando.setString(1, "%" + clienteBusca.getEmail() + "%");

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTimeId(resultado.getInt("time_id"));
                lista.add(cliente);
            }

            resultado.close();
            comando.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lista.get(0);
    }

    public void addCliente(Cliente cliente) {
        try {
            Connection conn = Conexao.abrir();

            String query = " insert into cliente (nome, email, time_id)"
                    + " values (?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, cliente.getNome());
            preparedStmt.setString(2, cliente.getEmail());
            preparedStmt.setInt(3, cliente.getTimeId());
            preparedStmt.execute();

            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeCliente(Cliente cliente) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from cliente where id = ?");
        try {
            Connection conn = Conexao.abrir();

            PreparedStatement preparedStmt = conn.prepareStatement(sql.toString());
            preparedStmt.setInt(1, cliente.getId());

            preparedStmt.executeQuery();

            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void associaCampanhasAoCliente(List<Campanha> campanhas, Cliente cliente) {
        for (Iterator<Campanha> i = campanhas.iterator(); i.hasNext();) {
            Campanha campanha = i.next();
            try {
                Connection conn = Conexao.abrir();

                String query = " insert into cliente_campanhas (cliente_id, campanha_id, data_inclusao)"
                        + " values (?, ?, ?)";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, cliente.getId());
                preparedStmt.setInt(2, campanha.getId());
                preparedStmt.setDate(3, new java.sql.Date(new Date().getTime()));
                preparedStmt.execute();

                conn.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }
}
