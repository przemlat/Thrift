package edu.pjwtk.sri.lab08.server;

import edu.pjwstk.sri.lab08.OrderItem;
import edu.pjwstk.sri.lab08.ProductCart.Iface;
import edu.pjwstk.sri.lab08.ProductNotAvailableException;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class ProductCartHandler implements Iface {

    private List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public void addItem(OrderItem orderItem) throws TException, ProductNotAvailableException {
        orderItems.forEach(item -> {
            if (item.productId == orderItem.productId) {
                item.setNumberOfPieces(item.getNumberOfPieces() + orderItem.getNumberOfPieces());
            } else {
                orderItems.add(orderItem);
            }
        });
    }

    @Override
    public void removeFromCart(long productId) throws TException, ProductNotAvailableException {
        orderItems.forEach(item -> {
            if (item.productId == productId) {
                orderItems.remove(item);
            } else {
                System.out.println("There is no such an item in your cart.");
            }
        });
    }

    @Override
    public void changeNumberOfPieces(OrderItem orderItem) throws TException, ProductNotAvailableException {
        orderItems.forEach(item -> {
            if (item.productId == orderItem.productId) {
                item.setNumberOfPieces(orderItem.numberOfPieces);
            } else {
                System.out.println("There is no such an item in your cart.");
            }
        });
    }

    @Override
    public String confirmOrder() throws TException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Items in your cart: ");
        System.out.println(orderItems);
        orderItems.forEach(item -> stringBuilder.append(item.numberOfPieces + " of " + item.productId + "; "));
        return stringBuilder.toString();
    }
}
