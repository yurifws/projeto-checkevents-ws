/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.entities;

import br.com.novaroma.checkevents.serializer.CustomContatoSerializer;
import br.com.novaroma.checkevents.serializer.CustomEnderecoSerializer;
import br.com.novaroma.checkevents.serializer.CustomEventoOrganizadorSerializer;
import br.com.novaroma.checkevents.serializer.CustomLocalizacaoSerializer;
import br.com.novaroma.checkevents.serializer.CustomTipoEventoSerializer;
import br.com.novaroma.checkevents.serializer.CustomUsuarioParticipanteSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo alves
 */
@Entity
@Table(name = "evento")
@XmlRootElement
public class Evento extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    @JsonSerialize(using = CustomEnderecoSerializer.class)
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "localizacao_id")
    @JsonSerialize(using = CustomLocalizacaoSerializer.class)
    private Localizacao localizacao;

    @OneToOne
    @JoinColumn(name = "contato_id")
    @JsonSerialize(using = CustomContatoSerializer.class)
    private Contato contato;

    @OneToOne
    @JoinColumn(name = "tipo_evento_id")
    @JsonSerialize(using = CustomTipoEventoSerializer.class)
    private TipoEvento tipoEvento;

    @ManyToOne
    private Usuario organizador;

    @ManyToMany(mappedBy = "eventosParticipados")
    @JsonSerialize(using = CustomUsuarioParticipanteSerializer.class)
    private List<Usuario> participantes;

    public Evento() {
    }

    public Evento(String descricao, Date dataInicio, Date dataTermino, Endereco endereco, Localizacao localizacao, Contato contato, TipoEvento tipoEvento, Usuario organizador) {
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.endereco = endereco;
        this.localizacao = localizacao;
        this.contato = contato;
        this.tipoEvento = tipoEvento;
        this.organizador = organizador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    @Override
    public String toString() {
        return " Evento[ id=" + this.getId() + " ]";
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

}
