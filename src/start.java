import java.net.InetSocketAddress;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.auth.AuthProvider;
import com.datastax.oss.driver.api.core.auth.ProgrammaticPlainTextAuthProvider;
import com.datastax.oss.driver.api.core.cql.*;

public class start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthProvider authProvider = new ProgrammaticPlainTextAuthProvider("cassandra", "cassandra");

		
		try (CqlSession session = CqlSession.builder()
				//.withAuthProvider(authProvider)
				//.addContactPoint(new InetSocketAddress("localhost", 9042))
				//.withKeyspace(CqlIdentifier.fromCql("my_keyspace"))
				.build()) {                                  										// (1)
		  ResultSet rs = session.execute("select release_version from system.local");              // (2)
		  Row row = rs.one();
		  System.out.println(row.getString("release_version"));                                    // (3)
		  
		  //session.execute("CREATE KEYSPACE ks1 WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};");
		  //session.execute("CREATE KEYSPACE ks2 WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};");
		  
		  CqlSession session1 = CqlSession.builder().withKeyspace(CqlIdentifier.fromCql("ks1")).build();
		  CqlSession session2 = CqlSession.builder().withKeyspace(CqlIdentifier.fromCql("ks2")).build();
		  
		  //session1.execute("CREATE TABLE user ( first_name text, last_name text, PRIMARY KEY (first_name));");
		  
		  //session1.execute("INSERT INTO user (first_name, last_name) VALUES ('Bill', 'Nguyen');");
		  //session1.execute("INSERT INTO user (first_name, last_name) VALUES ('Mary', 'Rodriguez');");
		  System.out.println("1");
		  ResultSet rs_users = session.execute("SELECT * FROM ks1.user;");
		  System.out.println("2");
		  for (Row row_user : rs_users) {
			  	System.out.println("3");
			    //System.out.println(row_user);
			    String firstName = row_user.getString(0);
			    String last_name = row_user.getString(1);
			    
			    System.out.println(firstName);
			    System.out.println(firstName);
			    
			}
		  
		}
	}

}
