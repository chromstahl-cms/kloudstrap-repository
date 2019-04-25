package software.kloud.kloudstrapregistry.exception;

public class PluginNotFoundException extends Exception {
    public PluginNotFoundException() {
        super("Plugin was not found");
    }
}
