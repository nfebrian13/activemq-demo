package com.nana.activemq;

import javax.jms.JMSException;

import com.nana.activemq.broker.Receiver;
import com.nana.activemq.broker.Sender;

public class Main {
	public static void main(String[] args) throws JMSException {
		Receiver receiver = new Receiver();
		Sender sender = new Sender();

		System.out.println(receiver.getMessageFromBroker());
//		System.out.println(sender.senderToBrocker());
	}

}
