
public class SimpleNameMap {

    Entry[] entries;

    public SimpleNameMap() {
        entries = new Entry[26];
    }

    /** Returns true if the map contains the KEY. */
    boolean containsKey (String key){
        if (isValidName(key)) {
            if (entries[key.hashCode()].equals(key))
                return true;
        }
        return false;
    }
    /** Returns the value for the specified KEY. */
    String get (String key){
        if (isValidName(key)) {
            return entries[key.hashCode()]._value;
        }
    }
    /** Put a (KEY, VALUE) pair into this map. */
    void put(String key, String value) {
        if (isValidName(key)) {
            entries[key.hashCode()] = new Entry(key,value);
        }
    }

    /**
     * Remove a single entry, KEY, from this table and returns true if successful.
     */
    String remove(String key) {
        if (isValidName(key)) {
            entries[key.hashCode()] = null;
            return "true";
        }
        return "false";
    }

    int hash(String key) {
        return (int) (key.charAt(0) - 'A');
    }

    /** A wrapper class for holding each (KEY, VALUE) pair. */
    private static class Entry {
        /** The key used for lookup. */
        private String _key;
        /** The associated value. */
        private String _value;
        private Entry _next;

        /** Create a new (KEY, VALUE) pair. */
        public Entry(String key, String value) {
            _key = key;
            _value = value;
            _next = null;
        }

        /** Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return _key.equals(other._key);
        }

        /** Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry &&
                    _key.equals(((Entry) other)._key) &&
                    _value.equals(((Entry) other)._value));
        }

    }

    /** Returns true if the given KEY is a valid name that starts with A-Z. */
    private static boolean isValidName(String key) {
        return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
    }

}