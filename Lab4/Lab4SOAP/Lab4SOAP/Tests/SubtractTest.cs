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
    public class SubtractTest
    {
        private static readonly Logger Logger = LogManager.GetCurrentClassLogger();

        [TestCaseSource(typeof(SubtractDataProvider), "NormalTestData")]
        public void Subtract_CorrectData_CorrectResult(int intA, int intB, int result)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            int actualResult = client.Subtract(intA, intB);
            Logger.Info($"Result: {actualResult}");

            //assert
            Assert.AreEqual(result, actualResult);
        }

        [TestCaseSource(typeof(SubtractDataProvider), "OverflowTestData")]
        public void Subtract_OwerflowData_ThrowsOverflowException(int intA, int intB)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var ex = Assert.Throws<FaultException>(() => client.Subtract(intA, intB));

            //assert
            Assert.That(ex.Message.Contains("OverflowException"));
        }

        [TestCaseSource(typeof(SubtractDataProvider), "NormalTestData")]
        public void SubtractAsync_CorrectData_CorrectResult(int intA, int intB, int result)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var taskResult = client.SubtractAsync(intA, intB);
            Task.WaitAny(taskResult);
            var actualResult = taskResult.Result;
            Logger.Info($"Result: {actualResult}");

            //assert
            Assert.AreEqual(result, actualResult);
        }

        [TestCaseSource(typeof(SubtractDataProvider), "OverflowTestData")]
        public void SubtractAsync_OwerflowData_ThrowsOverflowException(int intA, int intB)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var ex = Assert.Throws<AggregateException>(() =>
            {
                var taskResult = client.SubtractAsync(intA, intB);
                Task.WaitAny(taskResult);
                var actualResult = taskResult.Result;
            });

            //assert
            Assert.That(ex.InnerException.Message.Contains("OverflowException"));
        }
    }
}
