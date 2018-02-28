package br.com.jmary.home.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ana
 */
public class DB {
    
    public Connection derby(){
        Connection con = null;
        
        try{       
                  
            String url = "";           
            String driver = "";            
            switch( DB_Bean.tipo_de_conexao ){
                case "alone": 
                        url    = DB_Bean.urlAlone;           
                        driver = DB_Bean.driverAlone;
                    break;
                case "network": 
                        url    = DB_Bean.urlNetwork;           
                        driver = DB_Bean.driverNetwork;
                    break;    
            }
                   
            Class.forName( driver );        
            con = DriverManager.getConnection( url );
        }catch(ClassNotFoundException | SQLException e){ }
    
        return con;
    }
    
}
