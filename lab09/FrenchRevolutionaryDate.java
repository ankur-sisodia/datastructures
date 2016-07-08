public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }

    @Override
    public FrenchRevolutionaryDate nextDate() {
        int tempMonth = month();
        int tempDay = dayOfMonth();
        int tempYear = year();

        if (tempMonth == 13 && tempDay == 5) {
            return new FrenchRevolutionaryDate(tempYear + 1, 1, 1);
        } else if (tempDay == 30) {
            return new FrenchRevolutionaryDate(tempYear, tempMonth + 1, 1);
        } else {
            return new FrenchRevolutionaryDate(tempYear, tempMonth, tempDay + 1);
        }

    }

}
