package com.example.notepad;
import javafx.application.HostServices;
class HostService {
    private static HostService _instance;
    private HostServices hostServices;

    public HostService (HostServices hostServices) {
        // Do not allow this to be created externally
        // If its null it will not run
        if (hostServices == null) {
            throw new RuntimeException("Host services can't be null to instantiate this method");
        }
        this.hostServices = hostServices;
    }
    //creates instances of the host service
    public static void createInstance(HostServices hostServices) {
        if (_instance == null) {
            _instance = new HostService (hostServices);
        }
    }
    //if the instance is null it will not instantiate it otherwise it will return it
    public static HostService getInstance() {
        if (_instance== null) {
            throw new RuntimeException("HostServiceWrapper has not been correctly instantiated.");
        }
        return _instance;
    }
    //opens the url
    public void openURL(String url) {
        hostServices.showDocument(url);
    }
}
