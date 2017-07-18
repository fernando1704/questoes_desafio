package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dao.ClienteDao;
import br.com.dao.CampanhaDao;
import br.com.entity.Campanha;
import br.com.entity.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;
    private CampanhaDao campanhaDao;

    public void addCliente(Cliente cliente) {
        this.verificaClientesConflitantes(cliente);
        this.getClienteDao().addCliente(cliente);
        this.getClienteDao().associaCampanhasAoCliente(getCampanhaDao().buscarCampanhaPorTimeId(cliente.getTimeId()), cliente);
    }

    public List<Campanha> verificaClientesConflitantes(Cliente cliente) {
        List<Campanha> campanhas = new ArrayList<Campanha>();
        //Verifica se o email a ser cadastrado já existe na base da dados
        Cliente clienteEmail = this.getClienteDao().buscarClientePorEmail(cliente);
        //Se existem clientes conflitantes, retorna a lista de campanhas do time desse cliente
        if (clienteEmail != null) {
            campanhas = this.getCampanhaDao().buscarCampanhaPorTimeId(clienteEmail.getTimeId());
        }
        return campanhas;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }
    
    public CampanhaDao getCampanhaDao() {
        return campanhaDao;
    }

}
