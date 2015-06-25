package br.heusser.controleestoque.modelo;

import br.heusser.controleestoque.dao.core.DAOFactory;
import br.heusser.controleestoque.dao.produto.ProdutoDAO;
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
    @NamedQuery(name = "produto-listar-tudo",
            query = "SELECT p FROM Produto p")
})
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private int id;
    
    @Column(name = "nome", unique = true)
    private String nome;
    
    @Column(name = "preco_unitario", nullable = false)
    private double precoUnitario;
    
    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque;
    
    @Column(name = "unidade_medida", nullable = false)
    private String unidadeMedida;

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

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
    
    public void salvar(){
        ProdutoDAO dao = DAOFactory.getInstance().getProdutoDAO();
        dao.salvar(this);
    }
    
    public static void remover(int id){
        ProdutoDAO dao = DAOFactory.getInstance().getProdutoDAO();
        dao.remover(id);
    }
    
    public static List<Produto> listarTodos(){
        ProdutoDAO dao = DAOFactory.getInstance().getProdutoDAO();
        Map<String, Object> parametros = new HashMap<>();
        return dao.listar("produto-listar-tudo", parametros);
    }
    
}
