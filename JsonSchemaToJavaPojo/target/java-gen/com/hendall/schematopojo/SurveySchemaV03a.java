
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


/**
 * Surveys JSON Schema
 * <p>
 * first attempt at creating a JSON schema for all survey questions
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "surveyID",
    "title",
    "version",
    "versionHistory",
    "creationDate",
    "lastModifiedDate",
    "author",
    "description",
    "instructions",
    "page"
})
public class SurveySchemaV03a {

    /**
     * unique identifier allowing an answer JSON file to be linked to a particular survey/version JSON file
     * (Required)
     * 
     */
    @JsonProperty("surveyID")
    private Integer surveyID;
    @JsonProperty("title")
    private String title;
    @JsonProperty("version")
    private String version;
    @JsonProperty("versionHistory")
    private String versionHistory;
    @JsonProperty("creationDate")
    private String creationDate;
    @JsonProperty("lastModifiedDate")
    private String lastModifiedDate;
    @JsonProperty("author")
    private String author;
    @JsonProperty("description")
    private String description;
    @JsonProperty("instructions")
    private String instructions;
    @JsonProperty("page")
    private List<Page> page = new ArrayList<Page>();

    /**
     * unique identifier allowing an answer JSON file to be linked to a particular survey/version JSON file
     * (Required)
     * 
     * @return
     *     The surveyID
     */
    @JsonProperty("surveyID")
    public Integer getSurveyID() {
        return surveyID;
    }

    /**
     * unique identifier allowing an answer JSON file to be linked to a particular survey/version JSON file
     * (Required)
     * 
     * @param surveyID
     *     The surveyID
     */
    @JsonProperty("surveyID")
    public void setSurveyID(Integer surveyID) {
        this.surveyID = surveyID;
    }

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

    /**
     * 
     * @return
     *     The version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The versionHistory
     */
    @JsonProperty("versionHistory")
    public String getVersionHistory() {
        return versionHistory;
    }

    /**
     * 
     * @param versionHistory
     *     The versionHistory
     */
    @JsonProperty("versionHistory")
    public void setVersionHistory(String versionHistory) {
        this.versionHistory = versionHistory;
    }

    /**
     * 
     * @return
     *     The creationDate
     */
    @JsonProperty("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @param creationDate
     *     The creationDate
     */
    @JsonProperty("creationDate")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 
     * @return
     *     The lastModifiedDate
     */
    @JsonProperty("lastModifiedDate")
    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * 
     * @param lastModifiedDate
     *     The lastModifiedDate
     */
    @JsonProperty("lastModifiedDate")
    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * 
     * @return
     *     The author
     */
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The instructions
     */
    @JsonProperty("instructions")
    public String getInstructions() {
        return instructions;
    }

    /**
     * 
     * @param instructions
     *     The instructions
     */
    @JsonProperty("instructions")
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * 
     * @return
     *     The page
     */
    @JsonProperty("page")
    public List<Page> getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    @JsonProperty("page")
    public void setPage(List<Page> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(surveyID).append(title).append(version).append(versionHistory).append(creationDate).append(lastModifiedDate).append(author).append(description).append(instructions).append(page).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SurveySchemaV03a) == false) {
            return false;
        }
        SurveySchemaV03a rhs = ((SurveySchemaV03a) other);
        return new EqualsBuilder().append(surveyID, rhs.surveyID).append(title, rhs.title).append(version, rhs.version).append(versionHistory, rhs.versionHistory).append(creationDate, rhs.creationDate).append(lastModifiedDate, rhs.lastModifiedDate).append(author, rhs.author).append(description, rhs.description).append(instructions, rhs.instructions).append(page, rhs.page).isEquals();
    }

}
