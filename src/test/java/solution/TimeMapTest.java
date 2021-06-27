package solution;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TimeMapTest {
    @ParameterizedTest
    @MethodSource("TimeMapProvider")
    void timeMap(String command, String args, String expected) {

        String[] cmds = (String[])buildOneDimensionalArray(command,"string");
        String[] inputs = parseArguments(args);
        String result = act(cmds, inputs);
        assertEquals(expected, result);
    }

    private static Stream TimeMapProvider() {
        return Stream.of(
                Arguments.of(
                        "[TimeMap,set,get,get,set,get,get]",
                        "[[],[foo,bar,1],[foo,1],[foo,3],[foo,bar2,4],[foo,4],[foo,5]]",
                        "[null,null,bar,bar,null,bar2,bar2]"
                )
        );
    }

    private static String act(String[] cmds, String[] inputs){
        List<String> ans = new ArrayList<>();
        TimeMap obj = null;
        String key = "";
        String value = "";
        int timeStamp = 0;
        for(int i = 0 ; i < cmds.length; ++i){
            String cmd = cmds[i];
            String[] args = (String[]) buildOneDimensionalArray(inputs[i],"string");
            switch (cmd){
                case "TimeMap":
                    obj = new TimeMap();
                    ans.add("null");
                    break;
                case "set":
                    key = args[0];
                    value = args[1];
                    timeStamp = Integer.parseInt(args[2]);
                    obj.set(key,value,timeStamp);
                    ans.add("null");
                    break;
                case "get":
                    key = args[0];
                    timeStamp = Integer.parseInt(args[1]);
                    ans.add(obj.get(key,timeStamp));
                    break;
                default:
                    break;
            }
        }

        return serializeToString(ans);
    }

    public static String trimBracket(String str, Boolean trimOnce){
        int count = trimOnce ? 1 : Integer.MAX_VALUE;
        while(str.length()>0 && str.charAt(0)=='[' && str.charAt(str.length()-1)==']' && count > 0){
            str = str.substring(1, str.length()-1);
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

    public static <T> String serializeToString(List<T> ans){
        StringBuilder out = new StringBuilder();
        out.append("[");
        String token = "";
        for(T e : ans){
            out.append(token).append(e);
            token=",";
        }
        out.append("]");
        return out.toString();
    }

    public static String[] parseArguments(String input){
        String str = input;

        String[] tmp = (String[]) buildOneDimensionalArray(str,"string");
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
}
