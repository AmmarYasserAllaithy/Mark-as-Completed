package app.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static app.registry.Settings.APP_NAME;

public class Alert {
    private static final String
            FONT_FAMILY = "Consolas",
            FOOTER = "Developed with ♥ by: Ammar Yasser AllaiThy",
            COPYRIGHTS = "© All rights reserved";

    private enum params {_DEFAULT}

    public static void showInfo(String... messages) {
        final ArrayList<JComponent> componentList = new ArrayList<>();
        for (String msg : messages) componentList.add(createLabel(msg));

        JSeparator sep = new JSeparator();
        sep.setForeground(Color.GRAY);

        componentList.add(sep);
        componentList.add(createLabel(FOOTER, params._DEFAULT, 15, Color.GRAY));
        componentList.add(createLabel(COPYRIGHTS, params._DEFAULT, 15, Color.GRAY));

        showMessage(JOptionPane.INFORMATION_MESSAGE, componentList);
    }

    public static void showError(String... messages) {
        final ArrayList<JComponent> componentList = new ArrayList<>();

        for (String msg : messages)
            componentList.add(createLabel(msg, params._DEFAULT, params._DEFAULT, new Color(200, 50, 50)));

        showMessage(JOptionPane.ERROR_MESSAGE, componentList);
    }

    private static void showMessage(int type, ArrayList<JComponent> componentList) {
        final int len = componentList.size();
        final JPanel panel = new JPanel(new GridLayout(len, 1, 0, 10));

        componentList.forEach(panel::add);

        JOptionPane.showMessageDialog(null, panel, APP_NAME, type);
    }

    private static JLabel createLabel(String text, Object... args) {
        int style = Font.PLAIN;
        int size = 17;
        Color color = null;

        switch (args.length) {
            case 3:
                color = args[2] == params._DEFAULT ? color : (Color) args[2];
            case 2:
                size = args[1] == params._DEFAULT ? size : (int) args[1];
            case 1:
                style = args[0] == params._DEFAULT ? style : (int) args[0];
            default:
                break;
        }

        return createLabel(text, style, size, color);
    }

    private static JLabel createLabel(String text, int style, int size, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_FAMILY, style, size));
        if (color != null) label.setForeground(color);
        return label;
    }
}