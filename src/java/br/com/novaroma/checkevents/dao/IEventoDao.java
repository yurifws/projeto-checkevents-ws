/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.dao;

import br.com.novaroma.checkevents.entities.Contato;
import br.com.novaroma.checkevents.entities.Endereco;
import br.com.novaroma.checkevents.entities.Evento;
import br.com.novaroma.checkevents.entities.Localizacao;
import br.com.novaroma.checkevents.entities.TipoEvento;
import br.com.novaroma.checkevents.entities.Usuario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rodrigo alves
 */
public interface IEventoDao extends IDao<Evento> {
    
    public Evento inserir(String descricao, Date dataInicio, Date dataTermino, Endereco endereco, Localizacao localizacao, Contato contato,  TipoEvento tipoEvento, Usuario organizador);
    
    public List<Evento> eventosPorDescricao(String descricao);
    
    public List<Evento> carregarEventos(double distanciaRaio, double latCentrall, double lngCentral);
    
}
