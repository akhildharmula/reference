package jdbc;
import java.sql.*;
public class Assignment {

	    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assignment";
	    public static final String USER = "root";
	    public static final String PASSWORD = "root";
	    
	    
	    private static void deleteRecordWithLowestPrice(Connection connection) throws SQLException {
	    	
	    
	    	
	        String deleteQuery = "DELETE FROM Product WHERE Price = 10";
	        try (Statement statement = connection.createStatement()) {
	            int rows = statement.executeUpdate(deleteQuery);
	            if (rows > 0) {
	                System.out.println("Record with the lowest price deleted");
	            } else {
	                System.out.println("No records deleted");
	            }
	        }
	    }
	    
	    private static void updateLastTwoRecords(Connection connection) throws SQLException {
	        String updateQuery = "UPDATE Product SET ProdName = ?, Price = ?, Category = ? WHERE ProdId = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	            for (int i = 4; i <= 5; i++) {
	                preparedStatement.setString(1, "UpdatedProduct" + i);
	                preparedStatement.setDouble(2, 20.0 * i);
	                preparedStatement.setString(3, "UpdatedCategory" + i);
	                preparedStatement.setInt(4, i);
	                preparedStatement.executeUpdate();
	            }
	            System.out.println("Last 2 records updated");
	        }
	    }
	    
	    private static void viewRecords(Connection connection) throws SQLException {
	        String selectQuery = "SELECT * FROM Product";
	        try (Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(selectQuery)) {
	            System.out.println("Viewing all records:");
	            while (resultSet.next()) {
	                System.out.println("ProdId: " + resultSet.getInt("ProdId") +
	                        ", ProdName: " + resultSet.getString("ProdName") +
	                        ", Desc: " + resultSet.getString("Descr") +
	                        ", Price: " + resultSet.getDouble("Price") +
	                        ", Category: " + resultSet.getString("Category"));
	            }
	        }
	    }
	    
	    public static void insertRecords(Connection connection) throws SQLException {
	        String insertQuery = "INSERT INTO Product ( ProdName, Descr, Price, Category) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
	     
	            for (int i = 1; i <= 5; i++) {
	            	preparedStatement.setString(1, "Product" + i);
	                preparedStatement.setString(1, "Product" + i);
	                preparedStatement.setString(2, "Description" + i);
	                preparedStatement.setDouble(3, 10.0 * i); 
	                preparedStatement.setString(4, "Category" + i);
	                preparedStatement.executeUpdate();
	            }
	            System.out.println("5 records inserted");
	        }
	    }
	    
	    private static void createProductTable(Connection connection) throws SQLException {
	        String createTableQuery = "CREATE TABLE Product (" +
	                "ProdId INT AUTO_INCREMENT PRIMARY KEY," +
	                "ProdName VARCHAR(25)," +
	                "Descr VARCHAR(25)," +
	                "Price DOUBLE," +
	                "Category VARCHAR(25)" +
	                ")";
	        try (Statement statement = connection.createStatement()) {
	            statement.executeUpdate(createTableQuery);
	            System.out.println("Product table created");
	        }
	    }
	    

	    public static void main(String[] args) throws ClassNotFoundException {
	        try {
	        	
	        	Class.forName("com.mysql.jdbc.Driver");
	        	
	            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
	            
	            createProductTable(connection);

	            insertRecords(connection);

	            viewRecords(connection);

	            updateLastTwoRecords(connection);

	            deleteRecordWithLowestPrice(connection);

	            connection.close();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	}
