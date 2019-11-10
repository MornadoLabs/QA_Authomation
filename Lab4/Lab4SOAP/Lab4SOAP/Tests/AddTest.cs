using Lab4SOAP.DataProviders;
using Lab4SOAP.Utils;
using NLog;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using System.Web.Services.Protocols;

namespace Lab4SOAP.Tests
{
    [TestFixture]
    public class AddTest
    {
        private static readonly Logger Logger = LogManager.GetCurrentClassLogger();

        [TestCaseSource(typeof(AddDataProvider), "NormalTestData")]
        public void Add_CorrectData_CorrectResult(int intA, int intB, int result)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            int actualResult = client.Add(intA, intB);
            Logger.Info($"Result: {actualResult}");

            //assert
            Assert.AreEqual(result, actualResult);
        }

        [TestCaseSource(typeof(AddDataProvider), "OverflowTestData")]
        public void Add_OwerflowData_ThrowsOverflowException(int intA, int intB)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var ex = Assert.Throws<FaultException>(() => client.Add(intA, intB));

            //assert
            Assert.That(ex.Message.Contains("OverflowException"));
        }

        [TestCaseSource(typeof(AddDataProvider), "NormalTestData")]
        public void AddAsync_CorrectData_CorrectResult(int intA, int intB, int result)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var taskResult = client.AddAsync(intA, intB);
            Task.WaitAny(taskResult);
            var actualResult = taskResult.Result;
            Logger.Info($"Result: {actualResult}");

            //assert
            Assert.AreEqual(result, actualResult);
        }

        [TestCaseSource(typeof(AddDataProvider), "OverflowTestData")]
        public void AddAsync_OwerflowData_ThrowsOverflowException(int intA, int intB)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var ex = Assert.Throws<AggregateException>(() =>
            {
                var taskResult = client.AddAsync(intA, intB);
                Task.WaitAny(taskResult);
                var actualResult = taskResult.Result;
            });

            //assert
            Assert.That(ex.InnerException.Message.Contains("OverflowException"));
        }
    }
}
