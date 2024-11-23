package com.api.context;
import com.api.model.Comments;
import com.api.model.User;
import com.api.model.Post;
import lombok.Data;
import java.util.List;
/**
 * This class serves as a shared storage for data between different step definition classes.
 * It holds the current user, their posts, any error messages, and Id.
 */
@Data
public class WorkflowContext {
    private User currentUser;
    private List<Post> userPosts;
    private String errorMessage;
    private int Id;
    private int postId;
    private List<Comments> comments;
}
