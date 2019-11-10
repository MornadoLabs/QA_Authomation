using Lab_5_API.Enums;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab_5_API.Models
{
    public class Book
    {
        public int ID { get; set; }

        public string Name { get; set; }

        public string Author { get; set; }

        public Genres Genre { get; set; }
    }
}