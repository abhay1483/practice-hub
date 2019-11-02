package com.example.demo.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;
    
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }
    
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}
	
	private void initData(){
		
		Publisher pub = new Publisher("Harper Collins", "Texas");
		publisherRepository.save(pub);
		
        //JK
        Author eric = new Author("JK", "Rolling");
        Book  ddd = new Book("HarryPotter", "0001", pub);
        
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        


        //Rod
        //Author rod = new Author("Rod", "Johnson");
        //Book noEJB = new Book("J2EE Development without EJB", "23444", "Wrox" );
        //rod.getBooks().add(noEJB);
        //noEJB.getAuthors().add(rod);

        //authorRepository.save(rod);
        //bookRepository.save(noEJB);
    }

}
