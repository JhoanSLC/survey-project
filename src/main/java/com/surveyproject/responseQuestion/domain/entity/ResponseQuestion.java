package com.surveyproject.responseQuestion.domain.entity;

public class ResponseQuestion {
    private long id;
    private long responseId;
    private long subResponsesId;
    private String responseText;

    public ResponseQuestion(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getResponseId() {
        return responseId;
    }

    public void setResponseId(long responseId) {
        this.responseId = responseId;
    }

    public long getSubResponsesId() {
        return subResponsesId;
    }

    public void setSubResponsesId(long subResponsesId) {
        this.subResponsesId = subResponsesId;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    
}
