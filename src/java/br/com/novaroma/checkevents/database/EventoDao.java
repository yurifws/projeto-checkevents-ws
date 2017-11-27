/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.database;

import br.com.novaroma.checkevents.dao.DaoFactory;
import br.com.novaroma.checkevents.dao.IEventoDao;
import br.com.novaroma.checkevents.daojinq.GenericDaoJinq;
import br.com.novaroma.checkevents.entities.Contato;
import br.com.novaroma.checkevents.entities.Endereco;
import br.com.novaroma.checkevents.entities.Evento;
import br.com.novaroma.checkevents.entities.Localizacao;
import br.com.novaroma.checkevents.entities.TipoEvento;
import br.com.novaroma.checkevents.entities.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rodrigo alves
 */
public class EventoDao extends GenericDaoJinq<Evento> implements IEventoDao {
    
    protected final double R = 6372.8;    
    
    public EventoDao(Class<Evento> entityClass) {
        super(entityClass);
    }

    @Override
    public Evento inserir(String descricao, Date dataInicio, Date dataTermino, Endereco endereco, Localizacao localizacao, Contato contato, TipoEvento tipoEvento, Usuario organizador) {
        Evento evento = new Evento();
            evento.setDescricao(descricao);
            evento.setDataInicio(new Date());
            evento.setDataTermino(new Date());
            evento.setEndereco(endereco);
            evento.setLocalizacao(localizacao);
            evento.setContato(contato);
            evento.setTipoEvento(tipoEvento);
            evento.setOrganizador(organizador);
            organizador.getEventos().add(evento);
            DaoFactory.getUsuarioDao().update(organizador);
            return evento;
    }

    @Override
    public List<Evento> eventosPorDescricao(String descricao) {
        final String descAux = descricao;
        List<Evento> listaEvento = this.getStream().where(evento -> (evento.getDescricao().equals(descAux))).toList();
//        listaEvento.forEach(evento -> {
//            evento.getContato().setEvento(null);
//            evento.getContato().setUsuario(null);
//            evento.getEndereco().setEvento(null);
//            evento.getLocalizacao().setEvento(null);
//            evento.getOrganizador().setEventos(null);
//            evento.getTipoEvento().setEvento(null);
//            evento.setParticipantes(null);
//        });
        return listaEvento;
    }

    @Override
    public List<Evento> carregarEventos(double distanciaRaio, double latCentral, double lngCentral) {
        List<Evento> eventos = this.getStream().toList();
//                .where(evento -> 
//        (6371 * (Math.acos(
//                    Math.cos(Math.toRadians(latCentrall)) * 
//                    Math.cos(evento.getLocalizacao().getLatitude()) *
//                    Math.cos(Math.toRadians(evento.getLocalizacao().getLongitude()) - Math.toRadians(lngCentral)) +
//                    Math.sin(Math.toRadians(latCentrall)) *
//                    Math.sin(Math.toRadians(evento.getLocalizacao().getLatitude()))                
//                ))) <= distanciaRaio
//            ).toList();
        List<Evento> listaAux = new ArrayList();
        for (Evento evento : eventos) {
            if(haversine(latCentral, lngCentral, evento.getLocalizacao().getLatitude(), evento.getLocalizacao().getLongitude()) <= distanciaRaio){
                listaAux.add(evento);
            }
        }
        return listaAux;
    }
    
    protected double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    
    
}
