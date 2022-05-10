package daoIMP;
import bean.*;
import dao.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.DataBaseConnection;

public class StudentDAOIMP implements StudentDAO{
	// 添加操作
	    public void insert(Student s){
	    	String sql = "INSERT INTO student (id, name) values (?,?)";
	        PreparedStatement pstmt = null;
	        DataBaseConnection conn = null;
	        //针对数据库的具体操作
	        try{
	        	conn = new DataBaseConnection();
	        	pstmt = conn.getConnection().prepareStatement(sql);
	        	pstmt.setLong(1,s.getID());
	        	//pstmt.setString(1,s.getID());
	            pstmt.setString(2,s.getName());
	            pstmt.executeUpdate();
	            pstmt.close();
	            conn.close();
	        }
	        catch(Exception e){  }
	    }

	    public void update(Student s){
			String sql = "UPDATE student SET name = ?WHERE id= ?";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			//针对数据库的具体操作
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setLong(1,s.getID());
				//pstmt.setString(1,s.getID());
				pstmt.setString(2,s.getName());
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
			catch(Exception e){  }
		}

	    public void delete(String iD){
	    	String sql = "DELETE FROM WHERE id= ?";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			//针对数据库的具体操作
			try{
				long id=Long.parseLong(iD);
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setLong(1,id);;
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
			catch(Exception e){  }
	    }
	    
	    public List findAll(){
			String sql = "select * FROM Student";
			DataBaseConnection conn = null;
			Statement stmt=null;
			ResultSet rs=null;
			List<Student>list=new ArrayList<Student>();
			try{
				conn = new DataBaseConnection();
				stmt=conn.getConnection().createStatement();
				rs=stmt.executeQuery(sql);
				while (rs.next()){
					Student student=new Student();
					student.setID(rs.getLong("iD"));
					student.setName(rs.getString("name"));
					list.add(student);
				}
				rs.close();
				stmt.close();
				conn.close();
			}
			catch(Exception e){  }
			return list;
		}

	    public Student findByID(long iD) {
			String sql = "select * FROM Student WHERE id=?";
			DataBaseConnection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			Student student = new Student();
			try {
				conn = new DataBaseConnection();
				stmt = conn.getConnection().createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					student.setID(rs.getLong("iD"));
					student.setName(rs.getString("name"));
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {

			}
			return student;
		}
}
