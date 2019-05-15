package com.vaga.java.java8.stream.kata.exam;

import com.vaga.java.common.annotation.Difficult;
import com.vaga.java.common.annotation.Easy;
import com.vaga.java.java8.stream.kata.dataset.ClassicOnlineStore;
import com.vaga.java.java8.stream.kata.entity.Customer;
import com.vaga.java.java8.stream.kata.entity.Item;
import com.vaga.java.java8.stream.kata.entity.Shop;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description Customer相关的练习题
 * @Date 2019/4/17 17:44
 * @Version 1.0
 **/
public class CustomerExamTest extends ClassicOnlineStore {
    private List<Customer> customerList;
    private List<Shop> shopList;

    @BeforeClass
    public void init() {
        shopList = this.mall.getShopList();
        customerList = this.mall.getCustomerList();
        customerList.stream()
                .flatMap(c -> c.getWantToBuy().stream())
                .forEach(i -> {
                    OptionalInt price = shopList.stream()
                            .flatMap(s -> s.getItemList().stream())
                            .filter(si -> si.equals(i))
                            .mapToInt(Item::getPrice).findAny();
                    i.setPrice(price.orElse(0));
                });
    }

    /**
     * Create a {@link Stream} from customerList only including customer who has more budget than 10000.
     * Use lambda expression for Predicate and {@link Stream#filter} for filtering.
     */
    @Easy
    @Test
    public void findRichCustomers() {
        Stream<Customer> richCustomerStream = customerList.stream()
                .filter(t -> t.getBudget() > 10000);

        List<Customer> richCustomer = richCustomerStream.collect(toList());
        assertThat(richCustomer).hasSize(2);
        assertThat(richCustomer).contains(customerList.get(3)).contains(customerList.get(7));
    }

    /**
     * Create a {@link Stream} from customerList with age values.
     * Use method reference(best) or lambda expression(okay) for creating {@link Function} which will
     * convert {@link Customer} to {@link Integer}, and then apply it by using {@link Stream#map}.
     */
    @Easy @Test
    public void howOldAreTheCustomers() {
        Stream<Integer> ageStream = customerList.stream()
                .map(Customer::getAge);

        List<Integer> ages = ageStream.collect(Collectors.toList());
        assertThat(ages).hasSize(10);
        assertThat(ages).containsExactlyInAnyOrder(22, 27, 28, 38, 26, 22, 32, 35, 21, 36);
    }

    /**
     * Create a stream with ascending ordered age values.
     * Use {@link Stream#sorted} to sort them.
     */
    @Easy @Test
    public void sortByAge() {
        Stream<Integer> sortedAgeStream = customerList.stream()
                .map(Customer::getAge)
                .sorted();

        List<Integer> sortedAgeList = sortedAgeStream.collect(Collectors.toList());
        assertThat(sortedAgeList).containsSequence(21, 22, 22, 26, 27, 28, 32, 35, 36, 38);
    }

    /**
     * Create a stream with descending ordered age values.
     */
    @Easy @Test
    public void descSortByAge() {
        Stream<Integer> sortedAgeStream = customerList.stream()
                .map(Customer::getAge)
                .sorted(reverseOrder());

        List<Integer> sortedAgeList = sortedAgeStream.collect(Collectors.toList());
        assertThat(sortedAgeList).containsSequence(38, 36, 35, 32, 28, 27, 26, 22, 22, 21);
    }

    /**
     * Create a stream with top 3 rich customers using {@link Stream#limit} to limit the size of the stream
     */
    @Easy @Test
    public void top3RichCustomer() {
        Stream<String> top3RichCustomerStream = customerList.stream()
                .sorted(((o1, o2) -> o2.getBudget() - o1.getBudget()))
                .limit(3)
                .map(Customer::getName);

        List<String> top3RichCustomerList = top3RichCustomerStream.collect(Collectors.toList());
        assertThat(top3RichCustomerList).containsSequence("Diana", "Andrew", "Chris");
    }

    /**
     * Find the richest customer's budget by using {@link Stream#max} and {@link Comparator#naturalOrder}
     * Don't use {@link Stream#sorted}
     */
    @Easy @Test
    public void richestCustomer() {
        Optional<Integer> richestCustomer = customerList.stream()
                .map(Customer::getBudget)
                .max(naturalOrder());
        assertThat(richestCustomer.get()).isEqualTo(12000);

        // 上面写法不好
        OptionalInt richestBudget = customerList.stream()
                .mapToInt(Customer::getBudget)
                .max();
        assertThat(richestBudget.getAsInt()).isEqualTo(12000);
    }

    /**
     * Check whether all customer are older than 20 or not, by using {@link Stream#allMatch}
     */
    @Easy @Test
    public void isEverybodyOlderThan20() {
        boolean allOlderThan20 = customerList.stream()
                .allMatch(t -> t.getAge() > 20);
        assertThat(allOlderThan20).isTrue();
    }

    /**
     * Create a csv string of customer names in brackets "[]" by using {@link Collectors#joining}
     */
    @Easy @Test
    public void nameInCsv() {
        String string = customerList.stream()
                .map(Customer::getName)
                .collect(joining(",", "[", "]"));

        assertThat(string).isEqualTo("[Joe,Steven,Patrick,Diana,Chris,Kathy,Alice,Andrew,Martin,Amy]");
    }

    /**
     * Get the oldest customer by using {@link Collectors#maxBy}.
     * Don't use any intermediate operations.
     */
    @Easy @Test
    public void oldestCustomer() {
        Optional<Customer> oldestCustomer = customerList.stream()
                .collect(maxBy(Comparator.comparing((Customer::getAge))));
        assertThat(oldestCustomer.get()).isEqualTo(customerList.get(3));

        // find the max budget custmer
        Optional<Customer> richestCustomer = customerList.stream()
                .collect(maxBy(comparing(Customer::getBudget)));
        assertThat(richestCustomer.get()).extracting(Customer::getBudget).isEqualTo(12000);
    }

    /**
     * Create a map of age as key and number of customers as value
     * using {@link Collectors#groupingBy} and {@link Collectors#counting}
     */
    @Easy @Test
    public void ageDistribution() {
        Map<Integer, Long> ageDistribution = customerList.stream()
                .collect(groupingBy(Customer::getAge, counting()));
        assertThat(ageDistribution).hasSize(9);
        ageDistribution.forEach((k, v) -> {
            if (k.equals(22)) {
                assertThat(v).isEqualTo(2L);
            } else {
                assertThat(v).isEqualTo(1L);
            }
        });
    }

    /**
     * Create {@link IntStream} with customer ages by using {@link Stream#mapToInt}
     * Then calculate the average of ages by using {@link IntStream#average}
     */
    @Easy @Test
    public void averageAge() {
        OptionalDouble average = customerList.stream()
                .mapToInt(Customer::getAge)
                .average();
        assertThat(average.getAsDouble()).isEqualTo(28.7);
    }

    /**
     * Create a set of item names that are in {@link Customer.wantToBuy} but not on sale in any shop.
     */
    @Difficult
    @Test
    public void itemsNotOnSale() {
        List<String> shopItemList = shopList.stream()
                .flatMap(s -> s.getItemList().stream())
                .map(Item::getName)
                .collect(toList());
        
        Set<String> itemSetNotOnSale = customerList.stream()
                .flatMap(c -> c.getWantToBuy().stream())
                .map(Item::getName)
                .filter(t -> shopItemList.stream().noneMatch(t::equals))
                .collect(toSet());

        assertThat(itemSetNotOnSale).hasSize(3);
        assertThat(itemSetNotOnSale).containsExactlyInAnyOrder("bag", "pants", "coat");
    }

    /**
     * Create a customer's name list including who are having enough money to buy all items they want which is on sale.
     * Items that are not on sale can be counted as 0 money cost.
     * If there is several same items with different prices, customer can choose the cheapest one.
     */
    @Difficult @Test
    public void havingEnoughMoney() {
        List<Item> onSale = shopList.stream()
                .flatMap(s -> s.getItemList().stream())
                .filter(i -> customerList.stream()
                    .flatMap(c -> c.getWantToBuy().stream())
                    .anyMatch(ci -> ci.getName().equalsIgnoreCase(i.getName())))
                .collect(toList());
        List<String> customerNameList = customerList.stream()
                .filter(c -> c.getBudget() >= c.getWantToBuy().stream()
                        .filter(i -> onSale.contains(i))
                        .collect(toList())
                        .stream()
                        .mapToInt(i -> i.getPrice() == null ? 0 : i.getPrice())
                        .peek(System.out::println)
                        .sum())
                .map(Customer::getName)
                .collect(toList());

        assertThat(customerNameList).hasSize(6);
        assertThat(customerNameList).containsExactlyInAnyOrder("Joe", "Patrick", "Chris", "Kathy", "Andrew", "Amy");
    }
}
