package oop.focus.calendar.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import oop.focus.calendar.model.Format; 



public class HoursViewImpl implements HoursView, VBoxManager {


    //View
    private VBox myvbox;

    //Variables
    private int hoursformat;
    private boolean flag = true;
    private double spacing;




    public HoursViewImpl() {
        this.hoursformat = Format.NORMAL.getNumber();
    }


    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }


    public final double getSpacing() {
        return this.spacing;
    }



    public final void setFormat(final Format format) {
        hoursformat = format.getNumber();
    }


    public final int getFormat() {
        return hoursformat;
    }


    public final double getY(final int hour) {
        if (this.hoursformat == Format.NORMAL.getNumber()) {
            return this.myvbox.getChildren().get(hour).getLayoutY();
        } else {
            return this.myvbox.getChildren().get(hour * 2).getLayoutY();
        }
    }


    public final VBox getVBox() {
        if (this.myvbox == null) {
            buildVBox();
        }
        return this.myvbox;
    }

    /**
     * Used for configure the hours label.
     * @param label to configure
     * @param vbox where put the label
     * @param i index
     */
    private void buildLabel(final Label label, final VBox vbox, final int i) {
        label.setLayoutY(this.spacing / 2 + label.fontProperty().get().getSize() / 2 + this.spacing * i);
        label.setPrefHeight(spacing);
        label.setTextAlignment(TextAlignment.CENTER);
        label.alignmentProperty().set(Pos.CENTER);
        label.prefWidthProperty().bind(vbox.prefWidthProperty());
        vbox.getChildren().add(label);
    }


    public final void buildVBox() {
        final VBox vbox = new VBox();

        if (this.hoursformat == Format.NORMAL.getNumber()) {
            for (int i = 0; i <= hoursformat; i++) { 
                    final Label label = new Label(i + ":00");
                    buildLabel(label, vbox, i);
            }
        } else {
            for (int i = 0; i <= hoursformat; i++) { 
                if (flag) {
                    final Label label = new Label(i / 2 + ":00");
                    buildLabel(label, vbox, i);
                    flag = false;
                } else {
                    final Label label = new Label(i / 2 + ":30");
                    buildLabel(label, vbox, i);
                    flag = true;
                }
            } 
        }
        this.myvbox = vbox;
    }


}
