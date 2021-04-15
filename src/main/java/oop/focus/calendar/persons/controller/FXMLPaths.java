package oop.focus.calendar.persons.controller;


public enum FXMLPaths {

    /**
     * 
     */
    PERSONS(Constants.PERSONS + "view.fxml"),
    /**
     *
     */
    ADDNEWPERSON(Constants.PERSONS + "addNewPerson.fxml"),
    /**
     *
     */
    RELATIONSHIPS(Constants.PERSONS + "relationship.fxml"),
    /**
     *
     */
    ADDNEWRELATIONSHIP(Constants.PERSONS + "addNewRelationship.fxml");

    private final String path;

    FXMLPaths(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Constants {
        public static final String PERSONS = "/layouts/persons/";
    }
}
