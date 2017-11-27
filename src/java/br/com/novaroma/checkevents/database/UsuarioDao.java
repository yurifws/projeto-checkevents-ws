package br.com.novaroma.checkevents.database;

import br.com.novaroma.checkevents.dao.DaoFactory;
import br.com.novaroma.checkevents.dao.IUsuarioDao;
import br.com.novaroma.checkevents.daojinq.GenericDaoJinq;
import br.com.novaroma.checkevents.entities.Contato;
import br.com.novaroma.checkevents.entities.Evento;
import br.com.novaroma.checkevents.entities.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UsuarioDao extends GenericDaoJinq<Usuario> implements IUsuarioDao {

    public UsuarioDao(Class<Usuario> entityClass) {
        super(entityClass);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean verificarDisponibilidadeLogin(String login) {
//        List<Usuario> listaUsuario = DaoFactory.getUsuarioDao().retrieve();
//        for (Usuario usuario : listaUsuario) {
//            if (usuario.getLogin().equalsIgnoreCase(login)) {
//                return true;
//            }
//        }
//        return false;
        final String loginAux = login;
        long total = this.getStream().where(usuario -> usuario.getLogin().equals(loginAux)).count();
        return total > 0;
    }

    @Override
    public Usuario login(String login, String senha) {
        List<Usuario> listaUsuario = DaoFactory.getUsuarioDao().retrieve();
        for (Usuario usuario : listaUsuario) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario inserir(String login, String senha, String nome, String sobrenome, String sexo, String tipo, Contato contato) {
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setTipoUsuario(tipo.equalsIgnoreCase("Organizador"));
        usuario.setSexo(sexo);
        usuario.setContato(contato);
        //Collection<Evento> eventos = new ArrayList();
        //usuario.setEventos(eventos);
        DaoFactory.getUsuarioDao().insert(usuario);
        return usuario;
    }

    @Override
    public Usuario atualizarPeloId(int id, String senha, String nome, String sobrenome, String sexo, String tipo, int telefone, int celular, String email) {
        Usuario usuario = DaoFactory.getUsuarioDao().find(id);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setSexo(sexo);
        usuario.setTipoUsuario(tipo.equalsIgnoreCase("Organizador"));
        usuario.getContato().setTelefone(telefone);
        usuario.getContato().setCelular(celular);
        usuario.getContato().setEmail(email);
        
        DaoFactory.getUsuarioDao().update(usuario);
        DaoFactory.getContatoDao().update(usuario.getContato());
        return usuario;
    }

}
