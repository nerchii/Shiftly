import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Worker implements Serializable {
    private String name;
    private String reminders;
    private List<Shift> shifts;

    public Worker(String name, String reminders, List<Shift> shifts){
        this.name= name;
        this.reminders = reminders;
        this.shifts = shifts;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReminders(String reminders) {
        this.reminders = reminders;
    }

    public String getName() {
        return name;
    }

    public String getReminders() {
        return reminders;
    }

    public void addShifts(List<Shift> shifts) {
        this.shifts.addAll(shifts);
    }
}
