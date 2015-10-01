import java.sql.*;
import java.io.*;
import javax.servlet.*;
public class RegServlet extends GenericServlet
{
	Connection conn;
		public void service(ServletRequest req,ServletResponse res)
	{
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:xe","system","manager");
		String s1=req.getParameter("fname");
		String s2=req.getParameter("lname");
		String s3=req.getParameter("uname");
		String s4=req.getParameter("pword");
		PreparedStatement pstmt=conn.prepareStatement("insert into uinfo values(?,?,?,?)");
		pstmt.setString(1,s1);
		pstmt.setString(2,s2);
		pstmt.setString(3,s3);
		pstmt.setString(4,s4);
		pstmt.executeUpdate();
		PrintWriter pw=res.getWriter();
		pw.println("<html><body bgcolor=green text=wheat><center><h1>Registration Sucessfull</h1><br>");
		pw.println("<a href=login.html>Login</a></center></body></html>");
	}catch(Exception e)
	{
		System.err.println(e);
	}	
	}	
}
