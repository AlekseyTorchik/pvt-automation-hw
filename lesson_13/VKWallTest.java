import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.URISyntaxException;

public class VKWallTest {
    private String methodNameFirstStep = "https://api.vk.com/method/wall.post?";
    private String methodNameSecondStep = "https://api.vk.com/method/wall.edit?";
    private String methodNameThirdStep = "https://api.vk.com/method/wall.delete?";
    private String accessToken = "cdcc44f33a1899d45a8a213b9cf445f43fdf555125dcf8af24370051617788a1087cac944386a3437851b";
    private String ownerIdNumber = "13633739";
    private String messageCreate = "VK test";
    private String messageEdit = "test VK";
    private String versionAPI = "5.102";

    @Test
    public void testPostSomeMessageToWall() throws IOException, URISyntaxException {
        String postId = createSomeMessageAtWall(methodNameFirstStep, accessToken, ownerIdNumber, messageCreate, versionAPI);
        Assert.assertNotEquals("ERROR! Post don't posted!","",postId);
        editSomeMessageAtWall(methodNameSecondStep, accessToken, ownerIdNumber, postId, messageEdit, versionAPI);
        String postIdDelete = deleteSomeMessageAtWall(methodNameThirdStep,accessToken, ownerIdNumber, postId, versionAPI);
        Assert.assertEquals("ERROR! Post don't delete!", "1", postIdDelete);
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

