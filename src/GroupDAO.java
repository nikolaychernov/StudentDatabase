import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GroupDAO extends GenericDAO<Group> {

	public GroupDAO(Connection con) {
        super(con, TABLENAME);
    }

    @Override
    public int count() throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM "+this.tableName;
        PreparedStatement counter;
        try{
        	counter = this.con.prepareStatement(query);
        	ResultSet res = counter.executeQuery();
        	res.next();
        	return res.getInt("count");
        }
        catch(SQLException e){ throw e; }
    }

   //Private
   private final static String TABLENAME = "GROUPOFSTUDENTS";

   @Override
public Group create() throws SQLException {
		Group g = new Group();
		return persist(g);
	}

@Override
public Group persist(Group object) throws SQLException {
	Group result = new Group();
	String sql = "INSERT INTO GROUPOFSTUDENTS (groupnumber, faculty) \n" + "VALUES (?, ?);";
	try (PreparedStatement statement = con.prepareStatement(sql)) {
		statement.setInt(1, object.getNumber());
		statement.setString(2, object.getDepartment());
		int count = statement.executeUpdate();
		if (count != 1) {
			System.out.println("On persist modify more then 1 record: " + count);
		} 
	} catch (SQLException e ) {
		throw e;
	} 
	sql = "SELECT * FROM GROUPOFSTUDENTS WHERE id = last_insert_id();";
	try (PreparedStatement statement = con.prepareStatement(sql)) {
		ResultSet rs = statement.executeQuery();
		rs.next();
		result.setId(rs.getInt("id"));
		result.setNumber(rs.getInt("groupnumber"));
		result.setDepartment(rs.getString("faculty"));
	} catch (SQLException e ) {
		throw e;
	} 
	return result;
}



@Override
public void update(Group object) throws SQLException {
	String sql = "UPDATE GROUPOFSTUDENTS \n" + "SET groupnumber = ?, faculty = ? \n" + "WHERE id = ?;";
	try (PreparedStatement statement = con.prepareStatement(sql);) {
		statement.setInt(1, object.getNumber());
		statement.setString(2, object.getDepartment());
		statement.setInt(3,object.getId());
		int count = statement.executeUpdate();
		if (count != 1) {
			System.out.println("On update modify more then 1 record: " + count); 
		} 
	} catch (SQLException e) {
		throw e; 
	} 
	
}

@Override
public void delete(Group object) throws SQLException {
	String sql = "DELETE FROM GROUPOFSTUDENTS WHERE id= ?;";
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
public Group getbyPK(int key) throws SQLException {
	String sql = "SELECT * FROM GROUPOFSTUDENTS WHERE id = ?;";
	Group g = new Group();
	try (PreparedStatement stm = con.prepareStatement(sql)) {
		stm.setInt(1, key);
		ResultSet rs = stm.executeQuery();
		rs.next();
		g.setId(rs.getInt("id"));
		g.setNumber(rs.getInt("groupnumber"));
		g.setDepartment(rs.getString("faculty"));
	} 
	return g;
}

@Override
public List<Group> getAll() throws SQLException {
	String sql = "SELECT * FROM GROUPOFSTUDENTS;";
	List<Group> list = new ArrayList<Group>();
	try (PreparedStatement stm = con.prepareStatement(sql)) { 
		ResultSet rs = stm.executeQuery(); 
		while (rs.next()) { 
			Group g = new Group();
			g.setId(rs.getInt("id"));
			g.setNumber(rs.getInt("groupnumber"));
			g.setDepartment(rs.getString("faculty"));
			list.add(g); 
			} 
		} 
	return list;
}



}