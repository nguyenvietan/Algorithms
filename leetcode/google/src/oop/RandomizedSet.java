package oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Solution: HashSet + ArrayList
 */
class RandomizedSet {

    private HashMap<Integer, Integer> map;
    private List<Integer> array;
    private Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        array = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        array.add(val);
        map.put(val, array.size()-1);
//        System.out.printf("map added: (%d, %d)\n", val, array.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int n = array.size();
        int idx = map.get(val);
        if (idx == n-1) {
            map.remove(val);
            array.remove(idx);
        } else {
            array.set(idx, array.get(n-1));
            array.remove(n-1); // remove the last element
            map.put(array.get(idx), idx);
        }

//        System.out.printf("map removed: %d\n", val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int idx = rand.nextInt(array.size());
        return array.get(idx);
    }

    public static void main(String[] args) {
        RandomizedSet randSet = new RandomizedSet();
        System.out.println(randSet.insert(0));
        System.out.println(randSet.remove(0));

        System.out.println(randSet.remove(0));
        System.out.println(randSet.insert(0));

        System.out.println(randSet.getRandom());
    }
}
