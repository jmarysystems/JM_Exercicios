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
@Table(name = "SUBCATEGORIA", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "Subcategoria.findAll", query = "SELECT s FROM Subcategoria s")
    , @NamedQuery(name = "Subcategoria.findById", query = "SELECT s FROM Subcategoria s WHERE s.id = :id")
    , @NamedQuery(name = "Subcategoria.findBySubcategoria", query = "SELECT s FROM Subcategoria s WHERE s.subcategoria = :subcategoria")
    , @NamedQuery(name = "Subcategoria.findBySubcategoriapaginahtml", query = "SELECT s FROM Subcategoria s WHERE s.subcategoriapaginahtml = :subcategoriapaginahtml")
    , @NamedQuery(name = "Subcategoria.findByIdMateria", query = "SELECT s FROM Subcategoria s WHERE s.idMateria = :idMateria")
    , @NamedQuery(name = "Subcategoria.findByIdCategoria", query = "SELECT s FROM Subcategoria s WHERE s.idCategoria = :idCategoria")})
public class Subcategoria implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "SUBCATEGORIA", length = 70)
    private String subcategoria;
    @Column(name = "SUBCATEGORIAPAGINAHTML", length = 500)
    private String subcategoriapaginahtml;
    @Basic(optional = false)
    @Column(name = "ID_MATERIA", nullable = false)
    private int idMateria;
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA", nullable = false)
    private int idCategoria;

    public Subcategoria() {
    }

    public Subcategoria(Integer id) {
        this.id = id;
    }

    public Subcategoria(Integer id, int idMateria, int idCategoria) {
        this.id = id;
        this.idMateria = idMateria;
        this.idCategoria = idCategoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        String oldSubcategoria = this.subcategoria;
        this.subcategoria = subcategoria;
        changeSupport.firePropertyChange("subcategoria", oldSubcategoria, subcategoria);
    }

    public String getSubcategoriapaginahtml() {
        return subcategoriapaginahtml;
    }

    public void setSubcategoriapaginahtml(String subcategoriapaginahtml) {
        String oldSubcategoriapaginahtml = this.subcategoriapaginahtml;
        this.subcategoriapaginahtml = subcategoriapaginahtml;
        changeSupport.firePropertyChange("subcategoriapaginahtml", oldSubcategoriapaginahtml, subcategoriapaginahtml);
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        int oldIdMateria = this.idMateria;
        this.idMateria = idMateria;
        changeSupport.firePropertyChange("idMateria", oldIdMateria, idMateria);
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        int oldIdCategoria = this.idCategoria;
        this.idCategoria = idCategoria;
        changeSupport.firePropertyChange("idCategoria", oldIdCategoria, idCategoria);
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
        if (!(object instanceof Subcategoria)) {
            return false;
        }
        Subcategoria other = (Subcategoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.jmary.home.beans.Subcategoria[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
