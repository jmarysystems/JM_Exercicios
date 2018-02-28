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
@Table(name = "ASSUNTO", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "Assunto.findAll", query = "SELECT a FROM Assunto a")
    , @NamedQuery(name = "Assunto.findById", query = "SELECT a FROM Assunto a WHERE a.id = :id")
    , @NamedQuery(name = "Assunto.findByAssunto", query = "SELECT a FROM Assunto a WHERE a.assunto = :assunto")
    , @NamedQuery(name = "Assunto.findByAssuntopaginahtml", query = "SELECT a FROM Assunto a WHERE a.assuntopaginahtml = :assuntopaginahtml")
    , @NamedQuery(name = "Assunto.findByIdMateria", query = "SELECT a FROM Assunto a WHERE a.idMateria = :idMateria")
    , @NamedQuery(name = "Assunto.findByIdCategoria", query = "SELECT a FROM Assunto a WHERE a.idCategoria = :idCategoria")
    , @NamedQuery(name = "Assunto.findByIdSubcategoria", query = "SELECT a FROM Assunto a WHERE a.idSubcategoria = :idSubcategoria")})
public class Assunto implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ASSUNTO", length = 100)
    private String assunto;
    @Column(name = "ASSUNTOPAGINAHTML", length = 500)
    private String assuntopaginahtml;
    @Basic(optional = false)
    @Column(name = "ID_MATERIA", nullable = false)
    private int idMateria;
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA", nullable = false)
    private int idCategoria;
    @Basic(optional = false)
    @Column(name = "ID_SUBCATEGORIA", nullable = false)
    private int idSubcategoria;

    public Assunto() {
    }

    public Assunto(Integer id) {
        this.id = id;
    }

    public Assunto(Integer id, int idMateria, int idCategoria, int idSubcategoria) {
        this.id = id;
        this.idMateria = idMateria;
        this.idCategoria = idCategoria;
        this.idSubcategoria = idSubcategoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        String oldAssunto = this.assunto;
        this.assunto = assunto;
        changeSupport.firePropertyChange("assunto", oldAssunto, assunto);
    }

    public String getAssuntopaginahtml() {
        return assuntopaginahtml;
    }

    public void setAssuntopaginahtml(String assuntopaginahtml) {
        String oldAssuntopaginahtml = this.assuntopaginahtml;
        this.assuntopaginahtml = assuntopaginahtml;
        changeSupport.firePropertyChange("assuntopaginahtml", oldAssuntopaginahtml, assuntopaginahtml);
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

    public int getIdSubcategoria() {
        return idSubcategoria;
    }

    public void setIdSubcategoria(int idSubcategoria) {
        int oldIdSubcategoria = this.idSubcategoria;
        this.idSubcategoria = idSubcategoria;
        changeSupport.firePropertyChange("idSubcategoria", oldIdSubcategoria, idSubcategoria);
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
        if (!(object instanceof Assunto)) {
            return false;
        }
        Assunto other = (Assunto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.jmary.home.beans.Assunto[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
