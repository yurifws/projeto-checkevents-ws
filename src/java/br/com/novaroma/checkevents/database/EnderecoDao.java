/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.database;

import br.com.novaroma.checkevents.dao.DaoFactory;
import br.com.novaroma.checkevents.dao.IDao;
import br.com.novaroma.checkevents.dao.IEnderecoDao;
import br.com.novaroma.checkevents.daojinq.GenericDaoJinq;
import br.com.novaroma.checkevents.entities.Endereco;

/**
 *
 * @author rodrigo alves
 */
public class EnderecoDao extends GenericDaoJinq<Endereco> implements IEnderecoDao {
    
    public EnderecoDao(Class<Endereco> entityClass) {
        super(entityClass);
    }

    @Override
    public Endereco inserir(String logradouro, int numero, int cep, String bairro, String cidade, String uf, String pais, String complemento) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(uf);
        endereco.setPais(pais);
        endereco.setComplemento(complemento);
        DaoFactory.getEnderecoDao().insert(endereco);
        return endereco;
    }
    
}
