package com.jjfx.utils;

import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessageWindow extends Stage {
    private final VBox mMainLayout = new VBox(10);
    private final HBox mButtonsLayout = new HBox(10);
    private Dimension2D mDimension = new Dimension2D(300, 200);

    public MessageWindow(String title, Stage parent, String message, String description) {
        addText(message, description);
        initOwner(parent);
        setAlwaysOnTop(true);
        setTitle(title);
        mMainLayout.setAlignment(Pos.CENTER);
        mButtonsLayout.setAlignment(Pos.CENTER);
    }

    protected void addText(String message, String description) {
        Label titleLabel = new Label(message);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: large;");
        mMainLayout.getChildren().add(titleLabel);

        Label descriptionLabel = new Label(description);
        mMainLayout.getChildren().add(descriptionLabel);
    }

    public MessageWindow addAction(String text, MessageWindowListener action) {
        Button button = new Button(text);
        button.setOnAction(_ -> action.onOk(this));
        mButtonsLayout.getChildren().add(button);
        return this;
    }

    public MessageWindow setDimension(Dimension2D dimension) {
        mDimension = new Dimension2D(dimension.getWidth(), dimension.getHeight());
        return this;
    }

    public void showWindow() {
        mMainLayout.getChildren().add(mButtonsLayout);
        setWidth(mDimension.getWidth());
        setHeight(mDimension.getHeight());
        setScene(new Scene(mMainLayout, 0, 0));
        sizeToScene();
        show();
    }

    public void closeWindow() {
        close();
    }

    public interface MessageWindowListener {
        void onOk(MessageWindow window);
    }
}
