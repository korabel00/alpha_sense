package dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchResults {

    public String cursorToken;
    public Long originalStatementCount;
    public List<Statement> statements;
}
