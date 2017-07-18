package br.com.service;

import br.com.entity.Campanha;
import br.com.entity.Cliente;
import java.util.List;

public interface ClienteService {

    public void addCliente(Cliente cliente);
    public List<Campanha> verificaClientesConflitantes(Cliente cliente);
}
