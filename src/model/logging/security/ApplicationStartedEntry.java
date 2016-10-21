package model.logging.security;

/**
 * Created by Joshua on 10/21/16.
 */
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
