/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package home_controle;

import java.io.File;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author AnaMariana
 */
public class BookInfo_Modelo {
    
        public String bookName;
        public URL    bookURL;
        public String tipoNoBanco; 
        public int    idNoBanco; 
        
        public String treeRoot;
        public String treePastaNoExpande;
        public String treePastaExpande;
        public String treeNod;
        public String tree1Nod;

        public BookInfo_Modelo(String nome, String endereco, String root, String pastaNoExpande, String pastaExpande, String nod, String nodTree1 ) {
            bookName = nome;                    
            treeRoot = root;
            treePastaNoExpande = pastaNoExpande;
            treePastaExpande = pastaExpande;
            treeNod = nod;
            tree1Nod = nodTree1;
            
            try{ 
                File File = new File(endereco);
                if( File.exists() == true ){
                    bookURL = File.toURI().toURL(); 
                }
            }catch( Exception e ){}    
        }
        
        @Override
        public String toString() {
            return bookName;
        }
        
        private static DefaultMutableTreeNode book;    
    public static void livro( DefaultMutableTreeNode livroParaAdd, String nome, String url, String root, String pastaNoExpande, String pastaExpande, String nod, String nodTree1 ) {
        try{ 
            book = new DefaultMutableTreeNode(new BookInfo_Modelo ( nome, url, root, pastaNoExpande, pastaExpande, nod, nodTree1 ) );
        } catch( Exception e ) { 
            JOptionPane.showMessageDialog( null, "BookInfo - Método: livro \n" + e.getMessage() ); 
        }
        livroParaAdd.add( book );
    } 

}
