using Lab3Selenium.Utils;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.UI;
using System;

namespace Lab3Selenium.PageObjects
{
    abstract class BasePageObject : IDisposable
    {
        public double waitTimeoutSeconds { get; private set; }
        public double pollingIntervalMs { get; private set; }

        public IWebDriver driver { get; protected set; }

        protected DefaultWait<IWebDriver> wait { get {
                var w = new DefaultWait<IWebDriver>(driver);
                w.Timeout = TimeSpan.FromSeconds(waitTimeoutSeconds);
                w.PollingInterval = TimeSpan.FromMilliseconds(pollingIntervalMs);
                w.IgnoreExceptionTypes(typeof(NoSuchElementException));
                return w;
            } }

        public BasePageObject(double waitTimeoutSeconds = 10, double pollingIntervalMs = 500, IWebDriver driver = null)
        {
            this.waitTimeoutSeconds = waitTimeoutSeconds;
            this.pollingIntervalMs = pollingIntervalMs;

            this.driver = driver == null ? new FirefoxDriver() : driver;
        }
        protected IWebElement FindWhenElementVisible(string elementSelector)
        {
            return wait.Until(ExpectedConditions.ElementIsVisible(By.CssSelector(elementSelector)));
        }

        #region IDisposable implementation

        private bool disposed = false;
        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
        // Protected implementation of Dispose pattern.
        protected virtual void Dispose(bool disposing)
        {
            if (disposed)
                return;

            if (disposing)
            {
                // Free any managed objects here.
                try
                {
                    driver.Manage().Cookies.DeleteAllCookies();
                }
                catch { }
                driver.Quit();

            }

            disposed = true;
        }

        #endregion
    }
}
