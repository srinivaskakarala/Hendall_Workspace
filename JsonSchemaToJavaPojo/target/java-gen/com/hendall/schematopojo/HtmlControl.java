
package com.hendall.schematopojo;

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
    "controlID",
    "controlType",
    "placeholder",
    "text",
    "parentControlID"
})
public class HtmlControl {

    /**
     * unique identifier for a particular html control
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
    @JsonProperty("text")
    private String text;
    /**
     * allows html controls to be linked for navigation/validation purposes
     * 
     */
    @JsonProperty("parentControlID")
    private Integer parentControlID;

    /**
     * unique identifier for a particular html control
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
     * unique identifier for a particular html control
     * (Required)
     * 
     * @param controlID
     *     The controlID
     */
    @JsonProperty("controlID")
    public void setControlID(Integer controlID) {
        this.controlID = controlID;
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

    /**
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
     * @param text
     *     The text
     */
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(controlID).append(controlType).append(placeholder).append(text).append(parentControlID).toHashCode();
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
        return new EqualsBuilder().append(controlID, rhs.controlID).append(controlType, rhs.controlType).append(placeholder, rhs.placeholder).append(text, rhs.text).append(parentControlID, rhs.parentControlID).isEquals();
    }

}
