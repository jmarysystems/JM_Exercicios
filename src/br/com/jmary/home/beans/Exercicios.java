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
 * @author jm
 */
@Entity
@Table(name = "EXERCICIOS", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "Exercicios.findAll", query = "SELECT e FROM Exercicios e")
    , @NamedQuery(name = "Exercicios.findById", query = "SELECT e FROM Exercicios e WHERE e.id = :id")
    , @NamedQuery(name = "Exercicios.findByAssunto", query = "SELECT e FROM Exercicios e WHERE e.assunto = :assunto")
    , @NamedQuery(name = "Exercicios.findByTamanhoPergunta", query = "SELECT e FROM Exercicios e WHERE e.tamanhoPergunta = :tamanhoPergunta")
    , @NamedQuery(name = "Exercicios.findByPergunta", query = "SELECT e FROM Exercicios e WHERE e.pergunta = :pergunta")
    , @NamedQuery(name = "Exercicios.findByTamanhoRespostaA", query = "SELECT e FROM Exercicios e WHERE e.tamanhoRespostaA = :tamanhoRespostaA")
    , @NamedQuery(name = "Exercicios.findByRespostaA", query = "SELECT e FROM Exercicios e WHERE e.respostaA = :respostaA")
    , @NamedQuery(name = "Exercicios.findByTamanhoRespostaB", query = "SELECT e FROM Exercicios e WHERE e.tamanhoRespostaB = :tamanhoRespostaB")
    , @NamedQuery(name = "Exercicios.findByRespostaB", query = "SELECT e FROM Exercicios e WHERE e.respostaB = :respostaB")
    , @NamedQuery(name = "Exercicios.findByTamanhoRespostaC", query = "SELECT e FROM Exercicios e WHERE e.tamanhoRespostaC = :tamanhoRespostaC")
    , @NamedQuery(name = "Exercicios.findByRespostaC", query = "SELECT e FROM Exercicios e WHERE e.respostaC = :respostaC")
    , @NamedQuery(name = "Exercicios.findByTamanhoRespostaD", query = "SELECT e FROM Exercicios e WHERE e.tamanhoRespostaD = :tamanhoRespostaD")
    , @NamedQuery(name = "Exercicios.findByRespostaD", query = "SELECT e FROM Exercicios e WHERE e.respostaD = :respostaD")
    , @NamedQuery(name = "Exercicios.findByLetraDaRespostaCorreta", query = "SELECT e FROM Exercicios e WHERE e.letraDaRespostaCorreta = :letraDaRespostaCorreta")
    , @NamedQuery(name = "Exercicios.findByAjudaInternaImgAtual", query = "SELECT e FROM Exercicios e WHERE e.ajudaInternaImgAtual = :ajudaInternaImgAtual")
    , @NamedQuery(name = "Exercicios.findByAjudaInternaQuantidadeArquivos", query = "SELECT e FROM Exercicios e WHERE e.ajudaInternaQuantidadeArquivos = :ajudaInternaQuantidadeArquivos")
    , @NamedQuery(name = "Exercicios.findByAjudaInternaClasse", query = "SELECT e FROM Exercicios e WHERE e.ajudaInternaClasse = :ajudaInternaClasse")
    , @NamedQuery(name = "Exercicios.findByCaminhoVideos", query = "SELECT e FROM Exercicios e WHERE e.caminhoVideos = :caminhoVideos")
    , @NamedQuery(name = "Exercicios.findByCaminhoDocumentos", query = "SELECT e FROM Exercicios e WHERE e.caminhoDocumentos = :caminhoDocumentos")
    , @NamedQuery(name = "Exercicios.findByEnderecoExternoPastaPpt", query = "SELECT e FROM Exercicios e WHERE e.enderecoExternoPastaPpt = :enderecoExternoPastaPpt")
    , @NamedQuery(name = "Exercicios.findByEnderecoExternoImagemResposta", query = "SELECT e FROM Exercicios e WHERE e.enderecoExternoImagemResposta = :enderecoExternoImagemResposta")
    , @NamedQuery(name = "Exercicios.findByEnderecoExternoImagemPergunta", query = "SELECT e FROM Exercicios e WHERE e.enderecoExternoImagemPergunta = :enderecoExternoImagemPergunta")})
public class Exercicios implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ASSUNTO", length = 300)
    private String assunto;
    @Column(name = "TAMANHO_PERGUNTA")
    private Integer tamanhoPergunta;
    @Basic(optional = false)
    @Column(name = "PERGUNTA", nullable = false, length = 2500)
    private String pergunta;
    @Column(name = "TAMANHO_RESPOSTA_A")
    private Integer tamanhoRespostaA;
    @Basic(optional = false)
    @Column(name = "RESPOSTA_A", nullable = false, length = 2000)
    private String respostaA;
    @Column(name = "TAMANHO_RESPOSTA_B")
    private Integer tamanhoRespostaB;
    @Basic(optional = false)
    @Column(name = "RESPOSTA_B", nullable = false, length = 2000)
    private String respostaB;
    @Column(name = "TAMANHO_RESPOSTA_C")
    private Integer tamanhoRespostaC;
    @Basic(optional = false)
    @Column(name = "RESPOSTA_C", nullable = false, length = 2000)
    private String respostaC;
    @Column(name = "TAMANHO_RESPOSTA_D")
    private Integer tamanhoRespostaD;
    @Basic(optional = false)
    @Column(name = "RESPOSTA_D", nullable = false, length = 2000)
    private String respostaD;
    @Basic(optional = false)
    @Column(name = "LETRA_DA_RESPOSTA_CORRETA", nullable = false, length = 1)
    private String letraDaRespostaCorreta;
    @Column(name = "AJUDA_INTERNA_IMG_ATUAL")
    private Integer ajudaInternaImgAtual;
    @Column(name = "AJUDA_INTERNA_QUANTIDADE_ARQUIVOS")
    private Integer ajudaInternaQuantidadeArquivos;
    @Column(name = "AJUDA_INTERNA_CLASSE", length = 50)
    private String ajudaInternaClasse;
    @Column(name = "CAMINHO_VIDEOS", length = 300)
    private String caminhoVideos;
    @Column(name = "CAMINHO_DOCUMENTOS", length = 300)
    private String caminhoDocumentos;
    @Column(name = "ENDERECO_EXTERNO_PASTA_PPT", length = 300)
    private String enderecoExternoPastaPpt;
    @Column(name = "ENDERECO_EXTERNO_IMAGEM_RESPOSTA", length = 300)
    private String enderecoExternoImagemResposta;
    @Column(name = "ENDERECO_EXTERNO_IMAGEM_PERGUNTA", length = 300)
    private String enderecoExternoImagemPergunta;

    public Exercicios() {
    }

    public Exercicios(Integer id) {
        this.id = id;
    }

    public Exercicios(Integer id, String pergunta, String respostaA, String respostaB, String respostaC, String respostaD, String letraDaRespostaCorreta) {
        this.id = id;
        this.pergunta = pergunta;
        this.respostaA = respostaA;
        this.respostaB = respostaB;
        this.respostaC = respostaC;
        this.respostaD = respostaD;
        this.letraDaRespostaCorreta = letraDaRespostaCorreta;
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

    public Integer getTamanhoPergunta() {
        return tamanhoPergunta;
    }

    public void setTamanhoPergunta(Integer tamanhoPergunta) {
        Integer oldTamanhoPergunta = this.tamanhoPergunta;
        this.tamanhoPergunta = tamanhoPergunta;
        changeSupport.firePropertyChange("tamanhoPergunta", oldTamanhoPergunta, tamanhoPergunta);
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        String oldPergunta = this.pergunta;
        this.pergunta = pergunta;
        changeSupport.firePropertyChange("pergunta", oldPergunta, pergunta);
    }

    public Integer getTamanhoRespostaA() {
        return tamanhoRespostaA;
    }

    public void setTamanhoRespostaA(Integer tamanhoRespostaA) {
        Integer oldTamanhoRespostaA = this.tamanhoRespostaA;
        this.tamanhoRespostaA = tamanhoRespostaA;
        changeSupport.firePropertyChange("tamanhoRespostaA", oldTamanhoRespostaA, tamanhoRespostaA);
    }

    public String getRespostaA() {
        return respostaA;
    }

    public void setRespostaA(String respostaA) {
        String oldRespostaA = this.respostaA;
        this.respostaA = respostaA;
        changeSupport.firePropertyChange("respostaA", oldRespostaA, respostaA);
    }

    public Integer getTamanhoRespostaB() {
        return tamanhoRespostaB;
    }

    public void setTamanhoRespostaB(Integer tamanhoRespostaB) {
        Integer oldTamanhoRespostaB = this.tamanhoRespostaB;
        this.tamanhoRespostaB = tamanhoRespostaB;
        changeSupport.firePropertyChange("tamanhoRespostaB", oldTamanhoRespostaB, tamanhoRespostaB);
    }

    public String getRespostaB() {
        return respostaB;
    }

    public void setRespostaB(String respostaB) {
        String oldRespostaB = this.respostaB;
        this.respostaB = respostaB;
        changeSupport.firePropertyChange("respostaB", oldRespostaB, respostaB);
    }

    public Integer getTamanhoRespostaC() {
        return tamanhoRespostaC;
    }

    public void setTamanhoRespostaC(Integer tamanhoRespostaC) {
        Integer oldTamanhoRespostaC = this.tamanhoRespostaC;
        this.tamanhoRespostaC = tamanhoRespostaC;
        changeSupport.firePropertyChange("tamanhoRespostaC", oldTamanhoRespostaC, tamanhoRespostaC);
    }

    public String getRespostaC() {
        return respostaC;
    }

    public void setRespostaC(String respostaC) {
        String oldRespostaC = this.respostaC;
        this.respostaC = respostaC;
        changeSupport.firePropertyChange("respostaC", oldRespostaC, respostaC);
    }

    public Integer getTamanhoRespostaD() {
        return tamanhoRespostaD;
    }

    public void setTamanhoRespostaD(Integer tamanhoRespostaD) {
        Integer oldTamanhoRespostaD = this.tamanhoRespostaD;
        this.tamanhoRespostaD = tamanhoRespostaD;
        changeSupport.firePropertyChange("tamanhoRespostaD", oldTamanhoRespostaD, tamanhoRespostaD);
    }

    public String getRespostaD() {
        return respostaD;
    }

    public void setRespostaD(String respostaD) {
        String oldRespostaD = this.respostaD;
        this.respostaD = respostaD;
        changeSupport.firePropertyChange("respostaD", oldRespostaD, respostaD);
    }

    public String getLetraDaRespostaCorreta() {
        return letraDaRespostaCorreta;
    }

    public void setLetraDaRespostaCorreta(String letraDaRespostaCorreta) {
        String oldLetraDaRespostaCorreta = this.letraDaRespostaCorreta;
        this.letraDaRespostaCorreta = letraDaRespostaCorreta;
        changeSupport.firePropertyChange("letraDaRespostaCorreta", oldLetraDaRespostaCorreta, letraDaRespostaCorreta);
    }

    public Integer getAjudaInternaImgAtual() {
        return ajudaInternaImgAtual;
    }

    public void setAjudaInternaImgAtual(Integer ajudaInternaImgAtual) {
        Integer oldAjudaInternaImgAtual = this.ajudaInternaImgAtual;
        this.ajudaInternaImgAtual = ajudaInternaImgAtual;
        changeSupport.firePropertyChange("ajudaInternaImgAtual", oldAjudaInternaImgAtual, ajudaInternaImgAtual);
    }

    public Integer getAjudaInternaQuantidadeArquivos() {
        return ajudaInternaQuantidadeArquivos;
    }

    public void setAjudaInternaQuantidadeArquivos(Integer ajudaInternaQuantidadeArquivos) {
        Integer oldAjudaInternaQuantidadeArquivos = this.ajudaInternaQuantidadeArquivos;
        this.ajudaInternaQuantidadeArquivos = ajudaInternaQuantidadeArquivos;
        changeSupport.firePropertyChange("ajudaInternaQuantidadeArquivos", oldAjudaInternaQuantidadeArquivos, ajudaInternaQuantidadeArquivos);
    }

    public String getAjudaInternaClasse() {
        return ajudaInternaClasse;
    }

    public void setAjudaInternaClasse(String ajudaInternaClasse) {
        String oldAjudaInternaClasse = this.ajudaInternaClasse;
        this.ajudaInternaClasse = ajudaInternaClasse;
        changeSupport.firePropertyChange("ajudaInternaClasse", oldAjudaInternaClasse, ajudaInternaClasse);
    }

    public String getCaminhoVideos() {
        return caminhoVideos;
    }

    public void setCaminhoVideos(String caminhoVideos) {
        String oldCaminhoVideos = this.caminhoVideos;
        this.caminhoVideos = caminhoVideos;
        changeSupport.firePropertyChange("caminhoVideos", oldCaminhoVideos, caminhoVideos);
    }

    public String getCaminhoDocumentos() {
        return caminhoDocumentos;
    }

    public void setCaminhoDocumentos(String caminhoDocumentos) {
        String oldCaminhoDocumentos = this.caminhoDocumentos;
        this.caminhoDocumentos = caminhoDocumentos;
        changeSupport.firePropertyChange("caminhoDocumentos", oldCaminhoDocumentos, caminhoDocumentos);
    }

    public String getEnderecoExternoPastaPpt() {
        return enderecoExternoPastaPpt;
    }

    public void setEnderecoExternoPastaPpt(String enderecoExternoPastaPpt) {
        String oldEnderecoExternoPastaPpt = this.enderecoExternoPastaPpt;
        this.enderecoExternoPastaPpt = enderecoExternoPastaPpt;
        changeSupport.firePropertyChange("enderecoExternoPastaPpt", oldEnderecoExternoPastaPpt, enderecoExternoPastaPpt);
    }

    public String getEnderecoExternoImagemResposta() {
        return enderecoExternoImagemResposta;
    }

    public void setEnderecoExternoImagemResposta(String enderecoExternoImagemResposta) {
        String oldEnderecoExternoImagemResposta = this.enderecoExternoImagemResposta;
        this.enderecoExternoImagemResposta = enderecoExternoImagemResposta;
        changeSupport.firePropertyChange("enderecoExternoImagemResposta", oldEnderecoExternoImagemResposta, enderecoExternoImagemResposta);
    }

    public String getEnderecoExternoImagemPergunta() {
        return enderecoExternoImagemPergunta;
    }

    public void setEnderecoExternoImagemPergunta(String enderecoExternoImagemPergunta) {
        String oldEnderecoExternoImagemPergunta = this.enderecoExternoImagemPergunta;
        this.enderecoExternoImagemPergunta = enderecoExternoImagemPergunta;
        changeSupport.firePropertyChange("enderecoExternoImagemPergunta", oldEnderecoExternoImagemPergunta, enderecoExternoImagemPergunta);
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
        if (!(object instanceof Exercicios)) {
            return false;
        }
        Exercicios other = (Exercicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.Exercicios[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
