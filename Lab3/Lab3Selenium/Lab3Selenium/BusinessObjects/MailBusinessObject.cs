using Lab3Selenium.PageObjects;
using System;

namespace Lab3Selenium.BusinessObjects
{
    class MailBusinessObject: IDisposable
    {
        private IGmailLayoutPageObject layoutPageObject;
        private IComposeModalPageObject composeModalPageObject;
        private IMessageListPageObject messageListPageObject;
        private IMessagePageObject messagePageObject;

        public MailBusinessObject(BasePageObject baseSetup)
        {
            layoutPageObject = new GmailLayoutPageObject(baseSetup);
            composeModalPageObject = new ComposeModalPageObject(baseSetup);
            messageListPageObject = new MessageListPageObject(baseSetup);
            messagePageObject = new MessagePageObject(baseSetup);
        }
        public MailBusinessObject()
        {
            layoutPageObject = new GmailLayoutPageObject();
            composeModalPageObject = new ComposeModalPageObject();
            messageListPageObject = new MessageListPageObject();
            messagePageObject = new MessagePageObject();
        }

        public void SendMessage(string recipient, bool waitSent = true)
        {
            composeModalPageObject.ClearRecipient();
            composeModalPageObject.SetRecipientText(recipient);
            composeModalPageObject.ClickSendButton();
            if (waitSent)
            {
                WaitMessageSent();
            }
        }
        public void SendMessage(string recipient, string subject, string content = null, bool waitSent = true)
        {
            if (recipient != null && subject != null && content != null)
            {
                layoutPageObject.ClickComposeButton();
            }
            if (recipient != null)
            {
                composeModalPageObject.SetRecipientText(recipient);
            }
            if (subject != null)
            {
                composeModalPageObject.SetSubjectText(subject);
            }
            if (content != null)
            {
                composeModalPageObject.SetContentText(content);
            }
            composeModalPageObject.ClickSendButton();
            if (waitSent)
            {
                WaitMessageSent();
            }
        }

        public void CreateDraft(string recipient, string subject = null, string content = null)
        {
            if (recipient != null && subject != null && content != null)
            {
                layoutPageObject.ClickComposeButton();
            }
            if (recipient != null)
            {
                composeModalPageObject.SetRecipientText(recipient);
            }
            if (subject != null)
            {
                composeModalPageObject.SetSubjectText(subject);
            }
            if (content != null)
            {
                composeModalPageObject.SetContentText(content);
            }
            composeModalPageObject.CloseModal();
        }

        public bool HasError()
        {
            return composeModalPageObject.GetErrorMessage() != null;
        }

        public void CloseErrorModal()
        {
            composeModalPageObject.CloseErrorModal();
        }

        public bool IsMessageSent(string recipient, string content)
        {
            layoutPageObject.OpenSentFolder();
            messageListPageObject.WaitMessagesLoad();
            messageListPageObject.OpenLatestMessage();
            bool matchesRecipient = messagePageObject.MatchRecipient(recipient);
            bool containsMessageBody = messagePageObject.GetMessageBody().Contains(content);

            return matchesRecipient && containsMessageBody;
        }

        public bool IsMessageInDrafts(string recipient, string content)
        {
            layoutPageObject.OpenDraftFolder();
            messageListPageObject.WaitMessagesLoad();
            messageListPageObject.OpenLatestMessage();
            bool containsMessageBody = composeModalPageObject.GetContentText().Contains(content);

            return containsMessageBody;
        }
        public void SendOpenMessage()
        {
            composeModalPageObject.ClickSendButton();
            WaitMessageSent();
        }

        public void SearchMessage(string search, bool clear)
        {
            layoutPageObject.ApplyMessageSearch(search, clear);
        }

        public void WaitMessageSent()
        {
            composeModalPageObject.WaitMessageSent();
        }
        public void OpenSentFolder()
        {
            layoutPageObject.OpenSentFolder();
        }
        public void OpenImportantFolder()
        {
            layoutPageObject.OpenImportantFolder();
        }
        public void MarkAsImportant(int idx)
        {
            messageListPageObject.ClickImportantMark(idx);
        }
        public void SelectMessage(int idx)
        {
            messageListPageObject.SelectMessage(idx);
        }
        public void DeleteSelectedMessages()
        {
            messageListPageObject.DeleteSelectedMessages();
            try
            {
                composeModalPageObject.WaitMessageSent();
            }
            catch { }
        }
        public bool AreMessagesImportant(string subject, int expectedNumberOfMessages)
        {
            layoutPageObject.ApplyMessageSearch($"is:important {subject}", true);
            messageListPageObject.WaitMessagesLoad();
            var actualNumberOfMessages = messageListPageObject.GetNumberOfMessagesInList();
            return actualNumberOfMessages == expectedNumberOfMessages;
        }

        public void DeleteMessages(int[] idxs)
        {
            for (int i = 0; i < idxs.Length; i++) {
                messageListPageObject.SelectMessage(idxs[i]);
            }
            DeleteSelectedMessages();
        }
        public void WaitMessagesLoad()
        {
            messageListPageObject.WaitMessagesLoad();
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
                layoutPageObject.Dispose();
                composeModalPageObject.Dispose();
                messageListPageObject.Dispose();
                messagePageObject.Dispose();

            }

            disposed = true;
        }

        #endregion
    }
}
