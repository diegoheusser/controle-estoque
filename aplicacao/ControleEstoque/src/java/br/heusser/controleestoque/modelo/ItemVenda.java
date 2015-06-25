package br.heusser.controleestoque.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "item_venda")
public class ItemVenda implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "item_venda_id")
    private int id;
    
    @ManyToOne
    @JoinColumn( name = "produto_id", nullable = false)
    private Produto produto;
    
    @Column(nullable = false)
    private double valor;
    
    @ManyToOne
    @JoinColumn( name = "venda_id", nullable = false)
    private Venda venda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    
}
