package christmas.service;

import christmas.constant.Menu;
import christmas.domain.Order;
import christmas.util.OrderValidator;
import java.util.List;
import java.util.Map;

public class OrderService {
    public List<Order> createMultipleOrder(Map<String, Integer> multiOrder) {
        OrderValidator.validateOrder(multiOrder);
        return multiOrder.entrySet().stream()
            .map(entry -> createOrder(entry.getKey(), entry.getValue()))
            .toList();
    }

    private Order createOrder(String menuName, int amount) {
        return new Order(Menu.fromName(menuName), amount);
    }
}
