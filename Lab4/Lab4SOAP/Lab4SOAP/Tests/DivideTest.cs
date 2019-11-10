using System;
using NUnit.Framework;
using Lab4SOAP.DataProviders;
using Lab4SOAP.Utils;
using System.ServiceModel;
using System.Threading.Tasks;
using NLog;

namespace Lab4SOAP.Tests
{
    [TestFixture]
    public class DivideTest
    {
        private static readonly Logger Logger = LogManager.GetCurrentClassLogger();

        [TestCaseSource(typeof(DivideDataProvider), "NormalTestData")]
        public void Divide_CorrectData_CorrectResult(int intA, int intB, int result)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            int actualResult = client.Divide(intA, intB);
            Logger.Info($"Result: {actualResult}");

            //assert
            Assert.AreEqual(result, actualResult);
        }

        [TestCaseSource(typeof(DivideDataProvider), "DivideByZeroTestData")]
        public void Divide_DivideByZeroData_ThrowsDivideByZeroException(int intA, int intB)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();
            
            //act
            var ex = Assert.Throws<FaultException>(() => client.Divide(intA, intB));

            //assert
            Assert.That(ex.Message.Contains("OverflowException"));
        }

        [TestCaseSource(typeof(DivideDataProvider), "NormalTestData")]
        public void DivideAsync_CorrectData_CorrectResult(int intA, int intB, int result)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var taskResult = client.DivideAsync(intA, intB);
            Task.WaitAny(taskResult);
            var actualResult = taskResult.Result;
            Logger.Info($"Result: {actualResult}");

            //assert
            Assert.AreEqual(result, actualResult);
        }

        [TestCaseSource(typeof(DivideDataProvider), "DivideByZeroTestData")]
        public void DivideAsync_DivideByZeroData_ThrowsDivideByZeroException(int intA, int intB)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var ex = Assert.Throws<AggregateException>(() =>
            {
                var taskResult = client.DivideAsync(intA, intB);
                Task.WaitAny(taskResult);
                var actualResult = taskResult.Result;
            });

            //assert
            Assert.That(ex.InnerException.Message.Contains("OverflowException"));
        }
    }
}
