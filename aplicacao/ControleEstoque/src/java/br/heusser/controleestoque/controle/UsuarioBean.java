package br.heusser.controleestoque.controle;

import br.heusser.controleestoque.criptografia.SHA2;
import br.heusser.controleestoque.modelo.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diego Heusser
 */
@ManagedBean(name = "beanUsuario")
@SessionScoped
public class UsuarioBean {

    private final String telaCadastro = "/sistema/usuario/cadastro";
    private final String telaConsulta = "/sistema/usuario/consulta";
    private final String telaAlterar = "/sistema/usuario/alterar";
    private final String telaRedefinirSenha = "/sistema/usuario/redefinir";

    private Usuario usuario;
    private List<Usuario> usuarios;
    private String senhaAntiga;
    private String senhaNova;

    @PostConstruct
    public void init() {
        this.updateUsuarios();
    }

    public void updateUsuarios() {
        this.usuarios = Usuario.listarTodos();
    }

    public String alterar(Usuario usu) {
        this.usuario = usu;
        return telaAlterar;
    }

    public String redefinir() {
        return telaRedefinirSenha;
    }

    public String salvarRedefinir() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (SHA2.sha2(senhaAntiga).equals(usuario.getSenha())) {
            this.usuario.setSenha(senhaNova);
        }
        this.senhaAntiga = "";
        this.senhaNova = "";
        return telaAlterar;
    }

    public String cancelarRedefinir() {
        this.senhaAntiga = "";
        this.senhaNova = "";
        return telaAlterar;
    }
    
    public String gravar() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        this.usuario.setSenha(SHA2.sha2(senhaNova));
        return salvar();
    }

    public String salvar() {
        try {
            this.usuario.salvar();
            this.senhaNova = "";
            updateUsuarios();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "Erro", ex.getMessage()));
            return telaCadastro;
        }
        return telaConsulta;
    }

    public String cancelar() {
        this.usuario = new Usuario();
        return telaConsulta;
    }

    public String novo() {
        this.usuario = new Usuario();
        return telaCadastro;
    }

    public void excluir(Usuario u) {
        try {
            Usuario.remover(u.getId());
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "Salvo", ""
                    )
            );
            updateUsuarios();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()
                    )
            );
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

}
