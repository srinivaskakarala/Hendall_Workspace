
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
    "helpSectionName",
    "helpSectionLink"
})
public class Help {

    @JsonProperty("helpSectionName")
    private String helpSectionName;
    @JsonProperty("helpSectionLink")
    private List<HelpSectionLink> helpSectionLink = new ArrayList<HelpSectionLink>();

    /**
     * 
     * @return
     *     The helpSectionName
     */
    @JsonProperty("helpSectionName")
    public String getHelpSectionName() {
        return helpSectionName;
    }

    /**
     * 
     * @param helpSectionName
     *     The helpSectionName
     */
    @JsonProperty("helpSectionName")
    public void setHelpSectionName(String helpSectionName) {
        this.helpSectionName = helpSectionName;
    }

    public Help withHelpSectionName(String helpSectionName) {
        this.helpSectionName = helpSectionName;
        return this;
    }

    /**
     * 
     * @return
     *     The helpSectionLink
     */
    @JsonProperty("helpSectionLink")
    public List<HelpSectionLink> getHelpSectionLink() {
        return helpSectionLink;
    }

    /**
     * 
     * @param helpSectionLink
     *     The helpSectionLink
     */
    @JsonProperty("helpSectionLink")
    public void setHelpSectionLink(List<HelpSectionLink> helpSectionLink) {
        this.helpSectionLink = helpSectionLink;
    }

    public Help withHelpSectionLink(List<HelpSectionLink> helpSectionLink) {
        this.helpSectionLink = helpSectionLink;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(helpSectionName).append(helpSectionLink).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Help) == false) {
            return false;
        }
        Help rhs = ((Help) other);
        return new EqualsBuilder().append(helpSectionName, rhs.helpSectionName).append(helpSectionLink, rhs.helpSectionLink).isEquals();
    }

}
