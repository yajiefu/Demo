package Interview.study.exer;

/**
 * @author yajie.fu
 * @create 2019-06-28 21:54
 */
public class MyDate implements Comparable {
    private int year;
    private int month;
    private int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof  MyDate){
            MyDate myDate = (MyDate) o;
            int minusYear =this.year - myDate.getYear();
            if (minusYear != 0){
                return minusYear;
            }
            int minusMonth = this.month - myDate.getMonth();
            if (minusMonth != 0){
                return minusMonth;
            }
            return this.month - myDate.getDay();
        }
        throw new RuntimeException("输入的类型不匹配");

    }
}
