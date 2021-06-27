package solution;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeMap{
    private Map<String, TreeMap<Integer,String>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap();
    }

    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)){
            map.put(key, new TreeMap());
        }

        TreeMap<Integer,String> timeMap = map.get(key);
        timeMap.put(timestamp,value);

    }

    public String get(String key, int timestamp) {
        TreeMap<Integer,String> timeMap = map.get(key);

        if(timeMap != null){
            Integer prevTime = timeMap.floorKey(timestamp);
            return (prevTime == null) ? "" : timeMap.get(prevTime);
        }

        return "";
    }

}
