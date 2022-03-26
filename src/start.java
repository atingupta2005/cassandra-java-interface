import java.net.InetSocketAddress;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.auth.AuthProvider;
import com.datastax.oss.driver.api.core.auth.ProgrammaticPlainTextAuthProvider;
import com.datastax.oss.driver.api.core.cql.*;

public class start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthProvider authProvider = new ProgrammaticPlainTextAuthProvider("cassandra", "cassandra");
		
		try (CqlSession session = CqlSession.builder()
				.withAuthProvider(authProvider)
				.addContactPoint(new InetSocketAddress("10.0.0.7", 9042))
				.build()) {                                  										// (1)
		  ResultSet rs = session.execute("select release_version from system.local");              // (2)
		  Row row = rs.one();
		  System.out.println(row.getString("release_version"));                                    // (3)
		}
	}

}
