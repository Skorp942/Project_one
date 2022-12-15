package com.learning.post;

import com.learning.dao.mapper.UserMapper;
import com.learning.entity.Post;
import com.learning.entity.User;
import com.learning.post.mapper.PostMapper;
import com.learning.util.paginated.PaginatedListHelper;
import com.learning.util.paginated.SimplePaginatedList;
import com.learning.web.post.PostForm;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("PostRepository")
public class PostRepositoryImpl extends BasePostImpl implements PostRepository {


    public SimplePaginatedList getTitle(PostForm form){
        String sql = "select count(distinct p.title) from post p";
        int total = -1;
        if (form.getPageSize() != -1) {
            total = getJdbcTemplate().queryForObject(sql, Integer.class);
            form.fixPageNumber(total);
        }

        StringBuilder sql2 = new StringBuilder();

        sql2.append("select * from post p ");

        if (form.getPageSize() > 0) {
            sql2.append(" \nlimit ");
            if (form.getFirstResult() > 0)
                sql2.append(form.getFirstResult())
                        .append(", ");
            sql2.append(form.getPageSize());
        }

        List list = getJdbcTemplate().query(sql2.toString(), new PostMapper());
        if (total == -1)
            total = list.size();
        return PaginatedListHelper.getPaginatedList(list, total, form);
    }

    public void saveOrUpdate(final Post post) {
        if(post!=null && post.getTitle()!=null && post.getFul_text()!=null ) {
            if(post.getId() > 0) {
                String update = "update post set" +
                        " title = ?, anons = ?, ful_text = ?, views = ?" +
                        " where id = ?";
                getJdbcTemplate().update(update, new Object[]{
                        post.getTitle(),
                        post.getAnons(),
                        post.getFul_text(),
                        post.getViews(),
                        post.getId()
                        }
                );
            } else {
                String insert = "insert into post (" +
                        " title, anons , ful_text , views) " +
                        " VALUES('"+post.getTitle()+"', '"
                        +post.getAnons()+"','"
                        +post.getFul_text()+"','"
                        +post.getViews()+"',"
                        +post.isEnabled()+")";

                KeyHolder keyHolder = new GeneratedKeyHolder();
                getJdbcTemplate().update(
                        con -> {
                            PreparedStatement pst = con.prepareStatement(insert,new String[]{"id"});
                            return pst;
                        },
                        keyHolder
                );
                post.setId((long) keyHolder.getKey().intValue());
            }
        }
    }
}



