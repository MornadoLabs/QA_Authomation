using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab_5_API.Models
{
    public class BookInRent
    {
        public int ID { get; set; }

        public int BookID { get; set; }

        public int UserID { get; set; }
    }
}