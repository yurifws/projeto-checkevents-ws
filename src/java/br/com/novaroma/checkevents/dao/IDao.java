/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.novaroma.checkevents.dao;

/**
 *
 * @author yurifws
 */
import br.com.novaroma.checkevents.daojinq.IWhere;
import br.com.novaroma.checkevents.entities.BaseEntity;
import java.util.List;

public interface IDao<T extends BaseEntity> {
	void insert(T t);
	void update(T t);
	void delete(int id);
	T find(int id);
	List<T> retrieve();
	List<T> retrieve(IWhere<T> predicate);
}
