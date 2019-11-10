using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab4SOAP.DataProviders
{
    public class MultiplyDataProvider
    {
        static object[] NormalTestData =
        {
            new object[] { 1, 5, 5 },
            new object[] { 10, 526, 5260 },
            new object[] { -5, 5, -25 },
            new object[] { -1485, -7789, 11566665 }
        };

        static object[] OverflowTestData =
        {
            new object[] { int.MaxValue, 5 },
            new object[] { int.MinValue, -5 }
        };
    }
}
