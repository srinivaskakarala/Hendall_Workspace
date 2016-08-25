
package com.hendall.schematopojo;

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
    "questionText",
    "fileUpload",
    "htmlControl"
})
public class Question_ {

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
    @JsonProperty("htmlControl")
    private List<HtmlControl_> htmlControl = new ArrayList<HtmlControl_>();

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

    /**
     * 
     * @return
     *     The htmlControl
     */
    @JsonProperty("htmlControl")
    public List<HtmlControl_> getHtmlControl() {
        return htmlControl;
    }

    /**
     * 
     * @param htmlControl
     *     The htmlControl
     */
    @JsonProperty("htmlControl")
    public void setHtmlControl(List<HtmlControl_> htmlControl) {
        this.htmlControl = htmlControl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(questionText).append(fileUpload).append(htmlControl).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Question_) == false) {
            return false;
        }
        Question_ rhs = ((Question_) other);
        return new EqualsBuilder().append(questionText, rhs.questionText).append(fileUpload, rhs.fileUpload).append(htmlControl, rhs.htmlControl).isEquals();
    }

}
