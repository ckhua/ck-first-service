package com.example.demo.service.filter.impl;

import com.example.demo.model.StudyCallRecord;
import com.example.demo.utils.WordsFilterUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 默认 关键字过滤实现
 * @Date 2020/6/18 16:35
 * @Author chen kang hua
 * @Version 1.0
 **/
@Service("ITextWordsFilterService_DEFAULT")
public class DefaultTextFilterServiceImpl extends AbstractTextWordsFilterService<StudyCallRecord> {


    @Override
    protected List<String> buildWordsList() {
        return super.defaultWordsConfigList();
    }

    @Override
    protected List<StudyCallRecord> filterSourceList(WordsFilterUtils filterUtils, List<StudyCallRecord> sourceList) {
        if (sourceList != null && !sourceList.isEmpty()) {
            for (StudyCallRecord studyCallRecord : sourceList) {
                filterSource(filterUtils, studyCallRecord);
            }
        }
        return sourceList;
    }

    @Override
    protected StudyCallRecord filterSource(WordsFilterUtils filterUtils, StudyCallRecord source) {
        if (source != null) {
            if (StringUtils.isNotEmpty(source.getCallMobile())) {
                source.setCallMobile(super.filterImages(source.getCallMobile(), filterUtils, IMAGES_FLAG));
            }
        }
        return source;
    }

    @Override
    protected String buildReplaceWords() {
        return WordsFilterUtils.DEFAULT_REPLACE_WORDS;
    }
}
