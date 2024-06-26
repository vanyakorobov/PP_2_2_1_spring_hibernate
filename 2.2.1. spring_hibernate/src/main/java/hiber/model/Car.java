package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series")
    private int series;

    @Column(name = "model")
    private String model;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Car() {}

    public Car(String model, int series) {
        this.series = series;
        this.model = model;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }


    public void setSeries(int series) {
        this.series = series;
    }

    public int getSeries() {
        return series;
    }

    public User getUser() {
        return user;
    }
    public User setUser(User user) {
        this.user = user;
        return user;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", series=" + series +
                ", model='" + model + '\'' +
                '}';
    }
}
