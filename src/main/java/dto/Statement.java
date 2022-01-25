package dto;

import lombok.Getter;

import java.util.List;

@Getter
public class Statement {

    public String accessionNumber;
    public List<String> collapsedStatements;
    public String content;
    public boolean context;
    public Integer page;
    public boolean recurring;
    public Integer snippetCount;
    public Integer snippetOffset;
    public String statementId;
    public Integer statementIndex;
    public Integer statementIndexOffset;
}
