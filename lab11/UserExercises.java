import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserExercises extends DBTable<User> {
    UserExercises() {
    }

    UserExercises(Collection<User> lst) {
        super(lst);
    }

    /**
     * Get an ordered List of Users, sorted first on age,
     * then on their id if the age is the same.
     */
    public List<User> getOrderedByAgeThenId() {
        return getEntries().stream()
                .sorted((t1,t2)->
                        (t1.getAge() == t2.getAge()) ?
                                Integer.compare(t1.getId(),t2.getId()) :
                                Integer.compare(t1.getAge(),t2.getAge()))
                .collect(Collectors.toList());
    }

    /**
     * Get the average age of all the users.
     * If there are no users, the average is 0.
     */
    public double getAverageAge() {
        if (getEntries().size() == 0) {
            return 0;
        }
        double sum = getEntries().stream().mapToDouble(s -> s.getAge()).sum();
        double divisor = (double) getEntries().size();
        return sum / divisor;
        // FIX ME
    }

    /**
     * Group usernames by user age, for all users that have an age greater than min_age.
     * Usernames with ages less than or equal to min_age are excluded.
     * Returns a Map from each age present to a list of the usernames that have that age.
     */
    public Map<Integer, List<String>> groupUsernamesByAgeOlderThan(int min_age) {
        return getEntries().stream()
                .filter(t1 -> t1.getAge() > min_age)
                .collect(Collectors.groupingBy(t1 -> t1.getAge(),
                        Collectors.mapping(t1 -> t1.getUsername(), Collectors.toList())));
    }
}
