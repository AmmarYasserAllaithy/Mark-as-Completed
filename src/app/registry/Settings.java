package app.registry;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import static app.registry.WinRegistry.*;

public class Settings {
    public static final String APP_NAME = "Mark as Completed";

    public static boolean addRegistry() throws InvocationTargetException, IllegalAccessException {
        final String KEY_MAC = "SOFTWARE\\Classes\\*\\shell\\" + APP_NAME;
        final String KEY_COMMAND = KEY_MAC + "\\command";
        final String PATH = new File(System.getProperty("user.dir"), APP_NAME + ".exe").getAbsolutePath();

        createKey(HKEY_CURRENT_USER, KEY_MAC);
        createKey(HKEY_CURRENT_USER, KEY_COMMAND);

        writeStringValue(HKEY_CURRENT_USER, KEY_MAC, "", APP_NAME);
        writeStringValue(HKEY_CURRENT_USER, KEY_MAC, "icon", PATH);
        writeStringValue(HKEY_CURRENT_USER, KEY_COMMAND, "", PATH + " \"%1\"");

        return readString(HKEY_CURRENT_USER, KEY_COMMAND, "") != null;
    }
}