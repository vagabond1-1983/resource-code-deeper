package com.vaga.java.stream.kata.exam;

import com.vaga.java.stream.kata.annotation.Easy;
import com.vaga.java.stream.kata.dataset.ClassicOnlineStore;
import com.vaga.java.stream.kata.entity.Customer;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description Customer相关的练习题
 * @Date 2019/4/17 17:44
 * @Version 1.0
 **/
public class CustomerExamTest extends ClassicOnlineStore {
    @Easy
    @Test
    public void findRichCustomers() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Create a {@link Stream} from customerList only including customer who has more budget than 10000.
         * Use lambda expression for Predicate and {@link Stream#filter} for filtering.
         */
        Stream<Customer> richCustomerStream = customerList.stream()
                .filter(t -> t.getBudget() > 10000);

        List<Customer> richCustomer = richCustomerStream.collect(toList());
        assertThat(richCustomer).hasSize(2);
        assertThat(richCustomer).contains(customerList.get(3)).contains(customerList.get(7));
    }

}
