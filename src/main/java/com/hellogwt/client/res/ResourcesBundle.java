package com.hellogwt.client.res;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface ResourcesBundle extends ClientBundle {
    public static final ResourcesBundle INSTANCE =  GWT.create(ResourcesBundle.class);

    @Source("style.css")
    public CssResource css();
}