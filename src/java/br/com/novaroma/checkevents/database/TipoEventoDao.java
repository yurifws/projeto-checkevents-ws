/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.database;

import br.com.novaroma.checkevents.dao.DaoFactory;
import br.com.novaroma.checkevents.dao.ITipoEventoDao;
import br.com.novaroma.checkevents.daojinq.GenericDaoJinq;
import br.com.novaroma.checkevents.entities.TipoEvento;

/**
 *
 * @author rodrigo alves
 */
public class TipoEventoDao extends GenericDaoJinq<TipoEvento> implements ITipoEventoDao {
    
    public TipoEventoDao(Class<TipoEvento> entityClass) {
        super(entityClass);
    }

    @Override
    public TipoEvento inserir(String nome) {
        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setNome(nome);
        DaoFactory.getTipoEventoDao().insert(tipoEvento);
        return tipoEvento;
    }
    
}
