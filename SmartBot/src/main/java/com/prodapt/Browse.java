package com.prodapt;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import netscape.javascript.JSObject;

import org.w3c.dom.Document;
 
 
public class Browse extends Application implements Runnable {
    private Scene scene;
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Smart Bot");
        scene = new Scene(new Browser(),200,300, Color.web("#666970"));
        stage.setScene(scene);
        stage.setMaxHeight(300);
        stage.setMaxWidth(200);
        //scene.getStylesheets().add("webviewsample/BrowserToolbar.css");   
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 200);
        stage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 300);
        stage.setWidth(200);
        stage.setHeight(300);
        stage.show();
        
        
    }
    
    public void stop(){
    	Platform.exit();
    }
 
    public static void main(String[] args){
       
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String[] test = null;
		 launch(test);
		
	}
}
class Browser extends Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        webEngine.load("http://localhost:8080/index.html");
        webEngine.setJavaScriptEnabled(true);
       /* webEngine.documentProperty().addListener(new ChangeListener<Document>() {
            @Override public void changed(ObservableValue<? extends Document> prop, Document oldDoc, Document newDoc) {
                enableFirebug(webEngine);
              }
            }); */
        //add the web view to the scene
        
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {
                    public void changed(ObservableValue<? extends State> ov,
                        State oldState, State newState) {
                        if (newState == State.SUCCEEDED) {
                                JSObject win = (JSObject) webEngine.executeScript("window");
                                win.setMember("app", new JavaApp());
                            }
                        }
                    }
            );
        
        
        getChildren().add(browser);
 
    }
    
    private static void enableFirebug(final WebEngine engine) {
        engine.executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}"); 
      }
    
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}