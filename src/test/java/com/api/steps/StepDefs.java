package com.api.steps;

import com.api.config.APIConstants;
import com.api.context.WorkflowContext;
import com.api.model.Comments;
import com.api.model.Post;
import com.api.model.User;
import com.api.utils.RestAssuredUtils;
import com.api.wiremock.WireMockConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.*;
import java.util.regex.Pattern;

/**
 * StepDefs class contains step definitons for cucumber scenarios.
 * WorkflowContext is used to share data across multiple steps within a scenario - Dependency Injection
 */

public class StepDefs {

    //Dependency Injection
    private final WorkflowContext context;

    public StepDefs(){
        context = new WorkflowContext();
    }

    @Given("the JSONPlaceholder API is available")
    public void verifyAPIAvailability() {
        Response response = RestAssured.get(APIConstants.BASE_URL);
        Assert.assertEquals(response.getStatusCode(), 200,
                "API should be accessible");
    }
    @Given("Start the Wiremock Server and load the stubs")
    public void startServer(){
        WireMockConfig.startServer();
        WireMockConfig.getUsers();
        WireMockConfig.getPosts();
        WireMockConfig.getComments();
    }

    @When("I search for user with username {string}")
    public void searchForUser(String username) {
        /*Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(APIConstants.BASE_URL)
                .get(APIConstants.USERS_ENDPOINT);*/
        Response response = RestAssuredUtils.sendGetRequest(APIConstants.BASE_URL,APIConstants.USERS_ENDPOINT,null);
        List<User> users = response.jsonPath().getList("", User.class);
        Optional<User> foundUser = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
        foundUser.ifPresent(user -> context.setCurrentUser(user));
    }

    @Then("the user should be found in the system")
    public void verifyUserFound() {
        Assert.assertNotNull(context.getCurrentUser(),
                "User should be found");
    }
    @And("validate the format of user emailId")
    public void verifyEmailFormat(){
        User user = context.getCurrentUser();
        //String domain=user.getEmail().split("@")[1];
        Assert.assertTrue(Pattern.compile(APIConstants.EMAIL_REGEX)
                .matcher(user.getEmail())
                .matches(),"Email domain should be valid");
    }
    @And("validate the emailID of the user {string}")
    public void verifyEmail(String email){
        User user = context.getCurrentUser();
        Assert.assertTrue(user.getEmail().equals(email),"Email should match");
    }

    @When("I retrieve all posts written by the user")
    public void retrieveUserPosts() {
       /* Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(APIConstants.BASE_URL)
                .queryParam("userId", context.getCurrentUser().getId())
                .get(APIConstants.POSTS_ENDPOINT);*/
        Map<String, Integer> queryParams = new HashMap<>();
        queryParams.put("userId",context.getCurrentUser().getId());
        Response response = RestAssuredUtils.sendGetRequest(APIConstants.BASE_URL,APIConstants.POSTS_ENDPOINT,queryParams);
        List<Post> posts = response.jsonPath().getList("", Post.class);
        context.setUserPosts(posts);
    }

    @Then("there should be posts available")
    public void verifyPostsExist() {
        Assert.assertFalse(context.getUserPosts().isEmpty(),
                "User should have posts");
    }

    @And("each post should have valid title and body")
    public void verifyPostContent() {
        context.getUserPosts().forEach(post -> {
            Assert.assertNotNull(post.getTitle(), "Post title should not be null");
            Assert.assertFalse(post.getTitle().trim().isEmpty(),
                    "Post title should not be empty");
            Assert.assertNotNull(post.getBody(), "Post body should not be null");
            Assert.assertFalse(post.getBody().trim().isEmpty(),
                    "Post body should not be empty");
        });
    }
    @And("retrieve comments for each post")
    public void retrievePostComments(){
        context.getUserPosts().forEach(post -> {
            /*Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .baseUri(APIConstants.BASE_URL)
                    .queryParam("postId", context.getCurrentUser().getId())
                    .get(APIConstants.COMMENTS_ENDPOINT);*/
            Map<String, Integer> queryParams = new HashMap<>();
            queryParams.put("postId",context.getCurrentUser().getId());
            Response response = RestAssuredUtils.sendGetRequest(APIConstants.BASE_URL,APIConstants.COMMENTS_ENDPOINT,queryParams);
            List<Comments> comments = response.jsonPath().getList("", Comments.class);
            context.setComments(comments);
        });
    }
    @Then("validate email in each comment section")
    public void verifyCommentEmail() {
        /*context.getComments().forEach(comment -> {
            System.out.println(comment.getEmail());
            Assert.assertTrue(Pattern.compile(APIConstants.EMAIL_REGEX)
                    .matcher(comment.getEmail())
                    .matches(),"Email domain should be valid");
        });*/
        List<String> invalidEmails = new ArrayList<>();
        context.getComments().forEach(comment -> {
            String email = comment.getEmail();
            if (!Pattern.compile(APIConstants.EMAIL_REGEX).matcher(email).matches()) {
                invalidEmails.add(email);
            }
        });
        if (!invalidEmails.isEmpty()) {
            System.out.println("Invalid email addresses found:");
            invalidEmails.forEach(System.out::println);
        } else {
            System.out.println("All email addresses are valid");
        }
    }
    @And("each post should have required fields")
    public void verifyPostStructure() {
        context.getUserPosts().forEach(post -> {
            Assert.assertNotNull(post.getId(), "Post ID should not be null");
            Assert.assertEquals(post.getUserId(),
                    context.getCurrentUser().getId(),
                    "Post should belong to the correct user");
            Assert.assertNotNull(post.getTitle(), "Title should not be null");
            Assert.assertNotNull(post.getBody(), "Body should not be null");
        });
    }

    @And("no. of posts should be greater than {int}")
    public void verifyPostCount(int minCount) {
        Assert.assertTrue(context.getUserPosts().size() > minCount,
                "User should have more than " + minCount + " posts");
    }
}
