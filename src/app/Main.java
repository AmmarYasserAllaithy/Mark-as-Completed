package app;

import app.registry.Settings;
import app.view.Alert;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length > 0) Marking.markAll(args);
            else if (Settings.addRegistry())
                Alert.showInfo("Congratulations.",
                        "Your app has added its registries successfully.",
                        "Now, you can use it from the right-click context menu to mark..",
                        "- All Files types (pdf, docx, pptx, amr, mp4, jpg, ..etc).",
                        "- Any Folder / Directory.");
            else Alert.showError("We have encountered error while CONFIGURATIONS");

        } catch (IllegalAccessException | InvocationTargetException e) {
            Alert.showError(e.toString().split("\n"));
        }
    }
}