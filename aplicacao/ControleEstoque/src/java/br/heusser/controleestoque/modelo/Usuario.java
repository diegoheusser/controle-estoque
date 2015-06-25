package br.heusser.controleestoque.modelo;

import br.heusser.controleestoque.dao.core.DAOFactory;
import br.heusser.controleestoque.dao.usuario.UsuarioDAO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Diego Heusser
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "usuario-buscar-por-email-senha",
            query = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha"),
    @NamedQuery(name = "usuario-listar-tudo",
            query = "SELECT u FROM Usuario u")
})
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    //Permissões
    @Column(nullable = false)
    private boolean administrador;

    @Column(nullable = false)
    private boolean financeiro;

    @Column(nullable = false)
    private boolean crediario;

    @Column(nullable = false)
    private boolean vendedor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(boolean financeiro) {
        this.financeiro = financeiro;
    }

    public boolean isCrediario() {
        return crediario;
    }

    public void setCrediario(boolean crediario) {
        this.crediario = crediario;
    }

    public boolean isVendedor() {
        return vendedor;
    }

    public void setVendedor(boolean vendedor) {
        this.vendedor = vendedor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermissoes(){
        String p = "";
        if(administrador){
            p += "Administrador\n";
        } 
        if(financeiro){
            p += "Financeiro\n";
        }
        if(crediario){
            p += "Crediário\n";
        }
        if(vendedor){
            p += "Vendedor\n";
        }
        return p;
    }
    
    public void salvar() {
        UsuarioDAO dao = DAOFactory.getInstance().getUsuarioDAO();
        dao.salvar(this);
    }

    public static void remover(int id) {
        UsuarioDAO dao = DAOFactory.getInstance().getUsuarioDAO();
        dao.remover(id);
    }

    public Usuario buscar() {
        UsuarioDAO dao = DAOFactory.getInstance().getUsuarioDAO();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("email", this.email);
        parametros.put("senha", this.senha);
        return dao.buscar("usuario-listar-por-email-senha", parametros);
    }

    public static List<Usuario> listarTodos() {
        UsuarioDAO dao = DAOFactory.getInstance().getUsuarioDAO();
        Map<String, Object> parametros = new HashMap<>();
        return dao.listar("usuario-listar-tudo", parametros);
    }

    public static Usuario buscar(String email, String senha) {
        UsuarioDAO dao = DAOFactory.getInstance().getUsuarioDAO();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("email", email);
        parametros.put("senha", senha);
        return dao.buscar("usuario-buscar-por-email-senha", parametros);
    }
}
