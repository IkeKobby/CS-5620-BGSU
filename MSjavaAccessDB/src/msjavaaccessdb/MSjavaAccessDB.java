/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msjavaaccessdb;
import java.sql.*;
import java.util.*;
/**
 *
 * @author User3
 */
public class MSjavaAccessDB {    
    /** Creates a new instance of databaseApplication */
    public MSjavaAccessDB() {
    }
    
    /**
     * @paramm args the command line arguments
     */
    static String nameOfJdbcOdbcDriver =
                "com.mysql.cj.jdbc.Driver";
//  static String dataBaseNameDSN = "jdbc:odbc:myDataSource";

    static String dataBaseNameDSN = "jdbc:mysql://localhost:3306/JavaDB1";
    static String userName = "root";
    static String passwordForUser = "Kwabena0241";

    static Connection myConnectionRequest = null;
    static Statement myStatementObject = null;
    static ResultSet myResultTuples = null;
    static ResultSetMetaData myResultTuplesMetaData = null;

    static String queryToBeExecuted = "select SSN from student";


    public static void main(String args[])
                        throws ClassNotFoundException  {

      try {

        //Identify the driver to use
        Class.forName(nameOfJdbcOdbcDriver);

        //Attempt a connection to database...
        Connection myConnectionRequest =
                DriverManager.getConnection(
                        dataBaseNameDSN, userName, passwordForUser);

        //Create a statement object, use its method to execute query
        Statement  myStatementObject =
                myConnectionRequest.createStatement();

        //Use statement object method to execute a query.
        //Hold results in a resutl set...like a cursor
        ResultSet myResultTuples = myStatementObject.executeQuery
                                        (queryToBeExecuted);

        //Call metadata to get the number of attributes
        myResultTuplesMetaData = myResultTuples.getMetaData();
        int numberOfAttributes =
                myResultTuplesMetaData.getColumnCount();
        System.out.println(Integer.toString(numberOfAttributes));

        //For each row in result set, print ALL columns

        for(int rowNum = 1; myResultTuples.next(); rowNum++) {
                for (int i = 1; i <= numberOfAttributes; i++) {
                       if ( (i != 1) ) System.out.print (
                                myResultTuples.getString(i) + "\t");
                       else {//String xyz = myResultTuples.getString(1);    
                            int xyz = Integer.parseInt( myResultTuples.getString(1));
                            
                            System.out.print ("SSN =  " + xyz);
                       }
                }

                System.out.println("\n...next row... \n");
        }

      }  // end of try block

      //handle ALL exceptions to above database calls
      catch (SQLException sqlError) {
        System.out.println("Unexpected exception : " +
                sqlError.toString() + ", sqlstate = " +
                sqlError.getSQLState());
        sqlError.printStackTrace();
      }

    }  // end of main method of this class

}  // end of the class
