package ask.group.code.controller;

public class EmployeeDidNotExist extends RuntimeException {
    public EmployeeDidNotExist(String msg) {
        super(msg);
    }
}
