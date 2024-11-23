package com.api.context;
import com.api.model.User;
import com.api.model.Post;
import lombok.Data;
import java.util.List;

//Shared storage for data between different step definition classes
@Data
public class WorkflowContext {
    private User currentUser;
    private List<Post> userPosts;
    private String errorMessage;
    private int Id;
}
