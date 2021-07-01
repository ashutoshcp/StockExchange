package stackoverflow;

public class Transictions {
    @Entity
    @Table(name = "transiction")
    public class Transictions {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;

        @Column(name = "userId")
        private Long userId;

        @Column(name = "productName")
        private String productName;

        @Column(name = "quantity")
        private int quantity;

        @Column(name = "price")
        private double price;

        @Column(name = "transictionDate")
        private Date transictionDate;
}
