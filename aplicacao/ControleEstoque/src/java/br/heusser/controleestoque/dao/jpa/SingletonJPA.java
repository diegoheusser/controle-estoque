package br.heusser.controleestoque.dao.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Diego Heusser
 */
public class SingletonJPA {

    private static SingletonJPA instance;
    private final EntityManagerFactory emf;
    
    private SingletonJPA() {
        emf = Persistence.createEntityManagerFactory("ControleEstoquePU");
    }
    
    public static SingletonJPA getInstance() {
        if(instance == null){
            instance = new SingletonJPA();
        }
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
 
}
