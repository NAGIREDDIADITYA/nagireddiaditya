package sample;
import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class Display {
        Scanner sc=new Scanner(System.in);
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		Display i=new Display();
		System.out.println("1.List Movies");
		System.out.println("2.Add Movie");
		System.out.println("3.Add Actor");
		System.out.println("4.Add Producer");
		System.out.println("5.Delete Movie");
		System.out.println("6.Exit");
		int r=0;
		while(r>=0)
		{
			System.out.println("What do you want");
			int a=sc.nextInt();
			switch(a)
			{
			case 1:
				i.listMovies();
				r=r+1;
				break;
			case 2:
				System.out.println("How many actors want to add to this movie 1/2");
				int z=sc.nextInt();
				if(z==1)
				{
				i.addMovie();
				}
				else
				{
				i.addMovies();
				}
				r=r+1;
				break;
			case 3:
				i.addActor();
				r=r+1;
				break;
			case 4:
				i.addProducer();
				r=r+1;
				break;
			case 5:
				i.deleteMovie();
				r=r+1;
				break;
			case 6:
				System.out.println("Thankyou");
				r=-1;
				break;
				default:
					System.out.println("invalid Re-enter");
					break;
				}
		}
   }
	public Connection getConnect()
	   {
		  try {
			  DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			   return con;
		    } 
		  catch (Exception e) 
		  {
			System.out.println(e);
		  } 
		  return null;
	   }
	public void listMovies()
	{
		try {
			Connection con=getConnect();
			PreparedStatement pst=con.prepareStatement("SELECT * FROM IMDB");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				 System.out.println(rs.getString(2)+"("+rs.getString(3)+")");
				 System.out.println("Plot- "+rs.getString(4));
				 int a=rs.getInt(5);        
				 int b=rs.getInt(6);
				 int c=rs.getInt(7);
				 getActor(a,b,c);
				 System.out.println("---------------------------------------");
			}
			rs.close();
			pst.close();
			con.close();
	  }  
		catch (Exception e) 
		{
			System.out.println(e);
		}
}
	public void addMovies()
	{
		try {
		Connection con=getConnect();
		String query=("insert into imdb(sno,name,year,plot,actor1,actor2,producer) values(seq_imdb.nextval,?,?,?,?,?,?)");
		PreparedStatement pst=con.prepareStatement(query);
		System.out.println("Name:");
		String name=sc.nextLine();
		pst.setString(1,name);
		System.out.println("Year of release(YYYY):");
		int year=sc.nextInt();
		pst.setInt(2,year);
		System.out.println("Plot:");
		sc.nextLine();
		String plot=sc.nextLine();
		pst.setString(3,plot);
		System.out.print("Choose actor(s) :");
		actors();
		System.out.println();
		int actor1=sc.nextInt();
		pst.setInt(4,actor1);
		int actor2=sc.nextInt();
		pst.setInt(5,actor2);
		System.out.print("Choose producer :");
		producers();
		System.out.println();
		int producer=sc.nextInt();
		sc.nextLine();
		pst.setInt(6,producer);
		pst.executeUpdate();
		System.out.println("inserted sucessfull");
		pst.close();
		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);		
		}
  }
	public void addMovie()
	{
		try {
		Connection con=getConnect();
		String query=("insert into imdb(sno,name,year,plot,actor1,producer) values(seq_imdb.nextval,?,?,?,?,?)");
		PreparedStatement pst=con.prepareStatement(query);
		System.out.println("Movie Name:");
		String name=sc.nextLine();
		pst.setString(1,name);
		System.out.println("Year of release(YYYY):");
		int year=sc.nextInt();
		pst.setInt(2,year);
		System.out.println("Plot:");
		sc.nextLine();
		String plot=sc.nextLine();
		pst.setString(3,plot);
		System.out.print("Choose actor(s) :");
		actors();
		System.out.println();
		int actor1=sc.nextInt();
		pst.setInt(4,actor1);
		System.out.print("Choose producer :");
		producers();
		System.out.println();
		int producer=sc.nextInt();
		sc.nextLine();
		pst.setInt(5,producer);
		pst.executeUpdate();
		System.out.println("inserted sucessfull");
		pst.close();
		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);		
		}
	}
	public void addActor() 
	{
		try {
			Connection con=getConnect();
			String query="insert into actor(sno,name,dob) values(seq_actor.nextval,?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			System.out.println("Name :");
			String name=sc.nextLine();
			System.out.println("Dob(DD-MM-YYYY) :");
			String dob=sc.next();
			sc.nextLine();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date dob1=sdf.parse(dob);
			long dob2=dob1.getTime();
			java.sql.Date dob3=new java.sql.Date(dob2);
			pst.setString(1,name);
			pst.setDate(2, dob3);
			pst.executeUpdate();
			System.out.println("update sucessfull");
			con.close();
		  }
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void addProducer()
	{
		try {
			Connection con=getConnect();
			String query="insert into producer(sno,name,dob) values(seq_producer.nextval,?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			System.out.println("Name :");
			String name=sc.nextLine();
			System.out.println("Dob(DD-MM-YYYY) :");
			String dob=sc.next();
			sc.nextLine();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date dob1=sdf.parse(dob);
			long dob2=dob1.getTime();
			java.sql.Date dob3=new java.sql.Date(dob2);
			pst.setString(1,name);
			pst.setDate(2, dob3);
			pst.executeUpdate();
			System.out.println("updated sucessfull");
			con.close();
		  }
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void deleteMovie()
	{
		try {
			System.out.println("which movie want to delete");
			getMovies();
			System.out.println();
			System.out.println("Enter the SNO");
			int sno=Integer.parseInt(sc.nextLine());
			Connection con=getConnect();
			PreparedStatement pst=con.prepareStatement("delete from imdb where sno=?");
			pst.setInt(1,sno);
			int x=pst.executeUpdate();
			pst.close();
			con.close();
			if(x==1)
			{
				System.out.println("record deleted");
			}
			else
			{
				System.out.println("record not found");
			}
			} 
		   catch (Exception e) 
		   {
			System.out.println(e);
		   }
	}
	public void actors()
	{
		try {
			Connection con=getConnect();
			PreparedStatement pst=con.prepareStatement("SELECT * FROM Actor");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				 System.out.print(rs.getString(1)+"."+rs.getString(2)+"  ");
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
		public void producers()
    {
    	try {
			Connection con=getConnect();
			PreparedStatement pst=con.prepareStatement("SELECT * FROM PRODUCER");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				 System.out.print(rs.getString(1)+"."+rs.getString(2)+"  ");
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    public void getActor(int a,int b,int c)
    {
    	try {
			Connection con=getConnect();
			PreparedStatement pst1=con.prepareStatement("select name from actor where Sno in(select actor1 from imdb where actor1=?)");
			PreparedStatement pst=con.prepareStatement("select name from actor where Sno in(select actor2 from imdb where actor2=?)");
			PreparedStatement pst2=con.prepareStatement("select name from producer where Sno in(select producer from imdb where producer=?)");
			pst1.setInt(1,a);
			pst.setInt(1,b);
			pst2.setInt(1,c);
			ResultSet rs=pst1.executeQuery();
			ResultSet rs1=pst.executeQuery();
			ResultSet rs2=pst2.executeQuery();
			while(rs.next())
			{
				System.out.print("Actors :"+rs.getString(1));
			} 
			while(rs1.next())
			{
				System.out.print(","+rs1.getString(1));
			}
			System.out.println();
			while(rs2.next())
			{
				System.out.println("Producers :"+rs2.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    	public void getMovies()
    	{
    		try {
    			Connection con=getConnect();
    			PreparedStatement pst=con.prepareStatement("SELECT * FROM imdb");
    			ResultSet rs=pst.executeQuery();
    			while(rs.next())
    			{
    				 System.out.print(rs.getString(1)+"."+rs.getString(2)+"  ");
    			}
    			rs.close();
    			pst.close();
    			con.close();
    		} catch (Exception e) {
    			System.out.println(e);
    		}
        }
   }