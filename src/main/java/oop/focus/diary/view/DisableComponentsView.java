package oop.focus.diary.view;

import oop.focus.common.View;

/**
 * The interface Disable Components View defines method to set a node as enable or disabled.
 */
public interface DisableComponentsView extends View {
    /**
     * Sets the disabled of a View's component.
     * @param disable   true if the component must be disabled, false otherwise
     */
    void disableComponent(boolean disable);
}
