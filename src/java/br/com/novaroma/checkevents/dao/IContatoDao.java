package br.com.novaroma.checkevents.dao;

import br.com.novaroma.checkevents.entities.Contato;

public interface IContatoDao extends IDao<Contato> {
    
public Contato inserir(Integer telefone, Integer celular, String email);

public Contato atualizarPeloId(int id, int telefone, int celular, String email);

}
