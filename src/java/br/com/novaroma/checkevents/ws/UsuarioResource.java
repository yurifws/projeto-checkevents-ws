/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.ws;

import br.com.novaroma.checkevents.dao.DaoFactory;
import br.com.novaroma.checkevents.entities.Contato;
import br.com.novaroma.checkevents.entities.Usuario;
import br.com.novaroma.checkevents.util.Helper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.junit.Test;

/**
 * REST Web Service
 *
 * @author rodrigo alves
 */
@Path("usuario")
public class UsuarioResource {

    @Context
    private UriInfo context;

    private ObjectMapper conversor;

    /**
     * Creates a new instance of checkEvents
     */
    public UsuarioResource() {
        this.conversor = new ObjectMapper();
    }

    /**
     * Retrieves representation of an instance of WebService.checkEvents
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("inserir/{login}/{senha}/{nome}/{sobrenome}/{sexo}/{tipo}/{telefone}/{celular}/{email}")
    public String inserirUsuario(
            @PathParam("login") String login,
            @PathParam("senha") String senha,
            @PathParam("nome") String nome,
            @PathParam("sobrenome") String sobrenome,
            @PathParam("sexo") String sexo,
            @PathParam("tipo") String tipo,
            @PathParam("telefone") int telefone,
            @PathParam("celular") int celular,
            @PathParam("email") String email) {
        Usuario usuario;
        Contato contato;
        try {
            login = Helper.decodificar(login);
            senha = Helper.decodificar(senha);
            nome = Helper.decodificar(nome);
            sobrenome = Helper.decodificar(sobrenome);
            sexo = Helper.decodificar(sexo);
            tipo = Helper.decodificar(tipo);
            email = Helper.decodificar(email);

            contato = DaoFactory.getContatoDao().inserir(telefone, celular, email);
            usuario = DaoFactory.getUsuarioDao().inserir(login, senha, nome, sobrenome, sexo, tipo, contato);
            String usuarioJson = this.getConversor().writeValueAsString(usuario);
            usuario = this.getConversor().readValue(usuarioJson, Usuario.class);
            Gson gson = new Gson();
            return gson.toJson(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{id}")
    public String buscarUsuario(@PathParam("id") int id) {
        try {
            Usuario usuario = DaoFactory.getUsuarioDao().find(id);
            String user = this.getConversor().writeValueAsString(usuario);
            Usuario user2 = this.getConversor().readValue(user, Usuario.class);
            Gson gson = new Gson();
            return gson.toJson(user2);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("verificarLogin/{login}")
    public String verificarLogin(@PathParam("login") String login) {
        try {
            login = Helper.decodificar(login);
            Gson gson = new Gson();
            return gson.toJson(DaoFactory.getUsuarioDao().verificarDisponibilidadeLogin(login));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("logar/{login}/{senha}")
    public String logar(
            @PathParam("login") String login,
            @PathParam("senha") String senha) {
        try {
            String user = this.getConversor().writeValueAsString(DaoFactory.getUsuarioDao().login(login, senha));
            Usuario user2 = this.getConversor().readValue(user, Usuario.class);
            Gson gson = new Gson();
            return gson.toJson(user2);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("atualizarPeloId/{id}/{senha}/{nome}/{sobrenome}/{sexo}/{tipo}/{telefone}/{celular}/{email}")
    public String atualizarUsuarioId(
            @PathParam("id") int id,
            @PathParam("senha") String senha,
            @PathParam("nome") String nome,
            @PathParam("sobrenome") String sobrenome,
            @PathParam("sexo") String sexo,
            @PathParam("tipo") String tipo,
            @PathParam("telefone") int telefone,
            @PathParam("celular") int celular,
            @PathParam("email") String email) {
        Usuario usuario;
        try {
            senha = Helper.decodificar(senha);
            nome = Helper.decodificar(nome);
            sobrenome = Helper.decodificar(sobrenome);
            sexo = Helper.decodificar(sexo);
            tipo = Helper.decodificar(tipo);
            email = Helper.decodificar(email);

            usuario = DaoFactory.getUsuarioDao().atualizarPeloId(id, senha, nome, sobrenome, sexo, tipo, telefone, celular, email);
            String usuarioJson = this.getConversor().writeValueAsString(usuario);
            usuario = this.getConversor().readValue(usuarioJson, Usuario.class);
            Gson gson = new Gson();
            return gson.toJson(usuario);
        } catch (UnsupportedEncodingException | JsonProcessingException ex) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }

    }

    /**
     * PUT method for updating or creating an instance of checkEvents
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
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
