/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package io.favorites.common.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The purpose of this class is to save search result including list.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResult<T> {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Embedded<T> {

        private List<T> data;

        public void setData(List<T> data) {
            this.data = data;
        }

        public List<T> getData() {
            return data;
        }

    }

    public static class Link {
        private String href;

        /**
         * @return Returns the href.
         */
        public String getHref() {
            return href;
        }

        /**
         * @param href The href to set.
         */
        public void setHref(String href) {
            this.href = href;
        }
    }

    @JsonProperty("_embedded")
    protected Embedded<T> embedded;
    @JsonProperty("_links")
    protected Map<String, Link> links;

    /**
     * Creates a new instance of <code>SearchResult</code>.
     */
    public ListResult() {
    }

    /**
     * @return Returns the embedded.
     */
    public Embedded<T> getEmbedded() {
        return embedded;
    }

    /**
     * @param embedded The embedded to set.
     */
    public void setEmbedded(Embedded<T> embedded) {
        this.embedded = embedded;
    }

    /**
     * @return Returns the links.
     */
    public Map<String, Link> getLinks() {
        return links;
    }

    /**
     * @param links The links to set.
     */
    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }

}
