
package com.hendall.survey.schematopojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "title",
    "question"
})
public class Section {

    @JsonProperty("title")
    private String title;
    @JsonProperty("question")
    private List<Question> question = new ArrayList<Question>();

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Section withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 
     * @return
     *     The question
     */
    @JsonProperty("question")
    public List<Question> getQuestion() {
        return question;
    }

    /**
     * 
     * @param question
     *     The question
     */
    @JsonProperty("question")
    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public Section withQuestion(List<Question> question) {
        this.question = question;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(question).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Section) == false) {
            return false;
        }
        Section rhs = ((Section) other);
        return new EqualsBuilder().append(title, rhs.title).append(question, rhs.question).isEquals();
    }

}
