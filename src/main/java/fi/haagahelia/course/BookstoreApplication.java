package fi.haagahelia.course;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.BookstoreRepository;
import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.CategoryRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
@Bean
public CommandLineRunner bookDemo(BookstoreRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			
			crepository.save(new Category("IT"));
			crepository.save(new Category("Business"));
			crepository.save(new Category("Economics"));
			crepository.save(new Category("Mathematics"));
			
			
			repository.save(new Book("Title1", "Author1", 1996, "isbn1", 55, crepository.findByName("IT").get(0)));
			repository.save(new Book("Title2", "Author2", 1999, "isbn2", 65, crepository.findByName("Business").get(0)));	
			
			// Create users: admin/password2 user/password1
				User user1 = new User("user", "$2a$10$X9yO8y..u/Sp5biLLxNZuuBK4.hFWdgPWrlX51ItINEzn/82fPJ2a", "email1", "USER");//password1 bcrypt
				User user2 = new User("admin", "$2a$10$4KkPnyj.dMMbAIocGkXGcepFnJ61lDefQ7FB8Cwau9gWnRrrKKDei", "email2", "ADMIN"); //password2 bcrypt
				urepository.save(user1);
				urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}