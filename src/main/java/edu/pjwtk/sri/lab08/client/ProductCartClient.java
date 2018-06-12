package edu.pjwtk.sri.lab08.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import edu.pjwstk.sri.lab08.OrderItem;
import edu.pjwstk.sri.lab08.ProductCart;

public class ProductCartClient {
	  public static void main(String [] args) {

		   
		    try {
		      TTransport transport;
		     
		      transport = new TSocket("localhost", 9090);
		      transport.open();

		      TProtocol protocol = new  TBinaryProtocol(transport);
		      ProductCart.Client client = new ProductCart.Client(protocol);

		      perform(client);

		      transport.close();
		    } catch (TException x) {
		      x.printStackTrace();
		    } 
		  }

		  private static void perform(ProductCart.Client client) throws TException
		  {
		   OrderItem it = new OrderItem(1l, 100);
		   client.addItem(it);
//		    int product = client.multiply(3,5);
//		    System.out.println("3*5=" + product);
		  }
		}
