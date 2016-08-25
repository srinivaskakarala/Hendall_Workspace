
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
    "questionID",
    "text",
    "displayMediaIcon",
    "htmlControl"
})
public class Question {

    /**
     * unique identifier allowing an answer in a JSON file to be linked to a particular question (must be used in association with 'surveyID'
     * (Required)
     * 
     */
    @JsonProperty("questionID")
    private Integer questionID;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("text")
    private String text;
    /**
     * flag indicating whether to display media icon allowing capture of additional survey evidence (photo/sound/video)
     * 
     */
    @JsonProperty("displayMediaIcon")
    private Boolean displayMediaIcon;
    @JsonProperty("htmlControl")
    private List<HtmlControl> htmlControl = new ArrayList<HtmlControl>();

    /**
     * unique identifier allowing an answer in a JSON file to be linked to a particular question (must be used in association with 'surveyID'
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
     * unique identifier allowing an answer in a JSON file to be linked to a particular question (must be used in association with 'surveyID'
     * (Required)
     * 
     * @param questionID
     *     The questionID
     */
    @JsonProperty("questionID")
    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The text
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     * 
     * (Required)
     * 
     * @param text
     *     The text
     */
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    /**
     * flag indicating whether to display media icon allowing capture of additional survey evidence (photo/sound/video)
     * 
     * @return
     *     The displayMediaIcon
     */
    @JsonProperty("displayMediaIcon")
    public Boolean getDisplayMediaIcon() {
        return displayMediaIcon;
    }

    /**
     * flag indicating whether to display media icon allowing capture of additional survey evidence (photo/sound/video)
     * 
     * @param displayMediaIcon
     *     The displayMediaIcon
     */
    @JsonProperty("displayMediaIcon")
    public void setDisplayMediaIcon(Boolean displayMediaIcon) {
        this.displayMediaIcon = displayMediaIcon;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(questionID).append(text).append(displayMediaIcon).append(htmlControl).toHashCode();
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
        return new EqualsBuilder().append(questionID, rhs.questionID).append(text, rhs.text).append(displayMediaIcon, rhs.displayMediaIcon).append(htmlControl, rhs.htmlControl).isEquals();
    }

}
