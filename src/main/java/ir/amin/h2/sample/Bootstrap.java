package ir.amin.h2.sample;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bootstrap {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		H2Server h2Server = new H2Server();
//		h2Server.runDBByCMD();
		h2Server.createDBByNameAndDefaultUser("cs");
		H2Container h2Container = h2Server.getH2Container();
		Statement stmt = h2Container.getConnection().createStatement();
		stmt.executeUpdate("create table if not exists user (firstname varchar(30), lastname varchar(30))");
		stmt.executeUpdate("insert into user values ('f1','p1')");
		stmt.executeUpdate("insert into user values ('f2','p1')");
		stmt.executeUpdate("insert into user values ('f3','p1')");

		ResultSet rs = stmt.executeQuery("select count(*) from user");
		if (rs.next()) {
			System.out.println(rs.getInt(1));
		}
//		Properties properties = new Properties();
//		properties.setProperty("url", String.format(H2Container.JDBC_URL, server.getPort()));
//		properties.setProperty("tablename", H2Container.JDBC_TABLE);
//		properties.setProperty("username", H2Container.JDBC_USERNAME);
//		properties.setProperty("password", H2Container.JDBC_PASSWORD);
		
		
		
		String url = h2Container.getConnection().getMetaData().getURL();
		System.out.println(url);
//		String webUrl = h2Container.getWebServer().getURL();
		String address = "http://127.0.0.1:8082/";

		Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start chrome " + address });		
		
	}

}
