using Lab_5_API.Enums;
using Lab_5_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab_5_API.Repositories
{
    internal abstract class BaseRepository
    {
        protected static class Context
        {
            public static List<Book> Books { get; set; } = new List<Book>
            {
                new Book
                {
                    ID = 1,
                    Name = "The Lord of the Rings",
                    Author = "J. R. R. Tolkien",
                    Genre = Genres.Fantasy
                },
                new Book
                {
                    ID = 2,
                    Name = "A Study in Scarlet",
                    Author = "Sir Arthur Conan Doyle",
                    Genre = Genres.Detective
                },
                new Book
                {
                    ID = 3,
                    Name = "Helsreach",
                    Author = "Aaron Dembski-Bowden",
                    Genre = Genres.ScienceFiction
                },
                new Book
                {
                    ID = 4,
                    Name = "A Scandal in Bohemia",
                    Author = "Sir Arthur Conan Doyle",
                    Genre = Genres.Detective
                },
                new Book
                {
                    ID = 5,
                    Name = "The Five Orange Pips",
                    Author = "Sir Arthur Conan Doyle",
                    Genre = Genres.Detective
                },
            };

            public static List<User> Users { get; set; } = new List<User>
            {
                new User
                {
                    ID = 1,
                    FirstName = "Mykola",
                    LastName = "Pavlenchyk",
                    BirthDate = new DateTime(1998, 12, 14)
                }
            };

            public static List<BookInRent> BooksInRent { get; set; } = new List<BookInRent>
            {
                new BookInRent
                {
                    ID = 1,
                    BookID = 1,
                    UserID = 1
                },
                new BookInRent
                {
                    ID = 2,
                    BookID = 3,
                    UserID = 1
                },
                new BookInRent
                {
                    ID = 3,
                    BookID = 4,
                    UserID = 1
                },
            };
        }
    }
}