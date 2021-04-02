package oop.focus.calendar.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import oop.focus.calendar.controller.CalendarMonthController;




public class CalendarMonthView {


    private final double width;
    private final double height;
    private static final int BORDER = 20;
    private static final int FONTSIZE = 12;

    private final CalendarMonthController monthcontroller;

    private final Label monthinfo = new Label();

    private VBox monthview;

    public CalendarMonthView(final double width, final double height, final CalendarMonthController monthcontroller) {
        this.monthcontroller = monthcontroller;
        this.width = width;
        this.height = height;
        monthview = new VBox();
    }

    /**
     * Build the calendar month.
     * @return panel
     */
    private VBox buildMonthView() {



        final VBox container = new VBox();
        container.prefHeight(height);
        container.prefWidth(width);

        container.setAlignment(Pos.CENTER);


        container.getChildren().add(buildTopPanel(container));

        container.getChildren().add(monthcontroller.buildGridMonth());

        container.setPadding(new Insets(BORDER, BORDER, BORDER, BORDER));

        container.autosize();

        setMonthView(container);
        return container;

    }

    public final CalendarMonthController getController() {
        return this.monthcontroller;
    }


    public final Label getMonthInfo() {
        return this.monthinfo;
    }



    /**
     * 
     * monthview.
     * @param month
     */
    public void setMonthView(final VBox month) {
        monthview = month;
    }

    /**
     * @return monthview
     */
    public VBox getMonthView() {
        if (this.monthview.getChildren().size() == 0) {
            buildMonthView();
        }
        return monthview;
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
        this.monthinfo.setText(monthcontroller.getMonth().get(0).getYear() + "   " + monthcontroller.getMonth().get(0).getMonth());
        this.monthinfo.setFont(Font.font(FONTSIZE));
        this.monthinfo.setAlignment(Pos.CENTER);

        final Button next = new Button("next");
        final Button previous = new Button("previous");
        next.setOnAction(monthcontroller.changeMonthButton(this, false, monthcontroller));
        previous.setOnAction(monthcontroller.changeMonthButton(this, true, monthcontroller));

        toppanel.getChildren().add(previous);
        toppanel.getChildren().add(monthinfo);
        toppanel.getChildren().add(next);
        toppanel.setAlignment(Pos.CENTER);
        toppanel.prefWidthProperty().bind(container.widthProperty());
        toppanel.setSpacing(BORDER);
        return toppanel;
    }



}
