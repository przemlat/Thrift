package edu.pjwtk.sri.lab08.server;

import edu.pjwstk.sri.lab08.Product;
import edu.pjwstk.sri.lab08.ProductListService.Iface;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class ProductListServiceHandler implements Iface {

    private List<Product> productList = new ArrayList<>();

    @Override
    public List<Product> getProductsList() throws TException {
        return this.productList;
    }

    @Override
    public void stockMagazine() throws TException {
        this.productList.add(new Product(1, "Trousers", 100, 40));
        this.productList.add(new Product(2, "Gloves", 20, 30));
        this.productList.add(new Product(3, "T-shirt", 50, 20));
    }

    @Override
    public void addToMagazine(long productId, int numberOfPieces) throws TException {
        productList.forEach(product -> {
            if (product.id == productId) {
                product.numberOfPieces += numberOfPieces;
            }
        });
    }

    @Override
    public void removeFromMagazine(long productId, int numberOfPieces) throws TException {
        productList.stream().forEach(product -> {
            if (product.id == productId) {
                product.numberOfPieces -= numberOfPieces;
            }
        });
    }
}
