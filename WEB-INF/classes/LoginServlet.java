import java.sql.*;
import java.io.*;
import javax.servlet.*;
public class LoginServlet extends GenericServlet
{
	Connection conn;
	public void service(ServletRequest req,ServletResponse res)
	{
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:xe","system","manager");
		String s1=req.getParameter("uname");
		String s2=req.getParameter("pword");
		PreparedStatement pstmt=conn.prepareStatement("select * from uinfo where uname=? and pword=?");
		pstmt.setString(1,s1);
		pstmt.setString(2,s2);
		ResultSet rs=pstmt.executeQuery();
		PrintWriter pw=res.getWriter();
		pw.println("<html><body bgcolor=red text=black><h1>");
		ServletContext sc=getServletContext();
		if(rs.next())
		{
			pw.println("You have Successfully Logged in");
		}
		else
		{
			pw.println("Invalid uname/pword");
			RequestDispatcher rd=sc.getRequestDispatcher("/login.html");
			rd.include(req,res);
		}
		pw.println("</h1></body></html>");
							
		}catch(Exception e)
		{
			System.err.println(e);
		}	
	}	
}
