package OrdersPackage;

import PromoPackage.*;

public class OrderPromoItems {
    private PromoSetMeal promoItem;
    private long quantity;

    public OrderPromoItems(PromoSetMeal newPromoItem, long quantity) {
        promoItem = newPromoItem;
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public PromoSetMeal getPromoItem() {
        return promoItem;
    }
}
