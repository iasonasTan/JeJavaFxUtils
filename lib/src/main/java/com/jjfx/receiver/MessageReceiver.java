package com.jjfx.receiver;

import com.jjfx.message.Message;

@FunctionalInterface
public interface MessageReceiver {
	void onReceive(Message message);
}
