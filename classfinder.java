import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

    public class classfinder
    {
    	static int id = 0;
    	public static Connection connection = null;
    	static Scanner kdb = new Scanner(System.in);
    	static String username, password;
    	 public static void Use1()
    	    {
    		 	
    			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Please input a class to find the prerequisites of it: ");
    			String find_prereq_for = kdb.nextLine();
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("select prereq_id "
    	  		  									+ "from pre_req "
    	  		  									+ "where course_id = " + "'" + find_prereq_for + "'");
    	  		while(q1.next()){
    				  System.out.println(q1.getString("prereq_id"));
    			  }
    	  		  stat.close();
    	  	  } 
    		catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	  	  } 
    	    }
    	 public static void Use2()
    	    {
    		 
 			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Please input a class to find the classes that it is a prerequisite for: ");
    			String find_prereq_for = kdb.nextLine();
    	    	
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("select course_id "
    	  		  									+ "from pre_req "
    	  		  									+ "where prereq_id = " + "'" + find_prereq_for + "'");
    	  		while(q1.next()){
    				  System.out.println(q1.getString("course_id"));
    			  }
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use3(){
    		 
  			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	try {
    	    		Statement stat = connection.createStatement();
    	    		stat.setQueryTimeout(30);
    	    	
    	    		ResultSet q1 = stat.executeQuery("select s_name, (student.credits_req-student.credits_completed), "
    	    										+ "(Major.credits_req-major_credits_completed) "
    	    										+ "from Major, student "
    	    										+ "where major_id = maj_id AND s_id = " + "'"+id+"'");

    	    		System.out.println(q1.getString("s_name") + " | " + q1.getInt(2) + " | " + q1.getInt(3));
    	    		stat.close();
    	    	} catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    		   		System.exit(0);
    		  		} 
    	    }
    	 public static void Use4()
    	    {
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("select s_name, student.credits_req, Major.credits_req "
    	  				  						+ "from Major, student "
    	  				  						+ "where major_id = maj_id AND s_id = " + "'"+id+"'");

    	  		  System.out.println(q1.getString("s_name") + " | " + q1.getInt(2) + " | " + q1.getInt(3));
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use5()
    	    {
    		 
  			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Please enter the ID of an advisor to find all students under them: ");
    	    	int a_id = kdb.nextInt();
    	    	
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("select s_name, a_name "
    	  				+ "from advisor, student "
    	  				+ "where advisor_id = a_id and a_id = "+"'"+a_id+"'");
    	  		  
    	  		  
    	  		while(q1.next()){
    				  System.out.println(q1.getString("a_name")+ " | " + q1.getString("s_name"));
    			  }
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use6()
    	    {
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("select s_name, a_name "
    	  				+ "from advisor, student "
    	  				+ "where advisor_id = a_id and s_id = " +"'"+id+"'");
    	  		  
    	  		  
    	  		while(q1.next()){
    				  System.out.println(q1.getString("s_name")+ " | " + q1.getString("a_name"));
    			  }
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use7()
    	    {
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("select s_name, min_name, minor_credits_completed "
    	  		  		+ "from student, in_minor, Minor "
    	  		  		+ "where student.s_id = in_minor.s_id and minor_id = min_id and student.s_id = "+"'"+id+"'");
    	  		  
    	  		  
    	  		while(q1.next()){
    				  System.out.println(q1.getString("s_name")+ " | " + q1.getString("min_name")+ " | "+q1.getInt("minor_credits_completed"));
    			  }
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use8()
    	    {
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("SELECT DISTINCT course.course_id "
    	  		  								+ "FROM course, takes, student "
    	  		  								+ "WHERE student.s_id = " + "'" + id + "'"
    	  		  								+ "AND major_id = m_id "
    	  		  								+ "AND course.course_id NOT IN "
    	  		  								+ "(SELECT takes.course_id FROM takes)"
    	  		  								+ "ORDER BY course.course_id");
    	  		  while(q1.next()){
    	  			  System.out.println(q1.getString(1));
    	  		  }
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use9()
    	    {
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("SELECT DISTINCT course_id, grade "
    	  		  								+ "FROM takes "
    	  		  								+ "WHERE s_id = " + "'" + id + "' and grade <> 'IP' and grade <> 'N/A' "
    	  		  								+ "ORDER BY course_id");
    	  		  
    	  		  System.out.println("Course  |  Grade");
    	  		  while(q1.next()){
    	  			  System.out.println(q1.getString(1)+" | "+q1.getString(2));
    	  		  }
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use10()
    	    {
    		 
  			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Please enter the ID of the student you wish to provide an\n"
    	    					+ "override for, the class you wish to override, and the semester\n"
    	    					+ "and year of that class: ");
    	    	System.out.print("Student ID: ");
    	    	int stu_id = kdb.nextInt();
    	    	kdb.nextLine();
    	    	System.out.print("Course: ");
    	    	String course = kdb.nextLine();
    	    	System.out.println("Semester: ");
    	    	String sem = kdb.nextLine();
    	    	System.out.println("Year: ");
    	    	int year = kdb.nextInt();
    	    	
    			try {
    				connection.setAutoCommit(false);
    	  		  PreparedStatement stat = connection.prepareStatement("insert into takes values(?,?,?,?,?)");
    	  		  	stat.setInt(1, stu_id);
    	  			stat.setString(2, course);
    	  			stat.setString(3, sem);
    	  			stat.setInt(4, year);
    	  			stat.setString(5, "N/A");
    	  			
    	  			stat.executeUpdate();  
    	  			stat.close();
            		connection.commit();
            		connection.setAutoCommit(true);
    	  	  } catch ( Exception e ) {
    	  		if(connection != null){
    				try {
    					System.err.print("Transaction is being rolled back");
    					connection.rollback();
    				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
    				}
    			}
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use11()
    	    {
    		 
   			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Show how many students are currently enrolled in a class: ");
    	    	String course = kdb.nextLine();
    	    	
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("SELECT count(s_id) "
    	  		  		+ "from takes "
    	  		  		+ "where course_id = " + "'" + course + "'" + " and grade = 'IP'");
    	  		  
    	  		  System.out.println(q1.getInt(1));
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use12()
    	    {
    		 
   			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Show all students in a certain major: ");
    	    	String major = kdb.nextLine();
    	    	
    	    	try {
    	    		Statement stat = connection.createStatement();
    	    		stat.setQueryTimeout(30);
    	    		
    	    		ResultSet q1 = stat.executeQuery("select s_name "
    	    				+ "from student, Major "
    	    				+ "where major_id = maj_id and maj_name = "+"'"+major+"'");
    	    		while(q1.next()){
    	    			System.out.println(q1.getString(1));
    	    		}
    	    		
    	    	} catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    		   		System.exit(0);
    		  		} 
    	    	
    	    }
    	 public static void Use13()
    	    {
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("SELECT DISTINCT course_id "
    	  		  								+ "FROM takes "
    	  		  								+ "WHERE s_id = " + "'" + id + "' " 
    	  		  								+ "and grade = 'IP'"
    	  		  								+ "ORDER BY course_id");
    	  		  
    	  		  while(q1.next()){
    	  			  System.out.println(q1.getString(1));
    	  		  }
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use14()
    	    {
    		 
   			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Please enter the class you wish to enroll in, and it's semester and year");
    	    	System.out.print("Course: ");
    	    	String course = kdb.nextLine();
    	    	System.out.println("Semester: ");
    	    	String sem = kdb.nextLine();
    	    	System.out.println("Year: ");
    	    	int year = kdb.nextInt();
    	    	
    			try {
    				connection.setAutoCommit(false);
    				Statement stat = connection.createStatement();
    		  		stat.setQueryTimeout(30);
    				
    		  		ResultSet q1 = stat.executeQuery("SELECT DISTINCT prereq_id "
    		  				+ "FROM pre_req p, takes t "
    		  				+ "WHERE s_id = '"+id+"' AND p.course_id = '"+course+"' "
    		  						+ "AND prereq_id NOT IN"
    		  						+ "(SELECT t.course_id "
    		  						+ "FROM takes "
    		  						+ "WHERE s_id = '"+id+"')");
    		  		
    	
    		  	if(q1.getString(1)!=null){
    		  		
    		  		System.out.println("You still need to take: ");
    		  		while(q1.next()){
    					  System.out.println(q1.getString(1));
    				  }
    		  			
    	  		  }
    	  		  else{
    	  			System.out.println("Enrolling in: "+course);
    	  			PreparedStatement stat2 = connection.prepareStatement("insert into takes values(?,?,?,?,?)");
    	  			stat2.setInt(1, id);
    	  			stat2.setString(2, course);
    	  			stat2.setString(3, sem);
    	  			stat2.setInt(4, year);
    	  			stat2.setString(5, "IP");
    				
    	  			stat2.executeUpdate();
    	  			stat2.close();
    	  		  }
    		  
    	  		  stat.close();
    	  		connection.commit();
        		connection.setAutoCommit(true);
    	  	  } catch ( Exception e ) {
    	  		if(connection != null){
    				try {
    					System.err.print("Transaction is being rolled back");
    					connection.rollback();
    				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
    				}
    			}
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use15()
    	    {
    			try {
    	    	
    	  		  Statement stat = connection.createStatement();
    	  		  stat.setQueryTimeout(30);
    	  		  
    	  		  ResultSet q1 = stat.executeQuery("select s_name, maj_name, major_credits_completed "
    	  		  		+ "from student, Major "
    	  		  		+ "where major_id = maj_id and student.s_id = "+"'"+id+"'");
    	  		  
    	  		  
    	  		while(q1.next()){
    				  System.out.println(q1.getString("s_name")+ " | " + q1.getString("maj_name")+ " | "+q1.getInt("major_credits_completed"));
    			  }
    	  		  stat.close();
    	  	  } catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	    	   		System.exit(0);
    	    	  		} 
    	    }
    	 public static void Use16(){
    		 
    			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("What if: Enter the major you would like to switch into.\n"
    	    				+ "Below is a list of classes that you could take in that major: ");
    	    	String major = kdb.nextLine();
    	    	
    	    	try{
    	    		Statement stat = connection.createStatement();
    	    		 stat.setQueryTimeout(30);
    	    		 
    	    		 ResultSet q1 = stat.executeQuery("select distinct course.course_id "
    	    		 		+ "from course, student, takes, Major "
    	    		 		+ "where course.m_id = maj_id AND maj_name = "+"'"+major+"'"+" "
    	    		 		+ "AND course.course_id NOT IN "
    	    		 		+ "(select takes.course_id "
    	    		 		+ "from takes "
    	    		 		+ "where takes.s_id = "+"'"+id+"'"+")");
    	    		 while(q1.next()){
    	    			 System.out.println(q1.getString(1));
    	    		 }
    	    		  
    	    	} catch ( Exception e ) {
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    		   		System.exit(0);
    		  		}
    	    }    	 
    	
    	 public static void Use17(){ 
    		 
 			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Enter the student ID and the major you would like to change them into: ");
    	    	int stu_id = kdb.nextInt();
    	    	kdb.nextLine();
    	    	String major = kdb.nextLine();
    	    	
    	    	try {
    	    		connection.setAutoCommit(false);
    	    		Statement stat = connection.createStatement();
    	    		
    	    		
    	    		ResultSet q1 = stat.executeQuery("select maj_id from Major where maj_name = "+"'"+major+"'");
    	    		int major_id = q1.getInt(1);
    	    		
    	    		q1 = stat.executeQuery("select sum(cred) "
    	    				+ "from (select DISTINCT course.course_id, course.credits AS cred "
    	    				+ "from course, takes "
    	    				+ "where takes.course_id = course.course_id "
    	    				+ "and m_id = "+"'"+major_id+"'"+" and takes.s_id = "+"'"+stu_id+"'"
    	    				+ " and grade <> 'IP' and grade <> 'N/A')");
    	    		String cred = q1.getString(1);
    	    		
    	    		q1 = stat.executeQuery("select major_credits_completed from student where s_id = '"+stu_id+"'");
    	    		int mcred = q1.getInt(1);
    	    		
    	    		if(cred==null){
    	    			stat.executeUpdate("UPDATE student "
        	    				+ "set major_id = "+"'"+major_id+"'"+", "
        	    				+ "credits_completed = '"+mcred+"' + credits_completed,  "
        	    				+ "major_credits_completed = 0 "
        	    				+ "where s_id = "+"'"+stu_id+"'");
    	    		}
    	    		else{
    	    			stat.executeUpdate("UPDATE student "
        	    				+ "set major_id = '"+major_id+"', "
        	    				+ "credits_completed = "+mcred+"-"+cred+" + credits_completed, "
        	    				+ "major_credits_completed = '"+cred+"' "
        	    				+ "where s_id = '"+stu_id+"'");
    	    		}
    	    		
    	    		
    	    		 
    	    		stat.close();
    	    		connection.commit();
            		connection.setAutoCommit(true);
    	    		
    	    	}catch ( Exception e ) {
    	    		if(connection != null){
        				try {
        					System.err.print("Transaction is being rolled back");
        					connection.rollback();
        				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
        				}
        			}
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    		   		System.exit(0);
    		  		}
    	    }
    	
    	 public static void Use18(){
    		 
 			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Enter the student ID and the minor they would like to enroll in: ");
    	    	System.out.print("Student ID: ");
    	    	int stu_id = kdb.nextInt();
    	    	kdb.nextLine();
    	    	String minor = kdb.nextLine();
    	    	try {
    	    		connection.setAutoCommit(false);
    	    		Statement stat = connection.createStatement();
    	    		ResultSet q1 = stat.executeQuery("select min_id from Minor where min_name = "+"'"+minor+"'");
    	    		int minor_id = q1.getInt(1);
    	    		ResultSet q2 = stat.executeQuery("select minor_id from in_minor where s_id = '"+stu_id+"'");
    	    		
    	    		if(q2.getString(1)==null){
    	    			PreparedStatement stat2 = connection.prepareStatement("insert into in_minor values(?,?)");
        	    		stat2.setInt(1, minor_id);
        	  			stat2.setInt(2, stu_id);
        	  			stat2.executeUpdate();
        	    		stat2.close();
    	    		}
    	    		else{
    	    			PreparedStatement stat2 = connection.prepareStatement("update in_minor "
    	    					+ "set minor_id = '"+minor_id+"' where s_id = '"+stu_id+"'");
    	    			stat2.executeUpdate();
    	    			stat2.close();
    	    		}
    	    		stat.executeUpdate("UPDATE student set minor_credits_completed = 0 where s_id = "+"'"+stu_id+"'");
    	    		stat.close();
    	    		connection.commit();
            		connection.setAutoCommit(true);
    	    	}catch ( Exception e ) {
    	    		if(connection != null){
        				try {
        					System.err.print("Transaction is being rolled back");
        					connection.rollback();
        				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
        				}
        			}
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    		   		System.exit(0);
    		  		}
    	    }    	 
    	 public static void Use19(){
    		 
  			kdb.useDelimiter(System.getProperty("line.separator"));
    	    	System.out.println("Enter the class and it's new prerequisite: ");
    	    	String course = kdb.nextLine();
    	    	String prereq = kdb.nextLine();
    	    	
    	    	try{
        			Statement s = connection.createStatement();
        			ResultSet rs = s.executeQuery("select prereq_id from pre_req where course_id = '"+course+"' and prereq_id = '"+prereq+"';");
        			String num;
        			if(rs.next()){
        				num = rs.getString("prereq_id");
        				if(num.equals(prereq)){
   	    					System.out.println("error: "+prereq +" is already a prereq of "+ course);
   	    					rs.close();
   	    					s.close();
   	    					return;
   	    				}
        			}
        			rs = s.executeQuery("select prereq_id from pre_req where course_id = '"+prereq+"' and prereq_id = '"+course+"';");
        			if(rs.next()){
        				num = rs.getString(1);
        				if(num.equals(course)){
        					System.out.println("error: "+course +" is a prereq of "+ prereq);
        					rs.close();
        					s.close();
        					return;
        				}
        			}
    	    		connection.setAutoCommit(false);
        			PreparedStatement stat = connection.prepareStatement("insert into pre_req values(?,?)");
    	    		stat.setString(1, course);
    	  			stat.setString(2, prereq);
    	  			stat.executeUpdate();
    	  			stat.close();
    	  			rs.close();
        			s.close();
    	  			connection.commit();
    	  			connection.setAutoCommit(true);
    	    	}catch ( Exception e ) {
    	    		if(connection != null){
        				try {
        					System.err.print("Transaction is being rolled back");
        					connection.rollback();
        				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
        				}
        			}
    	  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    		   		System.exit(0);
    		  		}
    	    }
    	 public static void Menu(){
    	  System.out.println("Menu:");
    		  System.out.println("Enter -1: To Close");
    		  //System.out.println("Enter 100: print schema");
    		  //System.out.println("Enter 2: course");
    		  if(permission != 2 && permission != 1){
    			System.out.println("Enter 1: finds prerequisites of a class");
    		  	System.out.println("Enter 2: finds classes that a class is a prerequisite for");
    		  	System.out.println("Enter 3: how many more credits you need to fulfill");
    		  	System.out.println("Enter 4: how many credits you need in general");
    		  	System.out.println("Enter 5: find your advisor");
    		  	System.out.println("Enter 6: show the progress in your minor");
    		  	System.out.println("Enter 7: list of available classes left in your major");
    		  	System.out.println("Enter 8: show the classes you have taken");
    		  	System.out.println("Enter 9: show all classes you are currently taking");
    		  	System.out.println("Enter 10: enroll yourself in a class");
    		  	System.out.println("Enter 11: shows progress in your major");
    		  	System.out.println("Enter 12: shows list of classes if you were to change majors");
    		  }
    		  System.out.println("Enter 13: log out");
    		  if(permission == 1 || permission == 2){
    			System.out.println("Enter 14: find all students under an advisor");
    		  	System.out.println("Enter 15: provide an override for a student");
    		  	System.out.println("Enter 16: change major");
    		  	System.out.println("Enter 17: enroll in minor");
    		  	System.out.println("Enter 18: show how many students currently in a class");
    		  	System.out.println("Enter 19: show all students in a certain major");
    		  }
    		  if(permission == 1)
      		  	System.out.println("Enter 20: add new prerequisite");
    	}
    	static int year = 2016, permission;
    	public static void insert(Vector<String>v, int id){
    		try{
    			connection.setAutoCommit(false);
    			int cred = 0, sem, count = 0;
        		Statement statement = connection.createStatement();
           		for(int j = 0; j < v.size(); j++){
           			int check = 0;
           			ResultSet rs = statement.executeQuery("select course_id from takes where s_id = '"
           					+id+"'and course_id = '"+v.get(j)+"';");
           			if(rs.next()) check = 1;
           			rs.close();
           			if(check == 0){
           				String semester = "";
           				ResultSet rss = statement.executeQuery("select id, credits from course where course_id = '" + v.get(j)+"';");
           				int cid = rss.getInt("id"); 
           				int credits = rss.getInt("credits");
           				cred += credits;
           				if(cred >= 16){sem = 1;}
           				else sem = 0;
           				if(sem == 0){ semester = "FALL"; count = 0;}
           				else {semester = "SPRING"; count+=1;}
           				Random rand = new Random();
           				int  n = rand.nextInt((67 - 65) + 1) + 65;
           				statement.executeUpdate("INSERT INTO takes VALUES ('"+id+"','"+v.get(j)
           					+"','"+semester+"','"+year+"','"+(char)n+"');");  System.out.println("jlk;jkj;fladjflkdjl;kadjf");
           				rss = statement.executeQuery("select credits_completed from student where s_id ='100181994';");
           				int credcomp = rss.getInt("credits_completed"); System.out.println(credcomp);
           				rss = statement.executeQuery("select major_id from student where s_id ='"+id+"';");
           				int k = rss.getInt("major_id"); 
           				rss = statement.executeQuery("select minor_id from in_minor where s_id ='"+id+"';");
           				int l=-1;
           				if(rss.next()){l = rss.getInt("minor_id");} 
           				if(k == cid){
           					rss = statement.executeQuery("select major_credits_completed from student where s_id ='"+id+"';");
           					int temp = rs.getInt("major_credits_completed");
           					rss = statement.executeQuery("select credits_req from major where maj_id ='"+k+"';");
           					int temp2 = rs.getInt("credits_req");
           					if(temp >= temp2)
           						statement.executeUpdate("update student set credits_completed = '"
           								+(credits+credcomp)+ "' where s_id = '"+id+"';");
           					else
           						statement.executeUpdate("update student set major_credits_completed = '"
           								+(credits+temp)+ "' where s_id = '"+id+"';");
           					rss.close();
           				}
           				else if(l == cid){
           					ResultSet r =statement.executeQuery("select minor_credits_completed from student where s_id ='"+id+"';");
           					int temp = r.getInt("minor_credits_completed");
           					r =statement.executeQuery("select credits_req from minor where min_id ='"+l+"';");
           					int temp2 = r.getInt("credits_req");
           					if(temp >= temp2)
           						statement.executeUpdate("update student set credits_completed = '"
           								+(credits+credcomp)+ "' where s_id = '"+id+"';");
           					else
           						statement.executeUpdate("update student set minor_credits_completed = '"
               						+(temp+credits)+ "' where s_id = '"+id+"';");
           					r.close();
           				}
           				else{ 
           					statement.executeUpdate("update student set credits_completed = '"+(credcomp+credits)+"' where s_id = '"+id+"';"); System.out.println("asd");
           				}
           				if(count == 4){year -= 1;}
           			}
           		}
        		statement.close();
        		connection.commit();
        		connection.setAutoCommit(true);
        		}
        		catch(SQLException e)
                {
                  // if the error message is "out of memory", 
                  // it probably means no database file is found
        			if(connection != null){
        				try {
        					System.err.print("Transaction is being rolled back");
        					connection.rollback();
        				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
        				}
        			}
                  System.err.println(e.getMessage());
                }
    		finally{
    			v.clear();
    		}
    	}
    	public static void connect(){
    		try{
    			connection = DriverManager.getConnection("jdbc:sqlite:classfinder.db");
    	        Statement statement = connection.createStatement();
    	        System.out.println("Database classfinder conneced");
    	        statement.setQueryTimeout(30);  // set timeout to 30 sec.
    	    	statement.close();
    		}
    		catch(SQLException e)
            {
              // if the error message is "out of memory", 
              // it probably means no database file is found
              System.err.println(e.getMessage());
            }
    	}
    	public static void disconnect(){
    		try
            {
              if(connection != null){
                connection.close();
                connection = null;
              }
            }
            catch(SQLException e)
            {
              // connection close failed.
              System.err.println(e);
            }
    	}
		public static String[] prereq(String course){
    		String[] s = new String [20];
    		try{
        		Statement statement = connection.createStatement();
           		ResultSet rs = statement.executeQuery("SELECT prereq_id FROM pre_req p WHERE course_id = '"+ course +"';");
           		int i = 0;
           		while(rs.next()){
           			s[i] = rs.getString("prereq_id");
           			i++;
           		}
           		rs.close();
        		statement.close();
        		}
        		catch(SQLException e)
                {
                  // if the error message is "out of memory", 
                  // it probably means no database file is found
                  System.err.println(e.getMessage());
                }
			return s;
        	}
    	public static void course(){
    		try{
        		Statement statement = connection.createStatement();
        		
        		kdb.useDelimiter(System.getProperty("line.separator"));
           		Vector <String> v = new Vector<String>(10,2);
           		System.out.print("Enter couse: ");
           		String course = kdb.next();
           		v.add(course);
           		String[] s = prereq(course);
           		int i = 0;
           		while(s[i] != null){ 
           			v.add(s[i]);
           			i++;
           		}
           		s = null;
           		for(int j = 0; j < v.size(); j++){ //System.out.println(v.get(j));
           			i = 0;
           			s = prereq(v.get(j));
           			while(s[i] != null){
           				if(!v.contains(s[i]))
           					v.add(s[i]);
                   		i++;
                   		}
           		}
           		insert(v, id);
           		v.clear();
        		statement.close();
        		}
        		catch(SQLException e)
                {
                  // if the error message is "out of memory", 
                  // it probably means no database file is found
                  System.err.println(e.getMessage());
                }
        	}
   	public static void schema(){
   		try{
    		Statement statement = connection.createStatement();
    		
    		kdb.useDelimiter(System.getProperty("line.separator"));
    		DatabaseMetaData meta = connection.getMetaData();
			ResultSet rs = meta.getTables(null, null, "%", null);
			while (rs.next()) {
				  System.out.println(rs.getString(3));
				}
			rs.close();
    		statement.close();
    		return;
    		}
    		catch(SQLException e)
            {
              // if the error message is "out of memory", 
              // it probably means no database file is found
              System.err.println(e.getMessage());
            }
    	}
   	static int tries = 0;
   	public static void loggedout(){
   		username = password = ""; id = tries = permission = 0;
		kdb.useDelimiter(System.getProperty("line.separator"));
		System.out.println("enter 1: login");
		System.out.println("enter 0: close");
		int n = kdb.nextInt();  
	  	switch(n){
	  		case 1: loop(); break;
	  		case 0: disconnect(); System.exit(0);
	  	}
	  	
   	}
   	public static boolean login(){
   		
   		try{
   			Statement statement = connection.createStatement();
   			
   			kdb.useDelimiter(System.getProperty("line.separator"));
   			System.out.print("Enter Username: ");
   			username = kdb.next();
   			System.out.print("Enter Password: ");
   			password = kdb.next();
   			ResultSet rs = statement.executeQuery("select id, power from login where username = '"+username+"' and password = '"+password+"';");
   			if(rs.next()){
   				id = rs.getInt("id");
   				permission = rs.getInt("power");
   				rs.close();
   				return true;
   			}
   			else {
   				System.out.println("Incorrect Username or Password");
   				if(tries++ != 3){
   					loggedout();
   					return true; 
   				}
   				else return false;
   			}
   		}
   		catch(SQLException e)
        {
          // if the error message is "out of memory", 
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
		return false;
   	}
   	public static void loop(){
   		int n;
  		boolean log = login();
  		  while(tries != 3){
  			  if(log){
  				  boolean b = true;
  				Menu();
  				n = kdb.nextInt(); 
				  kdb.nextLine();
  				  while(b == true){
  					  switch(n){ 
  					  	case 13: loggedout(); 
  					  			break;
  					  	case -1: b = false; tries = 3; break;
  					  	case 1:
  					  		Use1(); // finds prerequisites of a class
  					  		break;
  					  	case 2:
  					  		Use2(); // finds classes that a class is a prerequisite for
  					  		break;
  					  	case 3:
  					  		Use3(); // how many more credits you need to fulfill
  					  		break;
  					  	case 4:
  					  		Use4(); // how many credits you need in general
  					  		break;
  					  	case 5:
  					  		Use6(); // find your advisor
  					  		break;
  					  	case 6:
  					  		Use7(); // show the progress in your minor
  					  		break;
  					  	case 7:
  					  		Use8(); // list of available classes left in your major
  					  		break;
  					  	case 8:
  					  		Use9(); // show the classes you have taken
  					  		break;
  					  	case 9:
  					  		Use13(); // show all classes you are currently taking
  					  		break;
  					  	case 10:
  					  		Use14(); // enroll yourself in a class
  					  		break;
  					  	case 11:
  					  		Use15(); // shows progress in your major
  					  		break;
  					  	case 12:
  					  		Use16(); // shows list of classes if you were to change majors
  					  		break;
  					  	case 14:
					  		if (permission == 1 || permission == 2)
					  			Use5(); // find all students under an advisor
					  		break;
  					  	case 15:
					  		if (permission == 1 || permission == 2)
					  			Use10(); // provide an override for a student
					  		else
					  			System.out.println("You do not have permission to do that");
					  		break;
  					  	case 16:
  					  		if(permission == 1 || permission ==2)
  					  		Use17(); // change major
  					  		break;
  					  	case 17:
  					  		if(permission == 1 || permission == 2)
  					  			Use18(); // enroll in minor
  					  		break;
  					  	case 20:
  					  		if (permission == 1)
  					  			Use19(); // add new prerequisite
  					  		break;
  					  	case 18:
					  		if (permission == 1 || permission == 2)
					  			Use11(); // show how many students currently in a class
					  		break;
					  	case 19:
					  		if (permission == 1 || permission == 2)
					  			Use12(); // show all students in a certain major
					  		break;
        	  	}
  					System.out.println("Enter another choice from the menu: ");
  					n = kdb.nextInt(); 
					 kdb.nextLine();
  			  }
  			  }
  			  else{
  				  tries++;
  				  System.out.println("incorrect");
  			  }
  		  }
   	}
	public static void main(String[] args) 
      {
          
          
  		  kdb.useDelimiter(System.getProperty("line.separator"));
  		  
  		  connect();
  		  loop();
          disconnect();
         
        
      }
    }
