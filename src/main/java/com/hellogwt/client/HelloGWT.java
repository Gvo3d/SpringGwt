package com.hellogwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class HelloGWT implements EntryPoint {

    public void onModuleLoad() {
        final Label greetingLabel = new Label("Hello, GWT!");
        RootPanel.get().add(greetingLabel);

        greetingLabel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                greetingLabel.setText("i was clicked");
            }
        });
    }
}