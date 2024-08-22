package com.surveyproject.chapter.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveyproject.chapter.domain.entity.Chapter;

public interface ChapterService {
    void createChapters(Chapter chapter);
    Optional<Chapter> findChaptersById(long id);
    List<Chapter> listAllChapter();
    void updateChapters(Chapter chapter,long id);
    void deleteChapters(long id);
}
