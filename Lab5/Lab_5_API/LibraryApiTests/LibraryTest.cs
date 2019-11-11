﻿using Lab_5_API.Models;
using LibraryApiTests.DataProviders;
using LibraryApiTests.Utils;
using NLog;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Web.Http.Results;

namespace LibraryApiTests
{
    [TestFixture]
    public class LibraryTest
    {
        private static readonly Logger Logger = LogManager.GetCurrentClassLogger();
        private LibraryClient Client { get; set; }

        public LibraryTest()
        {
            Client = new LibraryClient();
        }

        [TestCase]
        public void GetAllBooks_CorrectRequest_LoadedListOfAllBooks()
        {
            //arrange
            Logger.Info($"Loading all books.");

            //act
            var actualResult = Client.GetAllBooks();
            var content = actualResult.model;
            Logger.Info($"Result count: {content.Count}");

            //assert
            Assert.IsNotNull(content);
        }

        [TestCaseSource(typeof(LibraryDataProvider), "ExistingBooks")]
        public void GetBook_ExistingBookId_LoadedSelectedBook(int bookId, string bookName)
        {
            //arrange
            Logger.Info($"Loading bookId = {bookId}");

            //act
            var actualResult = Client.GetBook(bookId);
            var content = actualResult.model;
            Logger.Info($"Book name: {content.Name}");

            //assert
            Assert.AreEqual(bookName, content.Name);
        }

        [TestCaseSource(typeof(LibraryDataProvider), "NonexistentBookIds")]
        public void GetBook_NonexistentBookIds_BadRequestResultReturned(int bookId)
        {
            //arrange
            Logger.Info($"Loading bookId = {bookId}");

            //act
            var actualResult = Client.GetBook(bookId);
            var badRequestResult = actualResult.error;
            Logger.Info($"Request result: {badRequestResult.Message}");

            //assert
            Assert.AreEqual($"The book with ID = {bookId} has not been found.", badRequestResult.Message);
        }

        [TestCaseSource(typeof(LibraryDataProvider), "Authors")]
        public void GetAllBooks_CorrectRequest_LoadedListOfAllBooks(string author, int? count)
        {
            //arrange
            Logger.Info($"Loading {count?.ToString() ?? "all"} {author}'s books.");

            //act
            var actualResult = Client.GetBooksByAuthor(author, count);
            var content = actualResult.model;
            Logger.Info($"Result count: {content?.Count}");

            //assert
            Assert.IsTrue(content != null && (!count.HasValue || content.Count <= count));
        }

        [TestCaseSource(typeof(LibraryDataProvider), "NewBooks")]
        public void LoadNewBook_ValidBooks_LoadedNewBookId(Book newBook)
        {
            //arrange
            Logger.Info($"Loading book name = {newBook.Name}");

            //act
            var actualResult = Client.LoadNewBook(newBook);
            var content = actualResult.model;
            Logger.Info($"New book id: {content}");

            //assert
            Assert.IsNotNull(content);
        }

        [TestCaseSource(typeof(LibraryDataProvider), "BooksInRent")]
        public void ReturnBook_ValidBooks_LoadedSuccessMessage(int bookId)
        {
            //arrange
            Logger.Info($"Returning book with ID = {bookId}");

            //act
            var actualResult = Client.ReturnBook(bookId);
            var content = actualResult.model;
            Logger.Info($"Result: {content}");

            //assert
            Assert.AreEqual("Book returned successfully!", content);
        }

        [TestCaseSource(typeof(LibraryDataProvider), "NonexistentBookIds")]
        public void ReturnBook_NonexistentBookIds_BadRequestResultReturned(int bookId)
        {
            //arrange
            Logger.Info($"Returning book with ID = {bookId}");

            //act
            var actualResult = Client.ReturnBook(bookId);
            var content = actualResult.error;
            Logger.Info($"Result: {content.Message}");

            //assert
            Assert.AreEqual($"The book with ID = {bookId} has not been rent.", content.Message);
        }

        [TestCaseSource(typeof(LibraryDataProvider), "ReplaceBooks")]
        public void ReplaceBooks_ValidBooks_LoadedNewRentId(int oldBookId, int newBookId, int userId)
        {
            //arrange
            Logger.Info($"ReplaceBooks book with ID = {oldBookId}, to the book with ID = {newBookId} by {userId}.");

            //act
            var actualResult = Client.ReplaceBooks(oldBookId, newBookId, userId);
            var content = actualResult.model;
            Logger.Info($"New rent ID: {content}");

            //assert
            Assert.IsNotNull(content);
        }

        [TestCaseSource(typeof(LibraryDataProvider), "ReplaceBooksWithNonexistentUsers")]
        public void ReplaceBooks_InvalidUser_BadRequestResultReturned(int oldBookId, int newBookId, int userId)
        {
            //arrange
            Logger.Info($"ReplaceBooks book with ID = {oldBookId}, to the book with ID = {newBookId} by {userId}.");

            //act
            var actualResult = Client.ReplaceBooks(oldBookId, newBookId, userId);
            var content = actualResult.error;
            Logger.Info($"Result: {content.Message}");

            //assert
            Assert.AreEqual($"The user with ID = {userId} doesn't exist.", content.Message);
        }
    }
}