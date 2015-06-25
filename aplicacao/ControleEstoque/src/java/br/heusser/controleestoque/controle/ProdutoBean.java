package br.heusser.controleestoque.controle;

import br.heusser.controleestoque.modelo.Produto;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Diego Heusser
 */
@ManagedBean(name = "beanProduto")
@SessionScoped
public class ProdutoBean {
    
    private final String telaConsulta = "/sistema/produto/consulta";
    private final String telaCadastro = "/sistema/produto/cadastro";
    private final String telaEntrada = "/sistema/produto/entrada";
    
    private Produto produto;
    private List<Produto> produtos;
    private int quantidadeAdquirida;

    @PostConstruct
    public void init() {
        this.produto = new Produto();
        updateProdutos();
    }
    
        public void relatorioEstoque(String nomeRelatorio) throws JRException, IOException, SQLException, NamingException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ext = context.getExternalContext();

        InputStream fis = ext.getResourceAsStream("/relatorios/"+nomeRelatorio+".jrxml");

        JasperReport jasper = JasperCompileManager.compileReport(fis);

        HashMap<String, Object> parametros = new HashMap<String, Object>();
                HttpSession session
                = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        LoginBean beanLogin = (LoginBean) session.getAttribute("beanLogin");
        parametros.put("USUARIO", beanLogin.getUsuario().getNome());
        
        Context cxt = new InitialContext();
        DataSource dataSource = (DataSource) cxt.lookup("java:/comp/env/jdbc/controle_estoque");

        JasperPrint printer = JasperFillManager.fillReport(jasper, parametros, dataSource.getConnection());

        HttpServletResponse resp = (HttpServletResponse) ext.getResponse();
        resp.setContentType("application/pdf");
        resp.addHeader("Content-Disposition", "inline;filename="+nomeRelatorio+".pdf");

        JasperExportManager.exportReportToPdfStream(printer, resp.getOutputStream());
        context.responseComplete();
    }

    private void updateProdutos() {
        this.produtos = Produto.listarTodos();
    }

    public String cancelar() {
        this.produto = new Produto();
        return telaConsulta;
    }

    public String alterar(Produto p) {
        this.produto = p;
        return telaCadastro;
    }
    
    public String addEstoque(Produto p) {
        this.produto = p;
        return telaEntrada;
    }
    
    public String salvarEntrada(){
        this.produto.setQuantidadeEstoque(
                this.produto.getQuantidadeEstoque()+quantidadeAdquirida
        );
        return salvar();
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
        return telaCadastro;
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

    public int getQuantidadeAdquirida() {
        return quantidadeAdquirida;
    }

    public void setQuantidadeAdquirida(int quantidadeAdquirida) {
        this.quantidadeAdquirida = quantidadeAdquirida;
    }

}
