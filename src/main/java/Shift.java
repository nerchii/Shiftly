public class Shift {
    private int day; // 0 = Monday...
    private int time; // 0 = Morning, 1 = Afternoon, 2 = Evening

    public Shift(int time, int day) {
        this.time = time;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] times = {"8:00 - 12:30", "12:30 - 17:00", "17:00 - 22:00"};

        return "Day: " + days[day] + ", Time: " + times[time];
    }
}
