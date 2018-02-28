package mock;

public class UserAction {


    private UserService userService;

    public UserAction() {
    }

    public UserAction(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void executeForPublic(String something) {
        userService.sayHi(something);
        System.out.println(userService.sayHello(something));
    }

    public void executeForPrivate(String something) {
        userService.secreteSay(something);
    }

}
