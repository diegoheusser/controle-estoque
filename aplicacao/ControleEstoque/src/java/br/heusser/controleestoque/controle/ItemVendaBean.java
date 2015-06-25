package br.heusser.controleestoque.controle;

import br.heusser.controleestoque.modelo.ItemVenda;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author diego
 */
@ManagedBean(name = "beanItemVenda")
@SessionScoped
public class ItemVendaBean {
    
    private ItemVenda itemVenda;
    
    @PostConstruct
    public void init(){
        itemVenda = new ItemVenda();
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }
    
}
