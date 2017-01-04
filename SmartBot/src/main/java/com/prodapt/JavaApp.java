package com.prodapt;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javafx.application.Platform;

public class JavaApp {

    public void exit() {
       Platform.exit();
    }
    public void print(Date date) {
        System.out.println("Parm:"+date);
        if(Desktop.isDesktopSupported())
        {
          try {
			Desktop.getDesktop().browse(new URI("http://oneplive.prodapt.com/oneprodapt-new/login.htm#/sats"));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
     }
    public Date getValue() {
        return new Date();
     }
}