package com.surveyproject.chapter.application;

import java.util.Optional;

import com.surveyproject.chapter.domain.entity.Chapter;
import com.surveyproject.chapter.domain.service.ChapterService;

public class FindChapterByIdUC {
    private final ChapterService chapterService;

    public FindChapterByIdUC(ChapterService chapterService){
        this.chapterService = chapterService;
    }

    public Optional<Chapter> findById(long id){
        return chapterService.findChaptersById(id);
    }
}
