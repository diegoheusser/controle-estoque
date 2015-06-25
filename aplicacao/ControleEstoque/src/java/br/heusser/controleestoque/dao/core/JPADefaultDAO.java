package br.heusser.controleestoque.dao.core;

import br.heusser.controleestoque.dao.jpa.SingletonJPA;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Diego Heusser
 * @param <T>
 */
public abstract class JPADefaultDAO<T extends Object> implements DefaultDAO<T> {

    private final SingletonJPA singleton;
    private EntityManager em;

    public JPADefaultDAO() {
        singleton = SingletonJPA.getInstance();
    }

    public abstract Class getEntityClass();

    @Override
    public void salvar(T t) {
        em = singleton.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remover(int id) {
        em = singleton.getEmf().createEntityManager();
        T t = (T) em.find(getEntityClass(), id);
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<T> listar(String consulta, Map<String, Object> parametros) {
        em = singleton.getEmf().createEntityManager();
        Query q = em.createNamedQuery(consulta);
        for (String chave : parametros.keySet()) {
            q.setParameter(chave, parametros.get(chave));
        }
        List<T> lista = q.getResultList();
        em.close();
        return lista;
    }

    @Override
    public T buscar(String consulta, Map<String, Object> parametros) {
        em = singleton.getEmf().createEntityManager();
        Query q = em.createNamedQuery(consulta);
        for (String chave : parametros.keySet()) {
            q.setParameter(chave, parametros.get(chave));
        }
        T t = null;
        try {
            t = (T) q.getSingleResult();
        }catch(Exception e) {
            t = null;
        }
        em.close();
        return t;
    }

}
