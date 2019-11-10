using System;

namespace Lab3Selenium.PageObjects
{
    interface IMessageListPageObject : IDisposable
    {
        void OpenLatestMessage();
        void SelectMessage(int messageIdx);
        void ClickImportantMark(int messageIdx);
        void DeleteSelectedMessages();
        int GetNumberOfMessagesInList();
        void WaitMessagesLoad();
    }
}
