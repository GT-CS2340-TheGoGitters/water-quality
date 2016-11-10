package model.logging.security;

public class ApplicationStartedEntry extends SecurityLogEntry {

    public ApplicationStartedEntry() {
        super(null);
    }

    public String getLabel(){
        return "Application Started";
    }

    public String getMessage(){
        return null;
    }
}
