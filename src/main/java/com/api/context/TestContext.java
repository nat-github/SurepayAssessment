package com.api.context;
import com.api.model.User;
import com.api.model.Post;
import lombok.Data;
import java.util.List;

@Data
public class TestContext {
    private User currentUser;
    private List<Post> userPosts;
    private String errorMessage;
}
