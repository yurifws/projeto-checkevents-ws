/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.ws;

import br.com.novaroma.checkevents.dao.DaoFactory;
import br.com.novaroma.checkevents.entities.Contato;
import br.com.novaroma.checkevents.entities.Endereco;
import br.com.novaroma.checkevents.entities.Evento;
import br.com.novaroma.checkevents.entities.Localizacao;
import br.com.novaroma.checkevents.entities.TipoEvento;
import br.com.novaroma.checkevents.entities.Usuario;
import br.com.novaroma.checkevents.util.Helper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author yurifws
 */
@Path("evento")
public class EventoResource {

    @Context
    private UriInfo context;
    
    private ObjectMapper conversor;

    /**
     * Creates a new instance of EventoResource
     */
    public EventoResource() {
        this.conversor = new ObjectMapper();
    }

    /**
     * Retrieves representation of an instance of
     * br.com.novaroma.checkevents.ws.EventoResource
     *
     * @param descricao
     * @param logradouro
     * @param numero
     * @param cep
     * @param bairro
     * @param cidade
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("inserir/{descricao}/{logradouro}/{numero}/{cep}/{bairro}/{cidade}/{uf}/{pais}/{complemento}/{latitude}/{longitude}/{telefone}/{celular}/{email}/{nomeTipoEvento}/{idOrganizador}")
    public String inserirEvento(@PathParam("descricao") String descricao,
            @PathParam("logradouro") String logradouro,
            @PathParam("numero") int numero,
            @PathParam("cep") int cep,
            @PathParam("bairro") String bairro,
            @PathParam("cidade") String cidade,
            @PathParam("uf") String uf,
            @PathParam("pais") String pais,
            @PathParam("complemento") String complemento,
            @PathParam("latitude") float latitude,
            @PathParam("longitude") float longitude,
            @PathParam("telefone") int telefone,
            @PathParam("celular") int celular,
            @PathParam("email") String email,
            @PathParam("nomeTipoEvento") String nomeTipoEvento,
            @PathParam("idOrganizador") int idOrganizador) {
        Evento evento;
        Endereco endereco;
        Localizacao localizacao;
        Contato contato;
        TipoEvento tipoEvento;
        Usuario organizador; //para teste no ws
        try {
            descricao = Helper.decodificar(descricao);

            logradouro = Helper.decodificar(logradouro);
            bairro = Helper.decodificar(bairro);
            cidade = Helper.decodificar(cidade);
            uf = Helper.decodificar(uf);
            pais = Helper.decodificar(pais);
            complemento = Helper.decodificar(complemento);

            nomeTipoEvento = Helper.decodificar(nomeTipoEvento);

            endereco = DaoFactory.getEnderecoDao().inserir(logradouro, numero, cep, bairro, cidade, uf, pais, complemento);
            localizacao = DaoFactory.getLocalizacaoDao().inserir(latitude, longitude);
            contato = DaoFactory.getContatoDao().inserir(telefone, celular, email);
            tipoEvento = DaoFactory.getTipoEventoDao().inserir(nomeTipoEvento);
            organizador = DaoFactory.getUsuarioDao().find(idOrganizador); //para teste no ws
            evento = DaoFactory.getEventoDao().inserir(descricao, new Date(), new Date(), endereco, localizacao, contato, tipoEvento, organizador);
            //organizador.setEventos(new ArrayList());
            //organizador.getEventos().add(evento);
            //DaoFactory.getUsuarioDao().update(organizador);
            String eventoJson = this.getConversor().writeValueAsString(evento);
            Gson gson = new Gson();
            return gson.toJson(this.getConversor().readValue(eventoJson, Evento.class));// retorno pra teste
        } catch (UnsupportedEncodingException | JsonProcessingException encodingEx) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, encodingEx);
            return encodingEx.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{id}")
    public String buscarEvento(@PathParam("id") int id) {
        try {
            Evento evento = DaoFactory.getEventoDao().find(id);
            String eventoJson = this.getConversor().writeValueAsString(evento);
            Gson gson = new Gson();
            return gson.toJson(this.getConversor().readValue(eventoJson, Evento.class));// retorno pra teste
        } catch (UnsupportedEncodingException | JsonProcessingException encodingEx) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, encodingEx);
            return encodingEx.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscarPorDesc/{desc}")
    public String buscarEventoPorDesc(@PathParam("desc") String desc) {
        try {
            List<Evento> eventos = DaoFactory.getEventoDao().eventosPorDescricao(desc);
            String eventosJson = this.getConversor().writeValueAsString(eventos);
            eventos = this.getConversor().readValue(eventosJson, new TypeReference<List<Evento>>(){});
            Gson gson = new Gson();
            return gson.toJson(eventos);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscarProximos/{km}/{latCentral}/{lngCentral}")
    public String buscarEventoProximos(@PathParam("km") double km, 
                                       @PathParam("latCentral") double latCentral,
                                       @PathParam("lngCentral") double lngCentral) {
        try {
            String eventosJson = this.getConversor().writeValueAsString(DaoFactory.getEventoDao().carregarEventos(km, latCentral, lngCentral));
            List<Evento> eventos = this.getConversor().readValue(eventosJson, new TypeReference<List<Evento>>(){});
            Gson gson = new Gson();
            return gson.toJson(eventos);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    /**
     * PUT method for updating or creating an instance of EventoResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(String content) {
    }

    /**
     * @return the conversor
     */
    public ObjectMapper getConversor() {
        return conversor;
    }

    /**
     * @param conversor the conversor to set
     */
    public void setConversor(ObjectMapper conversor) {
        this.conversor = conversor;
    }
}
