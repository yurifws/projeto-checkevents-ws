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
import br.com.novaroma.checkevents.dao.IDao;
import br.com.novaroma.checkevents.entities.BaseEntity;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

public class GenericDaoJinq<Entity extends BaseEntity> implements IDao<Entity> {

    private EntityManagerFactory factory;

    private Class<Entity> entityClass;
    protected static JinqJPAStreamProvider streams;

    public GenericDaoJinq(Class<Entity> entityClass) {
        this.entityClass = entityClass;

        this.factory = EntityManagerFactoryLocator.getFactory();
        streams = new JinqJPAStreamProvider(factory);
    }

    @Override
    public void insert(final Entity entity) {
        executeNoResult(manager -> manager.persist(entity));
//        return execute(manager -> manager.merge(entity));
    }

    @Override
    public void update(final Entity entity) {
        executeNoResult(manager -> manager.merge(entity));
    }

    @Override
    public Entity find(final int id) {
        return execute(manager -> manager.find(entityClass, id));
    }

    @Override
    public void delete(final int id) {
        executeNoResult(
                manager -> {
                    Entity savedEntity = manager.find(entityClass, id);
                    manager.remove(savedEntity);
                }
        );
    }

    private void executeNoResult(Consumer<EntityManager> consumer) {
        execute(
                manager -> {
                    consumer.accept(manager);
                    return null;
                }
        );
    }

    private Entity execute(Function<EntityManager, Entity> func) {
        EntityManager manager = null;
        EntityTransaction transaction = null;

        try {
            manager = this.factory.createEntityManager();
            transaction = manager.getTransaction();
            transaction.begin();

            Entity result = func.apply(manager);

            manager.flush();
            transaction.commit();

            return result;
        } catch (RuntimeException exception) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (RuntimeException nestedException) {
                }
            }
            throw exception;
        } finally {
            closeManager(manager);
        }
    }

    @Override
    public List<Entity> retrieve() {
        return retrieve(entity -> true);
    }

    protected JinqStream<Entity> getStream() {
        EntityManager manager = this.factory.createEntityManager();

        return streams.streamAll(manager, entityClass);
    }

    public List<Entity> retrieve(IWhere<Entity> where) {
        EntityManager manager = null;

        try {
            manager = this.factory.createEntityManager();

            JinqStream<Entity> stream = streams.streamAll(manager, entityClass);

            return stream.where(where).toList();
        } finally {
            closeManager(manager);
        }
    }

    protected void closeManager(EntityManager manager) {
        try {
            if (manager != null) {
                manager.close();
            }
        } catch (RuntimeException e) {
        }
    }
}
