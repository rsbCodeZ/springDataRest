package com.example.demo.listener;

import com.example.demo.entity.Library;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class LibraryEventListener {


	@HandleBeforeCreate
	@HandleBeforeSave
	public void maintainBackReferences(Library library) {

		library.getBooks().forEach(book -> book.setLibrary(library));

	}

}
