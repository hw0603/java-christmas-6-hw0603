package christmas.service;

import christmas.domain.Order;
import christmas.domain.User;
import christmas.domain.VisitDate;
import java.util.List;

public class UserService {
    public User createUser(List<Order> orders, VisitDate visitDate) {
        return new User(orders, visitDate);
    }
}
