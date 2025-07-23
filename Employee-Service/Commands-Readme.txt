* To build your project use command:
	mvn clean package -Dmaven.test.skip

* To launch your application, move into the target folder (cd target). Run the following command to run the application:

	java -jar <your application jar file name>

* Please use 127.0.0.1 instead of localhost to test rest endpoints.

* Default credentials for MySQL:
	Username: root
	Password: pass@word1

* To login to mysql instance: Open new terminal and use following command:
      a.	sudo systemctl enable mysql
      b.	sudo systemctl start mysql
      NOTE: After typing any of the above commands you might encounter any warnings.
      --> Please note that these warnings are expected and can be disregarded. Proceed to the next step.
      c.	mysql -u root -p
The last command will ask for password which is ‘pass@word1’

* Mandatory: Before final submission run the following command: 
	mvn test
