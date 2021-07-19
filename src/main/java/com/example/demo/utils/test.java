package com.example.demo.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description 类注释
 * @Date 2021/6/3 18:38
 * @Author chen kang hua
 * @Version 1.0
 **/
public class test {


    public static void main(String[] args) {
        Map<Integer, String> byIdsAndLanguage = getNameByIdsAndLanguage(Lists.newArrayList(1, 2, null));
        System.out.println(byIdsAndLanguage);
    }

    public static Map<Integer, String> getNameByIdsAndLanguage(List<Integer> partyIdList) {
        Map<Integer, String> nameMap = Maps.newHashMapWithExpectedSize(16);
        Optional.ofNullable(partyIdList)
                .filter(CollectionUtils::isNotEmpty)
                .map(ids -> ids.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList()))
                .ifPresent(ids -> ids.stream()
                        .forEach(id -> {
                            nameMap.put(id, "");
                        }));
        return nameMap;
    }

    private void te(Integer i) {
        System.out.println(i);
        if (i == 15) {

        }
    }


}
