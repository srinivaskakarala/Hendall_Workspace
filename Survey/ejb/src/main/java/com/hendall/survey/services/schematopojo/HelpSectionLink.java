
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
    "helpLinkName",
    "helpLinkURL"
})
public class HelpSectionLink {

    @JsonProperty("helpLinkName")
    private String helpLinkName;
    @JsonProperty("helpLinkURL")
    private String helpLinkURL;

    /**
     * 
     * @return
     *     The helpLinkName
     */
    @JsonProperty("helpLinkName")
    public String getHelpLinkName() {
        return helpLinkName;
    }

    /**
     * 
     * @param helpLinkName
     *     The helpLinkName
     */
    @JsonProperty("helpLinkName")
    public void setHelpLinkName(String helpLinkName) {
        this.helpLinkName = helpLinkName;
    }

    public HelpSectionLink withHelpLinkName(String helpLinkName) {
        this.helpLinkName = helpLinkName;
        return this;
    }

    /**
     * 
     * @return
     *     The helpLinkURL
     */
    @JsonProperty("helpLinkURL")
    public String getHelpLinkURL() {
        return helpLinkURL;
    }

    /**
     * 
     * @param helpLinkURL
     *     The helpLinkURL
     */
    @JsonProperty("helpLinkURL")
    public void setHelpLinkURL(String helpLinkURL) {
        this.helpLinkURL = helpLinkURL;
    }

    public HelpSectionLink withHelpLinkURL(String helpLinkURL) {
        this.helpLinkURL = helpLinkURL;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(helpLinkName).append(helpLinkURL).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HelpSectionLink) == false) {
            return false;
        }
        HelpSectionLink rhs = ((HelpSectionLink) other);
        return new EqualsBuilder().append(helpLinkName, rhs.helpLinkName).append(helpLinkURL, rhs.helpLinkURL).isEquals();
    }

}
