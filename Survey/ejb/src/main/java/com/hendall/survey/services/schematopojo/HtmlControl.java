
package com.hendall.survey.services.schematopojo;

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
    "controlID",
    "controlType",
    "placeholder",
    "fieldSize",
    "fieldMaxLength",
    "controlText",
    "parentControlID",
    "fieldRequired",
    "fieldDefaultVisibility"
})
public class HtmlControl {

    /**
     * unique identifier for a particular html control, matches column in db
     * (Required)
     * 
     */
    @JsonProperty("controlID")
    private Integer controlID;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("controlType")
    private String controlType;
    @JsonProperty("placeholder")
    private String placeholder;
    @JsonProperty("fieldSize")
    private Integer fieldSize;
    @JsonProperty("fieldMaxLength")
    private Integer fieldMaxLength;
    /**
     * text for each control stored here
     * 
     */
    @JsonProperty("controlText")
    private String controlText;
    /**
     * allows html controls to be linked for navigation/validation purposes
     * 
     */
    @JsonProperty("parentControlID")
    private Integer parentControlID;
    @JsonProperty("fieldRequired")
    private Boolean fieldRequired;
    @JsonProperty("fieldDefaultVisibility")
    private Boolean fieldDefaultVisibility;

    /**
     * unique identifier for a particular html control, matches column in db
     * (Required)
     * 
     * @return
     *     The controlID
     */
    @JsonProperty("controlID")
    public Integer getControlID() {
        return controlID;
    }

    /**
     * unique identifier for a particular html control, matches column in db
     * (Required)
     * 
     * @param controlID
     *     The controlID
     */
    @JsonProperty("controlID")
    public void setControlID(Integer controlID) {
        this.controlID = controlID;
    }

    public HtmlControl withControlID(Integer controlID) {
        this.controlID = controlID;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The controlType
     */
    @JsonProperty("controlType")
    public String getControlType() {
        return controlType;
    }

    /**
     * 
     * (Required)
     * 
     * @param controlType
     *     The controlType
     */
    @JsonProperty("controlType")
    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public HtmlControl withControlType(String controlType) {
        this.controlType = controlType;
        return this;
    }

    /**
     * 
     * @return
     *     The placeholder
     */
    @JsonProperty("placeholder")
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * 
     * @param placeholder
     *     The placeholder
     */
    @JsonProperty("placeholder")
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public HtmlControl withPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    /**
     * 
     * @return
     *     The fieldSize
     */
    @JsonProperty("fieldSize")
    public Integer getFieldSize() {
        return fieldSize;
    }

    /**
     * 
     * @param fieldSize
     *     The fieldSize
     */
    @JsonProperty("fieldSize")
    public void setFieldSize(Integer fieldSize) {
        this.fieldSize = fieldSize;
    }

    public HtmlControl withFieldSize(Integer fieldSize) {
        this.fieldSize = fieldSize;
        return this;
    }

    /**
     * 
     * @return
     *     The fieldMaxLength
     */
    @JsonProperty("fieldMaxLength")
    public Integer getFieldMaxLength() {
        return fieldMaxLength;
    }

    /**
     * 
     * @param fieldMaxLength
     *     The fieldMaxLength
     */
    @JsonProperty("fieldMaxLength")
    public void setFieldMaxLength(Integer fieldMaxLength) {
        this.fieldMaxLength = fieldMaxLength;
    }

    public HtmlControl withFieldMaxLength(Integer fieldMaxLength) {
        this.fieldMaxLength = fieldMaxLength;
        return this;
    }

    /**
     * text for each control stored here
     * 
     * @return
     *     The controlText
     */
    @JsonProperty("controlText")
    public String getControlText() {
        return controlText;
    }

    /**
     * text for each control stored here
     * 
     * @param controlText
     *     The controlText
     */
    @JsonProperty("controlText")
    public void setControlText(String controlText) {
        this.controlText = controlText;
    }

    public HtmlControl withControlText(String controlText) {
        this.controlText = controlText;
        return this;
    }

    /**
     * allows html controls to be linked for navigation/validation purposes
     * 
     * @return
     *     The parentControlID
     */
    @JsonProperty("parentControlID")
    public Integer getParentControlID() {
        return parentControlID;
    }

    /**
     * allows html controls to be linked for navigation/validation purposes
     * 
     * @param parentControlID
     *     The parentControlID
     */
    @JsonProperty("parentControlID")
    public void setParentControlID(Integer parentControlID) {
        this.parentControlID = parentControlID;
    }

    public HtmlControl withParentControlID(Integer parentControlID) {
        this.parentControlID = parentControlID;
        return this;
    }

    /**
     * 
     * @return
     *     The fieldRequired
     */
    @JsonProperty("fieldRequired")
    public Boolean getFieldRequired() {
        return fieldRequired;
    }

    /**
     * 
     * @param fieldRequired
     *     The fieldRequired
     */
    @JsonProperty("fieldRequired")
    public void setFieldRequired(Boolean fieldRequired) {
        this.fieldRequired = fieldRequired;
    }

    public HtmlControl withFieldRequired(Boolean fieldRequired) {
        this.fieldRequired = fieldRequired;
        return this;
    }

    /**
     * 
     * @return
     *     The fieldDefaultVisibility
     */
    @JsonProperty("fieldDefaultVisibility")
    public Boolean getFieldDefaultVisibility() {
        return fieldDefaultVisibility;
    }

    /**
     * 
     * @param fieldDefaultVisibility
     *     The fieldDefaultVisibility
     */
    @JsonProperty("fieldDefaultVisibility")
    public void setFieldDefaultVisibility(Boolean fieldDefaultVisibility) {
        this.fieldDefaultVisibility = fieldDefaultVisibility;
    }

    public HtmlControl withFieldDefaultVisibility(Boolean fieldDefaultVisibility) {
        this.fieldDefaultVisibility = fieldDefaultVisibility;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(controlID).append(controlType).append(placeholder).append(fieldSize).append(fieldMaxLength).append(controlText).append(parentControlID).append(fieldRequired).append(fieldDefaultVisibility).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HtmlControl) == false) {
            return false;
        }
        HtmlControl rhs = ((HtmlControl) other);
        return new EqualsBuilder().append(controlID, rhs.controlID).append(controlType, rhs.controlType).append(placeholder, rhs.placeholder).append(fieldSize, rhs.fieldSize).append(fieldMaxLength, rhs.fieldMaxLength).append(controlText, rhs.controlText).append(parentControlID, rhs.parentControlID).append(fieldRequired, rhs.fieldRequired).append(fieldDefaultVisibility, rhs.fieldDefaultVisibility).isEquals();
    }

}
