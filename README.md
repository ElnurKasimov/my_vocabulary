## **Foreword**

The reason for writing this application was a request from my friends to get a simple desktop program - a foreign words tutor. Since they were far from programming, they needed a project with minimum requirements for additional software.  
'Сopy the executable file to computer - run it - enjoy a lerning process.'  
Therefore it was decided to use Spring Boot (with the embedded servlet dispatcher Tomcat and the embedded database H2).
For personal use, the capacity and performance of the H2 will be quite sufficient

## **Description**

The application implements a web service for creating, storing and updating bilingual word pairs in any languages that are supported by ASCII.  
The practice of memorizing words is also available.

## **How to launch the application**

After cloning the project to your computer, create an executable file. Use the './gradlew build' command to do this.  
The file named 'myVocabulary-0.0.1-SNAPSHOT' will appear in the folder '\my_vocabulary\build\libs'.  
You can copy it, put it wherever you like, and rename it (if you want).  
To launch the application use in command line command  
&nbsp;&nbsp;&nbsp;&nbsp;java -jar "path\to\your_jarfile\name_of_your_jar_file.jar"  
Regardless of where your jar file is placed, the database will be created in the directory where your JVM is located.  
The name of data base is 'database.mv.db'  
If you want to use several data bases you must use such command for launch  
&nbsp;&nbsp;&nbsp;&nbsp;java -jar "path\to\your_jarfile\name_of_your_jar_file.jar" --spring.datasource.url=jdbc:h2:file:"path\to\your_another_database\database"  
In this case You can use different directories to store different languages like '/ENGLISH', '/ARABIС'  and so on.   
The application uses migrations to create the database structure and fill it with the initial values.  
If you launch the application first time the database with initial values will be created in the directory where your JVM is located.  
Every next launch the application will use the actual state of the database.  
If there is no data base in 'path\to\your_another_database' it will be the same scenario.  

## **How to use the application**

After launching the application, you may see a lot of messages from Spring in the console. The last one should be  
"TO WORK WITH THE DICTIONARY VISIT"  
"http://localhost:8080"  
This means that all the functionality of the application is available on localhost:8080.  
The current directory of your JVM will be shown before this information. This is the default location where your database will be created.  

The home page displays 10 random words and 10 random tags from the database.  
Due to the fact that the program is very simple, its use is intuitive.  
In case of emergency situations, as a rule, an error handler page is displayed, according to the information from which you can figure out what was done wrong.

