package br.com.entity;

import java.io.Serializable;
import java.util.Date;

public class Campanha implements Serializable {

    private static final long serialVersionUID = 8684785589943206620L;
    private Integer id;
    private String nome;
    private Date dataVigenciaInicio;
    private Date dataVigenciaFim;
    private Integer timeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataVigenciaInicio() {
        return dataVigenciaInicio;
    }

    public void setDataVigenciaInicio(Date dataVigencia) {
        this.dataVigenciaInicio = dataVigencia;
    }
    
    public Date getDataVigenciaFim() {
        return dataVigenciaFim;
    }

    public void setDataVigenciaFim(Date dataVigencia) {
        this.dataVigenciaFim = dataVigencia;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

}
