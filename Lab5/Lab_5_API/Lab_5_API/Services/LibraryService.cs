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

        public List<Book> GetBooksByAuthor(string author, int? count)
        {
            var books = LibraryRepository.GetBooksByAuthor(author);

            if (count.HasValue)
            {
                books = books.Take(count.Value).ToList();
            }

            return books;
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

        public int LoadNewBook(Book newBook)
        {
            return LibraryRepository.LoadNewBook(newBook).ID;
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

        public void ReturnBook(int bookId)
        {
            var bookInRent = LibraryRepository.GetBookInRent(bookId);
            if (bookInRent == null)
            {
                throw new Exception($"The book with ID = {bookId} has not been rent.");
            }
            LibraryRepository.ReturnBook(bookInRent);
        }

        public int ReplaceBook(ReplaceBooksModel model)
        {
            var oldBookInRent = LibraryRepository.GetBookInRent(model.OldBookId);
            if (oldBookInRent == null)
            {
                throw new Exception($"The book with ID = {model.OldBookId} has not been rent.");
            }

            var newBookInRent = LibraryRepository.GetBookInRent(model.NewBookId);
            if (newBookInRent != null)
            {
                throw new Exception($"The book with ID = {model.NewBookId} has already been rent.");
            }

            var user = LibraryRepository.GetUser(model.UserId);
            if (user == null)
            {
                throw new Exception($"The user with ID = {model.UserId} doesn't exist.");
            }

            newBookInRent = new BookInRent
            {
                BookID = model.NewBookId,
                UserID = model.UserId
            };

            newBookInRent = LibraryRepository.ReplaceBook(oldBookInRent, newBookInRent);
            return newBookInRent.ID;
        }
    }
}