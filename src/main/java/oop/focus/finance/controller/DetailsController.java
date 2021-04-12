package oop.focus.finance.controller;

import oop.focus.common.Controller;

public interface DetailsController<X> extends Controller {

    /**
     * @return the element whose details we want to see
     */
    X getElement();

    /**
     * @param element to be deleted
     */
    void deleteElement(X element);
}
