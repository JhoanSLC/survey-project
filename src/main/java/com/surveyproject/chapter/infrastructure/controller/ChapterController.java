package com.surveyproject.chapter.infrastructure.controller;

import com.surveyproject.chapter.application.CreateChapterUC;
import com.surveyproject.chapter.application.DeleteChapterUC;
import com.surveyproject.chapter.application.FindChapterByIdUC;
import com.surveyproject.chapter.application.ListAllChaptersUC;
import com.surveyproject.chapter.application.UpdateChapterUC;
import com.surveyproject.chapter.domain.entity.Chapter;
import com.surveyproject.chapter.domain.service.ChapterService;
import com.surveyproject.chapter.infrastructure.repository.ChapterRepository;

import java.util.List;
import java.util.Optional;

public class ChapterController {
    private final ChapterService service;
    private final CreateChapterUC createChapterUC;
    private final DeleteChapterUC deleteChapterUC;
    private final FindChapterByIdUC findChapterByIdUC;
    private final ListAllChaptersUC listAllChaptersUC;
    private final UpdateChapterUC updateChapterUC;

    public ChapterController(){
        this.service = new ChapterRepository();
        this.createChapterUC = new CreateChapterUC(service);
        this.deleteChapterUC = new DeleteChapterUC(service);
        this.findChapterByIdUC = new FindChapterByIdUC(service);
        this.listAllChaptersUC = new ListAllChaptersUC(service);
        this.updateChapterUC = new UpdateChapterUC(service);
    }

    public void createChapter(Chapter chapter) {
        createChapterUC.create(chapter);
    }

    public void deleteChapter(long id) {
        deleteChapterUC.delete(id);
    }

    public Optional<Chapter> findChapterById(long id) {
        return findChapterByIdUC.findById(id);
    }

    public List<Chapter> listAllChapters() {
        return listAllChaptersUC.listAll();
    }

    public void updateChapter(Chapter chapter, long id) {
        updateChapterUC.update(chapter, id);
    }
}
