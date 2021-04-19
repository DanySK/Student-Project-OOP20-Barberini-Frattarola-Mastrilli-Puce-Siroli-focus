package oop.focus.common;
/**
 * The interface Action that defines any kind of action.
 */
@FunctionalInterface
public interface Action {
    /**
     * Execute the action.
     *
     * @throws Exception if the action fails
     */
    void execute() throws Exception;
}
