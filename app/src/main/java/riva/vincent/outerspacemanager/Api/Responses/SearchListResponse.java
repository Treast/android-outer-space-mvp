package riva.vincent.outerspacemanager.Api.Responses;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Search;

/**
 * Created by vriva on 18/04/2018.
 */

public class SearchListResponse {
    private Integer size;
    private List<Search> searches;

    public SearchListResponse(Integer size, List<Search> searches) {
        this.size = size;
        this.searches = searches;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Search> getSearches() {
        return searches;
    }

    public void setSearches(List<Search> searches) {
        this.searches = searches;
    }
}
