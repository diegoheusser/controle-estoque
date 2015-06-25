package br.heusser.controleestoque.dao.jpa;

import br.heusser.controleestoque.dao.cliente.ClienteDAO;
import br.heusser.controleestoque.dao.cliente.JPAClienteDAO;
import br.heusser.controleestoque.dao.core.DAOFactory;
import br.heusser.controleestoque.dao.itemvenda.ItemVendaDAO;
import br.heusser.controleestoque.dao.itemvenda.JPAItemVendaDAO;
import br.heusser.controleestoque.dao.produto.JPAProdutoDAO;
import br.heusser.controleestoque.dao.produto.ProdutoDAO;
import br.heusser.controleestoque.dao.usuario.JPAUsuarioDAO;
import br.heusser.controleestoque.dao.usuario.UsuarioDAO;
import br.heusser.controleestoque.dao.venda.JPAVendaDAO;
import br.heusser.controleestoque.dao.venda.VendaDAO;

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

    @Override
    public ClienteDAO getClienteDAO() {
        return new JPAClienteDAO();
    }

    @Override
    public ItemVendaDAO getItemVendaDAO() {
        return new JPAItemVendaDAO();
    }

    @Override
    public VendaDAO getVendaDAO() {
        return new JPAVendaDAO();
    }
    
}
