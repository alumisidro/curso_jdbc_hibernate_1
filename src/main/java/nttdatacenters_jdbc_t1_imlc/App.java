package nttdatacenters_jdbc_t1_imlc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	// Conexión con ORACLE
    	oracleConecction();
    	
    }
    
    private static void oracleConecction() {
    	
    	// Datos de la conexión
    	String dbUser = "user";
    	String dbPass = "password";
    	
    	try {
    		
    		// Driver de la conexión
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		
    		// Abrir conexión
    		final Connection dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", dbUser, dbPass);
    		
    		// Realizar consulta
    		final Statement sentence = dbConnection.createStatement();
    		final String query = "SELECT u.name AS username, p.avatar AS profile_avatar FROM users AS u INNER JOIN profiles AS p ON u.user_id = p.user_id";
    		final ResultSet queryResult = sentence.executeQuery(query);
    		
    		// Qué hacer con los datos...
    		StringBuilder user = new StringBuilder();
    		while(queryResult.next()) {
    			user.append("Nombre: ");
    			user.append(queryResult.getString("username"));
    			user.append( "\n" );
    		}
    		
    		// Mostrar por consola
    		System.out.println( user.toString() );
    		
    		// Cerrar conexión
    		dbConnection.close();
    		
    	// En caso de excepción
    	} catch (ClassNotFoundException | SQLException e) {
    		
    		// Mostrar por consola
    		System.out.println( "Error en la conexión con la BD" );
    		
    	}
    }
}
