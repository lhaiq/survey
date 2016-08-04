package com.hongrui.survey.core.model;

/**
 * Created by haiquanli on 16/8/3.
 */
public class ReportConfModel {

    private String template;

    private ReportModel content;

    public ReportModel getContent() {
        return content;
    }

    public void setContent(ReportModel content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
