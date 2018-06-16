package edu.pjwtk.sri.lab08.server;

import edu.pjwstk.sri.lab08.ProductCart;
import edu.pjwstk.sri.lab08.ProductListService;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
// Generated code

public class ProductCartServer {

    public static ProductCartHandler handler;
    public static ProductCart.Processor processor;

    public static ProductListServiceHandler handlerForList;
    public static ProductListService.Processor processorForList;

    public static void main(String[] args) {
        try {
            handler = new ProductCartHandler();
            processor = new ProductCart.Processor(handler);
            handlerForList = new ProductListServiceHandler();
            processorForList = new ProductListService.Processor(handlerForList);

            Runnable multi = new Runnable() {
                public void run() {
                    multi(processor, processorForList);
                }
            };

            new Thread(multi).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void multi(ProductCart.Processor processor, ProductListService.Processor processorForList) {
        try {
            TMultiplexedProcessor multiplexedProcessor = new TMultiplexedProcessor();
            multiplexedProcessor.registerProcessor("ProductCart", processor);
            multiplexedProcessor.registerProcessor("ProductListService", processorForList);

            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(multiplexedProcessor));

            System.out.println("Starting the server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}