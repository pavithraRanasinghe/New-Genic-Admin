package lk.robot.newgenicadmin.dto.request;

public class ProductPriceRequestDTO {

    private long combinationId;
    private int stock;
    private double weight;
    private double buyingPrice;
    private double salePrice;
    private double retailPrice;
    private boolean active;

    public ProductPriceRequestDTO() {
    }

    public ProductPriceRequestDTO(long combinationId,
                                  int stock,
                                  double weight,
                                  double buyingPrice,
                                  double salePrice,
                                  double retailPrice,
                                  boolean active) {
        this.combinationId = combinationId;
        this.stock = stock;
        this.weight = weight;
        this.buyingPrice = buyingPrice;
        this.salePrice = salePrice;
        this.retailPrice = retailPrice;
        this.active = active;
    }

    public long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(long combinationId) {
        this.combinationId = combinationId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProductPriceRequestDTO{" +
                "combinationId=" + combinationId +
                ", stock=" + stock +
                ", weight=" + weight +
                ", buyingPrice=" + buyingPrice +
                ", salePrice=" + salePrice +
                ", retailPrice=" + retailPrice +
                ", active=" + active +
                '}';
    }
}
