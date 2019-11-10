using Lab_5_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab_5_API.Repositories
{
    internal class LibraryRepository : BaseRepository, ILibraryRepository
    {
        public List<Book> GetAllBooks()
        {
            return Context.Books;
        }

        public Book GetBook(int id)
        {
            return Context.Books.FirstOrDefault(b => b.ID == id);
        }

        public List<Book> GetBooksByAuthor(string author)
        {
            return Context.Books.Where(b => b.Author.Equals(author)).ToList();
        }

        public Book LoadNewBook(Book newBook)
        {
            newBook.ID = Context.Books.Max(b => b.ID) + 1;
            Context.Books.Add(newBook);
            return newBook;
        }

        public BookInRent RentBook(BookInRent book)
        {
            book.ID = Context.BooksInRent.Max(b => b.ID) + 1;            
            Context.BooksInRent.Add(book);
            return book;
        }

        public void ReturnBook(BookInRent book)
        {
            Context.BooksInRent.Remove(book);
        }

        public void ReplaceBook(BookInRent oldBook, BookInRent newBook)
        {
            Context.BooksInRent.Remove(oldBook);
            Context.BooksInRent.Add(newBook);
        }

        public List<User> GetUsers()
        {
            return Context.Users;
        }

        public User GetUser(int userId)
        {
            return Context.Users.FirstOrDefault(u => u.ID == userId);
        }

        public BookInRent GetBookInRent(int bookId)
        {
            return Context.BooksInRent.FirstOrDefault(b => b.BookID == bookId);
        }

        public List<BookInRent> GetUsersBooksInRent(int userId)
        {
            return Context.BooksInRent.Where(b => b.UserID == userId).ToList();
        }
    }
}