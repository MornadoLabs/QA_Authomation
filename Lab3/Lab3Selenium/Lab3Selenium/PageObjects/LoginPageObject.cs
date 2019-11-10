using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace Lab3Selenium.PageObjects
{
    class LoginPageObject : BasePageObject, ILoginPageObject
    {
        private string emailInputSelector = "#identifierId";
        private string emailNextButtonSelector = "#identifierNext";
        private string passwordInputSelector = "input[type=\"password\"][name=\"password\"]";
        private string passwordNextButtonSelector = "#passwordNext";
        private string composeButtonSelector = "div[jscontroller] div[role=\"button\"]";

        public string PageUrl => @"https://www.google.com/gmail";

        public LoginPageObject() : base() { }
        public LoginPageObject(BasePageObject setup) : base(setup.waitTimeoutSeconds, setup.pollingIntervalMs, setup.driver) { }

        public void FillEmail(string email)
        {
            IWebElement emailInput = FindWhenElementVisible(emailInputSelector);
            emailInput.Clear();
            emailInput.SendKeys(email);
        }
        public void FillPassword(string password)
        {
            IWebElement passwordInput = FindWhenElementVisible(passwordInputSelector);
            passwordInput.Clear();
            passwordInput.SendKeys(password);
        }
        public void ClickEmailNextButton()
        {
            IWebElement emailNextButton = FindWhenElementVisible(emailNextButtonSelector);
            emailNextButton.Click();
        }
        public void ClickPasswordNextButton()
        {
            try
            {
                IWebElement passwordNextButton = wait.Until(
                    ExpectedConditions.ElementToBeClickable(By.CssSelector(passwordNextButtonSelector)));
                passwordNextButton.Click();
            } catch
            {
                IWebElement passwordNextButton = wait.Until(
                    ExpectedConditions.ElementToBeClickable(By.CssSelector(passwordNextButtonSelector)));
                passwordNextButton.Click();
            }
        }

        public void OpenLoginPage()
        {
            driver.Navigate().GoToUrl(PageUrl);
        }

        public void WaitForLogin()
        {
            FindWhenElementVisible(composeButtonSelector);
        }
    }
}
