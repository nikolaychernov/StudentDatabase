import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDAO extends GenericDAO<Student> {

	public StudentDAO(Connection con) {
        super(con, TABLENAME);
    }

    @Override
    public int count() throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM "+this.tableName;
        PreparedStatement counter;
        try
        {
        counter = this.con.prepareStatement(query);
        ResultSet res = counter.executeQuery();
        res.next();
        return res.getInt("count");
        }
        catch(SQLException e){ throw e; }
    }

   private final static String TABLENAME = "STUDENT";


   public Student persist(Student object) throws SQLException {
	   
	   Student result = new Student();
		String sql = "INSERT INTO STUDENT (firstname,secondname,lastname,groupnum) \n" + "VALUES (?, ?, ?, ?);";
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, object.getFirstName());
			statement.setString(2, object.getSecondName());
			statement.setString(3, object.getLastName());
			statement.setInt(4, object.getGroupNumber());
			int count = statement.executeUpdate();
			if (count != 1) {
				System.out.println("On persist modify more then 1 record: " + count);
			} 
		} catch (SQLException e ) {
			throw e;
		} 
		sql = "SELECT * FROM STUDENT WHERE id = last_insert_id();";
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			rs.next();
			result.setId(rs.getInt("id"));
			result.setFirstName(rs.getString("firstname"));
			result.setSecondName(rs.getString("secondname"));
			result.setLastName(rs.getString("lastname"));
			result.setGroupNumber(rs.getInt("groupnum"));
		} catch (SQLException e ) {
			throw e;
		} 
		return result;
   }

@Override
public Student create() throws SQLException {
	Student student = new Student();
	return persist(student);
}

@Override
public Student getbyPK(int key) throws SQLException {
	String sql = "SELECT * FROM STUDENT WHERE id = ?;";
	Student s = new Student();
	try (PreparedStatement stm = con.prepareStatement(sql)) {
		stm.setInt(1, key);
		ResultSet rs = stm.executeQuery();
		rs.next();
		s.setId(rs.getInt("id")); 
		s.setFirstName(rs.getString("firstname"));
		s.setSecondName(rs.getString("secondname"));
		s.setLastName(rs.getString("lastname"));
		s.setGroupNumber(rs.getInt("groupnum")); 
		}
	return s;
}

@Override
public void update(Student object) throws SQLException {
	String sql = "UPDATE STUDENT \n" + "SET firstname = ?, secondname = ?, lastname = ?, groupnum = ? \n" + "WHERE id = ?;";
	try (PreparedStatement statement = con.prepareStatement(sql);) {
		statement.setString(1, object.getFirstName());
		statement.setString(2, object.getSecondName());
		statement.setString(3, object.getLastName());
		statement.setInt(4,object.getGroupNumber());
		statement.setInt(5,object.getId());
		int count = statement.executeUpdate();
		if (count != 1) {
			System.out.println("On update modify more then 1 record: " + count); 
		} 
	} catch (SQLException e) {
		throw e; 
	} 
}

@Override
public void delete(Student object) throws SQLException {
	String sql = "DELETE FROM STUDENT WHERE id= ?;";
	try (PreparedStatement statement = con.prepareStatement(sql);) {
		statement.setInt(1,object.getId());
		int count = statement.executeUpdate();
		if (count != 1) {
			System.out.println("On update modify more then 1 record: " + count); 
		}
		statement.close();
	} catch (SQLException e) {
		throw e; 
	} 
}

@Override
public List<Student> getAll() throws SQLException {
	String sql = "SELECT * FROM STUDENT;";
	List<Student> list = new ArrayList<Student>();
	try (PreparedStatement stm = con.prepareStatement(sql)) { 
		ResultSet rs = stm.executeQuery(); 
		while (rs.next()) { 
			Student g = new Student();
			g.setId(rs.getInt("id"));
			g.setFirstName(rs.getString("firstname"));
			g.setSecondName(rs.getString("secondname"));
			g.setLastName(rs.getString("lastname"));
			g.setGroupNumber(rs.getInt("groupnum"));
			list.add(g); 
			} 
		} 
	return list;
}


public List<Student> select(String lastname, String groupnum) throws SQLException {
	String sql = "SELECT * FROM STUDENT WHERE LASTNAME LIKE '%" + lastname + "%' AND GROUPNUM LIKE '%" + groupnum + "%';";
	List<Student> list = new ArrayList<Student>();
	try (PreparedStatement stm = con.prepareStatement(sql)) {
		ResultSet rs = stm.executeQuery(); 
		while (rs.next()) { 
			Student g = new Student();
			g.setId(rs.getInt("id"));
			g.setFirstName(rs.getString("firstname"));
			g.setSecondName(rs.getString("secondname"));
			g.setLastName(rs.getString("lastname"));
			g.setGroupNumber(rs.getInt("groupnum"));
			list.add(g); 
			} 
		} 
	return list;
}

public List<Student> selectLastname(String lastname) throws SQLException {
	String sql = "SELECT * FROM STUDENT WHERE LASTNAME LIKE '%" + lastname + "%';";
	List<Student> list = new ArrayList<Student>();
	try (PreparedStatement stm = con.prepareStatement(sql)) {
		ResultSet rs = stm.executeQuery(); 
		while (rs.next()) { 
			Student g = new Student();
			g.setId(rs.getInt("id"));
			g.setFirstName(rs.getString("firstname"));
			g.setSecondName(rs.getString("secondname"));
			g.setLastName(rs.getString("lastname"));
			g.setGroupNumber(rs.getInt("groupnum"));
			list.add(g); 
			} 
		} 
	return list;
}

public List<Student> selectGroupNum(String groupnum) throws SQLException {
	String sql = "SELECT * FROM STUDENT WHERE GROUPNUM LIKE '%" + groupnum + "%';";
	List<Student> list = new ArrayList<Student>();
	try (PreparedStatement stm = con.prepareStatement(sql)) {
		ResultSet rs = stm.executeQuery(); 
		while (rs.next()) { 
			Student g = new Student();
			g.setId(rs.getInt("id"));
			g.setFirstName(rs.getString("firstname"));
			g.setSecondName(rs.getString("secondname"));
			g.setLastName(rs.getString("lastname"));
			g.setGroupNumber(rs.getInt("groupnum"));
			list.add(g); 
			} 
		} 
	return list;
}


}