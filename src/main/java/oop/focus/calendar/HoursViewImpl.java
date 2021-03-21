package oop.focus.calendar;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment; 



public class HoursViewImpl implements HoursView, VBoxManager {

    public enum Format {

        /**
         * Normal format day.
         */
        NORMAL(24),

        /**
         * Extended format day.
         */
        EXTENDED(48);

        private int number;

        Format(final int i) {
            number = i;
        }

        int getNumber() {
            return number;
        }


    }

    private int hoursformat;
    private VBox myvbox;
    private boolean flag = true;
    private double spacing;

    public HoursViewImpl() {
        this.hoursformat = Format.NORMAL.getNumber();
    }

    /**
     * @param spacing between two number
     * 
     */
    public void setSpacing(final double spacing) {
        this.spacing = spacing;
    }

    /**
     * @return spacing between two number 
     */
    public double getSpacing() {
        return this.spacing;
    }


    /**
     * @param format of the time hours or half
     * 
     */
    public void setFormat(final Format format) {
        hoursformat = format.getNumber();
    }

    /**
     * @return format of the time hours or half
     * 
     */
    public int getFormat() {
        return hoursformat;
    }

    /**
     * @param hour  Index of the hour
     * @return position of the object in the VBox
     */
    public double getY(final int hour) {
        if (this.hoursformat == Format.NORMAL.getNumber()) {
            return this.myvbox.getChildren().get(hour).getLayoutY();
        } else {
            return this.myvbox.getChildren().get(hour * 2).getLayoutY();
        }
    }

     /**
     * @return the hours vbox.
     */
    public VBox getVBox() {
        if (this.myvbox == null) {
            buildVBox();
        }
        return this.myvbox;
    }

    /**
     * Used for configure the hours label.
     * @param label to configure
     * @param vbox place where put the label
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

    /**
     * build the vbox.
     */
    public void buildVBox() {
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
