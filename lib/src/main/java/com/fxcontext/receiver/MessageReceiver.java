package com.fxcontext.receiver;

import com.fxcontext.message.Message;

@FunctionalInterface
public interface MessageReceiver {
	void onReceive(Message message);
}
