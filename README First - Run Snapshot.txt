
Directory 'Unit 5 NetBeans 3' has 4 items

	'README First - Run Snapshot' THIS txt file - 
		Useful info on NetBeans/Java/Database access and copy of sources

	'Hello World ... ' A Java app - simple Netbeans hello world app
	'MS Java Access ...' A Java app to access an Access db and spit out some values.
	'Database1'  An Access database with one table

	

--------------------
Use of database in Java requires Java compiler.
NetBeans 8.2 is a good IDE for 'barebones' & 'advanced' use. Let us do barebones!

 
Oracle has a free bundle (JDK 8u111) that has the Java compiler (Java SE 8) w NetBeans (ver 8.2)
Download url:
   https://www.oracle.com/technetwork/java/javase/downloads/jdk-netbeans-jsp-3413139-esa.html

How to install this bundle on a PC? 
1. Click on the PC version (for mine, I chose Windows x64)

2. Once download is complete, click it to run

3. Follow the install prompts - pretty simple

4. Takes a bit of time & finally says 'installed successfuly.'

At this point NetBeans is good to go.

Below we will walk-thru:
1. A barebones Java program (w/o database) run using NetBeans.

2. a barebones Java (database) program run using NetBeans.







----------------
Application 1: (Directory 'Hello World ... ' )
How to launch NetBeans and run this Project?
Start NetBeans; Open this Project ...; 
Run -> Build Proj; Run -> Project (HelloWorldApp)
Output window (seen in the bottom right) shows program output
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworldapp;

/**
 *
 * @author kresman
 */
public class HelloWorldApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World");
    }
}

NetBeans Run Output Window
init:
Deleting: ..
Updating property file: ..
compile:
run:
Hello World
BUILD SUCCESSFUL (total time: 1 second)

--------------

Application 2 (Database access: 'MSjavaAccessDB' )
	(App 2 also uses the 3rd folder 'Database1.'
/*
 * databaseApplication.java
 *
 * 
 *      ______________________________________
 *      I     Connection b/w logical and physical database
 *      ______________________________________

 * Win PC (MS Access) database: "YourDatabase.mdb"

 * How to map "YourDatabase.mdb" to logical name, "myDataSource."
 * Start -> run -> c:\windows\syswow64\odbcad32
 * 
 *     odbc datasource admn window pops up - configure it
       //we want to add a user Data Source Name ... 
         1. Click Add to add a driver 
 *       1. Click 'Driver,' below User Data Sources.' Click Add
              [your screen may be a bit different]
 *       2. select MS Access Driver (*.mdb), click Finish
 *       3. Data Source Name = myDataSource or whatever
              description: whatever
 *       4. Choose database by clicking "Select"
 *              Navigate to your MS Access database 
 *              (left big window should show your "YourDatabase.mdb.")
                         My sample is 'JavaDB1.mdb.'
 *              Click "YourDatabase.mdb" or whatever
 *              Click OK. Click OK
 *        5. Now,     PC has (logical mapping)  "myDataSource" for
 *                           (physical datbase) "YourDatabase.mdb."
 *        6. Reference for  "myDataSource" in Java should work.

  *      ______________________________________
 *       II                  Switch to 32 bit JDK compiler at run time (Hayes 025) 
 *       ______________________________________

 *   Netbeans default Java compiler is 64 bit
 *   However, odbc driver expects a 32 bit compiler 
          You may be able to get the 64 bit to work, but I gave up:-)

 *   CONFIGURE Netbeans for 32 bit w/o removing 64 bit compiler, see below.
 *   1. Download "Unit 5 32 bit jdk" zip from Canvas - from instructor
 *   2. Download it to your flashdrive.
 *   3. Extract all of zip (4 minutes), should create folder "Unit 5 32 bit jdk."
 *   4. Launch Netbeans, open this sample database project.
        [May open a  dialog 'Project Problems ...One or more resources can't be found ...' 
        Just click OK to close this 'Project Problems' dialog. ]

 *   5. Add the 32 bit jdk platform, see below.
 *          Tools -> Java Platforms -> Add Platform (bottom left)

 *          Should say Java standard edition,  click Next

 *          Navigate to the unzipped folder above. 
 *          Click on the folder, "jdk1.7.0_13" (in the middle large window)
 *          'Next' shoud be ungrayed. Click Next.
 *          (You should see platform name etc, nicely populated)

            Also, for 'Platform Sources' - navigate to your jdk1.7.0_13/bin using browse
 *          Click Finish
 *          Click Close

 *    TELL Netebeans to use the above 32 bit JDK for this project.
 *          
      6.    (top left project window) Right click project name -> Properties
 *          (under Categories) Click Libraries
 *          "Platform" should say "JDK 1.7" (not JDK 1.8 default)
 *              If so, all set!
 *              Else, pull down to JDK 1.7 and click "Manage Platforms"
 *                    Next window (under Platform) choose JDK 1.7 (not default)
 *          Exit the windows.
 *     Now, all set to use JDK 32 bit!

 *     If you log out and log back again, redo the above steps.
 *          

 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 * NetBeans IDE Java prog output window (Query: select * from student)
 *
 compile:
run:
3
ssn = 221	John S.	
...next row... 

ssn = 232	David T.	
...next row... 

ssn = 245	Cindy W.	
...next row... 

ssn = 254	Monica L.	
...next row... 

BUILD SUCCESSFUL (total time: 4 seconds)

 */


package a04msaccessdatabaseapplication;

/**
 *
 * @author kresman
 */
import java.sql.*;
import java.util.*;

public class databaseApplication {
    
    /** Creates a new instance of databaseApplication */
    public databaseApplication() {
    }
    
    /**
     * @param args the command line arguments
     */
    static String nameOfJdbcOdbcDriver =
                "sun.jdbc.odbc.JdbcOdbcDriver";
//  static String dataBaseNameDSN = "jdbc:odbc:myDataSource";

    static String dataBaseNameDSN = "jdbc:odbc:myDataSource";
    static String userName = "";
    static String passwordForUser = "";

    static Connection myConnectionRequest = null;
    static Statement myStatementObject = null;
    static ResultSet myResultTuples = null;
    static ResultSetMetaData myResultTuplesMetaData = null;

    static String queryToBeExecuted = "select * from student";


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
                            System.out.print ("ssn = " + xyz);
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

Actual run below
init:
Deleting: ...
Updating property file: ..
Compiling 1 source file to ..
compile:
run:
3
ssn = 221	John S.	
...next row... 

ssn = 232	David T.	
...next row... 

ssn = 245	Cindy W.	
...next row... 

ssn = 254	Monica L.	
...next row... 

BUILD SUCCESSFUL (total time: 2 seconds)


