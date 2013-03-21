mail-service
============

Notification sender which reads messages from a JMS queue.
------------

To run the application you need HornetQ server running with queue mailSenderQueue defined and JNDI enabled.
You can customize JNDI settings and queue name in jms.properties file.

If you use linux, you can use installHornetq.sh script to download and initialize the HornetQ server.
The script will:
 - add a queue to the server
 - create runHornetQ.sh script which you can use to start up the server and enable JNDI


To run the application either:
 - Run Server class in your favourite IDE
 - build maven project:<br/>
    <code>mvn clean install</code><br/>
   and run mail-service jar from target/bin:<br/>
    <code>java -jar target/bin/mail-service-1.0-SNAPSHOT.jar</code>
