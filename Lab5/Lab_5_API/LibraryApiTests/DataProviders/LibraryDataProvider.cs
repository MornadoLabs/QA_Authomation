using Lab_5_API.Enums;
using Lab_5_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LibraryApiTests.DataProviders
{
    class LibraryDataProvider
    {
        static object[] ExistingBooks =
        {
            new object[] { 1, "The Lord of the Rings" },
            new object[] { 2, "A Study in Scarlet" },
            new object[] { 3, "Helsreach" },
        };

        static object[] NonexistentBookIds =
        {
            new object[] { 0 },
            new object[] { -345 },
            new object[] { int.MinValue },
        };

        static object[] NewBooks =
        {
            new object[] { new Book { Author = "Sir Arthur Conan Doyle", Name = "The Man with the Twisted Lip", Genre = Genres.Detective } },
            new object[] { new Book { Author = "J. R. R. Tolkien", Name = "The Hobbit", Genre = Genres.Fantasy } },
        };

        static object[] Authors =
        {
            new object[] { "J. R. R. Tolkien", 1 },
            new object[] { "Sir Arthur Conan Doyle", null },
            new object[] { "Aaron Dembski-Bowden", 5 },
        };

        static object[] BooksInRent =
        {
            new object[] { 1 },
        };

        static object[] ReplaceBooks =
        {
            new object[] { 3, 2, 1 },
            new object[] { 4, 5, 1 },
        };

        static object[] ReplaceBooksWithNonexistentUsers =
        {
            new object[] { 3, 2, 2 },
            new object[] { 4, 5, 3 },
        };
    }
}
