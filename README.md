jms-examples
============

Notification sender which reads messages from a JMS queue.
------------

To run the application you need HornetQ server running with queue exampleQueue defined and JNDI enabled.
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
    <code>java -jar target/bin/<the-jar-for-module-that-you-want-to-run>.jar</code>

In case of any problems or questions feel free to contact me at: michal.l.szynkiewicz@gmail.com
