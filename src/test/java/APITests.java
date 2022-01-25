import dto.SearchMainDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api")
@Tag("smoke")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class APITests {

    private Response response;
    private SearchMainDTO searchMainDTO;
    private String accessionNumber = "PR-386ea743f2a90399fb0e4300ddf37d0697abc743";
    private String docPath = "services/i/public-document-data/document/" + accessionNumber + "/keyword-search/?keyword=alphasense&slop=15&positiveOnly=false&negativeOnly=false&released=1633003200000";

    @BeforeAll
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl");
        response = given()
                .contentType(ContentType.JSON)
                .param("keyword", "alphasense")
                .param("slop", "15")
                .param("positiveOnly", "false")
                .param("negativeOnly", "false")
                .param("released", "1633003200000")
                .when()
                .get(docPath)
                .then()
                .extract()
                .response();

        searchMainDTO = response.as(SearchMainDTO.class);
    }

    @Test
    public void getStatusCode() {
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    public void checkNumberOfEntriesInResponse() {
        int size = searchMainDTO.getSearchResults().getStatements().size();
        assertThat(size).isEqualTo(16);
    }

    @Test
    public void checkLastEntryText() {
        int size = searchMainDTO.getSearchResults().getStatements().size();
        String text = searchMainDTO.getSearchResults().getStatements().get(size - 1).getContent();
        assertThat(text).isEqualTo("Logo - <span class='hl'>https://mma.prnewswire.com/media/947841/AlphaSense_Logo.jpg</span>");
    }

    @Test
    public void checkAccessionNumberAllMatch() {
        assertThat(searchMainDTO.getSearchResults().getStatements().stream()
                .allMatch(entry -> entry.accessionNumber.equals(accessionNumber))).isTrue();
    }
}
