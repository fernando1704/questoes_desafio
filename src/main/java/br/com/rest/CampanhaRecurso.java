package br.com.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.entity.Campanha;
import br.com.entity.Cliente;
import br.com.service.CampanhaService;
import java.util.List;

@Path("/campanha")
@Component
public class CampanhaRecurso {

    @Autowired
    private CampanhaService campanhaService;

    @GET
    @Path("/{clienteId}")
    @Produces(APPLICATION_JSON)
    public List<Campanha> buscarCampanhaPorCliente(
            @PathParam("clienteId") Cliente cliente) {

        return this.campanhaService.buscarCampanhaPorCliente(cliente);

    }

    @GET
    @Path("/{timeId}")
    @Produces(APPLICATION_JSON)
    public List<Campanha> buscarCampanhaPorTimeId(
            @PathParam("timeId") Integer timeId) {

        return this.campanhaService.buscarCampanhaPorTimeId(timeId);

    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<Campanha> listarTodas() {
        return this.campanhaService.listarCampanhas();

    }

}
