using System;

namespace Lab3Selenium.PageObjects
{
    interface IMessagePageObject : IDisposable
    {
        bool MatchRecipient(string repipient);
        string GetMessageBody();
        void BackToMessageList();
    }
}
