
import br.heusser.controleestoque.modelo.Usuario;

/**
 *
 * @author Diego Heusser
 */
public class GerarBanco {
    public static void main(String[] args) {
        Usuario u = new Usuario();
        u.setAdministrador(true);
        u.setCrediario(true);
        u.setVendedor(true);
        u.setFinanceiro(true);
        u.setNome("Administrador");
        u.setEmail("email@email.com");
        u.setSenha("admin");
        
        u.salvar();
        
    }
    
}
