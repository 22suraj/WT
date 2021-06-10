

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String a=request.getParameter("user1"); 
		String b=request.getParameter("pass");
		String id=null,name=null,userid=null,email=null; 
		try
		{ 
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginpage","root","");
		//Connection con = databasecon.getconnection();
		PreparedStatement ps=con.prepareStatement("select sid,sname,suserid,email from student where suserid='"+a+"' && spass='"+b+"'"); 
		ResultSet rs=ps.executeQuery(); 
		HttpSession session = request.getSession();
		if(rs.next())
		{
		id=rs.getString("sid"); 
		name=rs.getString("sname"); 
		userid=rs.getString("suserid");
		email=rs.getString("email"); 
		session.setAttribute("sid",id);
		session.setAttribute("sname",name); 
		session.setAttribute("suserid",userid); 
		session.setAttribute("email",email);
		//response.sendRedirect("user5.jsp"); response.sendRedirect("LoginSuccess.jsp?Success");
		//out.print(name2);
		}
		else
		{
		response.sendRedirect("LoginFailure.jsp?Failure");
		}
		}
		catch(Exception e2){ 
			out.println(e2.getMessage());
		}
	}

}
