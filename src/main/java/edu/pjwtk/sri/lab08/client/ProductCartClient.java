package edu.pjwtk.sri.lab08.client;

import edu.pjwstk.sri.lab08.OrderItem;
import edu.pjwstk.sri.lab08.Product;
import edu.pjwstk.sri.lab08.ProductCart;
import edu.pjwstk.sri.lab08.ProductListService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;

import java.util.List;

public class ProductCartClient {
    public static void main(String[] args) {

        try {
            TSocket transport;
            transport = new TSocket("localhost", 9090);
            transport.open();

            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol protocolCart = new TMultiplexedProtocol(protocol, "ProductCart");
            ProductCart.Client client = new ProductCart.Client(protocolCart);

            TMultiplexedProtocol protocolList = new TMultiplexedProtocol(protocol, "ProductListService");
            ProductListService.Client clientForList = new ProductListService.Client(protocolList);

            System.out.println("Products in magazine:");

            clientForList.stockMagazine();
            List<Product> productList = clientForList.getProductsList();
            productList.forEach(product ->
                    System.out.println(product.name + " of id: " + product.id + " : " + product.numberOfPieces + " pieces"));

            perform(client, clientForList);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(ProductCart.Client client, ProductListService.Client clientForList) throws TException {
        OrderItem orderItem1 = new OrderItem(1, 2);
        clientForList.removeFromMagazine(1, 2);
        client.addItem(orderItem1);

        OrderItem orderItem2 = new OrderItem(2, 5);
        clientForList.removeFromMagazine(2, 5);
        client.addItem(orderItem2);

        OrderItem orderItem3 = new OrderItem(2, 3);
        clientForList.addToMagazine(2, 2);
        client.changeNumberOfPieces(orderItem3);

        client.removeFromCart(orderItem1.productId);

        System.out.println("\nProducts in magazine update:");
        List<Product> productList = clientForList.getProductsList();
        productList.forEach(product ->
                System.out.println(product.name + " of id: " + product.id + " : " + product.numberOfPieces + " pieces"));
    }
}
