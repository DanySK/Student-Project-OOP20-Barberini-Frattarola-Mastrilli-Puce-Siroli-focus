package oop.focus.calendar;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class CalendarMonthView {

    private final Map<Button, CalendarDaysLogicImpl> cells  = new HashMap<>();
    private Label monthinfo;
    private List<DayImpl> month;
    private final double width;
    private final double height;
    private static final int FONTSIZE = 20;
    private static final int BORDER = 20;


    public CalendarMonthView(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Build the calendar month.
     * @return panel
     */
    public VBox buildMonthView() {

        final CalendarMonthLogic logics = new CalendarMonthLogicImpl();
        final CalendarLogicImpl calendarlogic = new CalendarLogicImpl();
        month = calendarlogic.getMonth();


        final VBox container = new VBox();
        container.minHeight(height);
        container.minWidth(width);

        container.setAlignment(Pos.CENTER);
        final HBox toppanel = new HBox();

        final Button next = new Button("next");
        final Button previous = new Button("previous");


        next.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                calendarlogic.changeMonth(false);
                month = calendarlogic.getMonth();
                updateView(container, logics, monthinfo);
            }

        });

        previous.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                calendarlogic.changeMonth(true);
                month = calendarlogic.getMonth();
                updateView(container, logics, monthinfo);
            }

        });


        monthinfo = new Label(month.get(0).getYear() + "   " + month.get(0).getMonth());
        monthinfo.setFont(Font.font(FONTSIZE));
        monthinfo.setAlignment(Pos.CENTER);

        toppanel.getChildren().add(previous);
        toppanel.getChildren().add(monthinfo);
        toppanel.getChildren().add(next);
        toppanel.setAlignment(Pos.CENTER);
        toppanel.setSpacing(BORDER);

        container.getChildren().add(toppanel);

        container.getChildren().add(logics.buildMonth(month, cells));

        toppanel.setPrefWidth(container.getWidth());

        container.setPadding(new Insets(BORDER, BORDER, BORDER, BORDER));

        container.autosize();


        return container;

    }

    private void updateView(final VBox container, final CalendarMonthLogic logics, final Label monthname) {
        container.getChildren().remove(container.getChildren().size() - 1);
        container.getChildren().add(logics.buildMonth(month, cells));
        monthname.setText(month.get(0).getMonth() + "   " + month.get(0).getYear());
    }



}
