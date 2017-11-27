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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryLocator {	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("CheckEventsWSPU");
	
	public static EntityManagerFactory getFactory() {
		return factory;
	}
}
