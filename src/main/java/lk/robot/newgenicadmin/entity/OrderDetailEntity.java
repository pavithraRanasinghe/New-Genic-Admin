package lk.robot.newgenicadmin.entity;

import javax.persistence.*;


@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private long orderDetailId;
    @Column
    private int quantity;
    @Column(name = "order_price")
    private double orderPrice;
    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private ProductEntity productEntity;
    @ManyToOne
    @JoinColumn(name = "fk_order_id")
    private OrderEntity orderEntity;
    @OneToOne
    @JoinColumn(name = "return_request_id",referencedColumnName = "return_request_id")
    private ReturnEntity returnEntity;

    public OrderDetailEntity() {
    }

    public OrderDetailEntity(long orderDetailId,
                             int quantity,
                             double orderPrice,
                             ProductEntity productEntity,
                             OrderEntity orderEntity,
                             ReturnEntity returnEntity) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.productEntity = productEntity;
        this.orderEntity = orderEntity;
        this.returnEntity = returnEntity;
    }

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public ReturnEntity getReturnEntity() {
        return returnEntity;
    }

    public void setReturnEntity(ReturnEntity returnEntity) {
        this.returnEntity = returnEntity;
    }

    @Override
    public String toString() {
        return "OrderDetailEntity{" +
                "orderDetailId=" + orderDetailId +
                ", quantity=" + quantity +
                ", orderPrice=" + orderPrice +
                ", productEntity=" + productEntity +
                ", orderEntity=" + orderEntity +
                ", returnEntity=" + returnEntity +
                '}';
    }
}
