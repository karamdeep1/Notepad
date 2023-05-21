package com.example.notepad;
import javafx.application.HostServices;
class HostService {
    private static HostService _instance;
    private HostServices hostServices;

    public HostService (HostServices hostServices) {
        // Do not allow this to be created externally
        if (hostServices == null) {
            throw new RuntimeException("Host services can't be null to instantiate this method");
        }
        this.hostServices = hostServices;
    }

    public static void createInstance(HostServices hostServices) {
        if (_instance == null) {
            _instance = new HostService (hostServices);
        }
    }

    public static HostService getInstance() {
        if (_instance== null) {
            throw new RuntimeException("HostServiceWrapper has not been correctly instantiated.");
        }
        return _instance;
    }

    public void openURL(String url) {
        hostServices.showDocument(url);
    }
}
