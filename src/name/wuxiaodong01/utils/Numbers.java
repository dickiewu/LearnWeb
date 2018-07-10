package name.wuxiaodong01.utils;

import java.util.Optional;

public class Numbers {
    private Numbers(){

    }

    public static Optional<Integer> parseInt(String intString){
        try {
            int i = Integer.parseInt(intString);
            return Optional.ofNullable(i);
        }catch (Exception e){
            return Optional.empty();
        }
    }

}
