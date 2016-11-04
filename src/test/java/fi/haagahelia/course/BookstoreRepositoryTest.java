package fi.haagahelia.course;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookstoreRepository;
import fi.haagahelia.course.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {
	
	 @Autowired
	 private BookstoreRepository repository;
	 
	 @Test
	 public void findByIsbnShouldReturnBook() {
		 List<Book> books = repository.findByIsbn("isbn2");
		 
		 assertThat(books).hasSize(1);
		 assertThat(books.get(0).getTitle()).isEqualTo("Title2"); 
	 }
	 
	 @Test
	 public void createNewBook () {
		 Book book = new Book("Half of a Yellow Sun","Chimamanda Ngozi Adichie", 2007,"isbnthat", 30, new Category("IT"));
		 repository.save(book);
		 assertThat(book.getId()).isNotNull();
	 }
	 
	 
	 @Test
	 public void deleteBook() {
		
		Book book = repository.findByIsbn("isbn1").get(0);
		 assertThat(book.getId()).isNotNull();
		 repository.delete(book);
		 
	 }
	 
}
