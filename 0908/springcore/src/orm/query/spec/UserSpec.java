package orm.query.spec;

import java.util.ArrayList;
import java.util.List;

import model.User;
import orm.query.Query;
import orm.query.QueryFactory;
import orm.utils.StringUtils;

public class UserSpec {

	public UserSpec() {
		 
	}
	
    public static Query<User> findAuth(User user) {
        List<Query<User>> queries = new ArrayList<>();
        queries.add(withUsername(user.getUsername()));
        queries.add(withPassword(user.getPassword()));
        return QueryFactory.and(queries);


    }

    public static Query<User> withUsername(String username) {
		 if (StringUtils.isEmpty(username)) return null; 

        return QueryFactory.eq("username", username);
    }

    public static Query<User> withPassword(String password) {
		  if (StringUtils.isEmpty(password)) return null;  

        return QueryFactory.eq("password", password);
    }
	
	

}
