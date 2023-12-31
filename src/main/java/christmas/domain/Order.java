package christmas.domain;

import christmas.constant.Menu;

public class Order {
    private final Menu menu;
    private final int amount;

    public Order(Menu menu, int amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public int getPrice() {
        return menu.getPrice() * amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public String toString() {
        return String.format("%s %d개", menu.getName(), amount);
    }
}
