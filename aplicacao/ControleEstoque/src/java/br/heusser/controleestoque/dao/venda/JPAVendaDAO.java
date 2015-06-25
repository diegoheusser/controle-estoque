package br.heusser.controleestoque.dao.venda;

import br.heusser.controleestoque.dao.core.JPADefaultDAO;
import br.heusser.controleestoque.modelo.Venda;

/**
 *
 * @author diego
 */
public class JPAVendaDAO extends JPADefaultDAO<Venda> implements VendaDAO {

    @Override
    public Class getEntityClass() {
        return Venda.class;
    }
    
}
