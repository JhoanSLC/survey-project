package com.surveyproject.chapter.application;

import com.surveyproject.chapter.domain.entity.Chapter;
import com.surveyproject.chapter.domain.service.ChapterService;

public class CreateChapterUC {
    private final ChapterService chapterService;

    public CreateChapterUC(ChapterService chapterService){
        this.chapterService = chapterService;
    }

    public void create(Chapter chapter){
        chapterService.createChapters(chapter);
    }
}
