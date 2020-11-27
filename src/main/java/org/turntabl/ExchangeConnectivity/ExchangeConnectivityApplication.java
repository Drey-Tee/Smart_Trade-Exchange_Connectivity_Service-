package org.turntabl.ExchangeConnectivity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.turntabl.ExchangeConnectivity.model.ClientOrder;
import org.turntabl.ExchangeConnectivity.model.Order;
import org.turntabl.ExchangeConnectivity.model.OrderRequest;
import org.turntabl.ExchangeConnectivity.utility.MapTo;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;


@SpringBootApplication
public class ExchangeConnectivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeConnectivityApplication.class, args);

//		List<ClientOrder> clientOrders = new ArrayList<>();

//		Jedis publisher = new Jedis("192.168.128.237");
		Jedis orderQueue = new Jedis("192.168.128.237");
        int numb = 1;

        while (true){
            String data = orderQueue.rpop("ExchangeConnectivity");
            if(data == null){
				return;
            }
			System.out.println(data);
//			return;
            Order newOrder = Order.deserializer(data);

			System.out.println(newOrder.toString());
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setPrice(Double.parseDouble(newOrder.getPrice()));
			orderRequest.setProduct(newOrder.getProduct());
			orderRequest.setQuantity(Integer.parseInt(newOrder.getQuantity()));
			orderRequest.setSide(newOrder.getSide());
			System.out.println(orderRequest.toString());

			String url;
			if(newOrder.getMarketId() == 1){
            	url = "https://exchange.matraining.com";
			}else{
				url =  "https://exchange2.matraining.com";
			}


         String orderId =   placeOrder(url,orderRequest);
			if(orderId != null){
				System.out.println("Order placed successfully");
			}
        }

//        do {
//
//
//
//
//            String clientOrderString = orderQueue.rpop("ExchangeConnectivity");
//
////            System.out.println("Number " + numb);
//            System.out.println(clientOrderString);
//
//            if (clientOrderString != null) {
//
//                System.out.println(clientOrderString);
//
//                ClientOrder clientOrder = MapTo.convertToObject(clientOrderString, ClientOrder.class);
//
//                Order order = new Order(clientOrder.getTicker(), String.valueOf(clientOrder.getQuantity()), String.valueOf(clientOrder.getPrice()), clientOrder.getSide());
//
//                System.out.println(MapTo.convertToString(order));
//
//                String url = clientOrder.getUrl();
//                System.out.println(url);
//
//                String orderid = placeOrder(url, order);
//
//                System.out.println(orderid);
//
//                orderid = orderid.replace("\"", "");
//
//                clientOrder.setOrderKey(orderid);
//
//                System.out.println(MapTo.convertToString(clientOrder));
//
//                orderQueue.lpush("Placed Orders", MapTo.convertToString(clientOrder));//);
//
//            }else {
//                System.out.println("Nothing in Trading Engine queue.");
//            }
//
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            String updateOrderString = orderQueue.rpop("Placed Orders");
//
//            System.out.println(updateOrderString);
//
//            if (updateOrderString != null){
//                //update
//                ClientOrder uCliOrder = MapTo.convertToObject(updateOrderString, ClientOrder.class);
//            }
//
////            Order updOrder = new Order(uCliOrder.getTicker(),String.valueOf(uCliOrder.getQuantity()), String.valueOf(uCliOrder.getPrice()), uCliOrder.getSide());
////
////            System.out.println(updateOrder(uCliOrder.getUrl(), uCliOrder.getOrderKey(), updOrder));
////
////            //check
////            System.out.println(checkOrder(uCliOrder.getUrl(), uCliOrder.getOrderKey()));
////
////            //del
////            System.out.println(cancelOrder(uCliOrder.getUrl(), uCliOrder.getOrderKey()));
//
//
////			publisher.publish("Exchange Connectivity", "The order has been placed with an id of "+orderid);
//
//            numb++;
//        } while (true);


	}

	//Creating a webClient
	private static WebClient createClient(String url){

		return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
	}

	//Configured & Tested
	public static String placeOrder(String url, OrderRequest order){

		WebClient client = createClient(url);
		return client.post()
                .uri("/4e85d96c-43f1-4062-8d02-65b7a411ddc6/order")
                .body(BodyInserters.fromValue(order))
                .retrieve().bodyToMono(String.class).block();
	}





	//Configured, Not Tested
	public static String updateOrder(String url, String id, Order order){

		WebClient client = createClient(url);

		order.setProduct("ORCL");

        System.out.println("\nUpdating order with id : " + id);

        Boolean state = client.put()
				.uri("/4e85d96c-43f1-4062-8d02-65b7a411ddc6/order/" + id)
				.body(Mono.just(order), Order.class)
				.retrieve().bodyToMono(Boolean.class).block();

		if(!state){return "Could not update";}
		else return "Updated";
	}

	//Configured & Tested
	public static String cancelOrder(String url, String orderId){

		WebClient client = createClient(url);

        System.out.println("\nCancelling order with id : " + orderId);

        Boolean state = client.delete()
				.uri("/4e85d96c-43f1-4062-8d02-65b7a411ddc6/order/" + orderId)
                .retrieve().bodyToMono(Boolean.class)
                .block();

        if (!state) {
            return "Failed : No order by that id OR Could not reach exchange";
        } else { return "Cancelled";}


//		return "";
 	}

	//Configured, Not Tested
	public static String checkOrder(String url, String id){

        WebClient client = createClient(url);

        System.out.println("\nChecking order with id : " + id);

		return client.get()
                .uri("/4e85d96c-43f1-4062-8d02-65b7a411ddc6/order/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class).block();
	}

}
