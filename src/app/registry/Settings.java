package app.registry;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import static app.registry.WinRegistry.*;

public class Settings {
    public static final String APP_NAME = "Mark as Completed";

    private enum T {FILES, DIRECTORIES}

    public static boolean addRegistries() throws InvocationTargetException, IllegalAccessException {
        return addRegistry(T.FILES) & addRegistry(T.DIRECTORIES);
    }

    public static boolean addRegistry(T t) throws InvocationTargetException, IllegalAccessException {
        final String EXE_PATH = new File(System.getProperty("user.dir"), APP_NAME + ".exe").getAbsolutePath();
        final String KEY_MAC = getKeyMacOf(t);
        final String KEY_COMMAND = KEY_MAC + "\\command";

        createKey(HKEY_CURRENT_USER, KEY_MAC);
        createKey(HKEY_CURRENT_USER, KEY_COMMAND);

        writeStringValue(HKEY_CURRENT_USER, KEY_MAC, "", APP_NAME);
        writeStringValue(HKEY_CURRENT_USER, KEY_MAC, "icon", EXE_PATH);
        writeStringValue(HKEY_CURRENT_USER, KEY_COMMAND, "", EXE_PATH + " \"%1\"");

        return readString(HKEY_CURRENT_USER, KEY_COMMAND, "") != null;
    }

    private static String getKeyMacOf(T t) {
        return String.format("SOFTWARE\\Classes\\%s\\shell\\%s",
                t == T.FILES ? "*" : "Directory",
                APP_NAME);
    }
}