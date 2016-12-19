package com.mkoshmanov.training.transport.daodb.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.mkoshmanov.training.transport.userdetails.AppUser;
import com.mkoshmanov.training.transport.userdetails.Role;

public class AppUserExtractor implements ResultSetExtractor<AppUser> {
    @Override
    public AppUser extractData(final ResultSet rs) throws SQLException, DataAccessException {
        while (rs.next()) {
            final Map<Long, AppUser> users = new HashMap<>();
            AppUser user = null;
            final Role role = new Role();
            final Long id = rs.getLong("id");
            user = users.get(id);
            if (user == null) {
                user = new AppUser();
                user.setId(id);
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                if (rs.getString("roleId") != null) {
                    role.setId(rs.getLong("roleId"));
                    role.setRole(rs.getString("role"));
                    user.setAuthorities(Arrays.asList(role));
                }
                users.put(id, user);
            }
            return user;
        }
        return null;
    }
}
