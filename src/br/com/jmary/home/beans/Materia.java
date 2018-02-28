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
@Table(name = "MATERIA", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
    , @NamedQuery(name = "Materia.findById", query = "SELECT m FROM Materia m WHERE m.id = :id")
    , @NamedQuery(name = "Materia.findByMateria", query = "SELECT m FROM Materia m WHERE m.materia = :materia")
    , @NamedQuery(name = "Materia.findByMateriapaginahtml", query = "SELECT m FROM Materia m WHERE m.materiapaginahtml = :materiapaginahtml")})
public class Materia implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "MATERIA", length = 50)
    private String materia;
    @Column(name = "MATERIAPAGINAHTML", length = 500)
    private String materiapaginahtml;

    public Materia() {
    }

    public Materia(Integer id) {
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

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        String oldMateria = this.materia;
        this.materia = materia;
        changeSupport.firePropertyChange("materia", oldMateria, materia);
    }

    public String getMateriapaginahtml() {
        return materiapaginahtml;
    }

    public void setMateriapaginahtml(String materiapaginahtml) {
        String oldMateriapaginahtml = this.materiapaginahtml;
        this.materiapaginahtml = materiapaginahtml;
        changeSupport.firePropertyChange("materiapaginahtml", oldMateriapaginahtml, materiapaginahtml);
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
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.jmary.home.beans.Materia[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
