
import br.heusser.controleestoque.criptografia.SHA2;
import br.heusser.controleestoque.modelo.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Diego Heusser
 */
public class GerarBanco {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Usuario u = new Usuario();
        u.setAdministrador(true);
        u.setCrediario(true);
        u.setVendedor(true);
        u.setFinanceiro(true);
        u.setNome("Administrador");
        u.setEmail("email@email.com");
        u.setSenha(SHA2.sha2("admin"));
        
        u.salvar();
        
    }
    
}
