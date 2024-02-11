package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

import static onlineShop.common.constants.OutputMessages.COMPONENT_TO_STRING;
import static onlineShop.common.constants.OutputMessages.PRODUCT_TO_STRING;

public abstract class BaseComponent extends BaseProduct implements Component {
    private int generation;
    protected BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }
    @Override
    public int getGeneration() {
        return this.generation;
    }

    @Override
    public String toString() {
        return String.format(PRODUCT_TO_STRING + COMPONENT_TO_STRING
                , getOverallPerformance(), getPrice(), this.getClass().getSimpleName()
                , getManufacturer(), getModel(),getId(), getGeneration());
    }
}
