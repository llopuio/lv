import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class demo {
    public static void main(String[] args) throws IOException {
        List<Map<String,Integer>> result = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        Map<String,Integer> map1 = new HashMap<>();
        map1 = new Hashtable<>();
        Map<String,Integer> map2 = new HashMap<>();
        //HashMap<String,Integer> map = new HashMap<>();
        /*
        map1.put("12", 12);
        map1.put("13", 13);
        map2.put("20", 20);
        map2.put("30", 30);
        result.add(map1);
        result.add(map2);
        for(int i=0; i<result.size(); i++){
            for(Map.Entry<String,Integer> entry: result.get(i).entrySet()){
                map.put(entry.getKey(), entry.getValue());
            }
        }
         */

        for(int i=0;i<3; i++){
            Map<String,Integer> map = new HashMap<>();
            map.put("id", i);
            map.put("text",i);
            System.out.println(map);
            result.add(map);
            System.out.println(result);
        }
        //System.out.println(r);

        //python的路径以及py文件的绝对路径
        String[] args1=new String[]{"C:\\Anaconda\\envs\\pytorch\\python.exe","D:\\python_project\\calculate\\helloworld.py"};
        Process pr=Runtime.getRuntime().exec(args1);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();

    }




}
