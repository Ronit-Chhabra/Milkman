package ronit.subronit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Project
{
Connection con;
PreparedStatement pst;
Project()
{ 
try {
Class.forName("com.mysql.jdbc.Driver");
System.out.println("done");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdata","root","ronit");
System.out.println("okee");
} 
catch (Exception e)
{
e.printStackTrace();
}
}
void doSave() throws SQLException
{
//? is in parameter
pst=con.prepareStatement("insert into firsttable values(?,?,?)");
pst.setString(1, "Raka");
pst.setString(2, "Shaka");
pst.setString(3, "53647373");
int x=pst.executeUpdate();
System.out.println("Record Saved...."+x);
pst.close();

}
public static void main(String[] args) throws SQLException 
{
// TODO Auto-generated method stub
Project p=new Project();
System.out.println("*****");
p.doSave();

}
}
