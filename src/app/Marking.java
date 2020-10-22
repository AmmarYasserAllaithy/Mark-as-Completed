package app;

import app.view.Alert;

import java.io.File;

public class Marking {

    public static void markAll(String... args) {
        if (args.length > 0)
            for (String arg : args) {
                final File file = new File(arg);

                if (file.exists()) {
                    boolean toggle = toggle(file);
                    if (!toggle) Alert.showError("Can't mark file(s)..", file.getAbsolutePath());

                } else Alert.showError("File not found", file.getAbsolutePath());
            }
    }

    private static boolean toggle(File file) {
        final String parent = file.getParent(), name = file.getName();
        final char symbol = (char) 10004;
        final boolean isMarked = name.startsWith(symbol + " ") || name.startsWith("? ");
        final String mName = isMarked ? name.substring(2) : symbol + " " + name;
        final File newFile = new File(parent, mName);
        return file.renameTo(newFile);
    }
}