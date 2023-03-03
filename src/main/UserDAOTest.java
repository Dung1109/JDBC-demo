package main;

import DAO.UserDAOImpl;
import org.junit.jupiter.api.Test;

public class UserDAOTest {

    static UserDAOImpl userDAO = new UserDAOImpl();

    @Test
    public void test_login_user() {
        boolean login1 = userDAO.checkLogin("huypn", "123");
        System.out.println("login1 = " + login1);

        boolean login2 = userDAO.checkLogin("huypn' AND 1 = 1 --", "123");
        System.out.println("login2 = " + login2);
    }
}
