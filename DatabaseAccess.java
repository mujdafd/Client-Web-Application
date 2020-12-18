package ca.sheridancollege.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.sheridancollege.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.User;

@Repository
public class DatabaseAccess {

   @Autowired
	private NamedParameterJdbcTemplate jdbc;

   public User findUserAccount(String email) {

	   MapSqlParameterSource parameters = new  MapSqlParameterSource();

	   String query = "select * from sec_user where email=:email";
	   parameters.addValue("email", email);
	   ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters, new
			   BeanPropertyRowMapper<User>(User.class));
	   if (users.size()>0)
	       return users.get(0);
	   else
	       return null;

   }

   public  List<String> getRolesByID(Long userId){

	   ArrayList<String> roles = new ArrayList<String>();
       MapSqlParameterSource parameters = new  MapSqlParameterSource();

       String query = "select user_role.userId, sec_role.roleName "
    		   + "FROM user_role, sec_role "
    		   + "WHERE user_role.roleId=sec_role.roleId "
    		   + "AND userId=:userId";

       parameters.addValue("userId",userId);
       List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
       for (Map<String, Object> row : rows) {
       	roles.add((String)row.get("roleName"));
       }
       	return roles;
   }

	public void addUser(User user) {
		MapSqlParameterSource parameters = new  MapSqlParameterSource();

		String query = "insert into sec_user(email, encryptedPassword, enabled) values(:email, :encryptedPassword, 1)";
		parameters.addValue("email", user.getEmail());
		parameters.addValue("encryptedPassword", EncryptPassword.encrypt(user.getPassword()));
		jdbc.update(query, parameters);
	}

	public void addUserRole(String email, int role) {
		MapSqlParameterSource parameters = new  MapSqlParameterSource();

		String query = "insert into user_role(userId, roleId) select userId, :roleId from sec_user where email = :email";
		parameters.addValue("roleId", role);
		parameters.addValue("email", email);
		jdbc.update(query, parameters);
	}


}
