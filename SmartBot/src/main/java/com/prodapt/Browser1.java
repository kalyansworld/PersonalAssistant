package com.prodapt;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import org.w3c.dom.Document;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;


public class Browser1 extends Application {
  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage primaryStage) {
    final WebView webView = new WebView();
    final WebEngine engine = webView.getEngine();
    engine.load("http://localhost:8080/index.html");
    primaryStage.setScene(new Scene(webView));
    primaryStage.show();

    engine.documentProperty().addListener(new ChangeListener<Document>() {
      @Override public void changed(ObservableValue<? extends Document> prop, Document oldDoc, Document newDoc) {
        enableFirebug(engine);
        
      }
    });

    
    
    
    engine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<State>() {
                @Override
                public void changed(ObservableValue<? extends State> ov,
                    State oldState, State newState) {
                    if (newState == State.SUCCEEDED) {
                            JSObject win = (JSObject) engine.executeScript("window");
                            win.setMember("app", new JavaApp());
                        }
                    }
                }
        );
    
    
    
 // we need this to wait till document load
   /* engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
		@Override
		public void changed(ObservableValue<? extends State> arg0, State arg1,
				State arg2) {
			// TODO Auto-generated method stub
			
			//System.setProperty("webdriver.gecko.driver", "C:\\Users\\krishnareddy.v\\Desktop\\geckodriver.exe");
			System.setProperty("webdriver.ie.driver", "C:/Users/krishnareddy.v/Documents/My Received Files/test/resources/IEDriverServer.exe"); 
			WebDriver driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		     try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.get("http://oneplive.prodapt.com/oneprodapt-new/login.htm#/sats");
			
			
			System.out.println("arg1: "+ arg1 +"arg2: "+ arg2);
            if (arg2 == Worker.State.SUCCEEDED) {

                // note next classes are from org.w3c.dom domain
                EventListener listener = new EventListener() {
                    public void handleEvent(Event ev) {
                        System.out.println(ev.getType());
                    }
                };
                
                Document doc = engine.getDocument();
                System.out.println(doc);
                Element el = doc.getElementById("notificationImg");
                System.out.println(el);
            ((EventTarget) el).addEventListener("click", listener, false);}
			
		}

    });*/
    

  }

  /**
   * Enables Firebug Lite for debugging a webEngine.
   * @param engine the webEngine for which debugging is to be enabled.
   */
  private static void enableFirebug(final WebEngine engine) {
    engine.executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}"); 
  }
}
