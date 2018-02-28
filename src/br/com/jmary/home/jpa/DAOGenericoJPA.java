package br.com.jmary.home.jpa;

import br.com.jmary.utilidades.JOPM;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DAOGenericoJPA<Gene> {

    private static EntityManager em;
    private Gene gene;
    
    public DAOGenericoJPA( Gene gene2, EntityManager em2 ) { try{
        
            gene = gene2;
            em = em2;
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "DAOGenericoJPA( Gene gene2 ), \n"
                + e.getMessage() + "\n", "DAOGenericoJPA<Gene>" ); }
    }

    public void inserir() { try {                   
            
            em.getTransaction().begin();
            em.persist(gene);
            em.getTransaction().commit();
            em.close();
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "inserir(), \n"
                + e.getMessage() + "\n", "DAOGenericoJPA<Gene>" ); } 
    }
    
    public void excluir(){ /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );                   
                        
            em.getTransaction().begin();
            gene = em.merge( gene );
            em.remove( gene );
            em.getTransaction().commit();            
            em.close();  
            
            //UMA OUTRA OPÇÃO
          /*em.remove(em.merge(gene));
            em.getTransaction().commit();            
            em.close();*/
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "excluir, \n"
                + e.getMessage() + "\n", "DAOGenericoJPA<Gene>" ); } //} }.start();
    }

    public void atualizar(){ /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );                   
            
            em.getTransaction().begin();
            em.refresh(gene);
            em.getTransaction().commit();
            em.close();  
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "atualizar(), \n"
                + e.getMessage() + "\n", "DAOGenericoJPA<Gene>" ); } //} }.start(); 
    }

    public Gene gravanovoOUatualizar(){ /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );                   
            /*
            em.getTransaction().begin();
            em.merge(gene);
            em.getTransaction().commit();
            em.close(); */  
    
            em.getTransaction().begin();
            //em.merge(gene); - tirasse este e coloca-se este logo abaixo
            gene = em.merge( gene );
            em.getTransaction().commit();
            em.close();

        } catch( Exception e ){ e.printStackTrace(); JOPM JOPM = new JOPM( 2, "gravanovoOUatualizar(), \n"
                + e.getMessage() + "\n", "DAOGenericoJPA<Gene>" ); } //} }.start();
    
        return gene;
    }
     
    public Gene getBean( Integer id, Class clazz ){ /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );                   
            
            gene = (Gene) em.find(clazz, id);

        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "getBean(), \n"
                + e.getMessage() + "\n", "DAOGenericoJPA<Gene>" ); } //} }.start();
    
        return gene;
    }
    
    public List<Gene> getBeansCom_1_Parametro( Class clazz, String sql, String parametro1  ){  
        List<Gene> list = new ArrayList<Gene>();/*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );                   
            
            Query q = JPAUtil.em().createNativeQuery( sql, clazz );
            q.setParameter(1, parametro1);
            
            list = q.getResultList();

        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "getBeansCom_1_Parametro(), \n"
                + e.getMessage() + "\n", "DAOGenericoJPA<Gene>" ); } //} }.start();
    
        return list;
    }
    
    public List<Gene> getBeansCom_2_Parametro( Class clazz, String sql, String parametro1, String parametro2  ){  
        List<Gene> list = new ArrayList<Gene>();/*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );                   
            
            Query q = JPAUtil.em().createNativeQuery( sql, clazz );
            q.setParameter(1, parametro1);
            q.setParameter(2, parametro2);
            
            list = q.getResultList();

        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "getBeansCom_1_Parametro(), \n"
                + e.getMessage() + "\n", "DAOGenericoJPA<Gene>" ); } //} }.start();
    
        return list;
    }
    
    public List<Gene> getBeansCom_1_Parametro_Return_List( Class clazz, String sql, List<String> listaSap ){  
        List<Gene> list = new ArrayList<Gene>();/*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );                   
            
            for (int ix = 0; ix < listaSap.size(); ix++){ 
                
                try {
                    
                    List<Gene> list_Temp = new ArrayList<Gene>();
                
                    Query q = JPAUtil.em().createNativeQuery( sql, clazz );
                    q.setParameter(1, listaSap.get(ix));
            
                    list_Temp = q.getResultList();
                
                    list.add( list_Temp.get(0) );
                } catch( Exception e ){}
            }

        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "getBeansCom_1_Parametro(), \n"
                + e.getMessage() + "\n", "DAOGenericoJPA<Gene>" ); } //} }.start();
    
        return list;
    }

}