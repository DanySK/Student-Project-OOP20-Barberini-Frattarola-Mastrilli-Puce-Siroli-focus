package oop.focus.diary.model;

import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimerTest {
    private final EventManager me = new EventManagerImpl(new DataSourceImpl());
    private final CounterManager timer = new CounterManagerImpl(this.me, true);
    private final TotalTimeEvent csc = new TotalTimeEventImpl(this.me);
    @Test
    public void TimerTest() throws InterruptedException {
        final String str = "test1";
        this.timer.createCounter(str);
        //il timer relativo all'attività è settata a 5 sec
        this.timer.setStarterValue(5);
        this.timer.startCounter();
        Thread.sleep(5000);
        assertEquals(this.csc.computePeriod(str).get().getSeconds(), 5);
        this.me.removeAll();

    }

}
