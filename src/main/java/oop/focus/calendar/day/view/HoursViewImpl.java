package oop.focus.calendar.day.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import oop.focus.calendar.model.Format; 


/**
 * Implementation of {@link HoursView}.
 */
public class HoursViewImpl implements HoursView {


    //View
    private VBox myVBox;

    //Variables
    private int hoursFormat;
    private boolean flag = true;
    private double spacing;



    /**
     * Used for Initialize Hours view.
     */
    public HoursViewImpl() {
        this.hoursFormat = Format.NORMAL.getNumber();
    }

    /**
     * {@inheritDoc}
     */
    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }

    /**
     * {@inheritDoc}
     */
    public final double getSpacing() {
        return this.spacing;
    }

    /**
     * {@inheritDoc}
     */
    public final void setFormat(final Format format) {
        this.hoursFormat = format.getNumber();
    }

    /**
     * {@inheritDoc}
     */
    public final int getFormat() {
        return this.hoursFormat;
    }

    /**
     * {@inheritDoc}
     */
    public final double getY(final int hour) {
        if (this.hoursFormat == Format.NORMAL.getNumber()) {
            return this.myVBox.getChildren().get(hour).getLayoutY();
        } else {
            return this.myVBox.getChildren().get(hour * 2).getLayoutY();
        }
    }

    /**
     * {@inheritDoc}
     */
    public final VBox getVBox() {
        if (this.myVBox == null) {
            this.buildVBox();
        }
        return this.myVBox;
    }

    /**
     * Used for configure the hours label.
     * @param label to configure
     * @param vBox where put the label
     * @param i index
     */
    private void buildLabel(final Label label, final VBox vBox, final int i) {
        label.setLayoutY(this.spacing / 2 + label.fontProperty().get().getSize() / 2 + this.spacing * i);
        label.setPrefHeight(this.spacing);
        label.setTextAlignment(TextAlignment.CENTER);
        label.alignmentProperty().set(Pos.CENTER);
        label.prefWidthProperty().bind(vBox.prefWidthProperty());
        vBox.getChildren().add(label);
    }

    /**
     * {@inheritDoc}
     */
    public final void buildVBox() {
        final VBox vBox = new VBox();

        if (this.hoursFormat == Format.NORMAL.getNumber()) {
            for (int i = 0; i <= this.hoursFormat; i++) {
                    final Label label = new Label(i + ":00");
                    this.buildLabel(label, vBox, i);
            }
        } else {
            for (int i = 0; i <= this.hoursFormat; i++) {
                if (this.flag) {
                    final Label label = new Label(i / 2 + ":00");
                    this.buildLabel(label, vBox, i);
                    this.flag = false;
                } else {
                    final Label label = new Label(i / 2 + ":30");
                    this.buildLabel(label, vBox, i);
                    this.flag = true;
                }
            } 
        }
        this.myVBox = vBox;
    }


}
