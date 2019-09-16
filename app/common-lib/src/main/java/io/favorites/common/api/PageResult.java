/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package io.favorites.common.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The purpose of this class is to save search result including page.
 */
public class PageResult<T> extends ListResult<T> {

    public static class Page {

        private int size;
        @JsonProperty("totalElements")
        private int totalelements;
        @JsonProperty("totalPages")
        private int totalpages;
        private int number;

        public void setSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public void setTotalelements(int totalelements) {
            this.totalelements = totalelements;
        }

        public int getTotalelements() {
            return totalelements;
        }

        public void setTotalpages(int totalpages) {
            this.totalpages = totalpages;
        }

        public int getTotalpages() {
            return totalpages;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

    }
    private Page page;

    /**
     * Creates a new instance of <code>SearchResult</code>.
     */
    public PageResult() {
    }

    /**
     * @return Returns the page.
     */
    public Page getPage() {
        return page;
    }

    /**
     * @param page The page to set.
     */
    public void setPage(Page page) {
        this.page = page;
    }

}
