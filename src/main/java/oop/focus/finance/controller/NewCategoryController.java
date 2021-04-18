package oop.focus.finance.controller;

import oop.focus.common.Controller;

/**
 * Implementation of a controller interface that takes care of creating a new category.
 */
public interface NewCategoryController extends Controller {

    /**
     * Save the category in the database.
     *
     * @param name of the category to save
     * @param color of the category to save
     */
    void newCategory(String name, String color);
}
