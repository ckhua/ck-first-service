package com.example.demo.service.filter.impl;

import com.example.demo.service.filter.ITextWordsFilterService;
import com.example.demo.utils.common.WordsFilterUtils;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description 关键字过滤服务抽象类
 * @Date 2020/6/18 16:27
 * @Author chen kang hua
 * @Version 1.0
 **/
public abstract class AbstractTextWordsFilterService<T> implements ITextWordsFilterService<T> {

    protected static final String IMAGES_FLAG = "[$]null";


    @Override
    public List<T> filterText(List<T> sourceList) {
        //组装数据源
        WordsFilterUtils filterUtils = buildWordsData();
        //各自业务过滤实现
        this.filterSourceList(filterUtils, sourceList);
        return sourceList;
    }

    @Override
    public T filterText(T source) {
        //组装数据源
        WordsFilterUtils filterUtils = buildWordsData();
        //各自业务过滤实现
        this.filterSource(filterUtils, source);
        return source;
    }

    /**
     * 组装数据源 过滤工具类
     *
     * @return
     */
    private WordsFilterUtils buildWordsData() {
        //组装数据源
        List<String> wordsList = this.buildWordsList();
        //过滤工具类
        WordsFilterUtils filterUtils = new WordsFilterUtils(wordsList, buildReplaceWords());
        return filterUtils;
    }

    /**
     * 组装数据源
     *
     * @return
     */
    protected abstract List<String> buildWordsList();

    /**
     * 过滤实现
     *
     * @param wordsFilterUtils
     * @param sourceList
     * @return
     */
    protected abstract List<T> filterSourceList(WordsFilterUtils wordsFilterUtils, List<T> sourceList);

    /**
     * 过滤实现
     *
     * @param wordsFilterUtils
     * @return
     */
    protected abstract T filterSource(WordsFilterUtils wordsFilterUtils, T source);

    /**
     * 替换铭感字符
     *
     * @return
     */
    protected abstract String buildReplaceWords();

    /**
     * 查询关键字配置数据源
     *
     * @return
     */
    protected List<String> defaultWordsConfigList() {
        List<String> wordsList = Lists.newArrayList("日", "操");
        return wordsList;
    }

    /**
     * 过滤图片文本
     *
     * @param content
     * @param filterUtils
     * @return
     */
    protected String filterImages(String content, WordsFilterUtils filterUtils, String flag) {
        if (!content.contains(flag)) {
            return filterUtils.filterText(content);
        } else {
            int index = content.indexOf(flag);
            if (index == 0) {
                return content;
            } else {
                String substring = content.substring(0, index);
                String images = content.substring(index);
                return filterUtils.filterText(substring) + images;
            }
        }
    }
}
