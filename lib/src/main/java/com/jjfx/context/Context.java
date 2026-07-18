package com.jjfx.context;

import com.jjfx.message.Message;
import com.jjfx.receiver.MessageReceiver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.lang.reflect.Constructor;
import java.net.URL;

public interface Context {
	void broadcastMessage(Message data);
	void registerReceiver(MessageReceiver receiver);

	Stage getRootStage();
	
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
