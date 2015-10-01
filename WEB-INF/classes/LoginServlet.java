import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
public class LoginServlet extends HttpServlet
{
	Connection conn;
	
	public void doGet(HttpServletRequest hreq,HttpServletResponse hres)
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
		if(rs.next())
		{
			pw.println("You have Successfully Logged in");
		}
		else
		{
			pw.println("Invalid uname/pword");
		}
		pw.println("</h1></body></html>");
							
		}catch(Exception e)
		{
			System.err.println(e);
		}	
	}	
}
