using Lab_5_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab_5_API.Services
{
    public interface ILibraryService
    {
        List<Book> GetAllBooks();

        List<Book> GetBooksByAuthor(string author, int? count);

        Book GetBookById(int bookId);

        int LoadNewBook(Book newBook);

        int RentBook(int userId, int bookId);

        void ReturnBook(int bookId);

        int ReplaceBook(ReplaceBooksModel model);
    }
}
