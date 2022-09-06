Altenative version of getting macOS to work with ODBC-JDBC.

NB: There is no ODBC driver for macOS, hence the ODBC-JDBC driver won't work directly. Also, microsoft access database do not support the macOS. Hence all database files with extension `.mdb` would need a little tweak but limited functionality.

## Steps
1. Download the current version of netbeans from [here](https://netbeans.apache.org/kb/docs/java/index.html) and run the installation process. It is pretty simple with the `.dmg` file. 
2. Download the jdk version of your choice. For convenience verify first if you do not have any version installed by running the command `javac -version` but most preferrable `java -version` is better. You can get full instructions per your choice from [here](https://docs.oracle.com/en/java/javase/15/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE). You can get a direct download link [here](https://www.oracle.com/java/technologies/downloads/) for the `.dmg` file. 
3. Download the `MYSQL` driver connecter to link your database source and the java frontend [here](https://dev.mysql.com/downloads/connector/j/8.0.html). NB: On this first page, from the `select your operating system` drop down menu, please select `independent platform` for compactibility issues with the macOS. Unzip the file and keep the folder in a home directory, most preferrably. 
4. Download the `mdb viewer for mac`, you need this to access any `.mdb` file and be able to convert to `.sql` file for easy use on macOS. Get that from [here](https://eggerapps.at/mdbviewer/) and unzip it. 
5. One more download and very important is the `MySQL Workbench` software which gives your `MySQL` server and also to hold multiple database. Most importantly, helps you to import/export `MySQL` database directly. Please download from [here](https://dev.mysql.com/downloads/workbench/);
    - You'd need to install this software and set it up to work on the localhost. The instructions are pretty simple to follow. 
    - The software upon complete installation asks you to set up the `MySQL server`, here you choose a `username` mostly is `root`, `password`.
    - Once this is done, on the left window pane, under the `administration` (other tab is `schema`, where all available database should appear), click on the `server status`. Check from the right window pane, with a play button symbol and under it, you find the status `Running`. Once this is true, your MySQL server is up and running and can access any database query. 

6. Next, we connect the `JDBC` to your database and queries from the java frontend can work correctly. 
    - Donwnload the `Unit 5 NetBeans - 3` from canvas, if not already done. 
    - Extract and load the project. First load the `HelloWorldApp` by clicking on `file` from netbean and selecting `open project`.  Navigate your way through to the location of the the `HelloWorldApp`. Once it is loaded, you'd be prompted to resolve `jdk` version issues. Remember from step 2, you already installed a version of JDK. NetBeans suggests a resolution and you should go with the suggestion to get the `JDK` working. Once, it is resolved, run the `HelloWorldApp`.
    - Next we run the `MSjavaAccessDB`.
    - First using the `mdb viewer` for mac open the `JavaDB1.mdb` file and export it as `.sql` file into any directory of your choice. 
    - Now, import the new `.sql` into `MySQLWorkbench` by going to `sever` from the software and selecting `import data`. Then click on `Import from self-contained file`, here you click on the `.` after the box next to it to navigate to the directory where you exported the `.sql` file from the `mdb viewer`. Hit `start import` from the bottom right corner and it done. NB: The `mdb viewer` is a paid app so only allows a halfway row import of the database. However, you can open the `.sql` server in `TextEdit` on mac and add the remaining rows since the database is a simple one.  This  a disadvantage for big database. 
    - Again load the project as before and resolve the same `JDK` issues. Then, add the `Driver`. Right click on the `Libraries` and select `add JAR/Folder`, then navigate to the directory when you unzip your `driver connector` in step 3. The file extention must be `.jar` which is the JDBC driver to connect your java to your database. 
    - In the `MSjavaAccessDB.java` file, use `static String nameOfJdbcOdbcDriver = "com.mysql.cj.jdbc.Driver"` to point to the connection `Driver`. 
    - Point the database source with `static String dataBaseNameDSN = "jdbc:mysql://localhost:3306/mydatabasesource"`. NB; `mydatabasesource` can be any name you used during the importation of the `JavaDB1.mdb` into the `MySQLWorkbench` software. 
    - Once all these are set, you should up and running with the `MSjavaAccessDB.java`. 






