package com.cjx.utils;

import flex.messaging.MessageBroker;
import flex.messaging.messages.AsyncMessage;

public class MessageHelper {
	
	
	public static void send(Object data,String id)
	{
		AsyncMessage msg = new AsyncMessage();
		
		msg.setClientId("123456");
		msg.setMessageId(id);
		msg.setDestination("myDataMsg");
		msg.setBody(data);
		MessageBroker.getMessageBroker(null).routeMessageToService(msg, null);
		
	}

}
