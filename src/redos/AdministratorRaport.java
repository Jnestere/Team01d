package redos;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DBRequest;
public class AdministratorRaport extends HttpServlet {

    @Override
    public void init(){
        try{
        Class.forName("org.hsqldb.jdbcDriver");
            } catch (ClassNotFoundException e){
    throw new RuntimeException(e);
   }
    }

    
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdministratorRaport</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdministratorRaport at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showForm(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       
        String kuupaev=request.getParameter("kuupaev");
        String ayLiik=request.getParameter("ayLiik");
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dKp=new Date();
        if(kuupaev==null){
            kuupaev=dateFormat.format(dKp);
        }
        if(ayLiik==null){
            ayLiik="1";
        }
        int iAyLiik=Integer.parseInt(ayLiik);
        request.setAttribute("kuupaev", kuupaev);
        request.setAttribute("vYksus", ayLiik);
        
       
        DBRequest p=new DBRequest();
        String sql="SELECT riigi_admin_yksuse_lik_id,nimetus FROM RIIGI_ADMIN_YKSUSE_LIIK";
        Object tulem[][]=p.SelectParing(sql, new ArrayList());
        
        request.setAttribute("yLiik", tulem);
        
        ArrayList al=new ArrayList();
        al.add(iAyLiik);
        sql="select nimetus from riigi_admin_yksus where riigi_admin_yksuse_lik_id=?";
        Object ayd[][]=p.SelectParing(sql, al);
        request.setAttribute("yksused", ayd);
        
       
        
request.getRequestDispatcher("AdministratorRaport.jsp").forward(request, response);

    }
}
