using System;
using System.Collections.Specialized;
using System.Configuration;

namespace Lab3Selenium.Utils
{
    class ConfigurationService
    {
        public NameValueCollection ReadAllSettings()  
        {  
            try  
            {
                var appSettings = ConfigurationManager.AppSettings;
                return appSettings;
            }  
            catch (ConfigurationErrorsException)  
            {
                return null;
            }  
        }  
  
        public string ReadSetting(string key)  
        {  
            try  
            {  
                var appSettings = ConfigurationManager.AppSettings;  
                return appSettings[key] ?? null;
            }  
            catch (ConfigurationErrorsException)
            {
                return null;
            }  
        }  
    }
}
