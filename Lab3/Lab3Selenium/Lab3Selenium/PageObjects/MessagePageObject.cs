using OpenQA.Selenium;

namespace Lab3Selenium.PageObjects
{
    class MessagePageObject : BasePageObject, IMessagePageObject
    {
        private string backButtonSelector = "div[role=\"button\"][aria-label^=\"Back to\"]";
        private string messageBodySelector = "div[role=\"list\"] div[dir=\"ltr\"]";
        private string getRecipientFieldSelector(string email) => $"div[role=\"list\"] [email=\"{email}\"]";

        public MessagePageObject() : base() { }
        public MessagePageObject(BasePageObject setup) : base(setup.waitTimeoutSeconds, setup.pollingIntervalMs, setup.driver) { }

        public void BackToMessageList()
        {
            IWebElement backButton = FindWhenElementVisible(backButtonSelector);
            backButton.Click();
        }

        public string GetMessageBody()
        {
            try
            {
                IWebElement messageBody = FindWhenElementVisible(messageBodySelector);
                return messageBody.Text;
            }
            catch
            {
                IWebElement messageBody = FindWhenElementVisible(messageBodySelector);
                return messageBody.Text;
            }
        }

        public bool MatchRecipient(string repipient)
        {
            try
            {
                IWebElement recipientField = FindWhenElementVisible(getRecipientFieldSelector(repipient));
                return true;
            } catch(NoSuchElementException)
            {
                return false;
            }
        }
    }
}
