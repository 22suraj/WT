

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String a = request.getParameter("uidd"); 
		String x = request.getParameter("unn"); 
		String b = request.getParameter("passs");
		String c = request.getParameter("mobb"); 
		String d = request.getParameter("eidd"); 
		HttpSession session = request.getSession();
		session.setAttribute("d",d);
		java.util.Date now = new java.util.Date(); 
		String DATE_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT); String strDateNew = sdf.format(now) ;
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginpage","root",""); 
		PreparedStatement ps=con.prepareStatement("insert into student(sname,suserid,spass,mobile,email,sdate) values(?,?,?,?,?,'"+strDateNew+"')");
		ResultSet rs = ps.executeQuery(); 
		ps.setString(1,x); 
		ps.setString(2,a); 
		ps.setString(3,b);  
		ps.setString(4,c);  
		ps.setString(5,d); 
		ps.executeUpdate();
		response.sendRedirect("SignUp.jsp?success");
		}
		catch(Exception e1)
		{
		out.println(e1.getMessage()); response.sendRedirect("SignUp.jsp?Failure");
		
		}
	}

}
