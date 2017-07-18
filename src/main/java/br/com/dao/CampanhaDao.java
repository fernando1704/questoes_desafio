package br.com.dao;

import br.com.entity.Campanha;
import br.com.entity.Cliente;
import java.util.List;

public interface CampanhaDao {

    public List<Campanha> buscarCampanhaPorCliente(Cliente cliente);
    
    public List<Campanha> buscarCampanhaPorTimeId(Integer timeId);

    public void addCampanha(Campanha campanha);

    public void removeCampanha(Campanha campanha);
    
    public List<Campanha> buscarCampanhasConflitantes(Campanha campanhaAdicionada);
    
    public void atualizaDataVigenciaCampanha(Campanha campanha);
}
