package oop.focus.diary.model;
import oop.focus.finance.Repetition;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.ManagerEvent;
import org.joda.time.LocalDateTime;
public class TimerListenerImpl implements TimerListener {
    private LocalDateTime start;
    private final String eventName;
    private final ManagerEvent me;
    public TimerListenerImpl(final ManagerEvent me, final String eventName) {
        this.me = me;
        this.eventName = eventName;
        } 
    @Override
    public final void createEvent() {
        me.addEvent(new EventImpl(this.eventName, this.start, LocalDateTime.now(), Repetition.ONCE));
        }
    @Override
    public final void startCounter() {
        this.start = LocalDateTime.now();
        }
    }
