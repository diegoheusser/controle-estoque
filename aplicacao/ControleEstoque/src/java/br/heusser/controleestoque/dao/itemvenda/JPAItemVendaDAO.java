package br.heusser.controleestoque.dao.itemvenda;

import br.heusser.controleestoque.dao.core.JPADefaultDAO;
import br.heusser.controleestoque.modelo.ItemVenda;

/**
 *
 * @author diego
 */
public class JPAItemVendaDAO extends JPADefaultDAO<ItemVenda> implements ItemVendaDAO {

    @Override
    public Class getEntityClass() {
        return ItemVenda.class;
    }
    
}
