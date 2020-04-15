package com.example.demo.service.query;

import com.example.demo.model.StudyCallRecord;

import java.util.List;

/**
 * @Description 简单查询
 * @Date 2020/4/14 11:36
 * @Author chen kang hua
 * @Version 1.0
 **/
public interface ICallRecordQueryService {

    List<StudyCallRecord> queryCallRecordInfo();
}
