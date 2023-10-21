package ssp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveDataServlet")
public class SaveDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbUrl = "jdbc:mysql://localhost:3306/sevak_db";
        String dbUser = "root";
        String dbPassword = "Ammulasai@1234";       
        try {
        	//Treating the sevak id as string :2014-179-2321
        	String sevakId = request.getParameter("sevak-id");
        	double fineAmount = Double.parseDouble(request.getParameter("fineamount"));
        	String transactionDate = request.getParameter("transaction_date");
        	String transactionDetails = request.getParameter("transactiondetails");       	
        	Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        	
        	// Check if data for the given sevakId already exists, add one more entry with count+1
        	String selectQuery = "SELECT count(1) as existingcount FROM data WHERE sevak_id = ?";
        	PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
        	selectStatement.setString(1, sevakId);
        	ResultSet resultSet = selectStatement.executeQuery();
        	
        	if (resultSet.next()) {
        	    // Data for the given sevakId already exists, so have a count +1
        	    int count = resultSet.getInt("existingcount");
        	    count+=1;
        	    execute(conn,sevakId,fineAmount,transactionDate,transactionDetails,count);
        	}else {    
        		//New entry if there is no entry existing in our sevak_db
        		execute(conn,sevakId,fineAmount,transactionDate,transactionDetails,1);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            // Handle errors here
        }

        // Redirect the user back to the UI or a confirmation page
        response.sendRedirect("confirmation.jsp"); 
    }
    
    protected void execute(Connection conn,String sevakId,Double fineAmount,String transactionDate,String transactionDetails,int count) throws SQLException {
    	String insertQuery = "INSERT INTO data (sevak_id, fine_amount, transaction_date, transaction_details, count) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	    preparedStatement.setString(1, sevakId);
	    preparedStatement.setDouble(2, fineAmount);
	    preparedStatement.setDate(3, java.sql.Date.valueOf(transactionDate));
	    preparedStatement.setString(4, transactionDetails);
	    preparedStatement.setInt(5, count);
	    preparedStatement.executeUpdate();
    	conn.close();  	
    }
    
}