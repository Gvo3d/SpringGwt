package com.hellogwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class HelloGWT implements EntryPoint {

    public void onModuleLoad() {
        HelloGWTWidget helloGWTWidget = GWT.create(HelloGWTWidget.class);

        RootPanel.get().add(helloGWTWidget);
    }
}