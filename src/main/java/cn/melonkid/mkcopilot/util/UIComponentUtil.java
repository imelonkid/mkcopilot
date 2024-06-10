package cn.melonkid.mkcopilot.util;

import java.awt.*;
import java.util.Arrays;

public class UIComponentUtil {

    public static Component findComponentByFieldName(Container container, String fieldName) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component.getName() != null && component.getName().equals(fieldName)) {
                return component;
            }

            if (component instanceof Container) {
                Component foundComponent = findComponentByFieldName((Container) component, fieldName);
                if (foundComponent != null) {
                    return foundComponent;
                }
            }
        }
        return null;
    }
}