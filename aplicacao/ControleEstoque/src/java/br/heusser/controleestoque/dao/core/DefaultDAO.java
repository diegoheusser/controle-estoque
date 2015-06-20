package br.heusser.controleestoque.dao.core;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Diego Heusser
 */
public interface DefaultDAO<T> {
    
    public void salvar(T t);
    
    public void remover(Class c, int id);
    
    public List<T> listar(String consulta, Map<String,Object> parametros);
    
    public T buscar(String consulta, Map<String, Object> parametros);
}
