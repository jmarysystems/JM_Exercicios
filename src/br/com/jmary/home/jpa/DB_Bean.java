package br.com.jmary.home.jpa;

/**
 *
 * @author ana
 */
public class DB_Bean {
    
    public static String tipo_de_conexao = "alone"; //network
    public static String ip_do_servidor  = "127.0.0.1";
    public static String porta_servidor  = "1527";
    public static String tipoDeBanco     = "derby";
    public static String nomeDoBanco     = "Banco_De_Dados";
    public static String usuario         = "cleilson";
    public static String senha           = "23071354";    
    public static String endereco_db     = System.getProperty("user.dir"); // + System.getProperty("file.separator") + "JMarySystems"       
    public static String bootPassword    = "a@Simple_More_Than_16_Char_Password";
    
    public static String driverAlone     = "org.apache.derby.jdbc.EmbeddedDriver"; 
    public static String driverNetwork   = "org.apache.derby.jdbc.ClientDriver";
   
    public static String urlAlone        = "jdbc:"+tipoDeBanco+":" + endereco_db + "/" + nomeDoBanco+";" + "user="+usuario+";" + "password="+senha+"";
    public static String urlNetwork      = "jdbc:"+tipoDeBanco+"://" + ip_do_servidor + ":"+porta_servidor+"/" + nomeDoBanco+";" + "user="+usuario+";" + "password="+senha+"";
    
}
