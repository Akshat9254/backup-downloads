package com.reddit.repository;

import com.reddit.model.Comment;
import com.reddit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByParentIsNullAndPost(Post post);
    List<Comment> findAllByParent(Comment parent);
}
