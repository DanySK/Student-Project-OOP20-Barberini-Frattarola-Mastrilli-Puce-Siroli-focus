package oop.focus.homepage.model;

import java.util.Set;

public interface ManagerDegreeOfKinship {

    void add(String degree);

    Set<String> getAll();

    void remove(String degree);

}
