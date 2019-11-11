using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Web;

namespace LibraryApiTests.Utils
{
    public class RESTClient
    {
        public class RESTResult<TResponse, TError>
        {
            public TResponse model { get; set; }
            public TError error { get; set; }
        }

        public class RequestError
        {
            public string Message { get; set; }
        }

        protected string toUrlFormData(object formData)
        {
            if (formData == null)
            {
                return "";
            }

            var properties = from p in formData.GetType().GetProperties()
                             where p.GetValue(formData, null) != null
                             select p.Name + "=" + HttpUtility.UrlEncode(p.GetValue(formData, null).ToString());

            return string.Join("&", properties.ToArray());
        }

        public RESTResult<TResponse, TError> Get<TResponse, TError>(string url)
        {
            using (WebClient client = new WebClient())
            {
                var result = new RESTResult<TResponse, TError>();
                try
                {
                    var response = client.DownloadString(url);

                    result.model = JsonConvert.DeserializeObject<TResponse>(response);
                    if (result.model == null)
                    {
                        result.error = JsonConvert.DeserializeObject<TError>(response);
                    }

                }
                catch (WebException ex)
                {
                    var resp = (HttpWebResponse)ex.Response;
                    var errorStr = new StreamReader(resp.GetResponseStream()).ReadToEnd();
                    result.error = JsonConvert.DeserializeObject<TError>(errorStr);
                }
                return result;
            }
        }

        public RESTResult<TResponse, TError> Post<TResponse, TError>(string url, object model = null)
        {
            using (WebClient client = new WebClient())
            {

                var result = new RESTResult<TResponse, TError>();
                try
                {
                    client.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                    var str = toUrlFormData(model);

                    var response = client.UploadString(url, toUrlFormData(model));

                    result.model = JsonConvert.DeserializeObject<TResponse>(response);
                }
                catch (WebException ex)
                {
                    var resp = (HttpWebResponse)ex.Response;
                    var errorStr = new StreamReader(resp.GetResponseStream()).ReadToEnd();
                    result.error = JsonConvert.DeserializeObject<TError>(errorStr);
                }
                return result;
            }
        }
        public RESTResult<TResponse, TError> Put<TResponse, TError>(string url, object model = null)
        {
            using (WebClient client = new WebClient())
            {

                var result = new RESTResult<TResponse, TError>();
                try
                {
                    client.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                    var str = toUrlFormData(model);

                    var response = client.UploadString(url, "PUT", toUrlFormData(model));

                    result.model = JsonConvert.DeserializeObject<TResponse>(response);
                }
                catch (WebException ex)
                {
                    var resp = (HttpWebResponse)ex.Response;
                    var errorStr = new StreamReader(resp.GetResponseStream()).ReadToEnd();
                    result.error = JsonConvert.DeserializeObject<TError>(errorStr);
                }
                return result;
            }
        }
        public RESTResult<TResponse, TError> Delete<TResponse, TError>(string url, object model = null)
        {
            using (WebClient client = new WebClient())
            {

                var result = new RESTResult<TResponse, TError>();
                try
                {
                    client.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                    var str = toUrlFormData(model);

                    var response = client.UploadString(url, "DELETE", toUrlFormData(model));

                    result.model = JsonConvert.DeserializeObject<TResponse>(response);
                }
                catch (WebException ex)
                {
                    var resp = (HttpWebResponse)ex.Response;
                    var errorStr = new StreamReader(resp.GetResponseStream()).ReadToEnd();
                    result.error = JsonConvert.DeserializeObject<TError>(errorStr);
                }
                return result;
            }
        }
    }
}
