package br.heusser.controleestoque.dao.jpa;

import br.heusser.controleestoque.dao.core.DAOFactory;
import br.heusser.controleestoque.dao.produto.JPAProdutoDAO;
import br.heusser.controleestoque.dao.produto.ProdutoDAO;
import br.heusser.controleestoque.dao.usuario.JPAUsuarioDAO;
import br.heusser.controleestoque.dao.usuario.UsuarioDAO;

/**
 *
 * @author Diego Heusser
 */
public class JPADAOFactory extends DAOFactory {

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new JPAUsuarioDAO();
    }

    @Override
    public ProdutoDAO getProdutoDAO() {
        return new JPAProdutoDAO();
    }
    
}
