package br.com.dao;

import br.com.entity.Campanha;
import br.com.entity.Cliente;
import java.util.List;

public interface ClienteDao {

    public Cliente buscarClientePorEmail(Cliente cliente);

    public void addCliente(Cliente cliente);

    public void associaCampanhasAoCliente(List<Campanha> campanhas, Cliente cliente);
    
    public void removeCliente(Cliente cliente);

}
