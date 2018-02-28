/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jmary.home.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "TESTE", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "Teste.findAll", query = "SELECT t FROM Teste t")
    , @NamedQuery(name = "Teste.findById", query = "SELECT t FROM Teste t WHERE t.id = :id")
    , @NamedQuery(name = "Teste.findByTeste1", query = "SELECT t FROM Teste t WHERE t.teste1 = :teste1")
    , @NamedQuery(name = "Teste.findByTeste2", query = "SELECT t FROM Teste t WHERE t.teste2 = :teste2")
    , @NamedQuery(name = "Teste.findByTeste3", query = "SELECT t FROM Teste t WHERE t.teste3 = :teste3")
    , @NamedQuery(name = "Teste.findByTeste4", query = "SELECT t FROM Teste t WHERE t.teste4 = :teste4")})
public class Teste implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "TESTE1", length = 300)
    private String teste1;
    @Column(name = "TESTE2", length = 300)
    private String teste2;
    @Column(name = "TESTE3", length = 300)
    private String teste3;
    @Column(name = "TESTE4", length = 300)
    private String teste4;

    public Teste() {
    }

    public Teste(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getTeste1() {
        return teste1;
    }

    public void setTeste1(String teste1) {
        String oldTeste1 = this.teste1;
        this.teste1 = teste1;
        changeSupport.firePropertyChange("teste1", oldTeste1, teste1);
    }

    public String getTeste2() {
        return teste2;
    }

    public void setTeste2(String teste2) {
        String oldTeste2 = this.teste2;
        this.teste2 = teste2;
        changeSupport.firePropertyChange("teste2", oldTeste2, teste2);
    }

    public String getTeste3() {
        return teste3;
    }

    public void setTeste3(String teste3) {
        String oldTeste3 = this.teste3;
        this.teste3 = teste3;
        changeSupport.firePropertyChange("teste3", oldTeste3, teste3);
    }

    public String getTeste4() {
        return teste4;
    }

    public void setTeste4(String teste4) {
        String oldTeste4 = this.teste4;
        this.teste4 = teste4;
        changeSupport.firePropertyChange("teste4", oldTeste4, teste4);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teste)) {
            return false;
        }
        Teste other = (Teste) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans_Conexao_Com_Banco_De_Dados.Teste[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
