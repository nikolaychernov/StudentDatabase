import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.jdbcx.JdbcDataSource;


public class DAOManager {

    public static DAOManager getInstance() {
        return DAOManagerSingleton.INSTANCE.get();
    }  

    public void open() throws SQLException {
        try{
                this.con = ds.
                        getConnection();
                Statement st = null;
                st = this.con.createStatement();
                
                st.execute("CREATE TABLE IF NOT EXISTS GROUPOFSTUDENTS" + 
                "(id INT NOT NULL AUTO_INCREMENT ," +
                "groupnumber INT NOT NULL ," +
                "faculty VARCHAR(45) NULL ," + 
                "PRIMARY KEY (id) );");
                
                st.execute("CREATE TABLE IF NOT EXISTS STUDENT " + 
                "(id INT NOT NULL AUTO_INCREMENT ," +
                " firstname VARCHAR(45) NULL ," +
                " secondname VARCHAR(45) NULL , " +
                "lastname VARCHAR(45) NULL ," +
                "groupnum INT ," +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (groupnum) REFERENCES GROUPOFSTUDENTS(groupnumber) ON DELETE RESTRICT);");
        }
        catch(SQLException e) { throw e; }
    }

    public void close() throws SQLException {
        try{
            if(this.con!=null && !this.con.isClosed())
                this.con.close();
        }
        catch(SQLException e) { throw e; }
    }

 
    private Connection con;
    private JdbcDataSource ds;
    
    
    public GenericDAO getDAO(Table t) throws SQLException 
    {

        try{
            if(this.con == null || this.con.isClosed()) 
                this.open();
        }
        catch(SQLException e){ throw e; }

        switch(t){
        case STUDENT:
            return new StudentDAO(this.con);
        case GROUPOFSTUDENTS:
            return new GroupDAO(this.con);
        default:
            throw new SQLException("Trying to link to an unexistant table.");
        }

    }

    private DAOManager() throws Exception {
        try {
        	Class.forName("org.h2.Driver");
        	ds = new JdbcDataSource();
            ds.setURL("jdbc:h2:~/test5");
            ds.setUser("sa");
            ds.setPassword("");
        }
        catch(Exception e) { throw e; }
    }

    private static class DAOManagerSingleton {

        public static final ThreadLocal<DAOManager> INSTANCE;
        static{
            ThreadLocal<DAOManager> dm;
            try{
                dm = new ThreadLocal<DAOManager>(){
                    @Override
                    protected DAOManager initialValue() {
                        try
                        {
                            return new DAOManager();
                        }
                        catch(Exception e)
                        {
                            return null;
                        }
                    }
                };
            }
            catch(Exception e) {
                dm = null;
            }
            INSTANCE = dm;
            
        }        

    }

}