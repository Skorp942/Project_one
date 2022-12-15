package com.learning.post.mapper;

import com.learning.entity.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PostMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        Post post = new Post();

        post.setId(rs.getLong("id"));
        post.setTitle(rs.getString("title"));
        post.setAnons(rs.getString("anons"));
        post.setFul_text(rs.getString("ful_text"));
        post.setViews(rs.getInt("views"));

        return post;
    }
}
