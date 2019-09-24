/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package io.favorites.common.api;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.favorites.common.api.ListResult.Link;

/**
 * The purpose of this class is to encapsulate JSON data only
 * including a basic type from Spring data rest server.
 */
public class BasicType<T> {

    private Map<String, T> data = new HashMap<String, T>();
    @JsonProperty("_links")
    protected Map<String, Link> links;

    /**
     * @return Returns the data.
     */
    @JsonAnyGetter
    public Map<String, T> getData() {
        return data;
    }

    @JsonAnySetter
    public void setData(String key, T value) {
        data.put(key, value);
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
