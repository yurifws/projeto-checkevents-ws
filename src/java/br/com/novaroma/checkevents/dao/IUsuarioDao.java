package br.com.novaroma.checkevents.dao;

import br.com.novaroma.checkevents.entities.Contato;
import br.com.novaroma.checkevents.entities.Usuario;
import java.util.List;
import java.util.Map;

public interface IUsuarioDao extends IDao<Usuario> {

    public boolean verificarDisponibilidadeLogin(String login);
    
    public Usuario login(String login, String senha);
    
    public Usuario inserir(String login, String senha, String nome, String sobrenome, String sexo, String tipo, Contato contato);
    
    public Usuario atualizarPeloId(int id, String senha, String nome, String sobrenome, String sexo, String tipo, int telefone, int celular, String email); 
            //String dataNascimento,       
    
}
