package oop.focus.calendar;

//import javafx.scene.control.Label;
import javafx.scene.layout.VBox; 



public class HoursViewImpl implements HoursView {

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

    public HoursViewImpl() {
        this.hoursformat = Format.NORMAL.getNumber();
    }


    /**
     * @param format of the time hours or half
     * 
     */
    public void setSpacing(final Format format) {
        hoursformat = format.getNumber();
    }

    /**
     * @return  the position of the label
     * @param hour qualcosa part 2
     */
    public double getY(final int hour) {
        //System.out.println(this.myvbox.getChildren().get(hour).boundsInLocalProperty());
        return this.myvbox.getChildren().get(hour).getLayoutY();
    }

    /**
     *@param vbox set VBox hours.
     */
    public void setVBox(final VBox vbox) {
        this.myvbox = vbox;
    }

     /**
     * @return get the hours box.
     */
    public VBox getVBox() {
        if (this.myvbox == null) {
            buildVBox();
        }
        return this.myvbox;
    }

    private void buildVBox() {

    }


}
