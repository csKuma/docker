package com.erp.autenticador.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Locale;

public class StringPadronization extends StdDeserializer<String> {

    private static final long serialVersionUID = 7527542687158493910L;

    public StringPadronization() {
        super(String.class);
    }

    public static String converter(String string) {
        if (string != null) {
            String stringSemAcento = string.toUpperCase();
            return stringSemAcento;
        }

        return null;
    }


    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            return converter(_parseString(p, ctxt));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public static Boolean verificarSeEString(Object string) {
        if (string instanceof String) {
            return true;
        }
        return false;
    }
}
