package com.jjfx.message;

import java.util.HashMap;
import java.util.Map;

public final class Bundle {
	public static Builder builder() {
		return new Builder();
	}
	
	private final Map<String, Object> mData = new HashMap<>();

	Bundle() {
	}
	
	Bundle(Map<String, Object> data) {
		mData.putAll(data);
	}
	
	Bundle(Bundle bundle) {
		mData.putAll(bundle.mData);
	}
	
	public Object get(String key) {
		return mData.get(key);
	}
	
	public String getString(String key) {
		return (String)mData.get(key);
	}
	
	public int getInteger(String key, int defaultValue) {
		Object obj = mData.get(key);
		if(obj==null)
			return defaultValue;
		return (Integer)obj;
	}
	
	public double getDouble(String key, double defaultValue) {
		Object obj = mData.get(key);
		if(obj==null)
			return defaultValue;
		return (Double)obj;
	}
	
	public <T> T getObject(String key, Class<T> clazz) {
		Object obj = mData.get(key);
		return clazz.cast(obj);
	}
	
	public void put(String id, Object obj) {
		mData.put(id, obj);
	}
	
	public static final class Builder {
		private final Bundle mBundle = new Bundle();
		
		Builder put(String id, Object obj) {
			mBundle.put(id, obj);
			return this;
		}
		
		public Bundle build() {
			return mBundle;
		}
	}
}
