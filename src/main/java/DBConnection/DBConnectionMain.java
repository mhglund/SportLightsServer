
package DBConnection;


import java.sql.*;


public class DBConnectionMain {
    public static void main(String[] args) throws ClassNotFoundException {
        String server = "jdbc:mysql://mysql.dsv.su.se/anef3448";
        server = server + "?useSSL=false"; // this is only for testing!!!
        int port = 3306;
        String user = "anef3448";
        String password = "shaeCho2aeso";
        String database = "Sportlights";

        //String jdbcUrl = "jdbc:mysql://"+server+":"+port+";user="+user+";password="+password+";databaseName="+database+"";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(server, user, password);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM anef3448.Användare");

            while (rs.next()) {
                System.out.print("This is output : " + rs.getString(1) + " , " + rs.getString(2)+" , " + rs.getString(3));

            }


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                st.close();
                rs.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
