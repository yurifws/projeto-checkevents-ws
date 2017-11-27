/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.serializer;

import br.com.novaroma.checkevents.entities.Usuario;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo alves
 */
public class CustomUsuarioParticipanteSerializer extends StdSerializer<List<Usuario>> {

    public CustomUsuarioParticipanteSerializer() {
        this(null);
    }

    public CustomUsuarioParticipanteSerializer(Class<List<Usuario>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Usuario> items,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

//        List<Integer> ids = new ArrayList<Integer>();
//        for (Usuario item : items) {
//            item.setOrganizador(null);
//        }
        if (items.isEmpty()) {
            items.stream().forEach(usuario -> {
                usuario.setEventosParticipados(null);
            });
        } else {
            items = null;
        }

        generator.writeObject(items);
    }

}
