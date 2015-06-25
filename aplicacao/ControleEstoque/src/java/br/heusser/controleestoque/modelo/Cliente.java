package br.heusser.controleestoque.modelo;

import br.heusser.controleestoque.dao.cliente.ClienteDAO;
import br.heusser.controleestoque.dao.core.DAOFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "cliente-listar-tudo",
            query = "SELECT c FROM Cliente c ")
})
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private int telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> compras;

    public Cliente() {
        compras = new ArrayList<>();
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public List<Venda> getCompras() {
        return compras;
    }

    public void setCompras(List<Venda> compras) {
        this.compras = compras;
    }

    public void salvar() {
        ClienteDAO dao = DAOFactory.getInstance().getClienteDAO();
        dao.salvar(this);
    }

    public static List<Cliente> listarTodos() {
        ClienteDAO dao = DAOFactory.getInstance().getClienteDAO();
        return dao.listar("cliente-listar-tudo", new HashMap());
    }

    public static void remover(int id) {
        ClienteDAO dao = DAOFactory.getInstance().getClienteDAO();
        dao.remover(id);
    }
}
