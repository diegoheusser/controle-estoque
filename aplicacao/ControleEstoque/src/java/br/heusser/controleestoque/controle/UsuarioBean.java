package br.heusser.controleestoque.controle;

import br.heusser.controleestoque.modelo.Usuario;
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

    private Usuario usuarioLogado;
    private Usuario usuario;
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {
        this.usuarioLogado = new Usuario();
        updateUsuarios();
    }

    private void updateUsuarios() {
        this.usuarios = Usuario.listarTodos();
    }
    
    public String cancelar(){
        this.usuario = new Usuario();
        return "/usuario/consulta";
    }
    
    public String alterar(Usuario u){
        this.usuario = u;
        return "/usuario/cadastro";
    }

    public String salvar() {
        try {
            this.usuario.salvar();
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "Salvo", ""
                    )
            );
            updateUsuarios();
            return "/usuario/consulta";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()
                    )
            );
            return "/usuario/cadastro";
        }

    }
    
    public void excluir(Usuario u){
        try{
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
    
    public String novo(){
        this.usuario = new Usuario();
        return "/usuario/cadastro";
    }

    public String logar() {
        System.out.println("Entrouu");
        this.usuarioLogado = usuarioLogado.buscar();
        if (usuarioLogado == null) {
            System.out.println("nulo");
            return "index";
        } else {
            return "/usuario/consulta";
        }
        
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
