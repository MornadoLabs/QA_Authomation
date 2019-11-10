using Lab_5_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab_5_API.Repositories
{
    public interface ILibraryRepository
    {
        List<Book> GetAllBooks();

        Book GetBook(int id);

        Book LoadNewBook(Book newBook);

        BookInRent RentBook(BookInRent book);

        void ReturnBook(BookInRent book);

        void ReplaceBook(BookInRent oldBook, BookInRent newBook);

        List<Book> GetBooksByAuthor(string author);

        List<User> GetUsers();

        User GetUser(int userId);

        BookInRent GetBookInRent(int bookId);

        List<BookInRent> GetUsersBooksInRent(int userId);
    }
}
