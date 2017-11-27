/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.dao;

import br.com.novaroma.checkevents.entities.TipoEvento;

/**
 *
 * @author rodrigo alves
 */
public interface ITipoEventoDao extends IDao<TipoEvento> {
    
    public TipoEvento inserir(String nome);
    
}
