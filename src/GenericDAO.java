import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDAO<T> {

    public abstract int count() throws SQLException;
    public abstract T create() throws SQLException;
    public abstract T persist(T object) throws SQLException;
    public abstract T getbyPK(int key) throws SQLException;
    public abstract void update(T object) throws SQLException;
    public abstract void delete(T object) throws SQLException;
    public abstract List<T> getAll() throws SQLException;
    

    //Protected
    protected final String tableName;
    protected Connection con;

    protected GenericDAO(Connection con, String tableName) {
        this.tableName = tableName;
        this.con = con;
    }
	

}