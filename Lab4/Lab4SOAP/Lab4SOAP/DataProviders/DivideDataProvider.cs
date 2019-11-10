using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab4SOAP.DataProviders
{
    public class DivideDataProvider
    {
        static object[] NormalTestData =
        {
            new object[] { 5, 1, 5 },
            new object[] { 520, 10, 52 },
            new object[] { -5, 5, -1 },
            new object[] { 11566665, 7789, 1485 }
        };

        static object[] DivideByZeroTestData =
        {
            new object[] { 0, 0 },
            new object[] { 1, 0 },
            new object[] { int.MaxValue, 0 }
        };
    }
}
