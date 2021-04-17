package oop.focus.homepage;

import oop.focus.calendar.persons.controller.PersonLauncher;
import oop.focus.calendar.week.controller.NewEventLauncher;
import oop.focus.calendar.week.controller.WeekLauncher;
import oop.focus.homepage.controller.HomePageLauncher;
import oop.focus.event.controller.EventLauncher;

public class App{
	 private App() {
	 }
	     public static void main(final String... args) {

	         //PersonLauncher.main(args);
			 //HomePageLauncher.main(args);
	         //WeekLauncher.main(args);
			 //EventLauncher.main(args);
			 NewEventLauncher.main(args);
	     }

}
