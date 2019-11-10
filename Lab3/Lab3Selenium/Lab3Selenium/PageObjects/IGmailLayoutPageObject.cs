using System;

namespace Lab3Selenium.PageObjects
{
    interface IGmailLayoutPageObject : IDisposable
    {
        void ClickComposeButton();
        void OpenSentFolder();
        void OpenDraftFolder();
        void OpenImportantFolder();
        void ApplyMessageSearch(string searchText, bool clear = false);
    }
}
