package com.hellogwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/greetingService")
public interface GreetingService extends RemoteService {

    String greet(String name);
}