package com.example.serviceA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ServiceAApplication  extends SpringBootServletInitializer {

     public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext =SpringApplication.run(ServiceAApplication.class, args);
       readCSV();
    }
    static void readCSV() throws IOException {
        BufferedReader reader = null;


        InputStream inputStream = ServiceAApplication.class.getClassLoader().getResourceAsStream("test.csv");
        reader = new BufferedReader(new InputStreamReader(inputStream));


        List<Order> csv_objectList = new ArrayList<>();

        try {
            long start = System.currentTimeMillis();
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Order order = new Order();
                order.setId(data[0]);
                order.setEmail(data[1]);
                String ph = data[2].trim();
                order.setPhone_number(ph);
                order.setParcel_weight(Double.valueOf(data[3]));

                if (ph.startsWith("237")) {
                    order.setCountry("Cameroon");
                } else if (ph.startsWith("256")) {
                    order.setCountry("Uganda");
                } else if (ph.startsWith("251")) {
                    order.setCountry("Ethiopia");
                } else if (ph.startsWith("212")) {
                    order.setCountry("Morocco");
                } else if (ph.startsWith("258")) {
                    order.setCountry("Mozambique");
                }
                csv_objectList.add(order);
            }
            long end = System.currentTimeMillis();

            System.out.print("taken time" + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // call service B
        new RestTemplate().postForEntity("http://localhost:8081/orders/save", csv_objectList, void.class);
    }



}
