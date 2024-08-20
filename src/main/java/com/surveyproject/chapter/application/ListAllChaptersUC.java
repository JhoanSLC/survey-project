package com.surveyproject.chapter.application;

import java.util.List;

import com.surveyproject.chapter.domain.entity.Chapter;
import com.surveyproject.chapter.domain.service.ChapterService;

public class ListAllChaptersUC {
    private final ChapterService chapterService;

    public ListAllChaptersUC(ChapterService chapterService){
        this.chapterService = chapterService;
    }

    public List<Chapter> listAll(){
        return chapterService.listAllChapter();
    }

}
