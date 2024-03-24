package co.setu.splitwise.util;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Util {
    public static String randomString() {
        return UUID.randomUUID().toString();
    }
    public static int randomId(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    public static int randomId() {
        return randomId(0, 9999999);
    }

    public static ResponseEntity jsonResponse(Object ...args) {
        Map map = new HashMap();
        for(int i = 0; i < args.length; i += 2) {
            map.put(args[i].toString(), args[i+1]);
        }
        return ResponseEntity.ok().body(map);
    }
}
