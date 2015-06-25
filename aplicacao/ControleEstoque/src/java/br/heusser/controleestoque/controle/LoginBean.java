package br.heusser.controleestoque.controle;

import br.heusser.controleestoque.criptografia.SHA2;
import br.heusser.controleestoque.modelo.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author diego
 */
@ManagedBean(name = "beanLogin")
@SessionScoped
public class LoginBean {
    
    private final String telaLogin = "/login";
    private final String telaIndex = "/sistema/index";
    
    private Usuario usuario;
    private String email;
    private String senha;
    
    public String logar() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        usuario = Usuario.buscar(email, SHA2.sha2(senha));
        if (usuario == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_WARN,
                            "Acesso negado",
                            "E-mail ou senha inválidos"
                    )
            );
            return telaLogin;
        }
        email = "";
        senha = "";
        return telaIndex;
    }

    public String logoff() {
        this.usuario = null;
        HttpSession session
                = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        return telaLogin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
