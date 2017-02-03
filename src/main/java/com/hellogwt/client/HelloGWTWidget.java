package com.hellogwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.hellogwt.client.service.GreetingService;
import com.hellogwt.client.service.GreetingServiceAsync;
import com.hellogwt.shared.domain.Greeting;

import java.util.List;

public class HelloGWTWidget extends Composite {

    interface HelloGWTWidgetUiBinder extends UiBinder<Widget, HelloGWTWidget> {
    }

    private static HelloGWTWidgetUiBinder uiBinder = GWT.create(HelloGWTWidgetUiBinder.class);

    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private AsyncCallback<Void> callback = new AsyncCallback<Void>() {
        public void onFailure(Throwable caught) {
            Window.alert("ERROR: Cannot edit greetings!");
        }

        public void onSuccess(Void result) {
            refreshGreetingsTable();
        }
    };

    @UiField
    TextBox authorTextBox;
    @UiField
    TextBox textTextBox;
    @UiField
    Button addButton;
    @UiField
    Button updateButton;
    @UiField
    Button deleteButton;
    @UiField
    FlexTable greetingsFlexTable;

    public HelloGWTWidget() {
        initWidget(uiBinder.createAndBindUi(this));

        refreshGreetingsTable();
    }

    @UiHandler("addButton")
    void handleAddButtonClick(ClickEvent clickEvent) {
        if (!authorTextBox.getText().isEmpty() && !textTextBox.getText().isEmpty()) {
            greetingService.getGreeting(textTextBox.getText(), new AsyncCallback<Greeting>() {
                public void onFailure(Throwable caught) {
                    Window.alert("ERROR: Cannot find greeting!");
                }

                public void onSuccess(Greeting result) {
                    if (result == null) {
                        greetingService.addGreeting(authorTextBox.getText(), textTextBox.getText(),
                                callback);
                    } else {
                        Window.alert("Greeting already exists!");
                    }
                }
            });
        } else {
            Window.alert("\"Author\" and \"Text\" fields cannot be empty!");
        }
    }

    @UiHandler("updateButton")
    void handleUpdateButtonClick(ClickEvent clickEvent) {
        if (!authorTextBox.getText().isEmpty() && !textTextBox.getText().isEmpty()) {
            greetingService.updateGreeting(authorTextBox.getText(), textTextBox.getText(), callback);
        } else {
            Window.alert("\"Author\" and \"Text\" fields cannot be empty!");
        }
    }

    @UiHandler("deleteButton")
    void handleDeleteButtonClick(ClickEvent clickEvent) {
        greetingService.deleteGreeting(textTextBox.getText(), callback);
    }

    private void refreshGreetingsTable() {
        greetingService.getGreetings(new AsyncCallback<List<Greeting>>() {
            public void onFailure(Throwable throwable) {
                Window.alert("ERROR: Cannot load greetings!");
            }

            public void onSuccess(List<Greeting> greetings) {
                fillGreetingsTable(greetings);
            }
        });
    }

    private void fillGreetingsTable(List<Greeting> greetings) {
        greetingsFlexTable.removeAllRows();

        greetingsFlexTable.setText(0, 0, "Author");
        greetingsFlexTable.setText(0, 1, "Text");

        for (Greeting greeting : greetings) {
            int row = greetingsFlexTable.getRowCount();

            greetingsFlexTable.setText(row, 0, greeting.getAuthor());
            greetingsFlexTable.setText(row, 1, greeting.getText());
        }
    }
}