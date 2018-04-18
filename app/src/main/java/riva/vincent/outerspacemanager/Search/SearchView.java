package riva.vincent.outerspacemanager.Search;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.Api.Responses.Models.Search;

/**
 * Created by vriva on 18/04/2018.
 */

public interface SearchView {
    void showToastFailure();

    void displaySearchesInTheListView(List<Search> searches);
}
