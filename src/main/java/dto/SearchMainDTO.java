package dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchMainDTO {

    public List<String> topics;
    public SearchResults searchResults;

}
