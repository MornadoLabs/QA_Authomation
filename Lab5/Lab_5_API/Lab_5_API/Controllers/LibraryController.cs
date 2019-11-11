using Lab_5_API.Models;
using Lab_5_API.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Http;

namespace Lab_5_API.Controllers
{
    public class LibraryController : ApiController
    {
        public LibraryController(ILibraryService libraryService)
        {
            this.LibraryService = libraryService;
        }

        protected ILibraryService LibraryService { get; set; }

        [HttpGet]
        public IHttpActionResult GetAllBooks()
        {
            var booksList = LibraryService.GetAllBooks();
            return Ok(booksList);
        }

        [HttpGet]
        public IHttpActionResult GetBook(int bookId)
        {
            try
            {
                var book = LibraryService.GetBookById(bookId);
                return Ok(book);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet]
        public IHttpActionResult GetBooksByAuthor(string author, int? count = null)
        {
            try
            {
                var booksList = LibraryService.GetBooksByAuthor(author, count);
                return Ok(booksList);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut]
        public IHttpActionResult LoadNewBook([FromBody]Book newBook)
        {
            try
            {
                var newBookId = LibraryService.LoadNewBook(newBook);
                return Ok(newBookId);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpDelete]
        public IHttpActionResult ReturnBook(int bookId)
        {
            try
            {
                LibraryService.ReturnBook(bookId);
                return Ok("Book returned successfully!");
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost]
        public IHttpActionResult ReplaceBooks([FromBody]ReplaceBooksModel model)
        {
            try
            {
                var newRentId = LibraryService.ReplaceBook(model);
                return Ok(newRentId);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

    }
}
