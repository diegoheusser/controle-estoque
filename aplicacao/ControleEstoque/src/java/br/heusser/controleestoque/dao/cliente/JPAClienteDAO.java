package br.heusser.controleestoque.dao.cliente;

import br.heusser.controleestoque.dao.core.JPADefaultDAO;
import br.heusser.controleestoque.modelo.Cliente;

/**
 *
 * @author diego
 */
public class JPAClienteDAO extends JPADefaultDAO<Cliente> implements ClienteDAO {

    @Override
    public Class getEntityClass() {
        return Cliente.class;
    }
    
}
