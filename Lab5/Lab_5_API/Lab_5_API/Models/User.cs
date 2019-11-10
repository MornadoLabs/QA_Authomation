using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Lab_5_API.Models
{
    public class User
    {
        public int ID { get; set; }

        public string FirstName { get; set; }
        
        public string LastName { get; set; }

        public DateTime BirthDate { get; set; }
    }
}