package oop.focus.application;

import javafx.util.Pair;
import oop.focus.common.Controller;

import java.util.List;

public interface Sections {
    Controller getFirstWindow();
    List<Pair<Controller, String>> getList();
}
