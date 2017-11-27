/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.daojinq;

/**
 *
 * @author yurifws
 */
import java.io.Serializable;

import org.jinq.orm.stream.JinqStream.Where;

@FunctionalInterface
public interface IWhere<T> extends Where<T, Exception>, Serializable {
	
}
