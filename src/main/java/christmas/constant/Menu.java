package christmas.constant;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", MenuCategory.APPETIZER, 6000),
    TAPAS("타파스", MenuCategory.APPETIZER, 5500),
    CAESAR_SALAD("시저샐러드", MenuCategory.APPETIZER, 8000),

    TBONE_STEAK("티본스테이크", MenuCategory.MAIN_DISH, 55000),
    BBQ_RIB("바비큐립", MenuCategory.MAIN_DISH, 54000),
    SEAFOOD_PASTA("해산물파스타", MenuCategory.MAIN_DISH, 35000),
    XMAS_PASTA("크리스마스파스타", MenuCategory.MAIN_DISH, 25000),

    CHOCO_CAKE("초코케이크", MenuCategory.DESSERT, 15000),
    ICECREAM("아이스크림", MenuCategory.DESSERT, 5000),

    ZERO_COKE("제로콜라", MenuCategory.DRINK, 3000),
    REDWINE("레드와인", MenuCategory.DRINK, 60000),
    CHAMPAGNE("샴페인", MenuCategory.DRINK, 25000);


    private final String name;
    private final MenuCategory category;
    private final int price;

    Menu(String name, MenuCategory category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public MenuCategory getCategory() {
        return this.category;
    }

    public int getPrice() {
        return this.price;
    }

}
