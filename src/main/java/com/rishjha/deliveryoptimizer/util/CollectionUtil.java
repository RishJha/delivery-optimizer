package com.rishjha.deliveryoptimizer.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CollectionUtil {

    public static <T> List<List<T>> getPermutations(List<T> list) {
        List<List<T>> result = new ArrayList<>();
        if (list.isEmpty()) {
            result.add(new ArrayList<>());
            return result;
        }

        T firstElement = list.get(0);
        List<T> remainingList = list.subList(1, list.size());

        for (List<T> permutation : getPermutations(remainingList)) {
            for (int i = 0; i <= permutation.size(); i++) {
                List<T> temp = new ArrayList<>(permutation);
                temp.add(i, firstElement);
                result.add(temp);
            }
        }
        return result;
    }

}
