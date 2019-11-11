using Lab_5_API.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.Http;

namespace LibraryApiTests.Utils
{
    public class LibraryClient : RESTClient
    {
        private string baseUrl;

        public LibraryClient()
        {
            baseUrl = ConfigurationManager.AppSettings["BaseUrl"];
        }

        public RESTResult<List<Book>, RequestError> GetAllBooks()
        {            
            return Get<List<Book>, RequestError>(baseUrl);
        }
        
        public RESTResult<Book, RequestError> GetBook(int bookId)
        {
            return Get<Book, RequestError>($"{baseUrl}/GetBook?bookId={bookId}");
        }

        public RESTResult<List<Book>, RequestError> GetBooksByAuthor(string author, int? count)
        {
            return Get<List<Book>, RequestError>($"{baseUrl}?author={author}&count={count}");
        }

        public RESTResult<int?, RequestError> LoadNewBook(Book newBook)
        {
            return Put<int?, RequestError>($"{baseUrl}/LoadNewBook", newBook);
        }

        public RESTResult<string, RequestError> ReturnBook(int bookId)
        {
            return Delete<string, RequestError>($"{baseUrl}/ReturnBook?bookId={bookId}");
        }

        public RESTResult<int?, RequestError> ReplaceBooks(int oldBookId, int newBookId, int userId)
        {
            return Post<int?, RequestError>($"{baseUrl}/ReplaceBooks", new ReplaceBooksModel
            {
                OldBookId = oldBookId,
                NewBookId = newBookId,
                UserId = userId
            });
        }
    }
}
