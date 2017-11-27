package br.com.novaroma.checkevents.database;

import br.com.novaroma.checkevents.dao.DaoFactory;
import br.com.novaroma.checkevents.dao.IContatoDao;
import br.com.novaroma.checkevents.daojinq.GenericDaoJinq;
import br.com.novaroma.checkevents.entities.Contato;
import java.util.List;

public class ContatoDao extends GenericDaoJinq<Contato> implements IContatoDao {

    public ContatoDao(Class<Contato> entityClass) {
        super(entityClass);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Contato inserir(Integer telefone, Integer celular, String email) {
            Contato contato = new Contato();
            contato.setTelefone(telefone);
            contato.setCelular(celular);
            contato.setEmail(email);
            DaoFactory.getContatoDao().insert(contato);
            return contato;
    }

    @Override
    public Contato atualizarPeloId(int id, int telefone, int celular, String email) {
//        List<Contato> listaContatos = DaoFactory.getContatoDao().retrieve();
//        for(Contato contato : listaContatos){
//            if(contato.getUsuario().getId() == usuario.getId()){
//            contato.setUsuario(usuario);
//            contato.setTelefone(telefone);
//            contato.setCelular(celular);
//            contato.setEmail(email);
//            DaoFactory.getContatoDao().update(contato);
//            return contato;
//            }
//           
//
//        }
        Contato contato = DaoFactory.getContatoDao().find(id);
            contato.setTelefone(telefone);
            contato.setCelular(celular);
            contato.setEmail(email);
            DaoFactory.getContatoDao().update(contato);
        return contato;
    }


}
