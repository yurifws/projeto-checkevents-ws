/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 * @author yurifws
 */
public final class Helper {

    public static String codificar(String string) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, "UTF-8");
    }

    public static String decodificar(String string) throws UnsupportedEncodingException {
        return URLDecoder.decode(string, "UTF-8");
    }
    
    public static String codificar(String string, String enc) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, enc);
    }

    public static String decodificar(String string, String enc) throws UnsupportedEncodingException {
        return URLDecoder.decode(string, enc);
    }
    
    public static boolean verificarCamposNull(String campo){
       return isNullOrEmpty(campo);
    }
    
    protected static boolean isNullOrEmpty(String texto){
        return texto == null || texto.isEmpty();
    }

}
