package com.fxcontext.main;

import java.lang.reflect.Constructor;
import java.net.URL;

import com.fxcontext.message.Message;
import com.fxcontext.receiver.MessageReceiver;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.util.Callback;
import javafx.scene.Parent;

public interface Context {
	void broadcastMessage(Message data);
	void registerReceiver(MessageReceiver receiver);
	
	public static Callback<Class<?>, Object> controllerFactory(Context context) {
		return clazz -> {
			try {
				Constructor<?> constructor = clazz.getDeclaredConstructor(Context.class);
				return constructor.newInstance(context);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}
	
	public static Parent loadFXML(Context context, URL url, URL style) {
		try {
			FXMLLoader loader = new FXMLLoader(url);
			loader.setControllerFactory(controllerFactory(context));
			Parent scene = loader.load();
			scene.getStylesheets().add(style.toExternalForm());
			return scene;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
