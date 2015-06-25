package br.heusser.controleestoque.dao.core;

import br.heusser.controleestoque.dao.cliente.ClienteDAO;
import br.heusser.controleestoque.dao.itemvenda.ItemVendaDAO;
import br.heusser.controleestoque.dao.jpa.JPADAOFactory;
import br.heusser.controleestoque.dao.produto.ProdutoDAO;
import br.heusser.controleestoque.dao.usuario.UsuarioDAO;
import br.heusser.controleestoque.dao.venda.VendaDAO;

/**
 *
 * @author Diego Heusser
 */
public abstract class DAOFactory {

    public static DAOFactory getInstance(){
        return new JPADAOFactory();
    }
    
    public abstract UsuarioDAO getUsuarioDAO();
    
    public abstract ProdutoDAO getProdutoDAO();
    
    public abstract ClienteDAO getClienteDAO();
    
    public abstract ItemVendaDAO getItemVendaDAO();
    
    public abstract VendaDAO getVendaDAO();
    
}
