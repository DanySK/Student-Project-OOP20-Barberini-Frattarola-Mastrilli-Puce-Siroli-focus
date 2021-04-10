package oop.focus.diary.view;


import javafx.scene.Node;
import oop.focus.diary.controller.UseDiaryControllers;

public enum DiarySections {
    /**
     *
     */
    DIARY("Diario", "", new BaseDiary(UseDiaryControllers.getToDoListController(),
            UseDiaryControllers.getDiaryPages(), UseDiaryControllers.getDailyMoodController()).getRoot()),
    /**
     *
     */
    STOPWATCH("Cronometro", "", new StopwatchView(UseDiaryControllers.getTotalTimeController(),
            UseDiaryControllers.getCounterControllerStop()).getRoot()),
    /**
     *
     */
    TIMER("Timer", "", new TimerView(UseDiaryControllers.getTotalTimeController(),
            UseDiaryControllers.getCounterControllerTimer()).getRoot());
    private final String name;
    private final String style;
    private final Node view;
    DiarySections(final String name, final String style, final Node view) {
        this.name = name;
        this.style = style;
        this.view = view;
    }
    public String getName() {
        return this.name;
    }

    public String getStyle() {
        return this.style;
    }

    public Node getView() {
        return this.view;
    }

}


