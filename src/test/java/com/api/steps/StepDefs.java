package com.api.steps;

import com.api.config.APIConstants;
import com.api.context.WorkflowContext;
import com.api.model.Post;
import com.api.model.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
/*StepDefs class contains step definitons for cucumber scenarios.
WorkflowContext is used to share data across multiple steps within a scenario */
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

    @When("I search for user with username {string}")
    public void searchForUser(String username) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(APIConstants.BASE_URL)
                .get(APIConstants.USERS_ENDPOINT);
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
    @And("validate the user emailId")
    public void verifyEmail(){
        User user = context.getCurrentUser();
        //String domain=user.getEmail().split("@")[1];
        Assert.assertTrue(Pattern.compile(APIConstants.EMAIL_REGEX)
                .matcher(user.getEmail())
                .matches(),"Email domain shojld be valid");
    }

    @When("I retrieve all posts written by the user")
    public void retrieveUserPosts() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(APIConstants.BASE_URL)
                .queryParam("userId", context.getCurrentUser().getId())
                .get(APIConstants.POSTS_ENDPOINT);

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

    @And("each post should have required fields")
    public void verifyPostStructure() {
        context.getUserPosts().forEach(post -> {
            Assert.assertTrue(post.getId() > 0, "Post ID should be positive");
            Assert.assertEquals(post.getUserId(),
                    context.getCurrentUser().getId(),
                    "Post should belong to the correct user");
            Assert.assertNotNull(post.getTitle(), "Title should not be null");
            Assert.assertNotNull(post.getBody(), "Body should not be null");
        });
    }

    @And("post count should be greater than {int}")
    public void verifyPostCount(int minCount) {
        Assert.assertTrue(context.getUserPosts().size() > minCount,
                "User should have more than " + minCount + " posts");
    }
}
