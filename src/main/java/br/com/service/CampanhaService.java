package br.com.service;

import br.com.entity.Campanha;
import br.com.entity.Cliente;
import java.util.List;

public interface CampanhaService {

    public List<Campanha> buscarCampanhaPorCliente(final Cliente cliente);
    public void addCampanha(Campanha campanha);
    public List<Campanha> buscarCampanhaPorTimeId(final Integer timeId);
    
}
