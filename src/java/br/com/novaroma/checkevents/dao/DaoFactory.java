package br.com.novaroma.checkevents.dao;


import br.com.novaroma.checkevents.database.ContatoDao;
import br.com.novaroma.checkevents.database.EnderecoDao;
import br.com.novaroma.checkevents.database.EventoDao;
import br.com.novaroma.checkevents.database.LocalizacaoDao;
import br.com.novaroma.checkevents.database.TipoEventoDao;
import br.com.novaroma.checkevents.database.UsuarioDao;
import br.com.novaroma.checkevents.entities.Contato;
import br.com.novaroma.checkevents.entities.Endereco;
import br.com.novaroma.checkevents.entities.Evento;
import br.com.novaroma.checkevents.entities.Localizacao;
import br.com.novaroma.checkevents.entities.TipoEvento;
//import br.com.novaroma.checkevents.entities.Evento;
//import br.com.novaroma.checkevents.entities.TipoEvento;
import br.com.novaroma.checkevents.entities.Usuario;



public class DaoFactory {
	
	
	public static IUsuarioDao getUsuarioDao(){
		return new UsuarioDao(Usuario.class);
	}
	
	public static IContatoDao getContatoDao(){
		return new ContatoDao(Contato.class);
	}
	
	public static IEventoDao getEventoDao(){
		return new EventoDao(Evento.class);
	}
	
	public static ITipoEventoDao getTipoEventoDao(){
		return new TipoEventoDao(TipoEvento.class);
	}
        
        public static ILocalizacaoDao getLocalizacaoDao(){
            return new LocalizacaoDao(Localizacao.class);
        }
        
        public static IEnderecoDao getEnderecoDao(){
            return new EnderecoDao(Endereco.class);
        }
}