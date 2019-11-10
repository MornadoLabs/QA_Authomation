using Lab3Selenium.PageObjects;
using System;

namespace Lab3Selenium.BusinessObjects
{
    class LoginBusinessObject: IDisposable
    {
        private ILoginPageObject loginPageObject;

        public LoginBusinessObject(BasePageObject baseSetup)
        {
            loginPageObject = new LoginPageObject(baseSetup);
        }
        public LoginBusinessObject()
        {
            loginPageObject = new LoginPageObject();
        }

        public BasePageObject GetBaseSetup()
        {
            return loginPageObject as BasePageObject;
        }

        public void Login(string email, string password)
        {
            loginPageObject.OpenLoginPage();
            loginPageObject.FillEmail(email);
            loginPageObject.ClickEmailNextButton();
            loginPageObject.FillPassword(password);
            loginPageObject.ClickPasswordNextButton();
            loginPageObject.WaitForLogin();
        }

        #region IDisposable implementation

        private bool disposed = false;
        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
        // Protected implementation of Dispose pattern.
        protected virtual void Dispose(bool disposing)
        {
            if (disposed)
                return;

            if (disposing)
            {
                // Free any managed objects here.
                loginPageObject.Dispose();

            }

            disposed = true;
        }

        #endregion
    }
}
