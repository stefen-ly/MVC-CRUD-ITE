import controller.UserController;
import mapper.UserMapper;
import model.dao.UserDao;
import model.dao.UserDaoImpl;
import model.service.UserService;
import model.service.serviceimpl.UserServiceImpl;
import view.Utils;

public class Application {

    public static void main(String[] args) {

        UserMapper userMapper = new UserMapper();

        UserDao userDao = new UserDaoImpl(userMapper);

        UserService userService =
                new UserServiceImpl(userDao, userMapper);

        Utils utils = new Utils();

        UserController controller =
                new UserController(userService, utils);

        controller.start();
    }
}
