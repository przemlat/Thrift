package edu.pjwtk.sri.lab08.server;

import edu.pjwstk.sri.lab08.Product;
import edu.pjwstk.sri.lab08.ProductListService.Iface;
import org.apache.thrift.TException;

import java.util.List;

public class ProductListServiceHandler implements Iface{
    @Override
    public List<Product> getProductsList() throws TException {
        return null;
    }
}
