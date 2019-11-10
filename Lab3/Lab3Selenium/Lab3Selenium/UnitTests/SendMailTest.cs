using Lab3Selenium.BusinessObjects;
using Lab3Selenium.PageObjects;
using Lab3Selenium.Utils;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace Lab3Selenium.UnitTests
{
    [TestClass]
    public class SendMailTest
    {
        string email;
        string pass;
        string emailRecipient;

        LoginBusinessObject loginPage;
        ConfigurationService configurationService;

        [TestInitialize]
        public void TestInit()
        {
            loginPage = new LoginBusinessObject();
            configurationService = new ConfigurationService();
            email = configurationService.ReadSetting("EmailLogin");
            pass = configurationService.ReadSetting("EmailPassword");
            emailRecipient = configurationService.ReadSetting("EmailRecipient");

            loginPage.Login(email, pass);
        }
        [TestCleanup]
        public void TestCleanup()
        {
            loginPage.Dispose();
        }

        [TestMethod]
        public void Send_SendEmail_EmailIsSentOnSubmitClick()
        {
            using (MailBusinessObject mailPage = new MailBusinessObject(loginPage.GetBaseSetup()))
            {
                //arrange
                string emailSubject = "Lab 3";
                string emailContent = $"EmailIsSentOnSubmitClick {Guid.NewGuid()}";
                //act
                mailPage.SendMessage(emailRecipient, emailSubject, emailContent);

                bool isMessageSent = mailPage.IsMessageSent(emailRecipient, emailContent);

                //assert
                Assert.IsTrue(isMessageSent);
            }
        }

        [TestMethod]
        public void Send_SendEmail_InvalidAddressWarningDisplayed()
        {
            using (MailBusinessObject mailPage = new MailBusinessObject(loginPage.GetBaseSetup()))
            {
                //arrange
                string emailErrorRecipient = "invalid recipient";
                string emailSubject = "Lab 3";
                string emailContent = $"InvalidAddressWarningDisplayed {Guid.NewGuid()}";
                //act
                mailPage.SendMessage(emailErrorRecipient, emailSubject, emailContent, false);

                bool gotErrorMessage = mailPage.HasError();
                mailPage.CloseErrorModal();

                mailPage.SendMessage(emailRecipient);
                bool isMessageSent = mailPage.IsMessageSent(emailRecipient, emailContent);

                //assert
                Assert.IsTrue(gotErrorMessage && isMessageSent);
            }
        }

        [TestMethod]
        public void Draft_SaveEmailDraft_DraftSavedOnModalColse()
        {
            using (MailBusinessObject mailPage = new MailBusinessObject(loginPage.GetBaseSetup()))
            {
                //arrange
                string emailSubject = "Lab 3";
                string emailContent = $"DraftSavedOnModalColse {Guid.NewGuid()}";
                //act
                mailPage.CreateDraft(emailRecipient, emailSubject, emailContent);

                bool isMessageInDrafts = mailPage.IsMessageInDrafts(emailRecipient, emailContent);

                mailPage.SendOpenMessage();
                //assert
                Assert.IsTrue(isMessageInDrafts);
            }
        }
    }
}
