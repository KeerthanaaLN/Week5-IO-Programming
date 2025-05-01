package jsonexample;

import org.json.JSONObject;

public class MergeJSONObjects {
    public static void main(String[] args) {
        JSONObject obj1 = new JSONObject("{\"name\":\"Alice\"}");
        JSONObject obj2 = new JSONObject("{\"email\":\"alice@example.com\"}");
        for (String key : obj2.keySet()) {
            obj1.put(key, obj2.get(key));
        }
        System.out.println(obj1);
    }
}
