using OpenQA.Selenium;
using System;

namespace Lab3Selenium.PageObjects
{
    class GmailLayoutPageObject : BasePageObject, IGmailLayoutPageObject
    {
        private string composeButtonSelector = "div[jscontroller] div[role=\"button\"]";
        private string sentFolderButtonSelector = "div[jscontroller] a[aria-label=\"Sent\"]";
        private string importantFolderButtonSelector = "div[jscontroller] a[aria-label=\"Important\"]";
        private string draftFolderButtonSelector = "div[jscontroller] a[title=\"Drafts\"]";
        private string searchInputSelector = "form[role=\"search\"] input";

        public GmailLayoutPageObject() : base() { }
        public GmailLayoutPageObject(BasePageObject setup) : base(setup.waitTimeoutSeconds, setup.pollingIntervalMs, setup.driver) { }

        public void ApplyMessageSearch(string searchText, bool clear = false)
        {
            IWebElement searchInput = FindWhenElementVisible(searchInputSelector);
            if (clear)
            {
                searchInput.Clear();
            }
            searchInput.SendKeys(searchText);
            searchInput.SendKeys(Keys.Enter);
        }

        public void ClickComposeButton()
        {
            IWebElement composeButton = FindWhenElementVisible(composeButtonSelector);
            composeButton.Click();
        }

        public void OpenDraftFolder()
        {
            IWebElement draftFolderButton = FindWhenElementVisible(draftFolderButtonSelector);
            draftFolderButton.Click();
        }

        public void OpenImportantFolder()
        {
            IWebElement importantFolderButton = FindWhenElementVisible(importantFolderButtonSelector);
            importantFolderButton.Click();
        }

        public void OpenSentFolder()
        {
            IWebElement sentFolderButton = FindWhenElementVisible(sentFolderButtonSelector);
            sentFolderButton.Click();
        }
    }
}
