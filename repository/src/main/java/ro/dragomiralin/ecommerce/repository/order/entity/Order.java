//package ro.dragomiralin.ecommerce.repository.order.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@Builder
//@Entity
//@RequiredArgsConstructor
//@AllArgsConstructor
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;
//
//    private String status;
//
//    private Date orderedDate;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Item> items = new HashSet<Item>();
//}
