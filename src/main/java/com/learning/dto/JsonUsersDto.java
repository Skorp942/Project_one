package com.learning.dto;


import com.learning.util.paginated.SimplePaginatedList;

import java.util.ArrayList;
import java.util.List;

public class JsonUsersDto {

    private List users  = new ArrayList<>();

    public List getUsers() {
        return users;
    }

    public void setUsers(List users) {
        this.users = users;
    }

}