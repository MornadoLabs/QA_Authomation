using OpenQA.Selenium;
using System;
using OpenQA.Selenium.Support.UI;
using System.Threading;

namespace Lab3Selenium.PageObjects
{
    class ComposeModalPageObject : BasePageObject, IComposeModalPageObject
    {
        private string closeModalSelector = "div[role=\"dialog\"] img[alt=\"Close\"]";
        private string sendButtonSelector = "div[data-tooltip^=\"Send\"]";
        private string recipientFieldSelector = "textarea[name=\"to\"]";
        private string recipientHolderSelector = "form[accept-charset=\"utf-8\"] > div:nth-child(2)";
        private string recipientRemoveButtonSelector = "form[accept-charset=\"utf-8\"] span[data-hovercard-id] > div:last-child";
        private string subjectFieldSelector = "input[name=\"subjectbox\"]";
        private string contentFieldSelector = "div[aria-label=\"Message Body\"][role=\"textbox\"]";
        private string errorMessageSelector = "div[role=\"alertdialog\"] > div:nth-child(2)";
        private string errorModalCloseButtonSelector = "div[role=\"alertdialog\"] button[name=\"ok\"]";
        private string messageSentPopupSelector = "div[aria-live=\"assertive\"][aria-atomic=\"true\"][role=\"alert\"] > div > div:nth-child(2) > span";
        private string messageSentPopupCloseSelector = "div[aria-live=\"assertive\"][aria-atomic=\"true\"][role=\"alert\"] > div > div:nth-child(2) > div[role=\"button\"]";

        public ComposeModalPageObject(): base() { }
        public ComposeModalPageObject(BasePageObject setup) : base(setup.waitTimeoutSeconds, setup.pollingIntervalMs, setup.driver) { }

        public void ClickSendButton()
        {
            IWebElement sendButton = FindWhenElementVisible(sendButtonSelector);
            sendButton.Click();
        }

        public void CloseErrorModal()
        {
            try
            {
                IWebElement errorModalCloseButton = FindWhenElementVisible(errorModalCloseButtonSelector);
                errorModalCloseButton.Click();
            }catch (NoSuchElementException)
            {
            }
        }

        public string GetErrorMessage()
        {
            try
            {
                IWebElement errorMessage = FindWhenElementVisible(errorMessageSelector);
                return errorMessage.Text;
            } catch(NoSuchElementException)
            {
                return null;
            }
        }

        public void CloseModal()
        {
            IWebElement closeModal = FindWhenElementVisible(closeModalSelector);
            closeModal.Click();
        }

        public string GetContentText()
        {
            IWebElement contentField = FindWhenElementVisible(contentFieldSelector);
            return contentField.Text;
        }

        public string GetRecipientText()
        {
            IWebElement recipientField = FindWhenElementVisible(recipientFieldSelector);
            return recipientField.Text;
        }

        public void SetContentText(string content)
        {
            IWebElement contentField = FindWhenElementVisible(contentFieldSelector);
            contentField.SendKeys(content);
        }

        public void SetRecipientText(string recipient)
        {
            IWebElement recipientField = FindWhenElementVisible(recipientFieldSelector);
            recipientField.SendKeys(recipient);
        }

        public void SetSubjectText(string subject)
        {
            IWebElement subjectField = FindWhenElementVisible(subjectFieldSelector);
            subjectField.SendKeys(subject);
        }

        public void WaitMessageSent()
        {
            FindWhenElementVisible(messageSentPopupSelector);
            var close = wait.Until(ExpectedConditions.ElementToBeClickable(By.CssSelector(messageSentPopupCloseSelector)));
            close.Click();
        }
        public void ClearRecipient()
        {
            var input = FindWhenElementVisible(recipientHolderSelector);
            input.Click();
            var text = FindWhenElementVisible(recipientFieldSelector);
            text.SendKeys(Keys.Backspace);

        }
    }
}
