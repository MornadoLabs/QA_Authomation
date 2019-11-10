using Lab4SOAP.CalculatorService;
using System;
using System.Configuration;
using System.ServiceModel;
using System.ServiceModel.Channels;

namespace Lab4SOAP.Utils
{
    class SOAPClientFactory
    {
        public static CalculatorSoapClient CreateClient()
        {
            string url = ConfigurationManager.AppSettings.Get("ServiceUrl");
            Uri uri = new Uri(url);

            Binding binding;
            switch(uri.Scheme)
            {
                case "http":
                    binding = new BasicHttpBinding();
                    break;
                case "https":
                    binding = new BasicHttpsBinding();
                    break;
                default:
                    return null;
            }

            var address = new EndpointAddress(url);
            return new CalculatorSoapClient(binding, address);

        }
    }
}
