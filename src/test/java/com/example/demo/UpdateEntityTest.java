package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.example.demo.entity.Book;
import com.example.demo.entity.Library;
import com.example.demo.listener.LibraryEventListener;
import com.example.demo.repository.LibraryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UpdateEntityTest {

	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private LibraryRepository libraryRepository;

	@Autowired
	private LibraryEventListener eventListener;

	@Autowired
	private MockMvc mockMvc;

	private Library library;

	@Before
	public void setUp() throws IOException {

		library = objectMapper.readValue(new ClassPathResource("createLibrary.json").getFile(), Library.class);
		eventListener.maintainBackReferences(library);
		libraryRepository.save(library);
	}

	@Test
	public void updateLibraryWithNewBook() throws Exception {

		Library originalLibrary = libraryRepository.findOne(library.getId());
		Set<Book> originalBooks = originalLibrary.getBooks();

		List<Book> originalBookList = new ArrayList<>();
		originalBookList.addAll(originalLibrary.getBooks());

		Library updateJson = objectMapper.readValue(new ClassPathResource("updateLibrary.json").getFile(), Library.class);

		mockMvc.perform(
				put("/libraries/{id}", library.getId())
						.content(objectMapper.writeValueAsBytes(updateJson)))
				.andReturn();


		Library updatedLibrary = libraryRepository.findOne(library.getId());
		Set<Book> updatedBooks = updatedLibrary.getBooks();
		List<Book> updatedBookList = new ArrayList<>();
		updatedBookList.addAll(updatedLibrary.getBooks());

		Book updatedBookOne = updatedBooks.stream().filter(book -> book.getId().equals(1)).findFirst().get();
		Book originalBookOne = originalBooks.stream().filter(book -> book.getId().equals(1)).findFirst().get();

		assertEquals(originalBookOne.getTitle(), updatedBookOne.getTitle());

	}
}
