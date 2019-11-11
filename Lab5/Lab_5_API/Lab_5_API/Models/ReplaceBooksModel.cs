using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab_5_API.Models
{
    public class ReplaceBooksModel
    {
        public int OldBookId { get; set; }
        public int NewBookId { get; set; }
        public int UserId { get; set; }
    }
}