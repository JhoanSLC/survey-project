package com.surveyproject.chapter.application;

import com.surveyproject.chapter.domain.entity.Chapter;
import com.surveyproject.chapter.domain.service.ChapterService;

public class UpdateChapterUC {
    private final ChapterService chapterService;

    public UpdateChapterUC(ChapterService chapterService){
        this.chapterService = chapterService;
    }

    public void update(Chapter chapter){
        chapterService.updateChapters(chapter);
    }

}
