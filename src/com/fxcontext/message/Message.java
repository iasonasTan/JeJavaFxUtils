package com.fxcontext.message;

public class Message {
	public static Builder newBuilder() {
		return new Builder();
	}
	
	private String mAction;
	private Bundle mBundle = new Bundle();
	
	Message() {
	}

	Message(String action) {
		mAction = action;
	}
	
	public void putExtra(String key, Object value) {
		mBundle.put(key, value);
	}
	
	public Bundle getBundle() {
		return new Bundle(mBundle);
	}
	
	public String getAction() {
		return new String(mAction);
	}
	
	public static final class Builder {
		private final Message mMessage = new Message();
		
		Builder(String action) {
			mMessage.mAction = action;
		}
		
		Builder() {
		}
		
		public Builder setAction(String action) {
			mMessage.mAction = action;
			return this;
		}
		
		public Builder putExtra(String key, Object value) {
			mMessage.putExtra(key, value);
			return this;
		}
		
		public Message build() {
			return mMessage;
		}
	}
}
