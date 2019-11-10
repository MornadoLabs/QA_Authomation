using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using System.Threading;

namespace Lab3Selenium.PageObjects
{
    class MessageListPageObject : BasePageObject, IMessageListPageObject
    {
        private string selectMessageSelector = "div[role=\"main\"] div[role=\"checkbox\"]";
        private string importantMarksSelector = "div[role=\"main\"] tr div[aria-label*=\"important\"]";
        private string messageSubjectsSelector = "div[role=\"main\"] span.bog";
        private string deleteMessagesSelector = "div[aria-label=\"Delete\"][role=\"button\"],div[title=\"Delete\"][role=\"button\"]";

        public MessageListPageObject() : base() { }
        public MessageListPageObject(BasePageObject setup) : base(setup.waitTimeoutSeconds, setup.pollingIntervalMs, setup.driver) { }

        public void ClickImportantMark(int messageIdx)
        {
            FindWhenElementVisible(importantMarksSelector);
            var importantMarks = driver.FindElements(By.CssSelector(importantMarksSelector));
            importantMarks[messageIdx].Click();
        }

        public void DeleteSelectedMessages()
        {
            var deleteMessages = driver.FindElements(By.CssSelector(deleteMessagesSelector));
            for (int i = deleteMessages.Count; i >= 0; i--)
            {
                bool hasException = false;
                try
                {
                    deleteMessages[deleteMessages.Count - 1].Click();
                }
                catch
                {
                    hasException = true;
                }
                if (!hasException && i > 0)
                {
                    return;
                }
            }
        }

        public void OpenLatestMessage()
        {
            IWebElement messageSubjects = FindWhenElementVisible(messageSubjectsSelector);
            messageSubjects.Click();
        }

        public int GetNumberOfMessagesInList()
        {
            try
            {
                FindWhenElementVisible(messageSubjectsSelector);
                var messageSubjects = driver.FindElements(By.CssSelector(messageSubjectsSelector));
                return messageSubjects.Count;
            }catch(NoSuchElementException)
            {
                return 0;
            }
            catch (WebDriverTimeoutException)
            {
                return 0;
            }
        }

        public void SelectMessage(int messageIdx)
        {
            FindWhenElementVisible(selectMessageSelector);
            var messageSubjects = driver.FindElements(By.CssSelector(selectMessageSelector));
            try
            {
                messageSubjects[messageIdx].Click();
            }catch
            {
                wait.Until(ExpectedConditions.ElementToBeClickable(By.CssSelector(selectMessageSelector)));
                messageSubjects = driver.FindElements(By.CssSelector(selectMessageSelector));
                messageSubjects[messageIdx].Click();
            }
        }

        public void WaitMessagesLoad()
        {
            var oldUrl = driver.Url;
            oldUrl = oldUrl.Replace("/", "\\/").Replace(".", "\\.").Replace("+", "\\+");
            //min wait 1 sec;
            Thread.Sleep(1000);
            try {
                wait.Until(ExpectedConditions.UrlMatches($"^((?!{oldUrl}).)*$"));
            } catch
            {
                //same filters might be applied
            }
        }
    }
}
