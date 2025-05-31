import java.io.Serializable;
import java.util.ArrayList;

public class Worker implements Serializable {
    private String name;
    private String reminders;
    private ArrayList<String> daysWorking;
    private ArrayList<String> shift;
    private boolean fullTime;

    public Worker(String name, String reminders, ArrayList<String> daysWorking, ArrayList<String> shift, boolean fullTime){
        this.name= name;
        this.reminders = reminders;
        this.daysWorking = daysWorking;
        this.shift = shift;
        this.fullTime = fullTime;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", reminders='" + reminders + '\'' +
                ", daysWorking=" + daysWorking +
                ", shift=" + shift +
                ", fullTime=" + fullTime +
                '}';
    }
}
