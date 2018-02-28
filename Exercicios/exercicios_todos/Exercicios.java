/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 * Created on 26/10/2011, 10:32:54
 */

package exercicios_todos;

import Sons.Som;
import br.com.jmary.home.Home;
import br.com.jmary.home.jpa.DAOGenericoJPA;
import br.com.jmary.home.jpa.JPAUtil;
import br.com.jmary.utilidades.Exportando;
import br.com.jmary.utilidades.JOPM;
import br.com.jmary.visualizador_imagens.Visualizador_Externo;
import br.com.jmary.visualizador_imagens.Visualizador_Interno;
import imagens_dos_exercicios.Imagens_Dos_Exercicios;
import imagens_internas.Imagens_Internas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author AnaMariana
 */
public class Exercicios extends javax.swing.JPanel {
    
    Som tocarSon = new Som();
    int resposta = 0;
    boolean respondeuCerto = false;
    JPanel PerguntaX = new JPanel();
    JPanel RespostaX = new JPanel();
    
    Home Home;
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    int              numeroExercicio     = 0;
    String           assunto             = "";
    Class            Class_Ajuda_Interna = null;    
    br.com.jmary.home.beans.Exercicios Exercicios_Beans    = null;
    boolean criar_Exercicios = false;
    List<Integer> lista_Ids;
    int id_atual;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    
    /** Creates new form SombraVendas
     * @param Home2
     * @param certo2
     * @param errado2
     * @param numeroExercicio2
     * @param Class_Ajuda_Interna2
     * @param Exercicios_Beans2
     * @param criar_Exercicios2
     * @param lista_Ids2
     * @param id_atual2 */
    public Exercicios( Home Home2, 
            int certo2, int errado2, int numeroExercicio2,            
            Class Class_Ajuda_Interna2, br.com.jmary.home.beans.Exercicios Exercicios_Beans2,
            boolean criar_Exercicios2,
            List<Integer> lista_Ids2, int id_atual2
    ){
         
        initComponents();
        
        Home = Home2;
        
        jpSalvar.setVisible(false);
        lbMais.setVisible(false);
        lbMenos.setVisible(false);
        
        btProximo.setVisible(false);
        
        rb04.setVisible(false);
        rb03.setVisible(false);
        rb02.setVisible(false);
        rb01.setVisible(false);
        
        PerguntaX = Pergunta;
        RespostaX = Resposta;        
        Home.ControleTabs.removerTabSemControleSelecionadoPeloNome(jTabbedPane1,Pergunta);        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        lista_Ids = lista_Ids2;
        id_atual  = id_atual2;
        Exercicios_Beans = Exercicios_Beans2;
        criar_Exercicios = criar_Exercicios2;
        /////////////////
        assunto = Exercicios_Beans.getAssunto();
        /////////////////
        lbOk.setText(  String.valueOf(certo2) );
        lbNok.setText( String.valueOf(errado2) );
        numeroExercicio = numeroExercicio2;       
        Class_Ajuda_Interna = Class_Ajuda_Interna2; 
        if( criar_Exercicios == false ){
            lbTitulo.setText(String.valueOf(assunto.trim().replace(">", "-") + " - Exercício - " + numeroExercicio ) );
            Home.ControleTabs.removerTabSemControleSelecionadoPeloNome(jTabbedPane1,Resposta);
            
            video_inicio();
            pdf_inicio();
            ppt_inicio();
            imagem_resposta_inicio();
            imagem_pergunta_inicio();
            
            lbExcluir.setVisible(true);
        }  
        else{
            editarPergunta(true);
            btResponder.setEnabled(false);
            lbTitulo.setText(String.valueOf(assunto.trim().replace(">", "-") + " - Criar Exercícios"  ) );
            
            lbExcluir.setVisible(false);
        }        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                       
                       
        inserirTab_Interna_Ajuda();
                
        pergunta_inicio();
        setar_Resposta_A();
        setar_Resposta_B();
        setar_Resposta_C();
        setar_Resposta_D();
        setandoRespostaCorreta();
    }
    
    private void inserirTab_Interna_Ajuda(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            int getAjudaInternaImgAtual = 0; try{ getAjudaInternaImgAtual = Exercicios_Beans.getAjudaInternaImgAtual(); }catch( Exception e ){}
        
            if( getAjudaInternaImgAtual == 1){           
                //System.out.println("if( Exercicios_Beans.getAjudaInternaImgAtual() == 1){");
                JScrollPane sc = new JScrollPane();
                sc.setViewportView( new Visualizador_Interno(Home,
                        Exercicios_Beans.getAjudaInternaImgAtual(), 
                        Exercicios_Beans.getAjudaInternaQuantidadeArquivos(), Class_Ajuda_Interna) );
            
                Home.ControleTabs.AddTabSemSCControleNovo(jTabbedPane1, "Ajuda", "/imagens_internas/livroTp.gif", sc);
            
                Thread.sleep( 5 ); 
                //Adicionando pergunta após a ajuda
                Home.ControleTabs.AddTabSemSCControleNovo(jTabbedPane1, "Pergunta", "/imagens_internas/livroTp.gif", PerguntaX);
                        
            }
            else{
                //System.out.println("inserirTab_Interna_Ajuda() else{");
                Home.ControleTabs.AddTabSemSCControleNovo(jTabbedPane1, "Pergunta", "/imagens_internas/livroTp.gif", PerguntaX);
            }
        } catch( Exception e ){ e.printStackTrace(); } } }.start();                 
    }
    
    private void inserirTab_Externa_PPT(){
        
        new Thread() {   @Override public void run() { try { 
            
            String getEnderecoExternoPastaPpt = ""; try{ getEnderecoExternoPastaPpt = Exercicios_Beans.getEnderecoExternoPastaPpt(); }catch( Exception e ){}
            
            if(!getEnderecoExternoPastaPpt.equals("")){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
                String url_dir = System.getProperty("user.dir").replace("\\", "//");
                String endereco_Externo_da_Pasta_PPT_X = url_dir + Exercicios_Beans.getEnderecoExternoPastaPpt();  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
                int img_Atual = 0; 
            
                JScrollPane sc = new JScrollPane();
                sc.setViewportView( new Visualizador_Externo(Home,img_Atual,endereco_Externo_da_Pasta_PPT_X,0,0) );
            
                Home.ControleTabs.AddTabsAoHome("PPT", "/imagens_internas/livroTp.gif", sc );
            }else{

                JOPM JOptionPaneMod = new JOPM( 2, "PPT EXTERNO NÃO DISPONÍVEL, \n"+ "" +"\n", this.getClass().getSimpleName() );
            }
        } catch( Exception e ){  } } }.start();                             
    }
    
    private void pergunta(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
            HTMLEditorKit kit = new HTMLEditorKit();
            //tpPergunta.setEditorKit( kit );
            StyleSheet css = kit.getStyleSheet();
            
            int getTamanhoPergunta = 22; 
            try{ 
                if(Exercicios_Beans.getTamanhoPergunta() == null){
                    getTamanhoPergunta = 22; 
                }
                else{
                    getTamanhoPergunta = Exercicios_Beans.getTamanhoPergunta();
                } 
            }catch( Exception e ){}
            
            css.addRule("body{ font-family: Arial, Helvetica, sans-serif; font-size: "+ getTamanhoPergunta +"px; "
                    + "padding: 0px; margin: 0px; color: black; line-height: 1.5; }");
            /*
            css.addRule(".tudo{ font-family: Arial, Helvetica, sans-serif; font-size: "+ getTamanhoPergunta +"px; "
                    + "padding: 0px; margin: 0px; color: black; line-height: 1.5; }");*/
            kit.setStyleSheet(css);
                        
            Document doc = kit.createDefaultDocument();
            tpPergunta.setDocument(doc);
            
            //System.out.println( "pergunta() - " + tpPergunta.getText().trim() );
            
        } catch( Exception e ){ e.printStackTrace(); } } }.start();                 
    }
    
    private void pergunta_inicio(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            String getPergunta = ""; 
            try{ 
                if(Exercicios_Beans.getPergunta() == null){
                    getPergunta = ""; 
                }
                else{
                    getPergunta = Exercicios_Beans.getPergunta().trim();
                } 
            }catch( Exception e ){}
            
            //tpPergunta.setContentType("text/html");
            tpPergunta.setText( getPergunta );
            
            //System.out.println( "pergunta_inicio() - " + tpPergunta.getText().trim() );
            
            alterarTamanhoFonte();
            
        } catch( Exception e ){ e.printStackTrace(); } } }.start();                 
    }
    
    private void setar_Resposta_A(){        
        new Thread() {   @Override public void run() { try { 
            
            int    getTamanhoRespostaA = 22; try{ getTamanhoRespostaA = Exercicios_Beans.getTamanhoRespostaA(); }catch( Exception e ){}
            String getRespostaA        = ""; try{ getRespostaA = Exercicios_Beans.getRespostaA(); }catch( Exception e ){}
            
            taOpcao1.setFont(new java.awt.Font("Arial", 0, getTamanhoRespostaA )); // NOI18N
            taOpcao1.setText( getRespostaA );   
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void setar_Resposta_B(){        
        new Thread() {   @Override public void run() { try { 
            
            int    getTamanhoRespostaB = 22; try{ getTamanhoRespostaB = Exercicios_Beans.getTamanhoRespostaB(); }catch( Exception e ){}
            String getRespostaB        = ""; try{ getRespostaB = Exercicios_Beans.getRespostaB(); }catch( Exception e ){}
            
            taOpcao2.setFont(new java.awt.Font("Arial", 0, getTamanhoRespostaB)); // NOI18N
            taOpcao2.setText( getRespostaB );   
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void setar_Resposta_C(){        
        new Thread() {   @Override public void run() { try { 
            
            int    getTamanhoRespostaC = 22; try{ getTamanhoRespostaC = Exercicios_Beans.getTamanhoRespostaC(); }catch( Exception e ){}
            String getRespostaC        = ""; try{ getRespostaC = Exercicios_Beans.getRespostaC(); }catch( Exception e ){}
            
            taOpcao3.setFont(new java.awt.Font("Arial", 0, getTamanhoRespostaC)); // NOI18N
            taOpcao3.setText( getRespostaC );   
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void setar_Resposta_D(){        
        new Thread() {   @Override public void run() { try { 
            
            int    getTamanhoRespostaD = 22; try{ getTamanhoRespostaD = Exercicios_Beans.getTamanhoRespostaD(); }catch( Exception e ){}
            String getRespostaD        = ""; try{ getRespostaD = Exercicios_Beans.getRespostaD(); }catch( Exception e ){}
            
            taOpcao4.setFont(new java.awt.Font("Arial", 0, getTamanhoRespostaD)); // NOI18N
            taOpcao4.setText( getRespostaD ); 
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void video_inicio(){        
        new Thread() {   @Override public void run() { try { 

            if( Exercicios_Beans.getCaminhoVideos() == null ){
                btVideo.setVisible(false);
                //System.out.println("Exercicios_Beans.getCaminhoVideos() == null - "+Exercicios_Beans.getCaminhoVideos());
            }
            else if( Exercicios_Beans.getCaminhoVideos().equals("")){
                btVideo.setVisible(false);
                //System.out.println("Exercicios_Beans.getCaminhoVideos().equals - "+Exercicios_Beans.getCaminhoVideos());
            }
            else{
                btVideo.setVisible(true);
                //System.out.println("else{ "+Exercicios_Beans.getCaminhoVideos());
            }
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void pdf_inicio(){        
        new Thread() {   @Override public void run() { try { 

            if( Exercicios_Beans.getCaminhoDocumentos() == null ){
                btPdf.setVisible(false);
            }
            else if(Exercicios_Beans.getCaminhoDocumentos().equals("")){
                btPdf.setVisible(false);
            }
            else{
                btPdf.setVisible(true);
            }
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void ppt_inicio(){        
        new Thread() {   @Override public void run() { try {  

            if( Exercicios_Beans.getEnderecoExternoPastaPpt() == null ){
                btPpt.setVisible(false);
            }
            else if(Exercicios_Beans.getEnderecoExternoPastaPpt().equals("")){
                btPpt.setVisible(false);
            }
            else{
                btPpt.setVisible(true);
            }
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void imagem_resposta_inicio(){        
        new Thread() {   @Override public void run() { try {  

            if( Exercicios_Beans.getEnderecoExternoImagemResposta()== null ){

            }
            else if(Exercicios_Beans.getEnderecoExternoImagemResposta().equals("")){
                
            }
            else{
                String url;
                String getEnderecoExternoImagemResposta = ""; try{ getEnderecoExternoImagemResposta = Exercicios_Beans.getEnderecoExternoImagemResposta(); }catch( Exception e ){}
                url = System.getProperty("user.dir") + getEnderecoExternoImagemResposta;
                ////////////////////////////////////
                File img = new File( url );

                BufferedImage bufImg = null;
                ImageIcon     icon   = null;
                Image         image  = null;
                try{
                    bufImg = ImageIO.read( img );
                    icon   = new ImageIcon(bufImg);
                    image  = icon.getImage();//ImageIO.read(f);  
                } catch (IOException ex) {}  

                try {  
                    image = icon.getImage();//ImageIO.read(f);  
                    int widith = image.getWidth(icon.getImageObserver() );
                    int height = image.getHeight(icon.getImageObserver() );
                                                                        
                   lbImagen_Resposta.setIcon(new ImageIcon(image.getScaledInstance(
                    widith, height, Image.SCALE_DEFAULT)));
                }catch(Exception ex){}
            }
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void imagem_pergunta_inicio(){        
        new Thread() {   @Override public void run() { try {  

            if( Exercicios_Beans.getEnderecoExternoImagemPergunta()== null ){
                jpImagemPergunta.setVisible(false);
            }
            else if(Exercicios_Beans.getEnderecoExternoImagemPergunta().equals("")){
                jpImagemPergunta.setVisible(false);
            }
            else{
                String url;
                String getEnderecoExternoImagemPergunta = ""; try{ getEnderecoExternoImagemPergunta = Exercicios_Beans.getEnderecoExternoImagemPergunta(); }catch( Exception e ){}
                url = System.getProperty("user.dir") + getEnderecoExternoImagemPergunta;
                ////////////////////////////////////
                File img = new File( url );

                BufferedImage bufImg = null;
                ImageIcon     icon   = null;
                Image         image  = null;
                try{
                    bufImg = ImageIO.read( img );
                    icon   = new ImageIcon(bufImg);
                    image  = icon.getImage();//ImageIO.read(f);  
                } catch (IOException ex) {}  

                try {  
                    image = icon.getImage();//ImageIO.read(f);  
                    int widith = image.getWidth(icon.getImageObserver() );
                    int height = image.getHeight(icon.getImageObserver() );
                                                                        
                   lbImagen_Pergunta.setIcon(new ImageIcon(image.getScaledInstance(
                    widith, height, Image.SCALE_DEFAULT)));
                   
                   jpImagemPergunta.setVisible(true);
                }catch(Exception ex){}
            }
        } catch( Exception e ){  } } }.start();                 
    }
            
    private void setandoRespostaCorreta(){        
        new Thread() {   @Override public void run() { try { 
            
            String getLetraDaRespostaCorreta = ""; try{ getLetraDaRespostaCorreta = Exercicios_Beans.getLetraDaRespostaCorreta(); }catch( Exception e ){}
            
            if(Exercicios_Beans.getLetraDaRespostaCorreta() == null){
                rb01.setSelected(false);  
                rb02.setSelected(false);
                rb03.setSelected(false); 
                rb04.setSelected(false);
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, 22 )); 
                taRespostaCorreta_Parabens.setText( "" );
            }
            else if(getLetraDaRespostaCorreta.equals("")){
                rb01.setSelected(false);  
                rb02.setSelected(false);
                rb03.setSelected(false); 
                rb04.setSelected(false);
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, 22 )); 
                taRespostaCorreta_Parabens.setText( "" );
            }
            else if(getLetraDaRespostaCorreta.equals("A")){
                rb01.setSelected(true);  
                rb02.setSelected(false);
                rb03.setSelected(false); 
                rb04.setSelected(false);
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, Exercicios_Beans.getTamanhoRespostaA())); 
                taRespostaCorreta_Parabens.setText( Exercicios_Beans.getRespostaA() );
            }
            else if(getLetraDaRespostaCorreta.equals("B")){
                rb01.setSelected(false);  
                rb02.setSelected(true);
                rb03.setSelected(false); 
                rb04.setSelected(false);
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, Exercicios_Beans.getTamanhoRespostaB())); 
                taRespostaCorreta_Parabens.setText( Exercicios_Beans.getRespostaB() );
            }
            else if(getLetraDaRespostaCorreta.equals("C")){
                rb01.setSelected(false);  
                rb02.setSelected(false);
                rb03.setSelected(true); 
                rb04.setSelected(false);
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, Exercicios_Beans.getTamanhoRespostaC())); 
                taRespostaCorreta_Parabens.setText( Exercicios_Beans.getRespostaC() );
            }
            else if(getLetraDaRespostaCorreta.equals("D")){
                rb01.setSelected(false);  
                rb02.setSelected(false);
                rb03.setSelected(false); 
                rb04.setSelected(true);
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, Exercicios_Beans.getTamanhoRespostaD())); 
                taRespostaCorreta_Parabens.setText( Exercicios_Beans.getRespostaD() );
            }
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void setandoRespostaCorreta2(){        
        /*new Thread() {   @Override public void run() {*/ try { 
        
            String getLetraDaRespostaCorreta = ""; try{ getLetraDaRespostaCorreta = Exercicios_Beans.getLetraDaRespostaCorreta(); }catch( Exception e ){}
            
            if(getLetraDaRespostaCorreta.equals("A")){
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, Exercicios_Beans.getTamanhoRespostaA())); 
                taRespostaCorreta_Parabens.setText( Exercicios_Beans.getRespostaA() );
            }
            else if(getLetraDaRespostaCorreta.equals("B")){
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, Exercicios_Beans.getTamanhoRespostaB())); 
                taRespostaCorreta_Parabens.setText( Exercicios_Beans.getRespostaB() );
            }
            else if(getLetraDaRespostaCorreta.equals("C")){
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, Exercicios_Beans.getTamanhoRespostaC())); 
                taRespostaCorreta_Parabens.setText( Exercicios_Beans.getRespostaC() );
            }
            else if(getLetraDaRespostaCorreta.equals("D")){
                
                taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, Exercicios_Beans.getTamanhoRespostaD())); 
                taRespostaCorreta_Parabens.setText( Exercicios_Beans.getRespostaD() );
            }
        } catch( Exception e ){  } //} }.start();                 
    }
            
    String tipoAlteracaoFonte = "";
    private void alterar_Tamanho_Fonte_Mais(){        
        new Thread() {   @Override public void run() { try { 
            if( tipoAlteracaoFonte.equals("pergunta") ){

                int getTamanhoPergunta = 22; try{ getTamanhoPergunta = Exercicios_Beans.getTamanhoPergunta(); }catch( Exception e ){}
                getTamanhoPergunta++;
                Exercicios_Beans.setTamanhoPergunta( getTamanhoPergunta );
                Exercicios_Beans.setPergunta(tpPergunta.getText());
                alterarTamanhoFonte();
            }
            else if( tipoAlteracaoFonte.equals("resposta1") ){
                
                int getTamanhoRespostaA = 22; try{ getTamanhoRespostaA = Exercicios_Beans.getTamanhoRespostaA(); }catch( Exception e ){}
                getTamanhoRespostaA++;
                Exercicios_Beans.setTamanhoRespostaA(getTamanhoRespostaA);
                Exercicios_Beans.setRespostaA(taOpcao1.getText());
                setar_Resposta_A();                
            }
            else if( tipoAlteracaoFonte.equals("resposta2") ){
                
                int getTamanhoRespostaB = 22; try{ getTamanhoRespostaB = Exercicios_Beans.getTamanhoRespostaB(); }catch( Exception e ){}
                getTamanhoRespostaB++;
                Exercicios_Beans.setTamanhoRespostaB(getTamanhoRespostaB);
                Exercicios_Beans.setRespostaB(taOpcao2.getText());
                setar_Resposta_B();                
            }
            else if( tipoAlteracaoFonte.equals("resposta3") ){
                
                int getTamanhoRespostaC = 22; try{ getTamanhoRespostaC = Exercicios_Beans.getTamanhoRespostaC(); }catch( Exception e ){}
                getTamanhoRespostaC++;
                Exercicios_Beans.setTamanhoRespostaC(getTamanhoRespostaC);
                Exercicios_Beans.setRespostaC(taOpcao3.getText());
                setar_Resposta_C();                
            }
            else if( tipoAlteracaoFonte.equals("resposta4") ){
                
                int getTamanhoRespostaD = 22; try{ getTamanhoRespostaD = Exercicios_Beans.getTamanhoRespostaD(); }catch( Exception e ){}
                getTamanhoRespostaD++;
                Exercicios_Beans.setTamanhoRespostaD(getTamanhoRespostaD);
                Exercicios_Beans.setRespostaD(taOpcao4.getText());
                setar_Resposta_D();                
            }
        } catch( Exception e ){  } } }.start();                 
    }
    
    public void alterarTamanhoFonte2() {
        
        int getTamanhoPergunta = 22; try{ getTamanhoPergunta = Exercicios_Beans.getTamanhoPergunta(); }catch( Exception e ){}
        
        HTMLDocument doc = ( HTMLDocument ) tpPergunta.getDocument();
        MutableAttributeSet atributo = new SimpleAttributeSet();
        
        doc.getStyleSheet().addCSSAttribute( atributo, CSS.Attribute.FONT_SIZE, String.valueOf( getTamanhoPergunta ) );
        
        doc.setCharacterAttributes( 0, tpPergunta.getDocument().getLength(), atributo, false);
    }
    
    public void alterarTamanhoFonte() {
        
        /*new Thread() {   @Override public void run() {*/ try { 
            
            int    getTamanhoPergunta = 22; try{ getTamanhoPergunta = Exercicios_Beans.getTamanhoPergunta(); }catch( Exception e ){}
            String getPergunta        = ""; try{ getPergunta = Exercicios_Beans.getPergunta(); }catch( Exception e ){}
            
            tpPergunta.setFont(new java.awt.Font("Arial", 0, getTamanhoPergunta )); // NOI18N
            tpPergunta.setText( getPergunta );   
        } catch( Exception e ){  } //} }.start();   
    }
    
    private void alterar_Tamanho_Fonte_Menos(){        
        new Thread() {   @Override public void run() { try { 
            if( tipoAlteracaoFonte.equals("pergunta") ){

                int getTamanhoPergunta = 22; try{ getTamanhoPergunta = Exercicios_Beans.getTamanhoPergunta(); }catch( Exception e ){}
                getTamanhoPergunta--;
                Exercicios_Beans.setTamanhoPergunta(getTamanhoPergunta);
                Exercicios_Beans.setPergunta(tpPergunta.getText());
                alterarTamanhoFonte();
            }
            else if( tipoAlteracaoFonte.equals("resposta1") ){
                
                int getTamanhoRespostaA = 22; try{ getTamanhoRespostaA = Exercicios_Beans.getTamanhoRespostaA(); }catch( Exception e ){}
                getTamanhoRespostaA--;
                Exercicios_Beans.setTamanhoRespostaA(getTamanhoRespostaA);
                Exercicios_Beans.setRespostaA(taOpcao1.getText());
                setar_Resposta_A();                
            }
            else if( tipoAlteracaoFonte.equals("resposta2") ){
                
                int getTamanhoRespostaB = 22; try{ getTamanhoRespostaB = Exercicios_Beans.getTamanhoRespostaB(); }catch( Exception e ){}
                getTamanhoRespostaB--;
                Exercicios_Beans.setTamanhoRespostaB(getTamanhoRespostaB);
                Exercicios_Beans.setRespostaB(taOpcao2.getText());
                setar_Resposta_B();                
            }
            else if( tipoAlteracaoFonte.equals("resposta3") ){
                
                int getTamanhoRespostaC = 22; try{ getTamanhoRespostaC = Exercicios_Beans.getTamanhoRespostaC(); }catch( Exception e ){}
                getTamanhoRespostaC--;
                Exercicios_Beans.setTamanhoRespostaC(getTamanhoRespostaC);
                Exercicios_Beans.setRespostaC(taOpcao3.getText());
                setar_Resposta_C();                
            }
            else if( tipoAlteracaoFonte.equals("resposta4") ){
                
                int getTamanhoRespostaD = 22; try{ getTamanhoRespostaD = Exercicios_Beans.getTamanhoRespostaD(); }catch( Exception e ){}
                getTamanhoRespostaD--;
                Exercicios_Beans.setTamanhoRespostaD(getTamanhoRespostaD);
                Exercicios_Beans.setRespostaD(taOpcao4.getText());
                setar_Resposta_D();                
            }
        } catch( Exception e ){  } } }.start();                 
    }    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Pergunta = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        tpPergunta = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btResponder = new javax.swing.JButton();
        jpSalvar = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        lbMais = new javax.swing.JLabel();
        lbMenos = new javax.swing.JLabel();
        lbExcluir = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        JPOpcao_1 = new javax.swing.JPanel();
        taOpcao4 = new javax.swing.JTextArea();
        rb04 = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        JPOpcao_2 = new javax.swing.JPanel();
        taOpcao3 = new javax.swing.JTextArea();
        rb03 = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        JPOpcao_3 = new javax.swing.JPanel();
        taOpcao2 = new javax.swing.JTextArea();
        rb02 = new javax.swing.JRadioButton();
        jPanel12 = new javax.swing.JPanel();
        JPOpcao_4 = new javax.swing.JPanel();
        taOpcao1 = new javax.swing.JTextArea();
        rb01 = new javax.swing.JRadioButton();
        jpImagemPergunta = new javax.swing.JPanel();
        JPOpcao_5 = new javax.swing.JPanel();
        lbImagen_Pergunta = new javax.swing.JLabel();
        Resposta = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        lbResposta = new javax.swing.JLabel();
        tfImgCorreta = new javax.swing.JLabel();
        btProximo = new javax.swing.JButton();
        JPOpcao_6 = new javax.swing.JPanel();
        taRespostaCorreta_Parabens = new javax.swing.JTextArea();
        tfImgCorreta1 = new javax.swing.JLabel();
        lbImagen_Resposta = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btPpt = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbOk = new javax.swing.JLabel();
        lbNok = new javax.swing.JLabel();
        btVideo = new javax.swing.JButton();
        btPdf = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(726, 489));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbTitulo.setEditable(false);
        lbTitulo.setBackground(new java.awt.Color(0, 0, 102));
        lbTitulo.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbTitulo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbTitulo.setText("A Estrutura de Dados - Pilha - ");
        lbTitulo.setToolTipText("");

        tpPergunta.setEditable(false);
        tpPergunta.setColumns(20);
        tpPergunta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tpPergunta.setRows(5);
        tpPergunta.setText("8");
        tpPergunta.setBorder(null);
        tpPergunta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpPerguntaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tpPerguntaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tpPerguntaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tpPerguntaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpPergunta)
                .addGap(5, 5, 5))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpPergunta, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/anao.gif"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        btResponder.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btResponder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_internas/livroTp.gif"))); // NOI18N
        btResponder.setText("Responder");
        btResponder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btResponderMousePressed(evt);
            }
        });

        btSalvar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_internas/livroTp.gif"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btSalvarMousePressed(evt);
            }
        });

        lbMais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fonteMais.png"))); // NOI18N
        lbMais.setToolTipText("Aumentar Fonte");
        lbMais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbMaisMousePressed(evt);
            }
        });

        lbMenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fonteMenos.png"))); // NOI18N
        lbMenos.setToolTipText("Diminuir Fonte");
        lbMenos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbMenosMousePressed(evt);
            }
        });

        lbExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/lixo2.png"))); // NOI18N
        lbExcluir.setToolTipText("Excluir Exercício");
        lbExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbExcluirMousePressed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/duke.png"))); // NOI18N
        jLabel3.setToolTipText("Limpar dados Informados");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpSalvarLayout = new javax.swing.GroupLayout(jpSalvar);
        jpSalvar.setLayout(jpSalvarLayout);
        jpSalvarLayout.setHorizontalGroup(
            jpSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSalvarLayout.createSequentialGroup()
                .addGroup(jpSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSalvarLayout.createSequentialGroup()
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbMais)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbMenos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbExcluir))
                    .addComponent(jLabel3))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpSalvarLayout.setVerticalGroup(
            jpSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSalvarLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btSalvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMenos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btResponder, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btResponder, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPOpcao_1.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_1MousePressed(evt);
            }
        });

        taOpcao4.setEditable(false);
        taOpcao4.setColumns(20);
        taOpcao4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        taOpcao4.setRows(5);
        taOpcao4.setText("8");
        taOpcao4.setBorder(null);
        taOpcao4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taOpcao4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taOpcao4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                taOpcao4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taOpcao4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_1Layout = new javax.swing.GroupLayout(JPOpcao_1);
        JPOpcao_1.setLayout(JPOpcao_1Layout);
        JPOpcao_1Layout.setHorizontalGroup(
            JPOpcao_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taOpcao4, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_1Layout.setVerticalGroup(
            JPOpcao_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taOpcao4, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        rb04.setSelected(true);
        rb04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rb04MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(rb04)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JPOpcao_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rb04, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JPOpcao_2.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_2MousePressed(evt);
            }
        });

        taOpcao3.setEditable(false);
        taOpcao3.setColumns(20);
        taOpcao3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        taOpcao3.setRows(5);
        taOpcao3.setText("8");
        taOpcao3.setBorder(null);
        taOpcao3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taOpcao3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taOpcao3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                taOpcao3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taOpcao3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_2Layout = new javax.swing.GroupLayout(JPOpcao_2);
        JPOpcao_2.setLayout(JPOpcao_2Layout);
        JPOpcao_2Layout.setHorizontalGroup(
            JPOpcao_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taOpcao3, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_2Layout.setVerticalGroup(
            JPOpcao_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taOpcao3, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        rb03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rb03MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(rb03)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rb03, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JPOpcao_3.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_3MousePressed(evt);
            }
        });

        taOpcao2.setEditable(false);
        taOpcao2.setColumns(20);
        taOpcao2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        taOpcao2.setRows(5);
        taOpcao2.setText("8");
        taOpcao2.setBorder(null);
        taOpcao2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taOpcao2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taOpcao2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                taOpcao2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taOpcao2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_3Layout = new javax.swing.GroupLayout(JPOpcao_3);
        JPOpcao_3.setLayout(JPOpcao_3Layout);
        JPOpcao_3Layout.setHorizontalGroup(
            JPOpcao_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taOpcao2, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_3Layout.setVerticalGroup(
            JPOpcao_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taOpcao2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        rb02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rb02MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(rb02)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(JPOpcao_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rb02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JPOpcao_4.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_4.setToolTipText("");
        JPOpcao_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_4MousePressed(evt);
            }
        });

        taOpcao1.setEditable(false);
        taOpcao1.setColumns(20);
        taOpcao1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        taOpcao1.setRows(5);
        taOpcao1.setText("8");
        taOpcao1.setBorder(null);
        taOpcao1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taOpcao1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taOpcao1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                taOpcao1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taOpcao1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_4Layout = new javax.swing.GroupLayout(JPOpcao_4);
        JPOpcao_4.setLayout(JPOpcao_4Layout);
        JPOpcao_4Layout.setHorizontalGroup(
            JPOpcao_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taOpcao1, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_4Layout.setVerticalGroup(
            JPOpcao_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taOpcao1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        rb01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rb01MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(rb01)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rb01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JPOpcao_5.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_5.setToolTipText("CLICK PARA INCLUIR UMA IMAGEM NA PERGUNTA");
        JPOpcao_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_5MousePressed(evt);
            }
        });

        lbImagen_Pergunta.setToolTipText("");
        lbImagen_Pergunta.setPreferredSize(new java.awt.Dimension(0, 10));
        lbImagen_Pergunta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbImagen_PerguntaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_5Layout = new javax.swing.GroupLayout(JPOpcao_5);
        JPOpcao_5.setLayout(JPOpcao_5Layout);
        JPOpcao_5Layout.setHorizontalGroup(
            JPOpcao_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbImagen_Pergunta, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_5Layout.setVerticalGroup(
            JPOpcao_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbImagen_Pergunta, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpImagemPerguntaLayout = new javax.swing.GroupLayout(jpImagemPergunta);
        jpImagemPergunta.setLayout(jpImagemPerguntaLayout);
        jpImagemPerguntaLayout.setHorizontalGroup(
            jpImagemPerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpImagemPerguntaLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(JPOpcao_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(553, Short.MAX_VALUE))
        );
        jpImagemPerguntaLayout.setVerticalGroup(
            jpImagemPerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpImagemPerguntaLayout.createSequentialGroup()
                .addComponent(JPOpcao_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpImagemPergunta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jpImagemPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel17);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout PerguntaLayout = new javax.swing.GroupLayout(Pergunta);
        Pergunta.setLayout(PerguntaLayout);
        PerguntaLayout.setHorizontalGroup(
            PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitulo)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PerguntaLayout.setVerticalGroup(
            PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PerguntaLayout.createSequentialGroup()
                .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pergunta", Pergunta);

        lbResposta.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lbResposta.setForeground(new java.awt.Color(204, 0, 51));
        lbResposta.setText("Resposta correta - Parabéns!!!");

        tfImgCorreta.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tfImgCorreta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/certo_mao.jpg"))); // NOI18N

        btProximo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_internas/botaoEsquerdoHtmlPane.png"))); // NOI18N
        btProximo.setText("Próximo");
        btProximo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btProximoMousePressed(evt);
            }
        });
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });

        JPOpcao_6.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_6MousePressed(evt);
            }
        });

        taRespostaCorreta_Parabens.setEditable(false);
        taRespostaCorreta_Parabens.setColumns(20);
        taRespostaCorreta_Parabens.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        taRespostaCorreta_Parabens.setRows(5);
        taRespostaCorreta_Parabens.setText("8");
        taRespostaCorreta_Parabens.setBorder(null);
        taRespostaCorreta_Parabens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taRespostaCorreta_ParabensMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                taRespostaCorreta_ParabensMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taRespostaCorreta_ParabensMousePressed(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_6Layout = new javax.swing.GroupLayout(JPOpcao_6);
        JPOpcao_6.setLayout(JPOpcao_6Layout);
        JPOpcao_6Layout.setHorizontalGroup(
            JPOpcao_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taRespostaCorreta_Parabens, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_6Layout.setVerticalGroup(
            JPOpcao_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taRespostaCorreta_Parabens, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        tfImgCorreta1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tfImgCorreta1.setText("Resposta correta:");

        lbImagen_Resposta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbImagen_RespostaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbResposta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbImagen_Resposta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(tfImgCorreta)
                                .addGap(212, 212, 212)
                                .addComponent(btProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfImgCorreta1)
                            .addComponent(JPOpcao_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(150, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(lbResposta, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfImgCorreta)
                    .addComponent(btProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfImgCorreta1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImagen_Resposta, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel14);

        javax.swing.GroupLayout RespostaLayout = new javax.swing.GroupLayout(Resposta);
        Resposta.setLayout(RespostaLayout);
        RespostaLayout.setHorizontalGroup(
            RespostaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );
        RespostaLayout.setVerticalGroup(
            RespostaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Resposta", Resposta);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btPpt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btPpt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_internas/livroTp.gif"))); // NOI18N
        btPpt.setText("PPT");
        btPpt.setPreferredSize(new java.awt.Dimension(94, 31));
        btPpt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btPptMousePressed(evt);
            }
        });
        btPpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPptActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ACERTOS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 0, 0));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ERROS");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbOk.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbOk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbOk.setText("0");

        lbNok.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbNok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNok.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbNok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lbOk)
                .addGap(28, 28, 28)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lbNok)
                .addGap(0, 38, Short.MAX_VALUE))
        );

        btVideo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btVideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_internas/livroTp.gif"))); // NOI18N
        btVideo.setText("Vídeo");
        btVideo.setPreferredSize(new java.awt.Dimension(94, 31));
        btVideo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btVideoMousePressed(evt);
            }
        });
        btVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVideoActionPerformed(evt);
            }
        });

        btPdf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_internas/pdf_16_16.gif"))); // NOI18N
        btPdf.setText("PDF");
        btPdf.setPreferredSize(new java.awt.Dimension(94, 31));
        btPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btPdfMousePressed(evt);
            }
        });
        btPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btVideo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btPdf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btPpt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btVideo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btPdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btPpt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btPdf, btPpt, btVideo});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btProximoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btProximoMousePressed

        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
        for(int f = 0; f < lista_Ids.size(); f++ ){
            
            if( lista_Ids.get(f) == id_atual ){
                
                lista_Ids.remove( lista_Ids.get(f) );
            }
        }
        
        if( !lista_Ids.isEmpty() ){
            
            int proximo_id = 0;
            for(int j = 0; j < lista_Ids.size(); j++ ){
                proximo_id = lista_Ids.get(j);
                break;            
            }
                
            Exportando = new Exportando("ABRINDO...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
            
            boolean criar_Exercicios = false;
                      
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            int intCerto  = Integer.parseInt(lbOk.getText().trim());
            int intErrado = Integer.parseInt(lbNok.getText().trim());
            ////////////////
            numeroExercicio++;
            String nome_do_tab_exercicio = "Exercicio - " + numeroExercicio;
            
            ///////////////////////////////////////
            List<br.com.jmary.home.beans.Exercicios> Exercicios_Beans = null;
            try{
                Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM JM.EXERCICIOS WHERE ID = ?", br.com.jmary.home.beans.Exercicios.class );
                q.setParameter(1, proximo_id );
                Exercicios_Beans = q.getResultList();
            }catch(Exception e ){} 
            
            String rbusca = ""; try{ rbusca = Exercicios_Beans.get(0).getPergunta(); }catch( Exception e ){}
            
            if( !rbusca.equals("") ){                
                /////////////////////////////////////////////////////////////////////////////////////////
                Exercicios_Beans.get(0).setAssunto( assunto );
            
                Class Class_Ajuda_Interna = null; try{ Class_Ajuda_Interna = Exercicios_Beans.get(0).getClass(); }catch( Exception e){}                              
                /////////////////////////////////////////////////////////////////////////////////////////           
                Home.ControleTabs.removerTabSelecionado();
                Home.ControleTabs.AddTabSemSCControleNovo(nome_do_tab_exercicio, "/imagens_internas/livroTp.gif", 
                    new Exercicios( Home, 
                            intCerto, intErrado, numeroExercicio,
                            Class_Ajuda_Interna, Exercicios_Beans.get(0),
                            criar_Exercicios,
                            lista_Ids, proximo_id
                    ) );
            }

            Exportando.fechar();
        }   
        else{
            
            int certo = Integer.parseInt( lbOk.getText().trim() );
            int errado = Integer.parseInt( lbNok.getText().trim() );
            
            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "FIM DOS EXERCÍCIOS\n"
                    + "\nACERTOS: "+certo
                    + "\nERROS: " + errado
                    ,"FIM DOS EXERCÍCIOS", 
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); } } }.start(); 
    }//GEN-LAST:event_btProximoMousePressed

    private void btResponderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btResponderMousePressed
    if( btResponder.isEnabled() == true ){
        if( resposta == 1 ){
            Home.ControleTabs.AddTabSemControleNovoSemThread(jTabbedPane1, "Resposta", "/imagens_internas/livroTp.gif", RespostaX);
            btResponder.setVisible(false);
            btProximo.setVisible(true);

            if( respondeuCerto == true ){

                int random = (int)(Math.random() * (101+1-200)) + 200;
                tocarSon.tocar( random );

                int atual = Integer.parseInt( lbOk.getText().trim() );
                atual++;
                lbOk.setText( String.valueOf(atual) );
                                                
                Class<Imagens_Dos_Exercicios> clazzHome = Imagens_Dos_Exercicios.class;
                tfImgCorreta.setIcon(new ImageIcon( clazzHome.getResource("certo_mao.jpg")));
                
                abrir_Resposta("certo_001.gif");
            }
            else{

                int random = (int)(Math.random() * (201+1-300)) + 300;
                tocarSon.tocar( random );

                int atual = Integer.parseInt( lbNok.getText().trim() );
                atual++;
                lbNok.setText( String.valueOf(atual) );
                                                
                Class<Imagens_Dos_Exercicios> clazzHome = Imagens_Dos_Exercicios.class;
                tfImgCorreta.setIcon(new ImageIcon( clazzHome.getResource("errado_mao.jpg")));
                
                abrir_Resposta("erro_001.gif");
            }            
        }
        else if( resposta == 0 ){

            JOPM JOptionPaneMod = new JOPM( 2, "NENHUMA OPÇÃO SELECIONADA, "
                + "\nO USUÁRIO TEM DE ESCOLHER UMA OPÇÃO"
                + "\nESCOLHA UMA OPÇÃO"
                + "\n3 CLICK SEM ESCOLHA E SERÁ BLOQUEADO"
                + "\n", "NENHUMA OPÇÃO SELECIONADA" );
        }
    }
    else{
        
        JOPM JOptionPaneMod = new JOPM( 2, "CRIANDO EXERCÍCIOS, "
                + "\nO USUÁRIO ESTÁ CRIANDO EXERCÍCIOS"
                + "\nCRIANDO EXERCÍCIOS: "
                + "\nNÃO PODE RESPONDER AS PERGUNTAS"
                + "\n", "CRIANDO EXERCÍCIOS" );
    }
    }//GEN-LAST:event_btResponderMousePressed

    private void abrir_Resposta(String icon){
        try { 
            Class<Imagens_Dos_Exercicios> clazzHome = Imagens_Dos_Exercicios.class;
            JOptionPane pane = new JOptionPane( new ImageIcon( clazzHome.getResource(icon)) );
            final JDialog dialog = pane.createDialog( Home, "" ); 
            dialog.setModal(true);
            Timer timer = new Timer(2 * 1000, new ActionListener() {  
                @Override
                public void actionPerformed(ActionEvent ev) {  
                    dialog.dispose();   
                }  
            });  
            timer.setRepeats(false);  
            timer.start();  
            dialog.show();  
            timer.stop(); 
        } catch(Exception e){e.printStackTrace();}
    }
        
    private void JPOpcao_3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_3MousePressed
        JM_JPOpcao_1MousePressed(JPOpcao_3,JPOpcao_1,JPOpcao_2,JPOpcao_4,
        taOpcao2,taOpcao4,taOpcao3,taOpcao1, "B");
    }//GEN-LAST:event_JPOpcao_3MousePressed

    private void JPOpcao_3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_3MouseExited
        JM_JPOpcao_1MouseExited(JPOpcao_3, taOpcao2);
    }//GEN-LAST:event_JPOpcao_3MouseExited

    private void JPOpcao_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_3MouseEntered
        JM_JPOpcao_1MouseEntered(JPOpcao_3, taOpcao2);
    }//GEN-LAST:event_JPOpcao_3MouseEntered

    Exportando Exportando;
    private void JPOpcao_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_2MouseEntered
        JM_JPOpcao_1MouseEntered(JPOpcao_2, taOpcao3);
    }//GEN-LAST:event_JPOpcao_2MouseEntered

    private void JPOpcao_2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_2MouseExited
        JM_JPOpcao_1MouseExited(JPOpcao_2, taOpcao3);
    }//GEN-LAST:event_JPOpcao_2MouseExited

    private void JPOpcao_2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_2MousePressed
        JM_JPOpcao_1MousePressed(JPOpcao_2,JPOpcao_1,JPOpcao_3,JPOpcao_4,
        taOpcao3,taOpcao4,taOpcao2,taOpcao1, "C");
    }//GEN-LAST:event_JPOpcao_2MousePressed

    private void JPOpcao_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_4MouseEntered
        JM_JPOpcao_1MouseEntered(JPOpcao_4, taOpcao1);
    }//GEN-LAST:event_JPOpcao_4MouseEntered

    private void JPOpcao_4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_4MouseExited
        JM_JPOpcao_1MouseExited(JPOpcao_4, taOpcao1);
    }//GEN-LAST:event_JPOpcao_4MouseExited

    private void JPOpcao_4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_4MousePressed
        JM_JPOpcao_1MousePressed(JPOpcao_4,JPOpcao_1,JPOpcao_2,JPOpcao_3,
        taOpcao1,taOpcao4,taOpcao3,taOpcao2, "A");
    }//GEN-LAST:event_JPOpcao_4MousePressed

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btProximoActionPerformed

    String urlVideo;
    private void btVideoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btVideoMousePressed
        new Thread() {   @Override public void run() { try {
            if( jpSalvar.isVisible() == true ){

                JFileChooser JFileChooserJMPasta = new JFileChooser();
                JFileChooserJMPasta.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int status = JFileChooserJMPasta.showSaveDialog(null);
                
                if ( status == javax.swing.JFileChooser.CANCEL_OPTION ){ 

                }else if ( status == javax.swing.JFileChooser.APPROVE_OPTION ){
                    
                    File arquivo = JFileChooserJMPasta.getSelectedFile();
                    //System.out.println("caminho_videos - " + arquivo.getAbsolutePath() );
                    
                    String tempX = arquivo.getAbsolutePath().replace("\\", "/");
                    
                    String str[] = tempX.split("/");
                    if( !str.equals("") ){
                        
                        for (int i = 0; i < str.length; i++){
                            
                            String opcao = str[i].trim();
                            if(opcao.equals("00_Externo")){
                                
                                StringBuilder temp = new StringBuilder();
                                temp.append("//");
                                int ctemp = 0;
                                for (int j = i; j < str.length; j++){
                                    
                                    temp.append( str[j].trim() ); 
                                    
                                    ctemp = str.length - 1;
                                    if(j < ctemp){
                                        
                                        temp.append("//");
                                    }
                                }
                                
                                Exercicios_Beans.setCaminhoVideos( temp.toString() );
                                //System.out.println("caminho_videos - " + temp.toString());
                                break;
                            }
                        }
                    }
                }
            }
            else{

                String getCaminhoVideos = ""; try{ getCaminhoVideos = Exercicios_Beans.getCaminhoVideos(); }catch( Exception e ){}
          
                if(!getCaminhoVideos.equals("")){

                    Exportando = new Exportando("ABRINDO...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( 100 );
                    Exportando.pbg.setValue( 50 );
                    String url = System.getProperty("user.dir") + getCaminhoVideos;
            
                    if (Desktop.isDesktopSupported()) {

                        System.out.println( "URL: " + url);
                        Desktop.getDesktop().open(new File( url ));
                    }

                    Thread.sleep( 30 );
                    Exportando.fechar();
                }else{

                    JOPM JOptionPaneMod = new JOPM( 2, "VÍDEO EXTERNO NÃO DISPONÍVEL, \n"+ "" +"\n", this.getClass().getSimpleName() );
                }
            }
        } catch( Exception e ){
            Exportando.fechar();
            e.printStackTrace();
            JOPM JOptionPaneMod = new JOPM( 2, "VÍDEO EXTERNO NÃO DISPONÍVEL, \n"+ "" +"\n", this.getClass().getSimpleName() );
        } } }.start();
    }//GEN-LAST:event_btVideoMousePressed

    private void btVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVideoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btVideoActionPerformed

    String urlDocumento = "";
    private void btPdfMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btPdfMousePressed
        new Thread() {   @Override public void run() { try {
            if( jpSalvar.isVisible() == true ){

                JFileChooser JFileChooserJMPasta = new JFileChooser();
                JFileChooserJMPasta.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int status = JFileChooserJMPasta.showSaveDialog(null);
                
                if ( status == javax.swing.JFileChooser.CANCEL_OPTION ){ 

                }else if ( status == javax.swing.JFileChooser.APPROVE_OPTION ){
                    
                    File arquivo = JFileChooserJMPasta.getSelectedFile();
                    //System.out.println("caminho_documentos - " + arquivo.getAbsolutePath() );
                    
                    String tempX = arquivo.getAbsolutePath().replace("\\", "/");
                    
                    String str[] = tempX.split("/");
                    if( !str.equals("") ){
                        
                        for (int i = 0; i < str.length; i++){
                            
                            String opcao = str[i].trim();
                            if(opcao.equals("00_Externo")){
                                
                                StringBuilder temp = new StringBuilder();
                                temp.append("\\\\");
                                int ctemp = 0;
                                for (int j = i; j < str.length; j++){
                                    
                                    temp.append( str[j].trim() ); 
                                    
                                    ctemp = str.length - 1;
                                    if(j < ctemp){
                                        
                                        temp.append("\\\\");
                                    }
                                }
                                
                                Exercicios_Beans.setCaminhoDocumentos(temp.toString());
                                //System.out.println("caminho_documentos - " + temp.toString());
                                break;
                            }
                        }
                    }
                }
            }
            else{
                
                String getCaminhoDocumentos = ""; try{ getCaminhoDocumentos = Exercicios_Beans.getCaminhoDocumentos(); }catch( Exception e ){}
                
                if(!getCaminhoDocumentos.equals("")){
                    String url;

                    Exportando = new Exportando("ABRINDO...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( 100 );
                    Exportando.pbg.setValue( 50 );
                    
                    String url_dir = System.getProperty("user.dir").replace("\\", "\\\\");
                    url = url_dir + getCaminhoDocumentos;
            
                    if (Desktop.isDesktopSupported()) {

                        //System.out.println( "URL: " + url);
                        Desktop.getDesktop().open(new File( url ));
                    }

                    Thread.sleep( 30 );
                    Exportando.fechar();
                }else{

                    JOPM JOptionPaneMod = new JOPM( 2, "PPT EXTERNO NÃO DISPONÍVEL, \n"+ "" +"\n", this.getClass().getSimpleName() );
                }
            }
        } catch( Exception e ){
            Exportando.fechar();
            e.printStackTrace();
            JOPM JOptionPaneMod = new JOPM( 2, "PPT EXTERNO NÃO DISPONÍVEL, \n"+ "" +"\n", this.getClass().getSimpleName() );
        } } }.start();      
    }//GEN-LAST:event_btPdfMousePressed

    private void btPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPdfActionPerformed
       
    }//GEN-LAST:event_btPdfActionPerformed

    private void JPOpcao_1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_1MousePressed
        JM_JPOpcao_1MousePressed(JPOpcao_1,JPOpcao_2,JPOpcao_3,JPOpcao_4,
        taOpcao4,taOpcao3,taOpcao2,taOpcao1, "D");
    }//GEN-LAST:event_JPOpcao_1MousePressed

    private void JPOpcao_1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_1MouseExited
        JM_JPOpcao_1MouseExited(JPOpcao_1, taOpcao4);
    }//GEN-LAST:event_JPOpcao_1MouseExited

    private void JPOpcao_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_1MouseEntered
        JM_JPOpcao_1MouseEntered(JPOpcao_1, taOpcao4);
    }//GEN-LAST:event_JPOpcao_1MouseEntered

    private void taOpcao4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao4MouseEntered
        JM_JPOpcao_1MouseEntered(JPOpcao_1, taOpcao4);
    }//GEN-LAST:event_taOpcao4MouseEntered

    private void taOpcao4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao4MouseExited
        JM_JPOpcao_1MouseExited(JPOpcao_1, taOpcao4);
    }//GEN-LAST:event_taOpcao4MouseExited

    private void taOpcao3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao3MouseEntered
        JM_JPOpcao_1MouseEntered(JPOpcao_2, taOpcao3);
    }//GEN-LAST:event_taOpcao3MouseEntered

    private void taOpcao3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao3MouseExited
        JM_JPOpcao_1MouseExited(JPOpcao_2, taOpcao3);
    }//GEN-LAST:event_taOpcao3MouseExited

    private void taOpcao2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao2MouseEntered
        JM_JPOpcao_1MouseEntered(JPOpcao_3, taOpcao2);
    }//GEN-LAST:event_taOpcao2MouseEntered

    private void taOpcao2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao2MouseExited
        JM_JPOpcao_1MouseExited(JPOpcao_3, taOpcao2);
    }//GEN-LAST:event_taOpcao2MouseExited

    private void taOpcao1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao1MouseExited
        JM_JPOpcao_1MouseExited(JPOpcao_4, taOpcao1);
    }//GEN-LAST:event_taOpcao1MouseExited

    private void taOpcao1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao1MouseEntered
        JM_JPOpcao_1MouseEntered(JPOpcao_4, taOpcao1);
    }//GEN-LAST:event_taOpcao1MouseEntered

    private void taOpcao4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao4MousePressed
        JM_JPOpcao_1MousePressed(JPOpcao_1,JPOpcao_2,JPOpcao_3,JPOpcao_4,
        taOpcao4,taOpcao3,taOpcao2,taOpcao1, "D");
        
        if( taOpcao4.isEditable() == true ){
            tipoAlteracaoFonte = "resposta4";
                        
            lbMais.setVisible(true);
            lbMenos.setVisible(true);            
        }
    }//GEN-LAST:event_taOpcao4MousePressed

    private void taOpcao3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao3MousePressed
        JM_JPOpcao_1MousePressed(JPOpcao_2,JPOpcao_1,JPOpcao_3,JPOpcao_4,
        taOpcao3,taOpcao4,taOpcao2,taOpcao1, "C");
        
        if( taOpcao3.isEditable() == true ){
            tipoAlteracaoFonte = "resposta3";
                        
            lbMais.setVisible(true);
            lbMenos.setVisible(true);            
        }
    }//GEN-LAST:event_taOpcao3MousePressed

    private void taOpcao2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao2MousePressed
        JM_JPOpcao_1MousePressed(JPOpcao_3,JPOpcao_1,JPOpcao_2,JPOpcao_4,
        taOpcao2,taOpcao4,taOpcao3,taOpcao1, "B");
        
        if( taOpcao2.isEditable() == true ){
            tipoAlteracaoFonte = "resposta2";
                        
            lbMais.setVisible(true);
            lbMenos.setVisible(true);            
        }
    }//GEN-LAST:event_taOpcao2MousePressed

    private void taOpcao1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao1MousePressed
        JM_JPOpcao_1MousePressed(JPOpcao_4,JPOpcao_1,JPOpcao_2,JPOpcao_3,
        taOpcao1,taOpcao4,taOpcao3,taOpcao2, "A");
        
        if( taOpcao1.isEditable() == true ){
            tipoAlteracaoFonte = "resposta1";
                        
            lbMais.setVisible(true);
            lbMenos.setVisible(true);            
        }
    }//GEN-LAST:event_taOpcao1MousePressed

    private void taRespostaCorreta_ParabensMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taRespostaCorreta_ParabensMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_taRespostaCorreta_ParabensMouseEntered

    private void taRespostaCorreta_ParabensMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taRespostaCorreta_ParabensMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_taRespostaCorreta_ParabensMouseExited

    private void taRespostaCorreta_ParabensMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taRespostaCorreta_ParabensMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_taRespostaCorreta_ParabensMousePressed

    private void JPOpcao_6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_6MouseEntered

    private void JPOpcao_6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_6MouseExited

    private void JPOpcao_6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_6MousePressed

    private void btPptMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btPptMousePressed
        new Thread() {   @Override public void run() { try {
            if( jpSalvar.isVisible() == true ){

                JFileChooser JFileChooserJMPasta = new JFileChooser();
                JFileChooserJMPasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int status = JFileChooserJMPasta.showSaveDialog(null);
                
                if ( status == javax.swing.JFileChooser.CANCEL_OPTION ){ 

                }else if ( status == javax.swing.JFileChooser.APPROVE_OPTION ){
                    
                    File arquivo = JFileChooserJMPasta.getSelectedFile();
                    //System.out.println("endereco_Externo_da_Pasta_PPT - " + arquivo.getAbsolutePath() );
                    
                    String tempX = arquivo.getAbsolutePath().replace("\\", "/");
                    
                    String str[] = tempX.split("/");
                    if( !str.equals("") ){
                        
                        for (int i = 0; i < str.length; i++){
                            
                            String opcao = str[i].trim();
                            if(opcao.equals("00_Externo")){
                                
                                StringBuilder temp = new StringBuilder();
                                temp.append("//");
                                for (int j = i; j < str.length; j++){
                                                         
                                    temp.append( str[j].trim() );
                                                                       
                                    temp.append("//");
                                }
                                
                                Exercicios_Beans.setEnderecoExternoPastaPpt(temp.toString());
                                //System.out.println("endereco_Externo_da_Pasta_PPT - " + temp.toString() );
                                break;
                            }
                        }
                    }
                }
            }
            else{
                
                inserirTab_Externa_PPT();
            }
        } catch( Exception e ){
            e.printStackTrace();
            JOPM JOptionPaneMod = new JOPM( 2, "PPT EXTERNO NÃO DISPONÍVEL, \n"+ "" +"\n", this.getClass().getSimpleName() );
        } } }.start();        
    }//GEN-LAST:event_btPptMousePressed

    private void btPptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btPptActionPerformed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        String nome = "";
        nome = JOptionPane.showInputDialog("Informe a Senha de Alteração");
        if (nome.equals("23071354")) {
            
            editarPergunta(true);
        }
    }//GEN-LAST:event_jLabel2MousePressed

    private void btSalvarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalvarMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            Exportando = new Exportando("Salvando...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );   

            ////////////////////////////////////////////////////////////            
            Exercicios_Beans.setPergunta(tpPergunta.getText());
            Exercicios_Beans.setRespostaA(taOpcao1.getText());
            Exercicios_Beans.setRespostaB(taOpcao2.getText());
            Exercicios_Beans.setRespostaC(taOpcao3.getText());
            Exercicios_Beans.setRespostaD(taOpcao4.getText());
            //////////////////////////////////////////////////////////// 
            int getTamanhoPergunta = 22; try{ getTamanhoPergunta = Exercicios_Beans.getTamanhoPergunta(); }catch( Exception e ){}
            Exercicios_Beans.setTamanhoPergunta( getTamanhoPergunta );
            int getTamanhoRespostaA = 22; try{ getTamanhoRespostaA = Exercicios_Beans.getTamanhoRespostaA(); }catch( Exception e ){}
            Exercicios_Beans.setTamanhoRespostaA(getTamanhoRespostaA);
            int getTamanhoRespostaB = 22; try{ getTamanhoRespostaB = Exercicios_Beans.getTamanhoRespostaB(); }catch( Exception e ){}
            Exercicios_Beans.setTamanhoRespostaB(getTamanhoRespostaB);
            int getTamanhoRespostaC = 22; try{ getTamanhoRespostaC = Exercicios_Beans.getTamanhoRespostaC(); }catch( Exception e ){}
            Exercicios_Beans.setTamanhoRespostaC(getTamanhoRespostaC);
            int getTamanhoRespostaD = 22; try{ getTamanhoRespostaD = Exercicios_Beans.getTamanhoRespostaD(); }catch( Exception e ){}
            Exercicios_Beans.setTamanhoRespostaD(getTamanhoRespostaD);
            //////////////////////////////////////////////////////////// 
            
            setandoRespostaCorreta2();
            
            try {
                
                DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Exercicios_Beans, JPAUtil.em());
                br.com.jmary.home.beans.Exercicios Exercicios_Cadastrado = (br.com.jmary.home.beans.Exercicios) DAOGenericoJPA2.gravanovoOUatualizar();
            }catch( Exception e ){e.printStackTrace();}
            Exportando.fechar();
            
            ////////////////////////////////////////////////////////////////////
            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CRIANDO EXERCÍCIOS\n"
                    + "\nSTATUS DO EXERCÍCIO"
                    + "\nEXERCÍCIO SALVO COM SUCESSO!\n"
                    + "\nOK PARA PROSSEGUIR."
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            ////////////////////////////////////////////////////////////////////
            
            if( criar_Exercicios == false ){
                
                editarPergunta(false);        
                tipoAlteracaoFonte = "";
                lbMais.setVisible(false);
                lbMenos.setVisible(false);        
                video_inicio();
                pdf_inicio();
                ppt_inicio();
                imagem_resposta_inicio();
                imagem_pergunta_inicio();
            }
            
            //Home.ControleTabs.removerTabSelecionado();
            
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();        
    }//GEN-LAST:event_btSalvarMousePressed
    
    private void rb01MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb01MousePressed
        new Thread() {   @Override public void run() { try {  
            Exercicios_Beans.setLetraDaRespostaCorreta("A");
            rb_01();
            setandoRespostaCorreta2();
        } catch( Exception e ){} } }.start(); 
    }//GEN-LAST:event_rb01MousePressed

    private void rb02MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb02MousePressed
        new Thread() {   @Override public void run() { try {            
            Exercicios_Beans.setLetraDaRespostaCorreta("B");
            rb_02();
            setandoRespostaCorreta2();
        } catch( Exception e ){} } }.start();
    }//GEN-LAST:event_rb02MousePressed

    private void rb03MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb03MousePressed
        new Thread() {   @Override public void run() { try {            
            Exercicios_Beans.setLetraDaRespostaCorreta("C");
            rb_03();
            setandoRespostaCorreta2();
        } catch( Exception e ){} } }.start();
    }//GEN-LAST:event_rb03MousePressed

    private void rb04MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb04MousePressed
        new Thread() {   @Override public void run() { try {
            Exercicios_Beans.setLetraDaRespostaCorreta("D");
            rb_04();            
            setandoRespostaCorreta2();
        } catch( Exception e ){} } }.start();
    }//GEN-LAST:event_rb04MousePressed

    private void lbMaisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMaisMousePressed
        alterar_Tamanho_Fonte_Mais();
    }//GEN-LAST:event_lbMaisMousePressed

    private void lbMenosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMenosMousePressed
        alterar_Tamanho_Fonte_Menos();
    }//GEN-LAST:event_lbMenosMousePressed

    private void lbExcluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExcluirMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            int response = JOptionPane.showConfirmDialog(null, ""
                    + "**CONFIRMAR EXCLUSÃO**"
                    + "\n"
                    + "Deseja Excluir Este Exercício?");
            if( response == JOptionPane.YES_OPTION){ 
                
                try {
                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Exercicios_Beans, JPAUtil.em()); 
                    DAOGenericoJPA2.excluir(); 
                }catch( Exception e ){} 
                
                ////////////////////////////////////////////////////////////////////
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "EXCLUINDO EXERCÍCIO\n"
                    + "\nSTATUS DA EXCLUSÃO"
                    + "\nEXERCÍCIO EXCLUÍDO COM SUCESSO!\n"
                    + "\nOK PARA PROSSEGUIR."
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                ////////////////////////////////////////////////////////////////////
                
                Home.ControleTabs.removerTabSelecionado();
            }
            
        } catch( Exception e ){ e.printStackTrace(); } } }.start();
    }//GEN-LAST:event_lbExcluirMousePressed

    private void tpPerguntaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpPerguntaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tpPerguntaMouseEntered

    private void tpPerguntaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpPerguntaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tpPerguntaMouseExited

    private void tpPerguntaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpPerguntaMousePressed
        if( tpPergunta.isEditable() == true ){
            tipoAlteracaoFonte = "pergunta";

            lbMais.setVisible(true);
            lbMenos.setVisible(true);
        }
    }//GEN-LAST:event_tpPerguntaMousePressed

    private void lbImagen_RespostaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagen_RespostaMousePressed
        new Thread() {   @Override public void run() { try {
            if( jpSalvar.isVisible() == true ){

                JFileChooser JFileChooserJMPasta = new JFileChooser();
                JFileChooserJMPasta.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int status = JFileChooserJMPasta.showSaveDialog(null);
                
                if ( status == javax.swing.JFileChooser.CANCEL_OPTION ){ 

                }else if ( status == javax.swing.JFileChooser.APPROVE_OPTION ){
                    
                    File arquivo = JFileChooserJMPasta.getSelectedFile();
                    //System.out.println("caminho_videos - " + arquivo.getAbsolutePath() );
                    
                    String tempX = arquivo.getAbsolutePath().replace("\\", "/");
                    
                    String str[] = tempX.split("/");
                    if( !str.equals("") ){
                        
                        for (int i = 0; i < str.length; i++){
                            
                            String opcao = str[i].trim();
                            if(opcao.equals("00_Externo")){
                                
                                StringBuilder temp = new StringBuilder();
                                temp.append("//");
                                int ctemp = 0;
                                for (int j = i; j < str.length; j++){
                                    
                                    temp.append( str[j].trim() ); 
                                    
                                    ctemp = str.length - 1;
                                    if(j < ctemp){
                                        
                                        temp.append("//");
                                    }
                                }
                                
                                Exercicios_Beans.setEnderecoExternoImagemResposta(temp.toString() );
                                //System.out.println("caminho_videos - " + temp.toString());
                                String url;
                                String getEnderecoExternoImagemResposta = ""; try{ getEnderecoExternoImagemResposta = Exercicios_Beans.getEnderecoExternoImagemResposta(); }catch( Exception e ){}
                                url = System.getProperty("user.dir") + getEnderecoExternoImagemResposta;
                                ////////////////////////////////////
                                File img = new File( url );

                                BufferedImage bufImg = null;
                                ImageIcon     icon   = null;
                                Image         image  = null;
                                try{
                                    bufImg = ImageIO.read( img );
                                    icon   = new ImageIcon(bufImg);
                                    image  = icon.getImage();//ImageIO.read(f);  
                                } catch (IOException ex) {}  

                                try {  
                                    image = icon.getImage();//ImageIO.read(f);  
                                    int widith = image.getWidth(icon.getImageObserver() );
                                    int height = image.getHeight(icon.getImageObserver() );
                                                                        
                                    lbImagen_Resposta.setIcon(new ImageIcon(image.getScaledInstance(
                                        widith, height, Image.SCALE_DEFAULT)));
                                }catch(Exception ex){}
                                break;
                            }
                        }
                    }
                }
            }
        } catch( Exception e ){
            Exportando.fechar();
            e.printStackTrace();
            JOPM JOptionPaneMod = new JOPM( 2, "VÍDEO EXTERNO NÃO DISPONÍVEL, \n"+ "" +"\n", this.getClass().getSimpleName() );
        } } }.start();
    }//GEN-LAST:event_lbImagen_RespostaMousePressed

    private void lbImagen_PerguntaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagen_PerguntaMousePressed
        new Thread() {   @Override public void run() { try {
            if( jpSalvar.isVisible() == true ){

                JFileChooser JFileChooserJMPasta = new JFileChooser();
                JFileChooserJMPasta.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int status = JFileChooserJMPasta.showSaveDialog(null);
                
                if ( status == javax.swing.JFileChooser.CANCEL_OPTION ){ 

                }else if ( status == javax.swing.JFileChooser.APPROVE_OPTION ){
                    
                    File arquivo = JFileChooserJMPasta.getSelectedFile();
                    //System.out.println("caminho_videos - " + arquivo.getAbsolutePath() );
                    
                    String tempX = arquivo.getAbsolutePath().replace("\\", "/");
                    
                    String str[] = tempX.split("/");
                    if( !str.equals("") ){
                        
                        for (int i = 0; i < str.length; i++){
                            
                            String opcao = str[i].trim();
                            if(opcao.equals("00_Externo")){
                                
                                StringBuilder temp = new StringBuilder();
                                temp.append("//");
                                int ctemp = 0;
                                for (int j = i; j < str.length; j++){
                                    
                                    temp.append( str[j].trim() ); 
                                    
                                    ctemp = str.length - 1;
                                    if(j < ctemp){
                                        
                                        temp.append("//");
                                    }
                                }
                                
                                Exercicios_Beans.setEnderecoExternoImagemPergunta(temp.toString() );
                                //System.out.println("caminho_videos - " + temp.toString());
                                String url;
                                String getEnderecoExternoImagemPergunta = ""; try{ getEnderecoExternoImagemPergunta = Exercicios_Beans.getEnderecoExternoImagemPergunta(); }catch( Exception e ){}
                                url = System.getProperty("user.dir") + getEnderecoExternoImagemPergunta;
                                ////////////////////////////////////
                                File img = new File( url );

                                BufferedImage bufImg = null;
                                ImageIcon     icon   = null;
                                Image         image  = null;
                                try{
                                    bufImg = ImageIO.read( img );
                                    icon   = new ImageIcon(bufImg);
                                    image  = icon.getImage();//ImageIO.read(f);  
                                } catch (IOException ex) {}  

                                try {  
                                    image = icon.getImage();//ImageIO.read(f);  
                                    int widith = image.getWidth(icon.getImageObserver() );
                                    int height = image.getHeight(icon.getImageObserver() );
                                                                        
                                    lbImagen_Pergunta.setIcon(new ImageIcon(image.getScaledInstance(
                                        widith, height, Image.SCALE_DEFAULT)));
                                }catch(Exception ex){}
                                break;
                            }
                        }
                    }
                }
            }
        } catch( Exception e ){
            Exportando.fechar();
            e.printStackTrace();
            JOPM JOptionPaneMod = new JOPM( 2, "VÍDEO EXTERNO NÃO DISPONÍVEL, \n"+ "" +"\n", this.getClass().getSimpleName() );
        } } }.start();
    }//GEN-LAST:event_lbImagen_PerguntaMousePressed

    private void JPOpcao_5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_5MouseEntered

    private void JPOpcao_5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_5MouseExited

    private void JPOpcao_5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_5MousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        tpPergunta.setText("");
        taOpcao1.setText("");
        taOpcao2.setText("");
        taOpcao3.setText("");
        taOpcao4.setText("");
        /*
        try{    
            
            String contentsX = ""; 
            
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);
            boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
            if ( hasTransferableText ) {
                try { 
                    contentsX = (String)contents.getTransferData(DataFlavor.stringFlavor);
                }catch (Exception e){}
            }                
            tpPergunta.setText(contentsX);
            //taOpcao1.setText(contentsX);
            //taOpcao2.setText(contentsX);
            //taOpcao3.setText(contentsX);
            //taOpcao4.setText(contentsX);
        } catch( Exception e ){ }
        */
    }//GEN-LAST:event_jLabel3MousePressed

    PopupMenu_Colar PopupMenu_Colar;
    private void tpPerguntaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpPerguntaMouseClicked
        // Se o botão direito do mouse foi pressionado
        if (evt.getButton() == MouseEvent.BUTTON3){
            PopupMenu_Colar = new PopupMenu_Colar(tpPergunta);
            // Exibe o popup menu na posição do mouse.
            PopupMenu_Colar.show(tpPergunta, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tpPerguntaMouseClicked

    private void taOpcao1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao1MouseClicked
        // Se o botão direito do mouse foi pressionado
        if (evt.getButton() == MouseEvent.BUTTON3){
            PopupMenu_Colar = new PopupMenu_Colar(taOpcao1);
            // Exibe o popup menu na posição do mouse.
            PopupMenu_Colar.show(taOpcao1, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_taOpcao1MouseClicked

    private void taOpcao2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao2MouseClicked
        // Se o botão direito do mouse foi pressionado
        if (evt.getButton() == MouseEvent.BUTTON3){
            PopupMenu_Colar = new PopupMenu_Colar(taOpcao2);
            // Exibe o popup menu na posição do mouse.
            PopupMenu_Colar.show(taOpcao2, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_taOpcao2MouseClicked

    private void taOpcao3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao3MouseClicked
        // Se o botão direito do mouse foi pressionado
        if (evt.getButton() == MouseEvent.BUTTON3){
            PopupMenu_Colar = new PopupMenu_Colar(taOpcao3);
            // Exibe o popup menu na posição do mouse.
            PopupMenu_Colar.show(taOpcao3, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_taOpcao3MouseClicked

    private void taOpcao4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taOpcao4MouseClicked
        // Se o botão direito do mouse foi pressionado
        if (evt.getButton() == MouseEvent.BUTTON3){
            PopupMenu_Colar = new PopupMenu_Colar(taOpcao4);
            // Exibe o popup menu na posição do mouse.
            PopupMenu_Colar.show(taOpcao4, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_taOpcao4MouseClicked
   
    private void rb_01(){   
        if(rb01.isSelected() == true){
            rb01.setSelected(false);  
            rb02.setSelected(false);
            rb03.setSelected(false); 
            rb04.setSelected(false);
        }
        else{
            rb01.setSelected(false);  
            rb02.setSelected(false);
            rb03.setSelected(false); 
            rb04.setSelected(false);
        }
    }
    private void rb_02(){   
        if(rb02.isSelected() == true){
            rb01.setSelected(false); 
            rb02.setSelected(false);
            rb03.setSelected(false); 
            rb04.setSelected(false);
        }
        else{
            rb01.setSelected(false); 
            rb02.setSelected(false);
            rb03.setSelected(false); 
            rb04.setSelected(false);
        }
    }
    private void rb_03(){  
        if(rb03.isSelected() == true){            
            rb01.setSelected(false); 
            rb02.setSelected(false);
            rb03.setSelected(false); 
            rb04.setSelected(false);           
        }
        else{
            rb01.setSelected(false); 
            rb02.setSelected(false);
            rb03.setSelected(false); 
            rb04.setSelected(false);
        }
    }
    private void rb_04(){  
        if(rb04.isSelected() == true){  
            rb01.setSelected(false); 
            rb02.setSelected(false);
            rb03.setSelected(false); 
            rb04.setSelected(false);
        }
        else{
            rb01.setSelected(false); 
            rb02.setSelected(false);
            rb03.setSelected(false); 
            rb04.setSelected(false);
        }
    }
    
    private void editarPergunta(boolean editar){
       
        tpPergunta.setEditable(editar);
        taOpcao1.setEditable(editar);
        taOpcao2.setEditable(editar);
        taOpcao3.setEditable(editar);
        taOpcao4.setEditable(editar);

        rb01.setVisible(editar);
        rb02.setVisible(editar);
        rb03.setVisible(editar);
        rb04.setVisible(editar);

        jpSalvar.setVisible(editar);
        
        btVideo.setVisible(editar);
        btPpt.setVisible(editar);
        btPdf.setVisible(editar);
        
        jpImagemPergunta.setVisible(editar);
   }
    
    //OPÇÃO_01 INÍCIO///////////////////////////////////////////////////////
    private void JM_JPOpcao_1MouseEntered(Component CJPanel, Component cTextArea) {                                       
        if(CJPanel.getBackground() != Color.yellow ){
            CJPanel.setBackground(Color.pink);
            cTextArea.setBackground(Color.pink);
        }
    } 
    private void JM_JPOpcao_1MouseExited(Component CJPanel, Component cTextArea) {                              
        if(CJPanel.getBackground() != Color.yellow ){
            CJPanel.setBackground(Color.WHITE);
            cTextArea.setBackground(Color.WHITE);
        }
    } 
    //OPÇÃO_01 FIM//////////////////////////////////////////////////////
    private void JM_JPOpcao_1MousePressed(Component CJPanelPrincipal, Component CJPanel_1, Component CJPanel_2, Component CJPanel_3,
            Component CTextAreaPrincipal, Component CTextArea_1, Component CTextArea_2, Component CTextArea_3,
            String letraSelecionadaRecebida) {

        if(CJPanelPrincipal.getBackground() == Color.yellow ){

            CJPanelPrincipal.setBackground(Color.WHITE);
            CTextAreaPrincipal.setBackground(Color.WHITE);
            
            CJPanel_1.setBackground(Color.WHITE);
            CJPanel_2.setBackground(Color.WHITE);
            CJPanel_3.setBackground(Color.WHITE);
            
            CTextArea_1.setBackground(Color.WHITE);
            CTextArea_2.setBackground(Color.WHITE);
            CTextArea_3.setBackground(Color.WHITE);
            
            resposta = 0;
            respondeuCerto = false;
            lbResposta.setText("Resposta incorreta - Tente Melhorar!");
        }
        else{

            CJPanelPrincipal.setBackground(Color.yellow);
            CTextAreaPrincipal.setBackground(Color.yellow);
            
            CJPanel_1.setBackground(Color.WHITE);
            CJPanel_2.setBackground(Color.WHITE);
            CJPanel_3.setBackground(Color.WHITE);
            
            CTextArea_1.setBackground(Color.WHITE);
            CTextArea_2.setBackground(Color.WHITE);
            CTextArea_3.setBackground(Color.WHITE);
            
            if( Exercicios_Beans.getLetraDaRespostaCorreta() != null ){
                                        
                resposta = 1;
                
                String getLetraDaRespostaCorreta = ""; try{ getLetraDaRespostaCorreta = Exercicios_Beans.getLetraDaRespostaCorreta(); }catch( Exception e ){}
                                        
                if(letraSelecionadaRecebida.equals( getLetraDaRespostaCorreta )){
                
                    respondeuCerto = true;
                    lbResposta.setText("Resposta correta - Parabéns!!!");
                }
                else{
                
                    respondeuCerto = false;
                    lbResposta.setText("Resposta incorreta - Tente Melhorar!");
                }
            }
        }
    } 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPOpcao_1;
    private javax.swing.JPanel JPOpcao_2;
    private javax.swing.JPanel JPOpcao_3;
    private javax.swing.JPanel JPOpcao_4;
    private javax.swing.JPanel JPOpcao_5;
    private javax.swing.JPanel JPOpcao_6;
    private javax.swing.JPanel Pergunta;
    private javax.swing.JPanel Resposta;
    private javax.swing.JButton btPdf;
    private javax.swing.JButton btPpt;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btResponder;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btVideo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpImagemPergunta;
    private javax.swing.JPanel jpSalvar;
    private javax.swing.JLabel lbExcluir;
    private javax.swing.JLabel lbImagen_Pergunta;
    private javax.swing.JLabel lbImagen_Resposta;
    private javax.swing.JLabel lbMais;
    private javax.swing.JLabel lbMenos;
    private javax.swing.JLabel lbNok;
    private javax.swing.JLabel lbOk;
    private javax.swing.JLabel lbResposta;
    private javax.swing.JTextField lbTitulo;
    private javax.swing.JRadioButton rb01;
    private javax.swing.JRadioButton rb02;
    private javax.swing.JRadioButton rb03;
    private javax.swing.JRadioButton rb04;
    private javax.swing.JTextArea taOpcao1;
    private javax.swing.JTextArea taOpcao2;
    private javax.swing.JTextArea taOpcao3;
    private javax.swing.JTextArea taOpcao4;
    private javax.swing.JTextArea taRespostaCorreta_Parabens;
    private javax.swing.JLabel tfImgCorreta;
    private javax.swing.JLabel tfImgCorreta1;
    private javax.swing.JTextArea tpPergunta;
    // End of variables declaration//GEN-END:variables
    
}