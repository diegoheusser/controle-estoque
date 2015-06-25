package br.heusser.controleestoque.controle;

import br.heusser.controleestoque.modelo.Venda;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author diego
 */
@ManagedBean(name = "beanVenda")
@SessionScoped
public class VendaBean {
    
    private Venda venda;
    
    @PostConstruct
    public void init(){
        venda = new Venda();
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
}
