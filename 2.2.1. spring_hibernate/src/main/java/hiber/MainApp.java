package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car1 = new Car("Toyota", 2021);
      Car car2 = new Car("BMW", 2019);
      Car car3 = new Car("Mercedes", 2020);
      Car car4 = new Car("Audi", 2018);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Series = " + user.getCar().getSeries());
         } else {
            System.out.println("User does not have a car.");
         }
         System.out.println();
      }

      String modelToSearch = "Mercedes";
      int seriesToSearch = 2020;

      List<User> foundUsers = userService.getUsersByCarModelAndSeries(modelToSearch, seriesToSearch);

      if (foundUsers.isEmpty()) {
         System.out.println("Не найдено пользователей с указанной моделью автомобиля '" + modelToSearch + "' и серией '" + seriesToSearch + "'.");
      } else {
         System.out.println("Найдены пользователи с указанной моделью автомобиля '" + modelToSearch + "' и серией '" + seriesToSearch + "':");
         for (User user : foundUsers) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
               System.out.println("Car Model = " + user.getCar().getModel());
               System.out.println("Car Series = " + user.getCar().getSeries());
            } else {
               System.out.println("У пользователя нет автомобиля.");
            }
            System.out.println();
         }
      }
      context.close();
   }
}