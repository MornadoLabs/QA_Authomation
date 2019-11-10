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
    public class MultiplyTest
    {
        private static readonly Logger Logger = LogManager.GetCurrentClassLogger();

        [TestCaseSource(typeof(MultiplyDataProvider), "NormalTestData")]
        public void Multiply_CorrectData_CorrectResult(int intA, int intB, int result)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            int actualResult = client.Multiply(intA, intB);
            Logger.Info($"Result: {actualResult}");

            //assert
            Assert.AreEqual(result, actualResult);
        }

        [TestCaseSource(typeof(MultiplyDataProvider), "OverflowTestData")]
        public void Multiply_OwerflowData_ThrowsOverflowException(int intA, int intB)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var ex = Assert.Throws<FaultException>(() => client.Multiply(intA, intB));

            //assert
            Assert.That(ex.Message.Contains("OverflowException"));
        }

        [TestCaseSource(typeof(MultiplyDataProvider), "NormalTestData")]
        public void MultiplyAsync_CorrectData_CorrectResult(int intA, int intB, int result)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var taskResult = client.MultiplyAsync(intA, intB);
            Task.WaitAny(taskResult);
            var actualResult = taskResult.Result;
            Logger.Info($"Result: {actualResult}");

            //assert
            Assert.AreEqual(result, actualResult);
        }

        [TestCaseSource(typeof(MultiplyDataProvider), "OverflowTestData")]
        public void MultiplyAsync_OwerflowData_ThrowsOverflowException(int intA, int intB)
        {
            //arrange
            Logger.Info($"intA: {intA}; intB: {intB}");
            var client = SOAPClientFactory.CreateClient();

            //act
            var ex = Assert.Throws<AggregateException>(() =>
            {
                var taskResult = client.MultiplyAsync(intA, intB);
                Task.WaitAny(taskResult);
                var actualResult = taskResult.Result;
            });

            //assert
            Assert.That(ex.InnerException.Message.Contains("OverflowException"));
        }
    }
}
