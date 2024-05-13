package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Toyota", 2021);
      Car car2 = new Car("BMW", 2019);
      Car car3 = new Car("Mercedes", 2020);
      Car car4 = new Car("Audi", 2018);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(car2);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(car3);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUsersByCarModelAndSeries("BMW", 2019));

      context.close();
   }
}