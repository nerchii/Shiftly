import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Worker implements Serializable {
    private String name;
    private String reminders;
    private List<Shift> shifts;
    private String task;

    public Worker(String name, String reminders, List<Shift> shifts){
        this.name= name;
        this.reminders = reminders;
        this.shifts = shifts;
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
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
        if(reminders.isEmpty()){
            return "No reminders";
        }
        return reminders;
    }

    public void addShifts(List<Shift> shifts) {
        this.shifts.addAll(shifts);
    }

    @Override
    public String toString() {
        return "\n Worker{" +
                "name='" + name + '\'' +
                ", reminders='" + reminders + '\'' +
                ", shifts=" + shifts +
                ", task='" + task + '\'' +
                '}';
    }
}
