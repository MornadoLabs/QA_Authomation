using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab4SOAP.DataProviders
{
    class AddDataProvider
    {
        static object[] NormalTestData =
        {
            new object[] { 1, 5, 6 },
            new object[] { 10, 526, 536 },
            new object[] { -5, 5, 0 },
            new object[] { -1485, -7789, -9274 }
        };

        static object[] OverflowTestData =
        {
            new object[] { int.MaxValue, 5 },
            new object[] { int.MinValue, -5 }
        };
    }
}
