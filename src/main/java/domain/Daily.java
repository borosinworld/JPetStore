package domain;

import java.io.Serializable;
import java.util.Date;

public class Daily implements Serializable {

    private String account;
    private String category;
    private String product;
    private String item;
    private Date  date;
    private Date signoutdate;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    private String order;

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    private String cart;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getSignoutdate() {
        return signoutdate;
    }

    public void setSignoutdate(Date signoutdate) {
        this.signoutdate = signoutdate;
    }





}
