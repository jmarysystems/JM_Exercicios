/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package home_controle;

import br.com.jmary.home.Home;
import br.com.jmary.utilidades.JOPM;
import imagens_internas.Imagens_Internas;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Ana
 */
public class ControleTabs {
    Home Home;    
    public int qtdComponentes = 0;
    
    public ControleTabs( Home Home2 ) {
        Home = Home2;
    }
        
    public void AddTabsAoHome( String nomeTab, String imgURLIcon, Component comp ) {
        try{ 
            
            ImageIcon icon = new ImageIcon(getClass().getResource( imgURLIcon ));
            
            JScrollPane sc = new JScrollPane();
            sc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
            sc.setViewportView( comp );
                    
            Home.tabHome.addTab( "  "+nomeTab, icon, sc, nomeTab ); 
                    
            qtdComponentes+=1;
            Home.tabHome.setTabComponentAt( qtdComponentes, new ButtonTabComponent( Home.tabHome, nomeTab, icon, Home ) );                                        
            Home.tabHome.setSelectedIndex( qtdComponentes );
                    
            //removerTabHome();
        }catch( Exception e ){ JOptionPane.showMessageDialog(null,"ControleTabs - AddTabsAoHome\ns"+e); }
    }
    
    public void AddTabComControle( JTabbedPane paneCom, String nomeTab, String imgURLIcon, Component comp ) {
        try{ 
            //System.out.println("imgURLIcon: " + imgURLIcon);
                        
            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
            
            JScrollPane sc = new JScrollPane();
            sc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
            sc.setViewportView( comp );
            
            int qtdComponentesComControle = paneCom.getTabCount();
                    
            paneCom.addTab( "  "+nomeTab, icon, sc, nomeTab );                  
                        
            paneCom.setTabComponentAt( qtdComponentesComControle, new ButtonTabComponent_Com_Controle( paneCom, nomeTab, icon, Home ) );                                        
            paneCom.setSelectedIndex( qtdComponentesComControle );
                    
            //removerTabHome();
        }catch( Exception e ){ 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"ControleTabs - AddTabsAoHome\ns"+e.getMessage()); 
        }
    }
    
    public void removerTabSelecionado() {
        
        Home.tabHome.remove( Home.tabHome.getSelectedComponent() );
        qtdComponentes-=1;
    }
    
    
            
    // Setando ImageIcon Diferente pelo endereço não pela classe
    JTabbedPane paneSem;
    String nomeTabSem;
    String imgURLIconSem;
    Component compSem;
    public void AddTabSemControleNovo( JTabbedPane paneSem2, String nomeTabSem2, String imgURLIconSem2, Component compSem2 ) {
        paneSem = paneSem2; nomeTabSem = nomeTabSem2; imgURLIconSem = imgURLIconSem2; compSem = compSem2;
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );            
            
            ImageIcon icon = new ImageIcon(getClass().getResource( imgURLIconSem ) );  
            
            JScrollPane sc = new JScrollPane();
            sc.setBorder( null );
            sc.setViewportView( compSem );
            
            int qtdComponentesControl = paneSem.getTabCount();

            sc.setSize( 100, 19 );
            sc.setPreferredSize(new java.awt.Dimension( 100, 33 ) );
            
            paneSem.addTab( "  "+nomeTabSem, icon, sc, nomeTabSem );              
            paneSem.setTabComponentAt( qtdComponentesControl, new ButtonTabComponentSemControle( paneSem, nomeTabSem, icon ) ); 
            paneSem.setSelectedIndex ( qtdComponentesControl );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "AddTabSemControleNovo( JTabbedPane paneSem2, String nomeTabSem2, String imgURLIconSem2, Component compSem2 ), \n"
                + e.getMessage() + "\n", "ControleTabs" ); } } }.start();    
    }
    
    public void AddTabSemControleNovoSemThread( JTabbedPane paneSem2, String nomeTabSem2, String imgURLIconSem2, Component compSem2 ) {
        paneSem = paneSem2; nomeTabSem = nomeTabSem2; imgURLIconSem = imgURLIconSem2; compSem = compSem2;
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );            
            
            ImageIcon icon = new ImageIcon(getClass().getResource( imgURLIconSem ) );  
            
            JScrollPane sc = new JScrollPane();
            sc.setBorder( null );
            sc.setViewportView( compSem );
            
            int qtdComponentesControl = paneSem.getTabCount();

            sc.setSize( 100, 19 );
            sc.setPreferredSize(new java.awt.Dimension( 100, 33 ) );
            
            paneSem.addTab( "  "+nomeTabSem, icon, sc, nomeTabSem );              
            paneSem.setTabComponentAt( qtdComponentesControl, new ButtonTabComponentSemControle( paneSem, nomeTabSem, icon ) ); 
            paneSem.setSelectedIndex ( qtdComponentesControl );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "AddTabSemControleNovo( JTabbedPane paneSem2, String nomeTabSem2, String imgURLIconSem2, Component compSem2 ), \n"
                + e.getMessage() + "\n", "ControleTabs" ); } //} }.start();    
    }
    
    // Setando ImageIcon Diferente Sem sc pelo endereço não pela classe
    JTabbedPane paneSem2;
    String nomeTabSem2;
    String imgURLIconSem2;
    Component compSem2;
    public void AddTabSemSCControleNovo( JTabbedPane paneSem3, String nomeTabSem3, String imgURLIconSem3, Component compSem3 ) {
        paneSem2 = paneSem3; nomeTabSem2 = nomeTabSem3; imgURLIconSem2 = imgURLIconSem3; compSem2 = compSem3;
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );            
            
            ImageIcon icon = new ImageIcon(getClass().getResource( imgURLIconSem2 ) );  
            
            int qtdComponentesControl = paneSem2.getTabCount();
            
            paneSem2.addTab( "  "+nomeTabSem2, icon, compSem2, nomeTabSem2 );              
            paneSem2.setTabComponentAt( qtdComponentesControl, new ButtonTabComponentSemControle( paneSem2, nomeTabSem2, icon ) ); 
            paneSem2.setSelectedIndex ( qtdComponentesControl );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "AddTabSemControleNovo( JTabbedPane paneSem2, String nomeTabSem2, String imgURLIconSem2, Component compSem2 ), \n"
                + e.getMessage() + "\n", "ControleTabs" ); } } }.start();    
    }
    
    public void AddTabSemSCControleNovo2( JTabbedPane paneSem3, String nomeTabSem3, String imgURLIconSem3, Component compSem3 ) {
        paneSem2 = paneSem3; nomeTabSem2 = nomeTabSem3; imgURLIconSem2 = imgURLIconSem3; compSem2 = compSem3;
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );            
            
            ImageIcon icon = new ImageIcon(getClass().getResource( imgURLIconSem2 ) );  
            
            int qtdComponentesControl = paneSem2.getTabCount();
            
            paneSem2.addTab( "  "+nomeTabSem2, icon, compSem2, nomeTabSem2 );              
            paneSem2.setTabComponentAt( qtdComponentesControl, new ButtonTabComponentSemControle( paneSem2, nomeTabSem2, icon ) ); 
            paneSem2.setSelectedIndex ( qtdComponentesControl );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "AddTabSemControleNovo( JTabbedPane paneSem2, String nomeTabSem2, String imgURLIconSem2, Component compSem2 ), \n"
                + e.getMessage() + "\n", "ControleTabs" ); } } }.start();    
    }
    
    public void AddTabSemSCControleNovo( String nomeTab, String imgURLIcon, Component comp ) {
        try{ 
            
            ImageIcon icon = new ImageIcon(getClass().getResource( imgURLIcon ));
                    
            Home.tabHome.addTab( "  "+nomeTab, icon, comp, nomeTab ); 
                    
            qtdComponentes+=1;
            Home.tabHome.setTabComponentAt( qtdComponentes, new ButtonTabComponent( Home.tabHome, nomeTab, icon, Home ) );                                        
            Home.tabHome.setSelectedIndex( qtdComponentes );
                    
            //removerTabHome();
        }catch( Exception e ){ JOptionPane.showMessageDialog(null,"ControleTabs - AddTabsAoHome\ns"+e); }
    }
    
    public void removerTabSemControleSelecionado(JTabbedPane pane) {
        try{
            pane.remove( pane.getSelectedComponent() );
        }catch(Exception e ){}  
    }
    
    public void removerTabSemControleSelecionadoPeloNome(JTabbedPane pane, Component c) {
        try{
            pane.remove(c);
        }catch(Exception e ){}    
    }
    
}
