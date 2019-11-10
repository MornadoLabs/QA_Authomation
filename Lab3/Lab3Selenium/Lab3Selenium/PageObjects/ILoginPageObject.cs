using System;

namespace Lab3Selenium.PageObjects
{
    interface ILoginPageObject : IDisposable
    {
        string PageUrl { get; }
        void OpenLoginPage();
        void FillEmail(string email);
        void FillPassword(string password);
        void ClickEmailNextButton();
        void ClickPasswordNextButton();
        void WaitForLogin();
    }
}
