package com.surveyproject.chapter.application;

import com.surveyproject.chapter.domain.service.ChapterService;

public class DeleteChapterUC {
    private final ChapterService chapterService;

    public DeleteChapterUC(ChapterService chapterService){
        this.chapterService = chapterService;
    }

    public void delete(long id){
        chapterService.deleteChapters(id);
    }

}
