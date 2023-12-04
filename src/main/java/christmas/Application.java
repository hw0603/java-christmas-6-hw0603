package christmas;

import christmas.controller.PlannerController;
import christmas.service.BadgeService;
import christmas.service.CalendarService;
import christmas.service.DiscountService;
import christmas.service.GiveawayService;
import christmas.service.OrderService;
import christmas.service.UserService;

public class Application {
    public static void main(String[] args) {
        PlannerController plannerController = new PlannerController(
                new CalendarService(),
                new OrderService(),
                new UserService(),
                new DiscountService(),
                new GiveawayService(),
                new BadgeService()
        );
        plannerController.start();
        plannerController.plan();
    }
}
