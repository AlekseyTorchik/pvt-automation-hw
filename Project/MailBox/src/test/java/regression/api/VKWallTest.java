package regression.api;

import core.parser.AdditionalDataHandler;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.AdditionalData;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class VKWallTest {
    public static final String ADDITIONALDATA_XML = "AdditionalData.xml";
    private static Logger logger = Logger.getLogger(VKWallTest.class);
    private String methodNameFirstStep;
    private String methodNameSecondStep;
    private String methodNameThirdStep;
    private String accessToken;
    private String ownerIdNumber;
    private String messageCreate;
    private String messageEdit;
    private String versionAPI;
    private String postId;
    private String postIdDelete;

    @Given("^I create data for API$")
    public void parseDataForAPI() throws IOException, URISyntaxException, SAXException, ParserConfigurationException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        AdditionalDataHandler additionalDataHandler = new AdditionalDataHandler();
        saxParser.parse(new File(ADDITIONALDATA_XML), additionalDataHandler);
        List<AdditionalData> fields = additionalDataHandler.getFields();
        methodNameFirstStep = fields.get(1).getMethodNameFirstStep();
        methodNameSecondStep = fields.get(1).getMethodNameSecondStep();
        methodNameThirdStep = fields.get(1).getMethodNameThirdStep();
        accessToken = fields.get(1).getAccessToken();
        ownerIdNumber = fields.get(1).getOwnerIdNumber();
        messageCreate = fields.get(1).getMessageCreate();
        messageEdit = fields.get(1).getMessageEdit();
        versionAPI = fields.get(1).getVersionAPI();
    }

    @When("^I post on wall$")
    public void createPostOnWall() throws IOException, URISyntaxException {
        postId = createSomeMessageAtWall(methodNameFirstStep, accessToken, ownerIdNumber, messageCreate, versionAPI);
    }

    @Then("^post is create$")
    public void CreatePostTest() {
        Assert.assertNotEquals("ERROR! Post don't posted!", "", postId);
        logger.info("Check create post");
    }

    @And("^I edit post$")
    public void editPostOnWall() throws IOException, URISyntaxException {
        editSomeMessageAtWall(methodNameSecondStep, accessToken, ownerIdNumber, postId, messageEdit, versionAPI);
    }

    @And("^I delete post$")
    public void deletePostFromWall() throws IOException, URISyntaxException {
        postIdDelete = deleteSomeMessageAtWall(methodNameThirdStep, accessToken, ownerIdNumber, postId, versionAPI);
    }

    @Then("^post is delete$")
    public void DeletePostTest() {
        Assert.assertEquals("ERROR! Post don't delete!", "1", postIdDelete);
        logger.info("Check delete post");
    }

    public String createSomeMessageAtWall(String methodName, String setAccessToken, String ownerId, String message,
                                          String version) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builderCreatePost = new URIBuilder(methodName);
        builderCreatePost.setParameter("access_token", setAccessToken)
                .setParameter("owner_id", ownerId)
                .setParameter("message", message)
                .setParameter("v", version);
        HttpGet request = new HttpGet(builderCreatePost.build());
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        String postIdNumber = result.replaceAll("\\D", "");
        return postIdNumber;
    }

    public String editSomeMessageAtWall(String methodName, String setAccessToken, String ownerId, String postIdN,
                                        String message, String version) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builderCreatePost = new URIBuilder(methodName);
        builderCreatePost.setParameter("access_token", setAccessToken)
                .setParameter("owner_id", ownerId)
                .setParameter("post_id", postIdN)
                .setParameter("message", message)
                .setParameter("v", version);
        HttpGet request = new HttpGet(builderCreatePost.build());
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        String postId = result.replaceAll("\\D", "");
        return postId;
    }

    public String deleteSomeMessageAtWall(String methodName, String setAccessToken, String ownerId, String postIdN,
                                          String version) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builderCreatePost = new URIBuilder(methodName);
        builderCreatePost.setParameter("access_token", setAccessToken)
                .setParameter("owner_id", ownerId)
                .setParameter("post_id", postIdN)
                .setParameter("v", version);
        HttpGet request = new HttpGet(builderCreatePost.build());
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        String postIdNumber = result.replaceAll("\\D", "");
        return postIdNumber;
    }
}