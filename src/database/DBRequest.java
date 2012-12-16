package database;
import java.sql.*;
import java.util.ArrayList;
import org.apache.commons.dbutils.DbUtils;

public class DBRequest {
   
    private static String url="jdbc:hsqldb:file:${user.home}/i377/Team03d/piirivalveDb;shutdown=true";
    private static String user     = "";
    private static String pwds = "";
     private Object [][] resSet;
        
        
       // 
    public DBRequest(){
    
    
    }
    
    public Object[][] SelectParing(String ps, ArrayList param){
    ResultSet rs=null;
    Connection conn=null;
    PreparedStatement pLause=null;
        try {
           conn =DriverManager.getConnection(url);
           pLause=conn.prepareStatement(ps,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
            for(int a=0;a<param.size();a++){
                String kNimi=param.get(a).getClass().toString();
                if(kNimi.compareTo("class java.lang.String")==0){ 
                    pLause.setString(a+1, param.get(a).toString());
                }
                if(kNimi.compareTo("class java.lang.Integer")==0){ 
                    Integer iArv=(Integer)param.get(a);
                    pLause.setInt(a+1, iArv.intValue());
                
                }
                if(kNimi.compareTo("class java.lang.Long")==0){ 
                    Long iArv=(Long)param.get(a);
                    pLause.setLong(a+1, iArv.longValue());
                }
                
                if(kNimi.compareTo("class java.sql.Timestamp")==0){ 
                    pLause.setTimestamp(a+1, (Timestamp)param.get(a));
                    
                }
                if(kNimi.compareTo("class [B")==0){ 
                    pLause.setBytes(a+1, (byte[])param.get(a));
                    
                }
                
                if(kNimi.compareTo("class java.lang.Double")==0){ 
                    Double dArv=(Double)param.get(a);
                    pLause.setDouble(a+1, dArv.doubleValue());
                
                }
            }
            
            rs=pLause.executeQuery();
            
            ResultSetMetaData rsm=rs.getMetaData();
            int tulpi=rsm.getColumnCount();
            rs.last();
            int ridu=rs.getRow();
            this.resSet=new Object[ridu][tulpi];
            if(rs.first())
            {   for(int realoendur=0;realoendur<ridu;realoendur++)
                { 
                   
                    for(int tl=0;
                            tl<tulpi;
                            tl++){
                        this.resSet[realoendur][tl]=rs.getObject(tl+1);
                    } 
                    if(!rs.next()) break;
            
            
                } 
            } 
            
                   
            
        } catch (SQLException ex) {
           System.out.println(ex.getMessage()); 
        }
        finally{
         DbUtils.closeQuietly(rs);
         DbUtils.closeQuietly(pLause);
         DbUtils.closeQuietly(conn);
        }
    
    
        
    return this.resSet;
    }
   
    
    
    public ArrayList ODBCParing(String ps){
    ArrayList tulemus=new ArrayList(); 
    ResultSet rs=null; 
    Connection conn=null; 
    Statement lause=null; 
        try {
            conn =DriverManager.getConnection(url);
            lause=conn.createStatement(); 
            rs=lause.executeQuery(ps); 
            
            ResultSetMetaData rsm=rs.getMetaData();
            int tulpi=rsm.getColumnCount(); 
            Object[] oMass=new Object[tulpi]; 
            while(rs.next()){
              
                for(int aa=0;aa<tulpi;aa++){
                  Object oAjutine=new Object();
                  oAjutine=rs.getObject(aa+1);
                  oMass[aa]=oAjutine;
               } 
                tulemus.add(oMass);
                oMass=new Object[tulpi]; 
                
            } 
            
            System.out.println(oMass[0]);
            
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    
    return tulemus;
    }
    
    
    
    
    
    
    
    
    public int DMLParing(String ps, ArrayList param){
    int tulem=-1;
    Connection conn=null;
    PreparedStatement pLause=null;
        try {
            conn=DriverManager.getConnection(url);
            pLause=conn.prepareStatement(ps);
            
            for(int a=0;a<param.size();a++){
                String kNimi=param.get(a).getClass().toString();
                if(kNimi.compareTo("class java.lang.String")==0){ 
                    pLause.setString(a+1, param.get(a).toString());
                }
                if(kNimi.compareTo("class java.lang.Integer")==0){ 
                    Integer iArv=(Integer)param.get(a);
                    pLause.setInt(a+1, iArv.intValue());
                
                }
                if(kNimi.compareTo("class java.lang.Long")==0){ 
                    Long iArv=(Long)param.get(a);
                    pLause.setLong(a+1, iArv.longValue());
                }
                if(kNimi.compareTo("class java.sql.Timestamp")==0){ 
                    pLause.setTimestamp(a+1, (Timestamp)param.get(a));
                    
                }
                if(kNimi.compareTo("class [B")==0){ 
                    pLause.setBytes(a+1, (byte[])param.get(a));
                    
                }
                if(kNimi.compareTo("class java.lang.Double")==0){ 
                    Double dArv=(Double)param.get(a);
                    pLause.setDouble(a+1, dArv.doubleValue());
                }
             }
            
            tulem=pLause.executeUpdate();
            conn.close();
            
        } catch (SQLException ex) {
           System.out.println("DML päring"+ex.getMessage()); 
        }
        finally{
         
         DbUtils.closeQuietly(pLause);
         DbUtils.closeQuietly(conn);
        }
        
    
    
        
    return tulem;
    }
    
    public int teeTehing(ArrayList<String> laused, boolean koosCommitiga){
     Connection conn=null;
     try{
       conn= DriverManager.getConnection(url,user,pwds);
       int lauseid=laused.size();
    
       PreparedStatement psMas[]=new PreparedStatement[lauseid];
      
       if(!koosCommitiga) conn.setAutoCommit(false);
       for(int a=0;a<lauseid;a++){
           psMas[a]=conn.prepareStatement(laused.get(a));
           psMas[a].execute();
    
       if(koosCommitiga) conn.commit();
       }
      
       if(!koosCommitiga) conn.commit();
       for(int a=0;a<psMas.length;a++){
        if(psMas[a]!=null) psMas[a].close();
       }
     }
    catch(Exception e){
    System.out.println("SQL viga tehing "+e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                
            }
    }
    finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
    }
    
    
    return 0;
    }
   
      public Object[][] insSel(ArrayList<String> laused){
     Connection conn=null;
     ResultSet rs=null;
     try{
       conn= DriverManager.getConnection(url,user,pwds);
       
           PreparedStatement psIns =conn.prepareStatement(laused.get(0));
           psIns.executeUpdate();
           PreparedStatement psSel =conn.prepareStatement(laused.get(1),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
           rs=psSel.executeQuery();
           ResultSetMetaData rsm=rs.getMetaData();
            int tulpi=rsm.getColumnCount();
            rs.last();
            int ridu=rs.getRow();
            this.resSet=new Object[ridu][tulpi];
            if(rs.first())
            { 
                for(int realoendur=0;realoendur<ridu;realoendur++)
                { 
                   
                    for(int tl=0;
                            tl<tulpi;
                            tl++){
                        this.resSet[realoendur][tl]=rs.getObject(tl+1);
                    } 
                    if(!rs.next()) break;
            
            
                } 
            }
      
     }
    catch(Exception e){
    System.out.println("SQL viga insSel "+ e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                
            }
    }
    finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
    }
    
    
    return this.resSet;
    }   
    
    
    
}
