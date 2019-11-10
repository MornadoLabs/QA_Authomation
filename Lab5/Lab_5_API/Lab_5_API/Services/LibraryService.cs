using Lab_5_API.Models;
using Lab_5_API.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab_5_API.Services
{
    internal class LibraryService : ILibraryService
    {
        public LibraryService(ILibraryRepository libraryRepository)
        {
            this.LibraryRepository = libraryRepository;
        }

        protected ILibraryRepository LibraryRepository { get; set; }

        public List<Book> GetAllBooks()
        {
            return LibraryRepository.GetAllBooks();
        }

        public Book GetBookById(int bookId)
        {
            var book = LibraryRepository.GetBook(bookId);
            if (book == null)
            {
                throw new Exception($"The book with ID = {bookId} has not been found.");
            }

            return book;
        }

        public int RentBook(int userId, int bookId)
        {
            var book = LibraryRepository.GetBook(bookId);
            if (book == null)
            {
                throw new Exception($"The book with ID = {bookId} has not been found.");
            }

            var user = LibraryRepository.GetUser(userId);
            if (user == null)
            {
                throw new Exception($"The user with ID = {userId} has not been found.");
            }

            var isBookInRent = LibraryRepository.GetBookInRent(bookId) != null;
            if (isBookInRent)
            {
                throw new Exception($"The book with ID = {bookId} has already been rent.");
            }
            
            var newRentBook = new BookInRent
            {
                BookID = bookId,
                UserID = userId
            };

            newRentBook = LibraryRepository.RentBook(newRentBook);
            return newRentBook.ID;
        }
    }
}