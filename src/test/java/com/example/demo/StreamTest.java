package com.example.demo;

import com.example.demo.model.StudyCallRecord;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description Stream 测试类
 * @Date 2020/7/29 10:19
 * @Author chen kang hua
 * @Version 1.0
 **/
@SpringBootTest
public class StreamTest {


    /**
     * List<List<Object>>  ->  List<Object>
     */
    @Test
    void mergeList() {

        List<List<String>> arrayList = Lists.newArrayList();
        List<String> objects1 = Lists.newArrayList();
        objects1.add("1");
        objects1.add("2");
        objects1.add("3");
        List<String> objects2 = Lists.newArrayList();
        objects2.add("4");
        objects2.add("5");
        objects2.add("6");
        arrayList.add(objects1);
        arrayList.add(objects2);

        arrayList.stream().flatMap(Collection::stream).collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * 根据字段去重
     */

    @Test
    void distinctList() {

        List<StudyCallRecord> arrayList = Lists.newArrayList();
        StudyCallRecord studyCallRecord = new StudyCallRecord();
        studyCallRecord.setCallId("1");
        studyCallRecord.setCallMobile("156");
        arrayList.add(studyCallRecord);

        StudyCallRecord studyCallRecord2 = new StudyCallRecord();
        studyCallRecord2.setCallId("2");
        studyCallRecord2.setCallMobile("156");
        arrayList.add(studyCallRecord2);

        StudyCallRecord studyCallRecord3 = new StudyCallRecord();
        studyCallRecord3.setCallId("1");
        studyCallRecord3.setCallMobile("1562");
        arrayList.add(studyCallRecord3);

        ArrayList<StudyCallRecord> records = arrayList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(StudyCallRecord::getCallId))), ArrayList::new));

        System.out.println(records.size() + "----------------------------------------------");

    }

    @Test
    void testIn() {
    }


}
