/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.serializer;

import br.com.novaroma.checkevents.entities.TipoEvento;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 *
 * @author rodrigo alves
 */
public class CustomTipoEventoSerializer extends StdSerializer<TipoEvento> {

    public CustomTipoEventoSerializer() {
        this(null);
    }

    public CustomTipoEventoSerializer(Class<TipoEvento> t) {
        super(t);
    }

    @Override
    public void serialize(
            TipoEvento tipoEvento,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        tipoEvento.setEvento(null);
        
        generator.writeObject(tipoEvento);
    }
}
