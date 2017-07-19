package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dao.CampanhaDao;
import br.com.entity.Campanha;
import br.com.entity.Cliente;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class CampanhaServiceImpl implements CampanhaService {

    @Autowired
    private CampanhaDao campanhaDao;

    public List<Campanha> listarCampanhas() {
        return this.getCampanhaDao().listarCampanhas();
    }
    public List<Campanha> buscarCampanhaPorCliente(final Cliente cliente) {
        return this.getCampanhaDao().buscarCampanhaPorCliente(cliente);
    }

    public List<Campanha> buscarCampanhaPorTimeId(final Integer timeId) {
        return this.getCampanhaDao().buscarCampanhaPorTimeId(timeId);
    }

    public void addCampanha(Campanha campanha) {
        this.verificaCampanhasConflitantes(campanha);
        this.getCampanhaDao().addCampanha(campanha);
    }

    public void verificaCampanhasConflitantes(Campanha campanha) {
        Calendar calendar = Calendar.getInstance();
        //Verifica se a data de vigência da campanha atual conflita com alguma outra já existente
        List<Campanha> campanhasConflitantes = this.getCampanhaDao().buscarCampanhasConflitantes(campanha);
        //Se existem campanhas conflitantes
        if (campanhasConflitantes.size() > 0) {
            for (Iterator<Campanha> i = campanhasConflitantes.iterator(); i.hasNext();) {
                Campanha campanhaConflitante = i.next();
                calendar.setTime(campanhaConflitante.getDataVigenciaFim());
                calendar.add(Calendar.DATE, 1);
                campanhaConflitante.setDataVigenciaFim(new java.sql.Date(calendar.getTimeInMillis()));
                boolean encontrouData = false;
                //Enquanto não encontrar uma data que resolva o conflito
                while (!encontrouData) {
                    campanhasConflitantes = this.getCampanhaDao().buscarCampanhasConflitantes(campanhaConflitante);
                    if (campanhasConflitantes.size() <= 0) {
                        encontrouData = true;
                    } else {
                        calendar.setTime(campanhaConflitante.getDataVigenciaFim());
                        calendar.add(Calendar.DATE, 1);
                        campanhaConflitante.setDataVigenciaFim(new java.sql.Date(calendar.getTimeInMillis()));
                    }
                }

                //Atualiza a data de fim da campanha conflitante com a última data encontrada que não causou o conflito
                this.getCampanhaDao().atualizaDataVigenciaCampanha(campanhaConflitante);
            }
        }
    }

    public CampanhaDao getCampanhaDao() {
        return campanhaDao;
    }

}
