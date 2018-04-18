package riva.vincent.outerspacemanager.Search;

/**
 * Created by vriva on 18/04/2018.
 */

public interface SearchPresenter {

    void getSearches(String token);
    void createSearch(String token, Integer searchId);
}
