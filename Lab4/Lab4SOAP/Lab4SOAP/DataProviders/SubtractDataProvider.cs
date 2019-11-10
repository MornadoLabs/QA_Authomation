using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab4SOAP.DataProviders
{
    public class SubtractDataProvider
    {
        static object[] NormalTestData =
        {
            new object[] { 1, 5, -4 },
            new object[] { 10, 526, -516 },
            new object[] { -5, 5, -10 },
            new object[] { -1485, -7789, 6304 }
        };

        static object[] OverflowTestData =
        {
            new object[] { int.MaxValue, -5 },
            new object[] { int.MinValue, 5 }
        };
    }
}
