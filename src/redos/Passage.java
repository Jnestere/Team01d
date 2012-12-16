package redos;

import java.io.IOException;
import database.DBRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Passage extends HttpServlet {

    @Override
    public void init() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adm_liigi_redaktor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adm_liigi_redaktor at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

  
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         List<String> errors = getValidationErrors(request);
	        if (!errors.isEmpty()) {
	            request.setAttribute("errors", errors);
	            showForm(request, response);
	            return;
	        }
               
                
            
	String kood =request.getParameter("ay_liik_kood");
        String nimi =request.getParameter("ay_liik_nimi"); 
        String komm =request.getParameter("ay_liik_komm"); 
        
        String ylem_id =request.getParameter("ay_liik_ylemus");
        
        DBRequest p=new DBRequest();
        ArrayList params=new ArrayList();
        params.add(kood);
        params.add(nimi);
        params.add(komm);
                      
        String sql="INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',?,?,?,CURRENT_DATE,'9999-12-31')";
        p.DMLParing(sql, params);
        
    
        int ylem_ID_nr = Integer.parseInt(ylem_id);
        
        if(ylem_ID_nr!=0){
        
        ArrayList param2=new ArrayList();
        param2.add(kood);
        String sql2 = "SELECT riigi_admin_yksuse_lik_id  FROM RIIGI_ADMIN_YKSUSE_LIIK WHERE KOOD=?";
        Object res[][] = p.SelectParing(sql2, param2);
        String alluv_id = res[0][0].toString();
        
        
         ArrayList param3=new ArrayList();
         param3.add(ylem_id);
         param3.add(alluv_id);
         String sql3 = "INSERT INTO VOIMALIK_ALLUVUS  VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',?,?,'komm')";
         p.DMLParing(sql3, param3);
        }
         showForm(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        


    	DBRequest p = new DBRequest();
        String sql = "SELECT riigi_admin_yksuse_lik_id,nimetus FROM RIIGI_ADMIN_YKSUSE_LIIK";
        Object tulem[][] = p.SelectParing(sql, new ArrayList());
        
        
      
        
        String sql2 = "SELECT riigi_admin_yksuse_lik_id,NIMETUS FROM PUBLIC.RIIGI_ADMIN_YKSUSE_LIIK RIIGI_ADMIN_YKSUSE_LIIK WHERE RIIGI_ADMIN_YKSUSE_LIK_ID NOT IN (SELECT VOIMALIK_ALLUV_LIIK_ID FROM PUBLIC.VOIMALIK_ALLUVUS)";
        Object tulem2[][] = p.SelectParing(sql2, new ArrayList());
          
        
        request.setAttribute("vormiAndmed", tulem); 
        request.setAttribute("allumatud", tulem2);
        request.getRequestDispatcher("adm_liigi_redaktor.jsp").forward(request, response);
        
    } 


    private List<String> getValidationErrors(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        if ("".equals(request.getParameter("ay_liik_kood"))) {
            errors.add("Sisesta alluvusyksuse liigi kood!");
        }
        if ("".equals(request.getParameter("ay_liik_nimi"))) {
            errors.add("Sisesta alluvusyksuse liigi nimi!");
        }     
            if ("".equals(request.getParameter("ay_liik_komm"))) {
            errors.add("Sisesta alluvusyksuse liigi kommentaar!");
        }
                

        return errors;
    }
}