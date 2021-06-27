package client;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static String trimBracket(String str, Boolean trimOnce){
        int count = trimOnce ? 1 : Integer.MAX_VALUE;
        int len = str.length();
        while(str.charAt(0)=='[' && str.charAt(len-1)==']' && count > 0){
            str = str.substring(1,len-1);
            count--;
        }
        return str;
    }

    public static Cloneable buildOneDimensionalArray(String input, String type){

        String str = input;
        str = trimBracket(str, true);
        String[] tokens = str.split(",");

        switch (type){
            case "int":
                return Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
            case "double":
                return Arrays.stream(tokens).mapToDouble(Double::parseDouble).toArray();
            case "boolean":
                boolean[] bools = new boolean[tokens.length];
                for(int i = 0 ; i < tokens.length; ++i)
                    bools[i] = tokens[i].equals("true");
                return bools;
            case "char":
                char[] chars = new char[tokens.length];
                for(int i = 0 ; i < tokens.length; ++i)
                    chars[i] = tokens[i].charAt(0);
                return chars;
            default:
                return tokens;
        }
    }

    public static String[] parseArguments(String input){
        String str = input;

        String[] tmp = (String[]) buildOneDimensionalArray(str,"");
        if(tmp.length == 0) return null;

        if(tmp[0].charAt(0) != '[' && tmp[tmp.length-1].charAt(tmp[tmp.length-1].length()-1)!=']')
            return tmp;

        List<String> ans = new ArrayList<>();
        for(int i = 0 ; i < tmp.length;){
            if(tmp[i].charAt(0)=='[' && tmp[i].charAt(tmp[i].length()-1)==']'){
                ans.add(tmp[i]);
                i++;
            }else{
                StringBuilder result= new StringBuilder();
                while(tmp[i].charAt(tmp[i].length()-1)!=']'){
                    result.append(tmp[i++]).append(",");
                }
                result.append(tmp[i++]);
                ans.add(result.toString());
            }
        }

        return ans.toArray(new String[0]);
    }

    public static void display(int[] data){
        Arrays.stream(data).forEach(System.out::println);
    }

    public static void main(String[] args) {
        String input = "[true,false,true,false,false,false]";

        boolean[] vals = (boolean[]) buildOneDimensionalArray(input,"boolean");
        System.out.println(Arrays.toString(vals));



    }
}
