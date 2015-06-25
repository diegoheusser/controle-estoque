package br.heusser.controleestoque.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author diego
 */
@Entity
@Table
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venda_id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Usuario vendedor;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Column(name = "data_venda", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    
    @Column(name = "valor_total", nullable = false)
    private double valorTotal;
    
    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itensVenda;

    public Venda(){
        itensVenda = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }
    
}
