
package br.com.jmary.home;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import imagens_internas.Imagens_Internas;
import java.io.IOException;

public class HomeJApplet extends JApplet {
    static JFrame frame;

    @Override
    public void init() {

        add( new Home( frame ) ); // BorderLayout.CENTER

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {}
                
                frame = new JFrame( "JMARYSYSTEMS - Suporte: 85 9.9139.2441", null );                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JApplet applet = new HomeJApplet();
                applet.init();
                
                frame.setContentPane( applet.getContentPane() );
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setIconImage(null);
                
                iconeDoPrograma( frame );
                telaCheia ( frame );

                applet.start();
            }
        });
    }
    
    public static void iniciarJFrame(){
        main(new String [2]);
    }
    
    private static void telaCheia( JFrame frame ) {
        try{ frame.setUndecorated(true);                                    } catch( Exception e ){}
        try{ frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME); } catch( Exception e ){}
        try{ frame.setExtendedState(JFrame.MAXIMIZED_BOTH);                 } catch( Exception e ){}
    }
    
    /* Início Setando O Ícone do Programa */
    private static void iconeDoPrograma( JFrame frame ) {     
        try{ 
            BufferedImage bufImg = ImageIO.read(Imagens_Internas.class.getResource("jmol.png") );  
                        
            frame.setIconImage( bufImg ); //bi
        }catch( IOException e ){}      
    }
    /* Fim Setando O Ícone do Programa */

}
