/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.dao;

import br.com.novaroma.checkevents.entities.Localizacao;

/**
 *
 * @author rodrigo alves
 */
public interface ILocalizacaoDao extends IDao<Localizacao> {
    
    public Localizacao inserir(float latitude, float longitude);
    
}
