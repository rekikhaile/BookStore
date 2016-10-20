package fi.haagahelia.course.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookstoreRepository;
import fi.haagahelia.course.domain.CategoryRepository;


@Controller
public class BookController {
	@Autowired
	private BookstoreRepository repository;
	
	@Autowired
	private CategoryRepository crepository; 
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String bookStore(Model model)
	{
		 model.addAttribute("books", repository.findAll());
         return "booklist";
	}
	// Show all
    @RequestMapping(value="/booklist")
    public String studentList(Model model)
    {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    // Add new
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());

        return "addbook";
    }    
 // Save new
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }    
 // Delete
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.delete(bookId);
        return "redirect:../booklist";
       
    }     
}




