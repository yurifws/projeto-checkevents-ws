/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo alves
 */
@Entity
@Table(name = "tipo_evento")
@XmlRootElement
public class TipoEvento extends BaseEntity {

    @Column(name = "nome")
    private String nome;

    @OneToOne(mappedBy = "tipoEvento")
    private Evento evento;

    public TipoEvento() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return " TipoEvento[ id=" + this.getId() + " ]";
    }

}
