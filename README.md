# SpringDataRest

This is sample project which has failing test case to demonstrate the issue which occurs during PUT.

Run UpdateEntityTest class :

The test method updateLibraryBook fails as id & book's author name has mismatch.

Scenario:

1.Library having a set of books is created using createLibrary.json
    
2.Read Library 

    {
      "name": "Sycamore Public Library",
      "books": [
        {
          "id": 1,
          "title": "The Philosopher's Stone ",
          "author": "J K Rowlingss",
          "stockCount": 5
        },
        {
          "id": 2,
          "title": "Sense And Sensibility",
          "author": "Jane",
          "stockCount": 10
        }
      ],
      "_links": {
        "self": {
          "href": "http://localhost:8080/libraries/2"
        },
        "library": {
          "href": "http://localhost:8080/libraries/2"
        }
      }
    }
    
3.Update(PUT) the book's author using updateLibrary.json 
    
    The update results in below json where in id is jumbled across the entity
    
        {
            "name": "Sycamore Public Library",
            "books": [
                {
                    "id": 1,
                    "title": "Sense And Sensibility",
                    "author": "Jane Austen",
                    "stockCount": 10
                },
                {
                    "id": 2,
                    "title": "The Philosopher's Stone ",
                    "author": "J K Rowling",
                    "stockCount": 5
                }
            ],
            "_links": {
                "self": {
                    "href": "http://localhost:8080/libraries/1"
                },
                "library": {
                    "href": "http://localhost:8080/libraries/1"
                }
            }
        }
