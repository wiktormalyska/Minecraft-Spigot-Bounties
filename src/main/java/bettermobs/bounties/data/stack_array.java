package bettermobs.bounties.data;
import java.util.ArrayList;
import java.util.List;

public class stack_array {
    public static List<Integer> stack_array_list(){
        List<Integer> array = new ArrayList<>();
        for(int i =1;i<= 64;i++){
            array.add(i);
        }
        return array;
    }
}
