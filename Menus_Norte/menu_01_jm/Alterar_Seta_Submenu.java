/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_01_jm;

import br.com.jmary.home.Home;
import br.com.jmary.utilidades.JOPM;
import home_controle_menus_norte.Controle_de_Alterar_Menu_Norte;
import imagens.Imagens_Menu_Norte;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 *
 * @author Ana
 */
public class Alterar_Seta_Submenu {
    
    Menu_1_JM Menu_1_JM;
    Controle_de_Alterar_Menu_Norte Controle_de_Alterar_Menu_Norte;
    Home Home;
        
    URL       imgURL; 
    ImageIcon icon;
            
    URL       imgURL2;
    ImageIcon icon2;
        
    public Alterar_Seta_Submenu( Menu_1_JM Menu_1_JM2, Controle_de_Alterar_Menu_Norte Controle_de_Alterar_Menu_Norte2, Home Home2 ) {
        
        Menu_1_JM = Menu_1_JM2;
        Controle_de_Alterar_Menu_Norte = Controle_de_Alterar_Menu_Norte2;
        Home = Home2;
        
        setarUrl_e_ImageIcon();
    }
    
    private void setarUrl_e_ImageIcon(){
        try{                       
            
            imgURL  = Imagens_Menu_Norte.class.getResource("seta_para_baixo.png");
            icon    = new ImageIcon( imgURL );  
            
            imgURL2  = Imagens_Menu_Norte.class.getResource("seta_para_cima.png");
            icon2   = new ImageIcon( imgURL2 ); 
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "setarUrl_e_ImageIcon(), \n"
                + e.getMessage() + "\n", "Alterar_Seta_Submenu" ); }
    }
    
    private boolean cimabaixo = true; 
    public void tabJanelaSubmenu( JMenu seta_do_menu ){
        try{

            if( cimabaixo == false ){
                cimabaixo = true;
                
                seta_do_menu.setToolTipText( "Ocultar Submenu" );
                seta_do_menu.setIcon( icon2 );
                
                Menu_1_JM.Controle_de_Alterar_Menu_Norte.adicionarSubmenu( Home.Menu_Atual );               
                
            } else if( cimabaixo == true ){
                cimabaixo = false;
                                
                seta_do_menu.setToolTipText( "Mostrar Submenu" );
                seta_do_menu.setIcon( icon );

                Menu_1_JM.Controle_de_Alterar_Menu_Norte.retirarSubmenu();                                                 
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabJanelaSubmenu, \n"
                + e.getMessage() + "\n", "Alterar_Seta_Submenu" ); }
    }
    
}
