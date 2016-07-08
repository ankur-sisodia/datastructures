public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }

    @Override
    public GregorianDate nextDate() {
        int tempMonth = month();
        int tempDay = dayOfMonth();
        int tempYear = year();
        if (tempMonth == 12 && tempDay == 31){
            return new GregorianDate(tempYear + 1, 1, 1);
        } else if (monthLengths[tempMonth] == tempDay) {
            return new GregorianDate(tempYear, tempMonth + 1, 1);
        } else {
            return new GregorianDate(tempYear, tempMonth, tempDay);
        }
    }

}
