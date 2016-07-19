/**
 * Created by asisodia on 7/19/2016.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;

/**
 *  A simple mapping from string names to string values backed by an array.
 *  Supports only A-Z for the first character of the key name. Values can be
 *  any valid string.
 *
 *  @author asisodia
 */


public class HashMap<K, V> implements Map61BL<K, V>
{
    private Object[] entries;
    private float lf;
    private int arrSize;
    private int setSize = 1;

    public HashMap()
    {
        entries = new Object[setSize];
        this.lf = 1.0f;
        //arrSize = 0;
    }

    public HashMap(int initialSize)
    {
        entries = new Object[initialSize];
        this.lf = 1.0f;
        this.setSize = initialSize;
    }

    public HashMap(int setSize, float lf)
    {
        if (lf <= 0.1f)
            throw new IllegalArgumentException("Load Factor must be greater than 0.1");

        entries = new Object[setSize];
        this.lf = lf;
        this.setSize = setSize;

    }

    /** Return the capacity of this hash table's internal array. */
    int capacity()
    {
        return ((int) lf); //IS THIS RIGHT???
    }

    public int hash(K key, Object[] entries)
    {
        //if (isValidName(key))
        //{
        return ((key.hashCode()) % entries.length); //(key.charAt(0) - 'A')
        //return ((key.hashCode() & 0x7FFFFFFF) % entries.length);
        //}
        //return 0;
    }

    public void clear()
    {
        entries = new Object[setSize];
    }

    @Override
    Iterator <K> iterator()
    {
        throw new UnsupportedOperationException();
    }

    /** Returns true if the map contains the KEY. */
    public boolean containsKey(K key)
    {
        return get(key) != null; // (entries[hash(key)] != null);
    }

    /** Returns the value for the specified KEY. */
    public V get(K key)
    {
        //Entry cur = entries[hash(key)];

        //null key case
        int pos = hash(key, entries);

        if (entries[pos] == null)
        {
            return null;
        }
        else
        {
            ArrayList<Entry> a;
            a = ((ArrayList<Entry>) entries[pos]);

            int position = a.indexOf(new Entry(key, null));

            if (position == -1)
            {
                return null;
            }
            Entry e = a.get(position);
            return e._value;
        }

//        while (cur != null)
//        {
//            if (this.containsKey(key))
//            {
//                return cur._value;
//            }
//            cur = cur._next;
//        }
//
//        return entries[hash(key)]._value;
    }

    public int size()
    {
        return arrSize;
    }

    /** Put a (KEY, VALUE) pair into this map. */
    public void put(K key, V value)
    {
        int len = entries.length;
        if (((float)(arrSize + 1) / len) > lf)
        {
            resize();
        }

        Entry curr = new Entry(key, value);
        int pos = hash(key, entries);

        ArrayList<Entry> a;
        if (entries[pos] == null)
        {
            a = new ArrayList<Entry>();
            a.add(curr);
            arrSize++;
            entries[pos] = a;
        }
        else
        {
            a = ((ArrayList<Entry>) entries[pos]);

            if (a.contains(curr) == false) {
                a.add(curr);
                arrSize++;
            }
        }

//        if (cur == null)
//        {
//            cur = new Entry(key, value);
//            size++;
//        }
//        else
//        {
//            while (cur != null)
//            {
//                if (cur._next == null)
//                {
//                    cur._next = new Entry(key, value);
//                    size++;
//                }
//                cur = cur._next;
//            }
//        }
        //IMPLEMENT RESIZE METHOD AT BEGINNING IN WHICH CASE USE THE NEW ENTRY ARRAY I MADE (e)
        // ALSO CONVERT ALL THIS SHIT TO GENERICS FOR THE HASHMAP

        //Entry n = new Entry(key, value);
        //entries[hash(key)] = n;

    }

    public void resize()
    {
        int resized = ((arrSize + 1) * 2) / ((int) lf);
        arrSize = 0;

        Object[] replaced = new Object[resized];
        for (Object o: entries)
        {
            if (o != null)
            {
                ArrayList<Entry> a = ((ArrayList<Entry>) o);
                for (Entry x : a)
                {
                    int pos = hash(x._key, replaced);
                    ArrayList<Entry> b;
                    if (replaced[pos] == null){

                        b = new ArrayList<Entry>();
                        b.add(x);
                        arrSize++;
                        replaced[pos] = b;
                    }
                    else
                    {
                        b = ((ArrayList<Entry>) replaced[pos]);
                        if (b.contains(x) == false)
                        {
                            b.add(x);
                            arrSize++;
                        }
                    }
                }
            }
        }
        entries = replaced;
//        if (size > entries.length)
//        {
//            Entry[] e = new Entry[s*2];
//            s = s*2;
//            e = entries;
//        }
    }

    /** Remove a single entry, KEY, from this table and returns true if successful. */
    public V remove(K key)
    {

        int pos = hash(key, entries);
        Object curr = entries[pos];

        if (curr != null)
        {
            ArrayList<Entry> a;
            a = ((ArrayList<Entry>) entries[pos]);

            int position = a.indexOf(new Entry(key, null));

            if (position == -1)
            {
                return null;
            }

            Entry e = a.remove(position);
            arrSize--;
            return e._value;
        }
        else
        {
            return null;
        }

//        if (entries[hash(key)] != null)
//        {
//            while (cur._next != null)
//            {
//                if (cur._key.equals(key))
//                {
//                    entries[hash(key)] = entries[hash(key)]._next;
//                    size--;
//                }
//                cur = cur._next;
//            }
//        }
//        return cur._key;

    }

    public boolean remove(K key, V value)
    {
        int pos = hash(key, entries);

        if (entries[pos] == null)
        {
            return false;
        }
        else
        {
            ArrayList<Entry> a = ((ArrayList<Entry>) entries[pos]);

            int position = a.indexOf(new Entry(key, null));

            if (position == -1)
            {
                return false;
            }

            Entry x = a.get(position);

            if (x._value == value)
            {
                x = a.remove(position);

                arrSize--;

                if (a.size() == 0)
                {
                    entries[pos] = null;
                }
                return true;
            }

            return false;
        }
    }


    /** A wrapper class for holding each (KEY, VALUE) pair. */
    private class Entry
    {

        /** The key used for lookup. */
        public K _key;
        /** The associated value. */
        public V _value;

        public Entry _next;


        /** Create a new (KEY, VALUE) pair. */
        public Entry(K key, V value)
        {
            _key = key;
            _value = value;
            _next = null;
        }

        /** Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other)
        {
            return _key.equals(other._key);
        }

        /** Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other)
        {
            return (other instanceof Object &&
                    _key.equals(((Entry) other)._key) &&
                    _value.equals(((Entry) other)._value));
        }

    }

    /** Returns true if the given KEY is a valid name that starts with A-Z. */
//    private static boolean isValidName(T1 key)
//    {
//        return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
//    }

}