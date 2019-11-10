using System;

namespace Lab3Selenium.PageObjects
{
    interface IComposeModalPageObject : IDisposable
    {
        void SetRecipientText(string recipient);
        string GetRecipientText();
        void SetSubjectText(string subject);
        void SetContentText(string content);
        string GetContentText();
        void ClickSendButton();
        void CloseModal();
        string GetErrorMessage();
        void CloseErrorModal();
        void WaitMessageSent();
        void ClearRecipient();
    }
}
