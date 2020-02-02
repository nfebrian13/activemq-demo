package com.nana.activemq.broker;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	/*
	 * URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is
	 * on localhost. DEFAULT_BROKER_URL : tcp://localhost:61616
	 * 
	 */
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	/*
	 * Queue Name. you can create any/many queue names as per your requirement.
	 */

	private static String queueName = "MESSAGE_QUEUE";

	public static String getMessageFromBroker() throws JMSException {

		Connection connection = null;
		ConnectionFactory connectionFactory = null;
		String result = "failed";

		try {
			/*
			 * Getting JMS connection from the JMS server and starting it.
			 */
			connectionFactory = new ActiveMQConnectionFactory(url);
			connection = connectionFactory.createConnection();
			connection.start();

			/*
			 * creating a non transactional session to send/receive JMS message
			 */
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			/*
			 * The queue will be created automatically on the server
			 */
			Destination destination = session.createQueue(queueName);

			/*
			 * Message consumer is used for receiving (consuming) messages
			 */
			MessageConsumer consumer = session.createConsumer(destination);

			/*
			 * Here we are sending our message!
			 */
			Message message = consumer.receive();

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("println " + textMessage.getText());
				result = "Message receive  from broker successfully";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return result;
	}
}
