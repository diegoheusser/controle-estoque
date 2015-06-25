package br.heusser.controleestoque.dao.produto;

import br.heusser.controleestoque.dao.core.JPADefaultDAO;
import br.heusser.controleestoque.modelo.Produto;

/**
 *
 * @author Diego Heusser
 */
public class JPAProdutoDAO extends JPADefaultDAO<Produto> implements ProdutoDAO {

    public JPAProdutoDAO() {
        super();
    }

    @Override
    public Class getEntityClass() {
        return Produto.class;
    }
    
}
