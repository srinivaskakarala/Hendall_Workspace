
package com.hendall.survey.schematopojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "questionID",
    "questionText",
    "fileUpload",
    "citableTagName",
    "citableTagURL",
    "htmlControl"
})
public class Question {

    /**
     * unique identifier for each question [required field]
     * (Required)
     * 
     */
    @JsonProperty("questionID")
    private Integer questionID;
    /**
     * text for each question stored here
     * (Required)
     * 
     */
    @JsonProperty("questionText")
    private String questionText;
    /**
     * flag indicating whether to a file upload button assocaited with a question
     * 
     */
    @JsonProperty("fileUpload")
    private Boolean fileUpload;
    /**
     * optional field storing citable tag associated with this question
     * 
     */
    @JsonProperty("citableTagName")
    private String citableTagName;
    /**
     * optional field storing URL to citable tag
     * 
     */
    @JsonProperty("citableTagURL")
    private String citableTagURL;
    @JsonProperty("htmlControl")
    private List<HtmlControl> htmlControl = new ArrayList<HtmlControl>();

    /**
     * unique identifier for each question [required field]
     * (Required)
     * 
     * @return
     *     The questionID
     */
    @JsonProperty("questionID")
    public Integer getQuestionID() {
        return questionID;
    }

    /**
     * unique identifier for each question [required field]
     * (Required)
     * 
     * @param questionID
     *     The questionID
     */
    @JsonProperty("questionID")
    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Question withQuestionID(Integer questionID) {
        this.questionID = questionID;
        return this;
    }

    /**
     * text for each question stored here
     * (Required)
     * 
     * @return
     *     The questionText
     */
    @JsonProperty("questionText")
    public String getQuestionText() {
        return questionText;
    }

    /**
     * text for each question stored here
     * (Required)
     * 
     * @param questionText
     *     The questionText
     */
    @JsonProperty("questionText")
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Question withQuestionText(String questionText) {
        this.questionText = questionText;
        return this;
    }

    /**
     * flag indicating whether to a file upload button assocaited with a question
     * 
     * @return
     *     The fileUpload
     */
    @JsonProperty("fileUpload")
    public Boolean getFileUpload() {
        return fileUpload;
    }

    /**
     * flag indicating whether to a file upload button assocaited with a question
     * 
     * @param fileUpload
     *     The fileUpload
     */
    @JsonProperty("fileUpload")
    public void setFileUpload(Boolean fileUpload) {
        this.fileUpload = fileUpload;
    }

    public Question withFileUpload(Boolean fileUpload) {
        this.fileUpload = fileUpload;
        return this;
    }

    /**
     * optional field storing citable tag associated with this question
     * 
     * @return
     *     The citableTagName
     */
    @JsonProperty("citableTagName")
    public String getCitableTagName() {
        return citableTagName;
    }

    /**
     * optional field storing citable tag associated with this question
     * 
     * @param citableTagName
     *     The citableTagName
     */
    @JsonProperty("citableTagName")
    public void setCitableTagName(String citableTagName) {
        this.citableTagName = citableTagName;
    }

    public Question withCitableTagName(String citableTagName) {
        this.citableTagName = citableTagName;
        return this;
    }

    /**
     * optional field storing URL to citable tag
     * 
     * @return
     *     The citableTagURL
     */
    @JsonProperty("citableTagURL")
    public String getCitableTagURL() {
        return citableTagURL;
    }

    /**
     * optional field storing URL to citable tag
     * 
     * @param citableTagURL
     *     The citableTagURL
     */
    @JsonProperty("citableTagURL")
    public void setCitableTagURL(String citableTagURL) {
        this.citableTagURL = citableTagURL;
    }

    public Question withCitableTagURL(String citableTagURL) {
        this.citableTagURL = citableTagURL;
        return this;
    }

    /**
     * 
     * @return
     *     The htmlControl
     */
    @JsonProperty("htmlControl")
    public List<HtmlControl> getHtmlControl() {
        return htmlControl;
    }

    /**
     * 
     * @param htmlControl
     *     The htmlControl
     */
    @JsonProperty("htmlControl")
    public void setHtmlControl(List<HtmlControl> htmlControl) {
        this.htmlControl = htmlControl;
    }

    public Question withHtmlControl(List<HtmlControl> htmlControl) {
        this.htmlControl = htmlControl;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(questionID).append(questionText).append(fileUpload).append(citableTagName).append(citableTagURL).append(htmlControl).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Question) == false) {
            return false;
        }
        Question rhs = ((Question) other);
        return new EqualsBuilder().append(questionID, rhs.questionID).append(questionText, rhs.questionText).append(fileUpload, rhs.fileUpload).append(citableTagName, rhs.citableTagName).append(citableTagURL, rhs.citableTagURL).append(htmlControl, rhs.htmlControl).isEquals();
    }

}
