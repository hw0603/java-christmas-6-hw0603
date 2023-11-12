package christmas.domain;

import java.util.List;

public class User {
    private final List<Order> orders;
    private final VisitDate visitDate;

    public User(List<Order> orders, VisitDate visitDate) {
        this.orders = orders;
        this.visitDate = visitDate;
    }

}
