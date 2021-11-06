package OrdersPackage;

import PromoPackage.*;

/**
 * This is an entity class that combines the PromoSetMeal object and the quantity specified by the customer
 */
public class OrderPromoItems {

    /**
     * The PromoSetMeal that is selected by the customer.
     */
    private PromoSetMeal promoItem;

    /**
     * The quantity of the PromoSetMeal the customer wants to buy.
     */
    private long quantity;

    /**
     * Creates the orderItem object by amalgamation of the MenuItem and quantity.
     * @param newPromoItem customer selected PromoSetMeal.
     * @param quantity quantity of the selected PromoSetMeal.
     */
    public OrderPromoItems(PromoSetMeal newPromoItem, long quantity) {
        promoItem = newPromoItem;
        this.quantity = quantity;
    }

    /**
     * Gets how many of the PromoSetMeal the customer has ordered.
     * @return Number of the PromoSetMeal ordered.
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the PromoSetMeal requested by the customer.
     * @param quantity quantity set by the customer.
     */
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the specific PromoSetMeal the customer ordered.
     * @return PromoSetMeal the customer ordered.
     */
    public PromoSetMeal getPromoItem() {
        return promoItem;
    }
}
