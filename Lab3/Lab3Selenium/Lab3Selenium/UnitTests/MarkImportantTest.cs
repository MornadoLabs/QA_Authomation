using System;
using System.Collections.Generic;
using Lab3Selenium.BusinessObjects;
using Lab3Selenium.Utils;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Lab3Selenium.UnitTests
{
    [TestClass]
    public class MarkImportantTest
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
        public void MarkImportant_MarkImportantEmails_EmailsDisplayedInImportantFolder()
        {
            using (MailBusinessObject mailPage = new MailBusinessObject(loginPage.GetBaseSetup()))
            {
                //arrange
                int messagesNumber = 3;
                string[] emailSubjects = new string[messagesNumber];
                string[] emailContents = new string[messagesNumber];
                int[] messagesToDelete = new[] { 0, 1, 2 };
                string subjectPart = "Impotance test";
                for (int i = 0; i < messagesNumber; i++)
                {
                    emailSubjects[i] = $"{subjectPart} #{i}";
                    emailContents[i] = $"Important content {i}!";
                    //sent 3 messages we will mark important
                    mailPage.SendMessage(emailRecipient, emailSubjects[i], emailContents[i]);
                }

                //act
                mailPage.SearchMessage($"in:sent {subjectPart}", true);
                mailPage.WaitMessagesLoad();
                for (int i = 0; i < messagesNumber; i++) {
                    mailPage.MarkAsImportant(i);
                }
                var areMessagesImportant = mailPage.AreMessagesImportant(subjectPart, messagesNumber);

                mailPage.DeleteMessages(messagesToDelete);
                var areMessagesDeleted = mailPage.AreMessagesImportant(subjectPart, 0);

                //assert
                Assert.IsTrue(areMessagesImportant && areMessagesDeleted);
            }
        }
    }
}
