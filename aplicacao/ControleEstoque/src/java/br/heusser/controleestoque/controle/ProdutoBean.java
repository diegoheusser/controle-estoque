package br.heusser.controleestoque.controle;

import br.heusser.controleestoque.modelo.Produto;
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
@ManagedBean(name = "beanProduto")
@SessionScoped
public class ProdutoBean {
    
    private Produto produto;
    private List<Produto> produtos;

    @PostConstruct
    public void init() {
        this.produto = new Produto();
        updateProdutos();
    }

    private void updateProdutos() {
        this.produtos = Produto.listarTodos();
    }

    public String cancelar() {
        this.produto = new Produto();
        return "/produto/consulta";
    }

    public String alterar(Produto p) {
        this.produto = p;
        return "/produto/cadastro";
    }

    public String salvar() {
        try {
            this.produto.salvar();
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "Salvo", ""
                    )
            );
            updateProdutos();
            return "/produto/consulta";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()
                    )
            );
            return "/produto/cadastro";
        }

    }

    public void excluir(Produto p) {
        try {
            Produto.remover(p.getId());
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "Salvo", ""
                    )
            );
            updateProdutos();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()
                    )
            );
        }
    }

    public String novo() {
        this.produto = new Produto();
        return "/produto/cadastro";
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
