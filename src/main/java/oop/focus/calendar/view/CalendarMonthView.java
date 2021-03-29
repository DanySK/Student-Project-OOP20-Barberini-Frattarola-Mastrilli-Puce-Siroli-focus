package oop.focus.calendar.view;


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
import oop.focus.calendar.model.CalendarLogicImpl;
import oop.focus.calendar.model.DayImpl;


public class CalendarMonthView {

    private final Map<Button, CalendarDaysViewImpl> cells  = new HashMap<>();
    private List<DayImpl> month;
    private final double width;
    private final double height;
    private static final int BORDER = 20;
    private static final int FONTSIZE = 12;
    private final CalendarLogicImpl calendarlogic = new CalendarLogicImpl();
    private final CalendarMonthLogic logics = new CalendarMonthLogicImpl();
    private final Label monthinfo = new Label();

    private VBox monthview;

    public CalendarMonthView(final double width, final double height) {
        this.width = width;
        this.height = height;
        monthview = new VBox();
    }

    /**
     * Build the calendar month.
     * @return panel
     */
    public VBox buildMonthView() {



        month = calendarlogic.getMonth();


        final VBox container = new VBox();
        container.minHeight(height);
        container.minWidth(width);

        container.setAlignment(Pos.CENTER);


        container.getChildren().add(buildTopPanel(container));

        container.getChildren().add(logics.buildGridMonth(month, cells));

        container.setPadding(new Insets(BORDER, BORDER, BORDER, BORDER));

        container.autosize();

        setMonthView(container);
        return container;

    }


    private void updateView(final VBox container, final CalendarMonthLogic logics) {
        container.getChildren().remove(container.getChildren().size() - 1);
        container.getChildren().add(logics.buildGridMonth(month, cells));
        setMonthInfo(month.get(0).getMonth() + "   " + month.get(0).getYear());
    }


    private void setMonthView(final VBox month) {
        monthview = month;
    }

    /**
     * 
     * @return monthview
     */
    public VBox getMonthView() {
        return monthview;
    }

    /**
     * @param container
     * @param flag
     * @return next
     */
    private EventHandler<ActionEvent> buildButton(final VBox container, final Boolean flag) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                calendarlogic.changeMonth(flag);
                month = calendarlogic.getMonth();
                updateView(container, logics);
            }

        };
    }

    /**
     * 

     * @param container
     * @param previous
     * @param next
     * @return HBox
     */
    private HBox buildTopPanel(final VBox container) {
        final HBox toppanel = new HBox();
        this.monthinfo.setText(month.get(0).getYear() + "   " + month.get(0).getMonth());
        this.monthinfo.setFont(Font.font(FONTSIZE));
        this.monthinfo.setAlignment(Pos.CENTER);

        final Button next = new Button("next");
        final Button previous = new Button("previous");
        next.setOnAction(buildButton(container, false));
        previous.setOnAction(buildButton(container, true));

        toppanel.getChildren().add(previous);
        toppanel.getChildren().add(monthinfo);
        toppanel.getChildren().add(next);
        toppanel.setAlignment(Pos.CENTER);
        toppanel.setPrefWidth(container.getWidth());
        toppanel.setSpacing(BORDER);
        return toppanel;
    }

    /**
     * @param string    Month name and the years of the Month
     */
    private void setMonthInfo(final String string) {
        this.monthinfo.setText(string);
    }



}
