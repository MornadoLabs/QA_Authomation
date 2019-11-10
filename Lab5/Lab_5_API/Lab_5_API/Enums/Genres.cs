using System.ComponentModel.DataAnnotations;

namespace Lab_5_API.Enums
{
    public enum Genres
    {
        Fantasy,
        [Display(Name = "Science Fiction")]
        ScienceFiction,
        Western,
        Romance,
        Thriller,
        Mystery,
        Detective,
        Dystopia,
    }
}