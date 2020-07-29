package com.example.demo;

import com.example.demo.enums.RedissonDataConsumerType;
import com.example.demo.enums.WordsFilterTypeEnum;
import com.example.demo.model.StudyCallRecord;
import com.example.demo.service.filter.ITextWordsFilterService;
import com.example.demo.service.task.ITaskDataProductService;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Description service 服务测试类
 * @Date 2020/7/29 10:31
 * @Author chen kang hua
 * @Version 1.0
 **/
@SpringBootTest
public class ServiceTest {

    /**
     * 将 ITaskDataProductService 装配进入map
     */
    @Autowired
    private Map<String, ITaskDataProductService> taskDataProductServiceMap;

    @Autowired
    private Map<String, ITextWordsFilterService> textWordsFilterServiceMap;


    @Test
    public void redissonProduct() {

        //根据业务信息 获取对应服务信息
        String serviceName = Joiner.on("").join(ITaskDataProductService.class.getSimpleName(), "_", RedissonDataConsumerType.ORDER.getCode());
        Optional.ofNullable(serviceName)
                .map(taskDataProductServiceMap::get)
                .ifPresent(service -> service.supply());
    }


    @Test
    public void testFilterText() {
        List<StudyCallRecord> arrayList = Lists.newArrayList();
        StudyCallRecord studyCallRecord = new StudyCallRecord();
        studyCallRecord.setCallId("1");
        studyCallRecord.setCallMobile("操真的是日");
        arrayList.add(studyCallRecord);


        ITextWordsFilterService wordsFilterService = textWordsFilterServiceMap.get(ITextWordsFilterService.class.getSimpleName() + "_" + WordsFilterTypeEnum.DEFAULT.getType());
        if (wordsFilterService != null) {
            wordsFilterService.filterText(arrayList);
        }

        String json = new Gson().toJson(arrayList);
        System.out.println(json);
    }


}
