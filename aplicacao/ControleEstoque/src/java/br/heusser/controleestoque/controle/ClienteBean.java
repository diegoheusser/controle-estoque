package br.heusser.controleestoque.controle;

import br.heusser.controleestoque.modelo.Cliente;
import br.heusser.controleestoque.modelo.Produto;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author diego
 */
@ManagedBean(name = "beanCliente")
@SessionScoped
public class ClienteBean {

    private final String telaConsulta = "/sistema/cliente/consulta";
    private final String telaCadastro = "/sistema/cliente/cadastro";

    private Cliente cliente;
    private List<Cliente> clientes;

    @PostConstruct
    public void init() {
        cliente = new Cliente();
        updateClientes();
    }

    private void updateClientes() {
        this.clientes = Cliente.listarTodos();
    }

    public String cancelar() {
        this.cliente = new Cliente();
        return telaConsulta;
    }

    public String alterar(Cliente c) {
        this.cliente = c;
        return telaCadastro;
    }

    public String salvar() {
        try {
            this.cliente.salvar();
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "Salvo", ""
                    )
            );
            updateClientes();
            return telaConsulta;
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()
                    )
            );
            return telaCadastro;
        }

    }

    public void excluir(Cliente c) {
        try {
            Cliente.remover(c.getId());
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "Salvo", ""
                    )
            );
            updateClientes();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()
                    )
            );
        }
    }

    public String novo() {
        this.cliente = new Cliente();
        return telaCadastro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}
