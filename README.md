Digital Scanner
------------------------------------------------------------------------------------------------------------------------
### Purpose

Digital Scanner is:
* a command line Java application which scans a digitally formatted character in a file and prints it to console
* runnable as an executable jar
------------------------------------------------------------------------------------------------------------------------
### How to open project in Intellij

* Click on File -> New -> Project from existing sources -> double-click on pom.xml in DigitalScanner directory
------------------------------------------------------------------------------------------------------------------------
### How to run the Digital Scanner application

1) In your terminal/command console use command `mvn clean package` to create an executable jar of the application

2) Run the jar from the generated target directory with the following command
   `java -jar target/DigitalScanner-1.0-SNAPSHOT.jar`

3) The following commands can be used once the application is running:

    * To convert the digital characters in a file to text provide the file path (e.g. /Users/Bob/Docs/myFile.txt) and hit Enter
    * To quit program Hit 'Q' and hit Enter
------------------------------------------------------------------------------------------------------------------------