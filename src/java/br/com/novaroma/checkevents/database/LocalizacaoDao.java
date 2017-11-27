/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.database;

import br.com.novaroma.checkevents.dao.DaoFactory;
import br.com.novaroma.checkevents.dao.ILocalizacaoDao;
import br.com.novaroma.checkevents.daojinq.GenericDaoJinq;
import br.com.novaroma.checkevents.entities.Localizacao;

/**
 *
 * @author rodrigo alves
 */
public class LocalizacaoDao extends GenericDaoJinq<Localizacao> implements ILocalizacaoDao {
    
    public LocalizacaoDao(Class<Localizacao> entityClass) {
        super(entityClass);
    }

    @Override
    public Localizacao inserir(float latitude, float longitude) {
            Localizacao localizacao = new Localizacao();
            localizacao.setLatitude(latitude);
            localizacao.setLongitude(longitude);
            DaoFactory.getLocalizacaoDao().insert(localizacao);
            return localizacao;
    }
    
    
    
}
