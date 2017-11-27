/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.dao;

import br.com.novaroma.checkevents.entities.Endereco;

/**
 *
 * @author rodrigo alves
 */
public interface IEnderecoDao extends IDao<Endereco> {
    
    public Endereco inserir(String logradouro, int numero, int cep, String bairro, String cidade, String uf, String pais, String complemento);
    
}
