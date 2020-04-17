package com.example.demo.service.query.impl;

import com.example.demo.dal.StudyCallRecordMapper;
import com.example.demo.model.StudyCallRecord;
import com.example.demo.model.StudyCallRecordExample;
import com.example.demo.service.query.ICallRecordQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 通话记录查询实现类
 * @Date 2020/4/14 11:36
 * @Author chen kang hua
 * @Version 1.0
 **/
@Service
public class CallRecordQueryServiceImpl implements ICallRecordQueryService {

    @Autowired
    private StudyCallRecordMapper studyCallRecordMapper;

    @Override
    public List<StudyCallRecord> queryCallRecordInfo() {

        StudyCallRecordExample example = new StudyCallRecordExample();
        example.createCriteria()
                .andCallIdIsNotNull();
        return this.studyCallRecordMapper.selectByExample(example);
    }
}
