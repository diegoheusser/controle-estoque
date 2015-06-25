package br.heusser.controleestoque.dao.usuario;

import br.heusser.controleestoque.dao.core.JPADefaultDAO;
import br.heusser.controleestoque.modelo.Usuario;

/**
 *
 * @author Diego Heusser
 */
public class JPAUsuarioDAO extends JPADefaultDAO<Usuario> implements UsuarioDAO {

    public JPAUsuarioDAO() {
        super();
    }

    @Override
    public Class getEntityClass() {
        return Usuario.class;
    }

}
