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
 * @author Mariana
 */
@Entity
@Table(name = "CATEGORIA", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findById", query = "SELECT c FROM Categoria c WHERE c.id = :id")
    , @NamedQuery(name = "Categoria.findByCategoria", query = "SELECT c FROM Categoria c WHERE c.categoria = :categoria")
    , @NamedQuery(name = "Categoria.findByCategoriapaginahtml", query = "SELECT c FROM Categoria c WHERE c.categoriapaginahtml = :categoriapaginahtml")
    , @NamedQuery(name = "Categoria.findByIdMateria", query = "SELECT c FROM Categoria c WHERE c.idMateria = :idMateria")})
public class Categoria implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "CATEGORIA", length = 60)
    private String categoria;
    @Column(name = "CATEGORIAPAGINAHTML", length = 500)
    private String categoriapaginahtml;
    @Basic(optional = false)
    @Column(name = "ID_MATERIA", nullable = false)
    private int idMateria;

    public Categoria() {
    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(Integer id, int idMateria) {
        this.id = id;
        this.idMateria = idMateria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        String oldCategoria = this.categoria;
        this.categoria = categoria;
        changeSupport.firePropertyChange("categoria", oldCategoria, categoria);
    }

    public String getCategoriapaginahtml() {
        return categoriapaginahtml;
    }

    public void setCategoriapaginahtml(String categoriapaginahtml) {
        String oldCategoriapaginahtml = this.categoriapaginahtml;
        this.categoriapaginahtml = categoriapaginahtml;
        changeSupport.firePropertyChange("categoriapaginahtml", oldCategoriapaginahtml, categoriapaginahtml);
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        int oldIdMateria = this.idMateria;
        this.idMateria = idMateria;
        changeSupport.firePropertyChange("idMateria", oldIdMateria, idMateria);
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.jmary.home.beans.Categoria[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
