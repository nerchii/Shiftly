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
}
